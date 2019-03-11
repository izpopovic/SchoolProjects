package com.gui.forms;


import com.dal.entities.Doctor;
import com.dal.entities.Medicine;
import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class PrescribeMedicine extends MyForm {
    private JPanel contentPane;
    private JButton btnPrescribe;
    private JLabel lblID;
    private JLabel lblName;
    private JLabel lblSex;
    private JLabel lblWeight;
    private JLabel lblHeight;
    private JLabel lblDateOfBirth;
    private JComboBox ddlAllMedicines;
    private JTextField txtQuantity;
    private JTable tblPrescribedMedicine;
    private Patient patient;


    public PrescribeMedicine(Patient patient, Doctor doctor) throws HeadlessException {
        super("Prescribe medicine", 1000, 500);
        this.patient = patient;
        setContentPane(contentPane);
        String weight = String.format("%,.2f", patient.getWeight());
        String height = String.format("%,.2f", patient.getHeight());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String dateOfBirth = df.format(patient.getDateOfBirth());
        lblID.setText("ID:" + patient.getId().toString());
        lblName.setText("Name: " + patient.getName());
        lblSex.setText("Sex: " + patient.getSex());
        lblWeight.setText("Weight: " + weight + " kg");
        lblHeight.setText("Height: " + height + " cm");
        lblDateOfBirth.setText("Date of birth: " + dateOfBirth);

        java.util.List<Pair<Integer, String>> medicines = RepoFactory.getRepo().getMedicines();
        // Pretvara kolekciju parova <int, string> u kolekciju samo stringova (jos na kraju castamo u array za ComboBoxModel)
        ddlAllMedicines.setModel(new DefaultComboBoxModel(medicines.stream().map(kvp -> kvp.getValue()).toArray()));

        fillTable();

        btnPrescribe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Double quantity = Double.parseDouble(txtQuantity.getText());
                Medicine medicine = RepoFactory.getRepo().insertMedicine(
                        quantity, ddlAllMedicines.getSelectedItem().toString(), patient.getId(), doctor.getId()
                );
                patient.getMedicine().add(medicine);
                //PrescribeMedicine.this.setVisible(false);
                fillTable();
                //JOptionPane.showMessageDialog(null, "Lijek uspješno dodan");
            }
        });
    }

    private void fillTable() {
        List<Medicine> prescribedMedicine = patient.getMedicine();
        Object[][] data = new Object[prescribedMedicine.size()][];
        for(int i = 0; i < data.length; i++){
            data[i] = new Object[3];
            data[i][0] = prescribedMedicine.get(i).getDateIssued();
            data[i][1] = prescribedMedicine.get(i).getName();
            data[i][2] = prescribedMedicine.get(i).getQuantity();
        }
        String[] headeri = { "Izdano", "Naziv", "Količina" };
        tblPrescribedMedicine.setModel(new DefaultTableModel(data, headeri));
    }
}
