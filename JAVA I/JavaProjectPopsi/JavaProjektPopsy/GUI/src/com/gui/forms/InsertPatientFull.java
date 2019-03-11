package com.gui.forms;

import com.dal.entities.Patient;
import com.dal.repo.RepoFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertPatientFull extends MyForm {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JFormattedTextField txtDateOfBirth;
    private JTextField txtSexID;
    private JTextField txtName;
    private JTextField txtOIB;
    private JTextField txtEmail;
    private JTextField txtFax;
    private JTextField txtTelephoneWork;
    private JTextField txtTelephoneHome;
    private JTextField txtMobile;
    private JTextField txtPager;
    private JTextField txtKinName;
    private JTextField txtKinRelationship;
    private JTextField txtKinContactAdress;
    private JTextField txtKinTelephoneWork;
    private JTextField txtKinTelephoneHome;
    private JTextField txtKinEmail;
    private JTextField txtKinMobile;
    private JTextField txtKinPager;
    private JTextField txtKinFax;
    private JTextField txtMaritalStatus;
    private JTextField txtNumOfDependents;
    private JTextField txtHeight;
    private JTextField txtWeight;
    private JTextField txtBloodType;
    private JTextField txtOccupation;
    private JTextField txtGrossAnnualIncome;
    private JCheckBox cbVegetarian;
    private JCheckBox cbSmoker;
    private JCheckBox cbConsumesAlcohol;
    private JCheckBox cbUsesStimulants;
    private JTextField txtStimulantsUsed;
    private JCheckBox cbRegularMeals;
    private JTextField txtCoffeConsumptionPerDay;
    private JTextField txtTeaConsumptionPerDay;
    private JTextField txtSofrtDrinkConsumptionPerDay;
    private JTextField txtPredominantEatingOption;
    private JTextArea txtStatementOfComplaintArea;
    private JTextArea txtHistoryofPreviousTreatment;
    private JTextField txtHospitalorPhysicianTreated;
    private JCheckBox cbDiabetic;
    private JCheckBox cbHypertensive;
    private JTextField txtCardiacCondition;
    private JTextField txtRespiratoryCondition;
    private JTextField txtDigestiveCondition;
    private JTextField txtOrthopedicCondition;
    private JTextField txtMuscularCondition;
    private JTextField txtNeurologicalCondition;
    private JTextField txtKnownAllergies;
    private JTextField txtKnowAdverseReactionToSpecDrugs;
    private JTextField txtMajorSurgeries;
    private JButton btnInsert;

    public InsertPatientFull() throws HeadlessException {
        super("Insert Patient (Full)", 1024, 768);
        setContentPane(contentPane);

        txtStatementOfComplaintArea.setLineWrap(true);
        txtHistoryofPreviousTreatment.setLineWrap(true);


        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Patient p = new Patient();

                p.setName(txtName.getText());
                p.setOib(txtOIB.getText());
                p.setSexID(Integer.parseInt(txtSexID.getText()));
                p.setDateOfBirth((Date)txtDateOfBirth.getValue());
                p.setTelephoneNumberWork(txtTelephoneWork.getText());
                p.setTelephoneNumberHome(txtTelephoneHome.getText());
                p.setMobile(txtMobile.getText());
                p.setPager(txtPager.getText());
                p.setFax(txtFax.getText());
                p.setEmail(txtEmail.getText());
                p.setNextOfKinName(txtKinName.getText());
                p.setNextOfKinRelationshipWithOutpatient(txtKinRelationship.getText());
                p.setNextOfKinContactAddress(txtKinContactAdress.getText());
                p.setNextOfKinHomeTelephone(txtKinTelephoneHome.getText());
                p.setNextOfKinWorkTelephone(txtKinTelephoneWork.getText());
                p.setNextOfKinEmail(txtKinEmail.getText());
                p.setNextOfKinMobile(txtKinMobile.getText());
                p.setNextOfKinFax(txtKinFax.getText());
                p.setNextOfKinPager(txtKinPager.getText());
                p.setMaritalStatus(txtMaritalStatus.getText());
                p.setNumberOfDependents(Integer.parseInt(txtNumOfDependents.getText()));
                p.setHeight(Double.parseDouble(txtHeight.getText()));
                p.setWeight(Double.parseDouble(txtWeight.getText()));
                p.setBloodTypeRH(txtBloodType.getText());
                p.setOccupation(txtOccupation.getText());
                p.setGrossAnnualIncome(BigDecimal.valueOf(Double.parseDouble(txtGrossAnnualIncome.getText())));
                p.setVegetarian(cbVegetarian.isSelected());
                p.setSmoker(cbSmoker.isSelected());
                p.setConsumesAlcoholicBeverage(cbConsumesAlcohol.isSelected());
                p.setUsesStimulants(cbUsesStimulants.isSelected());
                p.setStimulantsUsed(txtStimulantsUsed.getText());
                p.setCoffeeConsumptionPerDay(Double.parseDouble(txtCoffeConsumptionPerDay.getText()));
                p.setTeaConsumptionPerDay(Double.parseDouble(txtTeaConsumptionPerDay.getText()));
                p.setSoftDrinkConsumptionPerDay(Double.parseDouble(txtSofrtDrinkConsumptionPerDay.getText()));
                p.setRegularMeals(cbRegularMeals.isSelected());
                p.setPredominantEatingOptionID(Integer.parseInt(txtPredominantEatingOption.getText()));
                p.setStatementOfComplaint(txtStatementOfComplaintArea.getText());
                p.setHistoryOfPreviousTreatment(txtHistoryofPreviousTreatment.getText());
                p.setPhysicianOrHospitalTreated(txtHospitalorPhysicianTreated.getText());
                p.setDiabetic(cbDiabetic.isSelected());
                p.setHypertensive(cbHypertensive.isSelected());
                p.setCardiacCondition(txtCardiacCondition.getText());
                p.setRespiratoryCondition(txtRespiratoryCondition.getText());
                p.setDigestiveCondition(txtDigestiveCondition.getText());
                p.setOrthopedicCondition(txtOrthopedicCondition.getText());
                p.setMuscularCondition(txtMuscularCondition.getText());
                p.setNeurologicalCondition(txtNeurologicalCondition.getText());
                p.setKnownAllergies(txtKnownAllergies.getText());
                p.setKnownAdverseReactionToSpecificDrugs(txtKnowAdverseReactionToSpecDrugs.getText());
                p.setMajorSurgeries(txtMajorSurgeries.getText());

                if (RepoFactory.getRepo().insertPatientFull(p)) {
                    //doctor.getPatients().add(p);
                    JOptionPane.showMessageDialog(null, "Success!");
                    txtName.setText("");
                    txtSexID.setText("");
                    txtDateOfBirth.setText("");
                    txtOIB.setText("");

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
