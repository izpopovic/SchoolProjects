package com.gui.forms;

import com.dal.entities.Appoitment;
import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterData extends MyForm {
    private JLabel lblPAtient;
    private JPanel contentPane;
    private JComboBox ddlAppointments;
    private JTextArea textAreaEdit;
    private JTextField txtEditedName;
    private JButton btnEdit;
    private java.util.List<Appoitment> patientAppointments;

    public AlterData(Patient patient) throws HeadlessException {
        super("Alter data", 1000, 500);
        setContentPane(contentPane);

        patientAppointments = patient.getAppointments();

        ddlAppointments.setModel(new DefaultComboBoxModel(patientAppointments.toArray()));

        ddlAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Appoitment selectedAppointment = patientAppointments.get(ddlAppointments.getSelectedIndex());
                txtEditedName.setText(selectedAppointment.getName());
                textAreaEdit.setText(selectedAppointment.getDetails());
            }
        });
        ddlAppointments.setSelectedIndex(0);
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Appoitment selectedAppointment = patientAppointments.get(ddlAppointments.getSelectedIndex());
                if(RepoFactory.getRepo().updateAppointment(
                        selectedAppointment.getId(),
                        textAreaEdit.getText(), txtEditedName.getText()))
                {
                    selectedAppointment.setName(txtEditedName.getText());
                    selectedAppointment.setDetails(textAreaEdit.getText());
                    //ddlAppointments.setSelectedIndex(ddlAppointments.getSelectedIndex());
                    JOptionPane.showMessageDialog(null, "Success!");
                }else{
                    JOptionPane.showMessageDialog(null, "Failed!");
                }
            }
        });
    }


}
