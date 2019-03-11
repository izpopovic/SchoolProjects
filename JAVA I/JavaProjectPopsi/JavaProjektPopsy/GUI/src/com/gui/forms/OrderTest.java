package com.gui.forms;

import com.dal.entities.Appoitment;
import com.dal.entities.Doctor;
import com.dal.entities.Medicine;
import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderTest extends MyForm {
    private JPanel contentPane;
    private JTextArea textAreaDetails;
    private JButton btnConfirm;
    private JTextField txtBoxName;
    private java.util.List<Appoitment> patientAppointments;


    public OrderTest(Patient patient,Doctor doctor) throws HeadlessException {
        super("Order test", 500, 500);
        setContentPane(contentPane);
        textAreaDetails.setLineWrap(true);

        patientAppointments = RepoFactory.getRepo().getAppointments(patient.getId());




        //nekomunicira ovo s bazom, samo dodajem u listu
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Appoitment appoitment = new Appoitment();

                Date date = new Date();

                appoitment.setDetails(textAreaDetails.getText());
                appoitment.setName(txtBoxName.getText());
                appoitment.setPatientID(patient.getId());
                appoitment.setDoctorID(doctor.getId());
                appoitment.setDateTime(date);

                if(RepoFactory.getRepo().insertPatientsAppointment(appoitment))
                {
                    JOptionPane.showMessageDialog(null,"Success!");
                    textAreaDetails.setText("");
                    txtBoxName.setText("");
                    patient.getAppointments().add(appoitment);
                }
                else
                    JOptionPane.showMessageDialog(null,"Error!");


            }
        });
    }
}
