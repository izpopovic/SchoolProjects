package com.gui.forms;

import com.dal.entities.Doctor;
import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// mora extendat MyForm, ctrl + . da overridas konstruktor i makni parametre, postavi ih u super() pozivu
public class Test extends MyForm {
    private JPanel contentPane;
    //tablica MORA biti u JScrollPane-u da bi radila
    private JTable tblPatients;
    private JComboBox ddlDoctors;
    private JButton orderTestForAButton;
    private JButton prescribeMedicineToPatientButton;
    private JButton alterFutureAppointmentsButton;
    private java.util.List<Doctor> doctors;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemOrderTests;
    private JMenuItem menuItemPrescribeMedicine;
    private JMenuItem menuItemAlterFutureAppointments;
    private Patient patient;
    private Doctor selectedDoctor;
    private boolean hidePopup;

    public Test() throws HeadlessException {
        super("Testing", 1200, 600);

        setContentPane(contentPane);


        popupMenu = new JPopupMenu();
        menuItemOrderTests = new JMenuItem("Order test for a patient");
        menuItemPrescribeMedicine = new JMenuItem("Prescribe medicine to patient");
        menuItemAlterFutureAppointments = new JMenuItem("Alter future appointments");


        menuItemOrderTests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                OrderTest orderTest = new OrderTest(patient, selectedDoctor);
                orderTest.setVisible(true);
            }
        });
        menuItemAlterFutureAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AlterData alterData = new AlterData(patient);//ovdje smo pacijenta proslijedili za sljedeci prozor
                alterData.setVisible(true);
            }
        });

        menuItemPrescribeMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PrescribeMedicine prescribeMedicine = new PrescribeMedicine(patient, selectedDoctor);
                prescribeMedicine.setVisible(true);
            }
        });
        popupMenu.add(menuItemOrderTests);
        popupMenu.add(menuItemPrescribeMedicine);
        popupMenu.add(menuItemAlterFutureAppointments);

        doctors = RepoFactory.getRepo().getDoctors();
        //popunjavanje kontrola se radi kroz modele - uvijek postoji default
        //implementacija pa nju iskoristis
        //za ddl koristimo onaj koji prima array i za svqki item u array-u
        //popuni drop down tako da doda opciju s textom koji odgovara
        //ToString()-u tog objekta
        ddlDoctors.setModel(new DefaultComboBoxModel(doctors.toArray()));
        fillTable();
        ddlDoctors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fillTable();
            }
        });
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent mouseEvent) {
//                if (hidePopup)
//                    popupMenu.setVisible(false);
//            }
//        });
        tblPatients.setComponentPopupMenu(popupMenu);

        tblPatients.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            //ovo radi samo na lijevi klik, moram sfixat i staviti uvjet ako je desni klik da se ovco dole desi
            //sve u funkciji mousclicked se mora desiti na desni ne na lijevi
            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                if (SwingUtilities.isRightMouseButton(evt)) {
                    int row = tblPatients.rowAtPoint(evt.getPoint());
                    int col = tblPatients.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col >= 0) {
                        //select na desni klik u jtable tablici
                        tblPatients.setRowSelectionInterval(row, row);

                        Doctor selectedDoctor = doctors.get(ddlDoctors.getSelectedIndex());
                        //koji redak odaberem, taj pacijent s tim indexom je selektiran
                        patient = selectedDoctor.getPatients().get(row);//selektirani pacijent

//                        popupMenu.setLocation(getX() + tblPatients.getX() + evt.getX(), getY() + tblPatients.getY() + evt.getY() + popupMenu.getHeight());
//                        popupMenu.setVisible(true);
//                        hidePopup = true;
//                        return;
//                    }


//                    tblPatients.setComponentPopupMenu(popupMenu);
//                    tblPatients.mouse
                }

//                hidePopup = true;
//                popupMenu.setVisible(false);
//                tblPatients.setComponentPopupMenu(null);
            }


        });
    }

    private void fillTable() {
        //dohvati iz liste na indexu koji odgovara indexu ddl-a
        selectedDoctor = doctors.get(ddlDoctors.getSelectedIndex());//doctors[ddl.index()]
        java.util.List<Patient> patients = selectedDoctor.getPatients();

        //napravi za svakog pacijenta zasebno polje - ovo je samo inicijalizacija
        //svaki pacijent mora napravit svoje (novo) polje
        Object[][] data = new Object[patients.size()][];

        for (int i = 0; i < data.length; i++) {
            //trenutni pacijent (objekt iz baze)
            Patient patient = patients.get(i);

            //Novi pacijent - popunit cemo 3 polja
            data[i] = new Object[5];

            //popunjavanje njegovih polja
            data[i][0] = patient.getId();
            data[i][1] = patient.getName();
            data[i][2] = patient.getOib();
            data[i][3] = patient.getDateOfBirth();
            data[i][4] = patient.getNextOfKinName();
        }

        String[] columnNames = {"ID", "Name", "OIB", "Date of birth", "Next of kin name"};

        //za tablicu u defaultni model saljemo podatke koje prikazujemo i
        //header-e koji se prikazuju na vrhu tablice
        tblPatients.setModel(new DefaultTableModel(data, columnNames));
    }
}
