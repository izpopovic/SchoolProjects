package com.gui.forms;

import com.dal.entities.Bill;

import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;
import javafx.util.Pair;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

public class IssueNSeeBill extends MyForm {
    private JPanel contentPane;
    private JComboBox ddlBills;
    private JButton btnConfirm;
    private JTextField txtPaymentType;
    private JTextField txtAmount;
    private java.util.List<Bill> patientBills;

    //treba mi pomoc jer neznam kako dohvatiti id iz jtablea
    public IssueNSeeBill(Patient patient) throws HeadlessException {
        super("Issue a bill", 1000, 500);
        setContentPane(contentPane);

        patientBills = RepoFactory.getRepo().getBills(patient.getId());

        ddlBills.setModel(new DefaultComboBoxModel(patientBills.toArray()));
        //stavi ovo gore mozda i na listener na ddl

        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Bill bill = new Bill();

                Date date = new Date();
                String amount = txtAmount.getText();
                bill.setAmount(new BigDecimal(amount));
                bill.setPaymentTypeID(Integer.parseInt(txtPaymentType.getText()));
                bill.setDateIssued(date);
                bill.setPatientID(patient.getId());

                if(RepoFactory.getRepo().insertPatientsBill(bill))
                {
                    JOptionPane.showMessageDialog(null,"Success!");
                    txtAmount.setText("");
                    txtPaymentType.setText("");
                    patient.getBills().add(bill);
                }
                else
                    JOptionPane.showMessageDialog(null,"Error!");

            }
        });
    }
}
