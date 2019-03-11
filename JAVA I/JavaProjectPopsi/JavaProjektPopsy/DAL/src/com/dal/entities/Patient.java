package com.dal.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Patient {
    private List<Appoitment> appointments;
    private List<Bill> bills;
    private List<Medicine> medicine;

    private Integer id;
    private Date registrationDate;
    private String name;
    private String oib;
    private Integer sexID;
    private String sex;
    private Date dateOfBirth;
    private String telephoneNumberWork;
    private String telephoneNumberHome;
    private String mobile;
    private String fax;
    private String email;
    private String pager;
    private String maritalStatus;
    private Integer numberOfDependents;
    private Double height;
    private Double weight;
    private String bloodTypeRH;
    private String occupation;
    private BigDecimal grossAnnualIncome;
    private Boolean vegetarian;
    private Boolean smoker;
    private Boolean consumesAlcoholicBeverage;
    private Boolean usesStimulants;
    private String stimulantsUsed;
    private Double coffeeConsumptionPerDay;
    private Double teaConsumptionPerDay;
    private Double softDrinkConsumptionPerDay;
    private Boolean regularMeals;
    private Integer predominantEatingOptionID;
    private String predominantEatingOption;
    private String statementOfComplaint;
    private String historyOfPreviousTreatment;
    private String physicianOrHospitalTreated;
    private Boolean diabetic;
    private Boolean hypertensive;
    private String cardiacCondition;
    private String respiratoryCondition;
    private String digestiveCondition;
    private String orthopedicCondition;
    private String muscularCondition;
    private String neurologicalCondition;
    private String knownAllergies;
    private String knownAdverseReactionToSpecificDrugs;
    private String majorSurgeries;
    private String nextOfKinName;
    private String nextOfKinRelationshipWithOutpatient;
    private String nextOfKinContactAddress;
    private String nextOfKinWorkTelephone;
    private String nextOfKinHomeTelephone;
    private String nextOfKinMobile;
    private String nextOfKinPager;
    private String nextOfKinFax;
    private String nextOfKinEmail;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPredominantEatingOption() {
        return predominantEatingOption;
    }

    public void setPredominantEatingOption(String predominantEatingOption) {
        this.predominantEatingOption = predominantEatingOption;
    }

    public List<Appoitment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appoitment> appointments) {
        this.appointments = appointments;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public Integer getSexID() {
        return sexID;
    }

    public void setSexID(Integer sexID) {
        this.sexID = sexID;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephoneNumberWork() {
        return telephoneNumberWork;
    }

    public void setTelephoneNumberWork(String telephoneNumberWork) {
        this.telephoneNumberWork = telephoneNumberWork;
    }

    public String getTelephoneNumberHome() {
        return telephoneNumberHome;
    }

    public void setTelephoneNumberHome(String telephoneNumberHome) {
        this.telephoneNumberHome = telephoneNumberHome;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(Integer numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBloodTypeRH() {
        return bloodTypeRH;
    }

    public void setBloodTypeRH(String bloodTypeRH) {
        this.bloodTypeRH = bloodTypeRH;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public BigDecimal getGrossAnnualIncome() {
        return grossAnnualIncome;
    }

    public void setGrossAnnualIncome(BigDecimal grossAnnualIncome) {
        this.grossAnnualIncome = grossAnnualIncome;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    public Boolean getConsumesAlcoholicBeverage() {
        return consumesAlcoholicBeverage;
    }

    public void setConsumesAlcoholicBeverage(Boolean consumesAlcoholicBeverage) {
        this.consumesAlcoholicBeverage = consumesAlcoholicBeverage;
    }

    public Boolean getUsesStimulants() {
        return usesStimulants;
    }

    public void setUsesStimulants(Boolean usesStimulants) {
        this.usesStimulants = usesStimulants;
    }

    public String getStimulantsUsed() {
        return stimulantsUsed;
    }

    public void setStimulantsUsed(String stimulantsUsed) {
        this.stimulantsUsed = stimulantsUsed;
    }

    public Double getCoffeeConsumptionPerDay() {
        return coffeeConsumptionPerDay;
    }

    public void setCoffeeConsumptionPerDay(Double coffeeConsumptionPerDay) {
        this.coffeeConsumptionPerDay = coffeeConsumptionPerDay;
    }

    public Double getTeaConsumptionPerDay() {
        return teaConsumptionPerDay;
    }

    public void setTeaConsumptionPerDay(Double teaConsumptionPerDay) {
        this.teaConsumptionPerDay = teaConsumptionPerDay;
    }

    public Double getSoftDrinkConsumptionPerDay() {
        return softDrinkConsumptionPerDay;
    }

    public void setSoftDrinkConsumptionPerDay(Double softDrinkConsumptionPerDay) {
        this.softDrinkConsumptionPerDay = softDrinkConsumptionPerDay;
    }

    public Boolean getRegularMeals() {
        return regularMeals;
    }

    public void setRegularMeals(Boolean regularMeals) {
        this.regularMeals = regularMeals;
    }

    public Integer getPredominantEatingOptionID() {
        return predominantEatingOptionID;
    }

    public void setPredominantEatingOptionID(Integer predominantEatingOptionID) {
        this.predominantEatingOptionID = predominantEatingOptionID;
    }

    public String getStatementOfComplaint() {
        return statementOfComplaint;
    }

    public void setStatementOfComplaint(String statementOfComplaint) {
        this.statementOfComplaint = statementOfComplaint;
    }

    public String getHistoryOfPreviousTreatment() {
        return historyOfPreviousTreatment;
    }

    public void setHistoryOfPreviousTreatment(String historyOfPreviousTreatment) {
        this.historyOfPreviousTreatment = historyOfPreviousTreatment;
    }

    public String getPhysicianOrHospitalTreated() {
        return physicianOrHospitalTreated;
    }

    public void setPhysicianOrHospitalTreated(String physicianOrHospitalTreated) {
        this.physicianOrHospitalTreated = physicianOrHospitalTreated;
    }

    public Boolean getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(Boolean diabetic) {
        this.diabetic = diabetic;
    }

    public Boolean getHypertensive() {
        return hypertensive;
    }

    public void setHypertensive(Boolean hypertensive) {
        this.hypertensive = hypertensive;
    }

    public String getCardiacCondition() {
        return cardiacCondition;
    }

    public void setCardiacCondition(String cardiacCondition) {
        this.cardiacCondition = cardiacCondition;
    }

    public String getRespiratoryCondition() {
        return respiratoryCondition;
    }

    public void setRespiratoryCondition(String respiratoryCondition) {
        this.respiratoryCondition = respiratoryCondition;
    }

    public String getDigestiveCondition() {
        return digestiveCondition;
    }

    public void setDigestiveCondition(String digestiveCondition) {
        this.digestiveCondition = digestiveCondition;
    }

    public String getOrthopedicCondition() {
        return orthopedicCondition;
    }

    public void setOrthopedicCondition(String orthopedicCondition) {
        this.orthopedicCondition = orthopedicCondition;
    }

    public String getMuscularCondition() {
        return muscularCondition;
    }

    public void setMuscularCondition(String muscularCondition) {
        this.muscularCondition = muscularCondition;
    }

    public String getNeurologicalCondition() {
        return neurologicalCondition;
    }

    public void setNeurologicalCondition(String neurologicalCondition) {
        this.neurologicalCondition = neurologicalCondition;
    }

    public String getKnownAllergies() {
        return knownAllergies;
    }

    public void setKnownAllergies(String knownAllergies) {
        this.knownAllergies = knownAllergies;
    }

    public String getKnownAdverseReactionToSpecificDrugs() {
        return knownAdverseReactionToSpecificDrugs;
    }

    public void setKnownAdverseReactionToSpecificDrugs(String knownAdverseReactionToSpecificDrugs) {
        this.knownAdverseReactionToSpecificDrugs = knownAdverseReactionToSpecificDrugs;
    }

    public String getMajorSurgeries() {
        return majorSurgeries;
    }

    public void setMajorSurgeries(String majorSurgeries) {
        this.majorSurgeries = majorSurgeries;
    }

    public String getNextOfKinName() {
        return nextOfKinName;
    }


    public void setNextOfKinName(String nextOfKinName) {
        this.nextOfKinName = nextOfKinName;
    }

    public String getNextOfKinRelationshipWithOutpatient() {
        return nextOfKinRelationshipWithOutpatient;
    }

    public void setNextOfKinRelationshipWithOutpatient(String nextOfKinRelationshipWithOutpatient) {
        this.nextOfKinRelationshipWithOutpatient = nextOfKinRelationshipWithOutpatient;
    }

    public String getNextOfKinContactAddress() {
        return nextOfKinContactAddress;
    }

    public void setNextOfKinContactAddress(String nextOfKinContactAddress) {
        this.nextOfKinContactAddress = nextOfKinContactAddress;
    }

    public String getNextOfKinWorkTelephone() {
        return nextOfKinWorkTelephone;
    }

    public void setNextOfKinWorkTelephone(String nextOfKinWorkTelephone) {
        this.nextOfKinWorkTelephone = nextOfKinWorkTelephone;
    }

    public String getNextOfKinHomeTelephone() {
        return nextOfKinHomeTelephone;
    }

    public void setNextOfKinHomeTelephone(String nextOfKinHomeTelephone) {
        this.nextOfKinHomeTelephone = nextOfKinHomeTelephone;
    }

    public String getNextOfKinMobile() {
        return nextOfKinMobile;
    }

    public void setNextOfKinMobile(String nextOfKinMobile) {
        this.nextOfKinMobile = nextOfKinMobile;
    }

    public String getNextOfKinPager() {
        return nextOfKinPager;
    }

    public void setNextOfKinPager(String nextOfKinPager) {
        this.nextOfKinPager = nextOfKinPager;
    }

    public String getNextOfKinFax() {
        return nextOfKinFax;
    }

    public void setNextOfKinFax(String nextOfKinFax) {
        this.nextOfKinFax = nextOfKinFax;
    }

    public String getNextOfKinEmail() {
        return nextOfKinEmail;
    }

    public void setNextOfKinEmail(String nextOfKinEmail) {
        this.nextOfKinEmail = nextOfKinEmail;
    }


    //konvertiranje retka u bazu u objekt u Javi
    public static Patient convert(Object[] row){
        Patient patient = new Patient();

        patient.setId((Integer)row[0]);
        patient.setRegistrationDate((Date) row[1]);
        patient.setName((String) row[2]);
        patient.setOib((String) row[3]);
        patient.setSexID((Integer) row[4]);
        patient.setDateOfBirth((Date) row[5]);
        patient.setTelephoneNumberWork((String) row[6]);
        patient.setTelephoneNumberHome((String) row[7]);
        patient.setMobile((String) row[8]);
        patient.setFax((String) row[9]);
        patient.setEmail((String) row[10]);
        patient.setPager((String) row[11]);
        patient.setMaritalStatus((String) row[12]);
        patient.setNumberOfDependents((Integer) row[13]);
        patient.setHeight((Double) row[14]);
        patient.setWeight((Double) row[15]);
        patient.setBloodTypeRH((String) row[16]);
        patient.setOccupation((String) row[17]);
        patient.setGrossAnnualIncome((BigDecimal) row[18]);
        patient.setVegetarian((Boolean) row[19]);
        patient.setSmoker((Boolean) row[20]);
        patient.setConsumesAlcoholicBeverage((Boolean) row[21]);
        patient.setUsesStimulants((Boolean) row[22]);
        patient.setStimulantsUsed((String) row[23]);
        patient.setCoffeeConsumptionPerDay((Double) row[24]);
        patient.setTeaConsumptionPerDay((Double) row[25]);
        patient.setSoftDrinkConsumptionPerDay((Double) row[26]);
        patient.setRegularMeals((Boolean) row[27]);
        patient.setPredominantEatingOptionID((Integer) row[28]);
        patient.setStatementOfComplaint((String) row[29]);
        patient.setHistoryOfPreviousTreatment((String) row[30]);
        patient.setPhysicianOrHospitalTreated((String) row[31]);
        patient.setDiabetic((Boolean) row[32]);
        patient.setHypertensive((Boolean) row[33]);
        patient.setCardiacCondition((String) row[34]);
        patient.setRespiratoryCondition((String) row[35]);
        patient.setDigestiveCondition((String) row[36]);
        patient.setOrthopedicCondition((String) row[37]);
        patient.setMuscularCondition((String) row[38]);
        patient.setNeurologicalCondition((String) row[39]);
        patient.setKnownAllergies((String) row[40]);
        patient.setKnownAdverseReactionToSpecificDrugs((String) row[41]);
        patient.setMajorSurgeries((String) row[42]);
        patient.setNextOfKinName((String) row[43]);
        patient.setNextOfKinRelationshipWithOutpatient((String) row[44]);
        patient.setNextOfKinContactAddress((String) row[45]);
        patient.setNextOfKinWorkTelephone((String) row[46]);
        patient.setNextOfKinHomeTelephone((String) row[47]);
        patient.setNextOfKinMobile((String) row[48]);
        patient.setNextOfKinPager((String) row[49]);
        patient.setNextOfKinFax((String) row[50]);
        patient.setNextOfKinEmail((String) row[51]);

        return patient;
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(
                "id=" + id +
                "\n registrationDate=" + registrationDate +
                "\n name='" + name + '\'' +
                "\n oib='" + oib + '\'' +
                "\n sexID=" + sexID +
                "\n dateOfBirth=" + dateOfBirth +
                "\n telephoneNumberWork='" + telephoneNumberWork + '\'' +
                "\n telephoneNumberHome='" + telephoneNumberHome + '\'' +
                "\n mobile='" + mobile + '\'' +
                "\n fax='" + fax + '\'' +
                "\n email='" + email + '\'' +
                "\n pager='" + pager + '\'' +
                "\n maritalStatus='" + maritalStatus + '\'' +
                "\n numberOfDependents=" + numberOfDependents +
                "\n height=" + height +
                "\n weight=" + weight +
                "\n bloodTypeRH='" + bloodTypeRH + '\'' +
                "\n occupation='" + occupation + '\'' +
                "\n grossAnnualIncome=" + grossAnnualIncome +
                "\n vegetarian=" + vegetarian +
                "\n smoker=" + smoker +
                "\n consumesAlcoholicBeverage=" + consumesAlcoholicBeverage +
                "\n usesStimulants=" + usesStimulants +
                "\n stimulantsUsed='" + stimulantsUsed + '\'' +
                "\n coffeeConsumptionPerDay=" + coffeeConsumptionPerDay +
                "\n teaConsumptionPerDay=" + teaConsumptionPerDay +
                "\n softDrinkConsumptionPerDay=" + softDrinkConsumptionPerDay +
                "\n regularMeals=" + regularMeals +
                "\n predominantEatingOptionID=" + predominantEatingOptionID +
                "\n statementOfComplaint='" + statementOfComplaint + '\'' +
                "\n historyOfPreviousTreatment='" + historyOfPreviousTreatment + '\'' +
                "\n physicianOrHospitalTreated='" + physicianOrHospitalTreated + '\'' +
                "\n diabetic=" + diabetic +
                "\n hypertensive=" + hypertensive +
                "\n cardiacCondition='" + cardiacCondition + '\'' +
                "\n respiratoryCondition='" + respiratoryCondition + '\'' +
                "\n digestiveCondition='" + digestiveCondition + '\'' +
                "\n orthopedicCondition='" + orthopedicCondition + '\'' +
                "\n muscularCondition='" + muscularCondition + '\'' +
                "\n neurologicalCondition='" + neurologicalCondition + '\'' +
                "\n knownAllergies='" + knownAllergies + '\'' +
                "\n knownAdverseReactionToSpecificDrugs='" + knownAdverseReactionToSpecificDrugs + '\'' +
                "\n majorSurgeries='" + majorSurgeries + '\'' +
                "\n nextOfKinName='" + nextOfKinName + '\'' +
                "\n nextOfKinRelationshipWithOutpatient='" + nextOfKinRelationshipWithOutpatient + '\'' +
                "\n nextOfKinContactAddress='" + nextOfKinContactAddress + '\'' +
                "\n nextOfKinWorkTelephone='" + nextOfKinWorkTelephone + '\'' +
                "\n nextOfKinHomeTelephone='" + nextOfKinHomeTelephone + '\'' +
                "\n nextOfKinMobile='" + nextOfKinMobile + '\'' +
                "\n nextOfKinPager='" + nextOfKinPager + '\'' +
                "\n nextOfKinFax='" + nextOfKinFax + '\'' +
                "\n nextOfKinEmail='" + nextOfKinEmail + '\'' + '\n');

        output.append("\nAppointments: ");
        output.append(getAppointments());
        output.append("\nBills: ");
        output.append(getBills());
        output.append("\nMedicine: ");
        output.append(getMedicine());
        output.append("\n=========================================================================\n");

        return output.toString();
    }
}
