package com.gui.forms;

import com.dal.entities.Doctor;
import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class InsertPatientMini extends MyForm {
    private JPanel contentPane;
    private JTextField txtName;
    private JTextArea txtStatementofComplaint;
    private JTextField txtSexID;
    private JFormattedTextField txtDateOfBirth;
    private JTextField txtTelephoneNumberWork;
    private JTextField txtTelephoneNumberHome;
    private JTextField txtNextOfKinName;
    private JButton btnInsert;

    public InsertPatientMini() throws HeadlessException {
        super("Mini registration", 500, 650);

        setContentPane(contentPane);
        txtStatementofComplaint.setLineWrap(true);



        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                java.util.Date date = new java.util.Date();
                Patient p = new Patient();

                String txtDatum = txtDateOfBirth.getText();
//                java.sql.Date dateOfBirth = null;
//                try {
//                    dateOfBirth = (Date) df.parse(txtDatum);
//                } catch (Exception ex) {
//                    System.out.println(ex);
//                }

                p.setName(txtName.getText());
                p.setSexID(Integer.parseInt(txtSexID.getText()));
                p.setDateOfBirth((Date)txtDateOfBirth.getValue());
                p.setTelephoneNumberWork(txtTelephoneNumberWork.getText());
                p.setTelephoneNumberHome(txtTelephoneNumberHome.getText());
                p.setNextOfKinName(txtNextOfKinName.getText());
                p.setStatementOfComplaint(txtStatementofComplaint.getText());

                if (RepoFactory.getRepo().insertPatientMini(p)) {
                    //doctor.getPatients().add(p);
                    JOptionPane.showMessageDialog(null, "Success!");
                    txtName.setText("");
                    txtSexID.setText("");
                    txtDateOfBirth.setText("");
                    txtTelephoneNumberWork.setText("");
                    txtTelephoneNumberHome.setText("");
                    txtNextOfKinName.setText("");
                    txtStatementofComplaint.setText("");
                }
                else
                    JOptionPane.showMessageDialog(null,"Error!");
            }
        });
    }


    private void createUIComponents() {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
        txtDateOfBirth = new JFormattedTextField(df);
    }
}
