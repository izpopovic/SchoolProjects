package com.console;

import com.dal.entities.Appoitment;
import com.dal.entities.Bill;
import com.dal.entities.Doctor;
import com.dal.entities.Patient;
import com.dal.repo.IRepo;
import com.dal.repo.RepoFactory;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Console {
    public static void start(){
        System.out.println("Console");
        IRepo repo = RepoFactory.getRepo();
//        List<Pair<Integer, String>> sexes = RepoFactory.getRepo ().getSexes();
//        System.out.println(sexes);

        List<Doctor> doctors = repo.getDoctors();
        System.out.println(doctors);
//        List<Appoitment> appoitments = RepoFactory.getRepo().getAppointments(1);
//        System.out.println(appoitments);//ispisuje [] jer jos nemam nista appoitmenta

        List<Patient> patients = repo.getPatients();
        System.out.println(patients);

        // 1 - Ispis doktora
        // 2 - Ispis pacijenata
        // Upisite opciju: _



        for (Patient patient : patients) {
            System.out.println(patient);
        }
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
        for (Patient patient: patients) {

        System.out.println(patient.getBills());

        }



/*
        Patient patient = new Patient();
        patient.setName("Milica Krmpotić");
        patient.setSexID(1);
        patient.setDateOfBirth(new java.util.Date(1997,1,10));
        patient.setNextOfKinName("Viliboj Krmpotevčki");
        patient.setStatementOfComplaint("Previse alkohola, treba na ispumpavanje hitno bez čekanja");
        patient.setTelephoneNumberHome("2109382131");
        patient.setTelephoneNumberWork("321321312421321");
        if (RepoFactory.getRepo().insertPatientMini(patient))
            System.out.println(patient);
        else
            System.out.println("Njet proslo umetanje, probaj ponovno");

        Patient p = new Patient();

        p.setName("Lucy Pervan");
        p.setOib("12345678901");
        p.setSexID(2);
        p.setDateOfBirth(new java.util.Date(1999,3,3));
        p.setTelephoneNumberWork("12321321321");
        p.setTelephoneNumberHome("12321321");
        p.setMobile("1321321321321321");
        p.setFax("+232132131");
        p.setEmail("lucy.pervan@email.hr");
        p.setPager("0023210");
        p.setMaritalStatus("In a happy relationship");
        p.setNumberOfDependents(0);
        p.setHeight(167.4f);
        p.setWeight(57.2f);
        p.setBloodTypeRH("RH+");
        p.setOccupation("College Algebra");
        p.setGrossAnnualIncome(new BigDecimal(12000));
        p.setVegetarian(false);
        p.setSmoker(false);
        p.setConsumesAlcoholicBeverage(true);
        p.setUsesStimulants(true);
        p.setStimulantsUsed("Lung stimulants");
        p.setCoffeeConsumptionPerDay(7.4f);
        p.setTeaConsumptionPerDay(0.0f);
        p.setSoftDrinkConsumptionPerDay(1.4f);
        p.setRegularMeals(false);
        p.setPredominantEatingOptionID(2);
        p.setStatementOfComplaint("Pacijentica dolazi u bolnicu zbog povremenih bolova u glavi, žali se na premalo učenja" +
                " na faksu te smatra kako bi dan od 24 sata trebalo potrošiti na način da jedeš učiš i spavaš");
        p.setHistoryOfPreviousTreatment("None");
        p.setPhysicianOrHospitalTreated("Dr. Ivan , Hospital St. July");
        p.setDiabetic(false);
        p.setHypertensive(true);
        p.setCardiacCondition("Los krvotok");
        p.setRespiratoryCondition("Astma");
        p.setDigestiveCondition("Premalo hrane u stomaku kroz dan");
        p.setOrthopedicCondition("Sindrom malih stopala");
        p.setMuscularCondition("Preveliki biceps i triceps za svoju dob");
        p.setNeurologicalCondition("Perfect");
        p.setKnownAllergies("Chicken");
        p.setKnownAdverseReactionToSpecificDrugs("None");
        p.setMajorSurgeries("Jajnik Removed.");
        p.setNextOfKinName("Vicko Pervan");
        p.setNextOfKinRelationshipWithOutpatient("Father");
        p.setNextOfKinContactAddress("Savišće");
        p.setNextOfKinWorkTelephone("+0038532235");
        p.setNextOfKinHomeTelephone("321321321");
        p.setNextOfKinMobile("09932132131");
        p.setNextOfKinPager("123");
        p.setNextOfKinFax("3849203");
        p.setNextOfKinEmail("next@ofkin.com");

        if (RepoFactory.getRepo().insertPatientFull(p))
            System.out.println(p);
        else
            System.out.println("Umetanje pacijenta u bazu nije proslo, pokusaj ponovno ili kontaktirajte administratore!");
*/



    }
}
