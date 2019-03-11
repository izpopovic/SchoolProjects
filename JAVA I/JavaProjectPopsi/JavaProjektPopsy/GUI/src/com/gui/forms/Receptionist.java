package com.gui.forms;

import com.dal.entities.Doctor;
import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Receptionist extends MyForm {
    private JPanel contentPane;
    private JButton btnInsertPatientBasic;
    private JTable tblPatients;
    private JButton btnInsertPatientFull;
    private JComboBox ddlDoctors;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemMakeAnAppointment;
    private JMenuItem menuItemIssueBill;
//    private Patient patient;
    private Patient selectedPatient;
    private java.util.List<Patient> patients;
    private java.util.List<Doctor> doctors;

    public Receptionist() throws HeadlessException {
        super("Receptionist", 1200, 500);

        patients = RepoFactory.getRepo().getPatients();
        doctors = RepoFactory.getRepo().getDoctors();

        setContentPane(contentPane);

        popupMenu = new JPopupMenu();
        menuItemMakeAnAppointment = new JMenuItem("Order appointment for a patient");
        menuItemIssueBill = new JMenuItem("Issue a bill to a patient");

        menuItemMakeAnAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Doctor selectedDoctor = doctors.get(ddlDoctors.getSelectedIndex());
                OrderTest orderTest = new OrderTest(selectedPatient,selectedDoctor);
                orderTest.setVisible(true);
//                MakeAnAppointment makeAnAppointment = new MakeAnAppointment();
//                makeAnAppointment.setVisible(true);
            }
        });

        menuItemIssueBill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IssueNSeeBill issueBill = new IssueNSeeBill(selectedPatient);
                issueBill.setVisible(true);
            }
        });

        popupMenu.add(menuItemMakeAnAppointment);
        popupMenu.add(menuItemIssueBill);
        tblPatients.setComponentPopupMenu(popupMenu);

        //napuni ddl doktorima
        ddlDoctors.setModel(new DefaultComboBoxModel(doctors.toArray()));
        fillTable();
//        ddlDoctors.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                fillTable();
//            }
//
//        });


        tblPatients.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            //ovo radi samo na lijevi klik, moram sfixat i staviti uvjet ako je desni klik da se ovco dole desi
            //sve u funkciji mousclicked se mora desiti na desni ne na lijevi
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = tblPatients.rowAtPoint(evt.getPoint());
                int col = tblPatients.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    tblPatients.setRowSelectionInterval(row, row);

                    //Doctor selectedDoctor = doctors.get(ddlDoctors.getSelectedIndex());
                    //koji redak odaberem, taj pacijent s tim indexom je selektiran
                    selectedPatient = patients.get(row); //selectedDoctor.getPatients().get(row);//selektirani pacijent

                }

            }


        });


        btnInsertPatientBasic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InsertPatientMini patientMini = new InsertPatientMini();
                patientMini.setVisible(true);
            }
        });
        btnInsertPatientFull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                InsertPatientFull patientFull = new InsertPatientFull();
                patientFull.setVisible(true);
            }
        });
    }
    private void fillTable() {
        //dohvati iz liste na indexu koji odgovara indexu ddl-a
//        selectedDoctor = doctors.get(ddlDoctors.getSelectedIndex());//doctors[ddl.index()]
//        java.util.List<Patient> patients = selectedDoctor.getPatients();

        //napravi za svakog pacijenta zasebno polje - ovo je samo inicijalizacija
        //svaki pacijent mora napravit svoje (novo) polje
        Object[][] data = new Object[patients.size()][];

        for (int i = 0; i < data.length; i++) {
            //trenutni pacijent (objekt iz baze)
            Patient patient = patients.get(i);

            //Novi pacijent - popunit cemo 3 polja
            data[i] = new Object[7];

            //popunjavanje njegovih polja
            data[i][0] = patient.getId();
            data[i][1] = patient.getName();
            data[i][2] = patient.getOib();
            data[i][3] = patient.getDateOfBirth();
            data[i][4] = patient.getNextOfKinName();
            data[i][5] = patient.getEmail();
            data[i][6] = patient.getTelephoneNumberHome();
        }

        String[] columnNames = {"ID", "Name", "OIB", "Date of birth", "Next of kin name","E-mail","Mobile"};

        //za tablicu u defaultni model saljemo podatke koje prikazujemo i
        //header-e koji se prikazuju na vrhu tablice
        tblPatients.setModel(new DefaultTableModel(data, columnNames));
    }
}
