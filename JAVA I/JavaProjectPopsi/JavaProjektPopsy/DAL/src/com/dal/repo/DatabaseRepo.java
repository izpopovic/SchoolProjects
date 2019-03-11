package com.dal.repo;

import com.dal.entities.*;
import com.lib.dal.access.DBExecutor;
import com.lib.dal.entities.DBConfig;
import com.lib.dal.entities.SQLParameter;
import javafx.util.Pair;

import java.sql.Types;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.function.Function;

public class DatabaseRepo implements IRepo {
    private DBExecutor dbExecutor;
    private Function<Object[], Pair<Integer, String>> enumConverter;

    public DatabaseRepo() throws Exception {
        DBConfig config = new DBConfig("C:\\Users\\Ivan\\Desktop\\Algebra\\Projekti Algebra\\JAVA I\\JavaProjectPopsi\\Postaavke.xml");
        dbExecutor = new DBExecutor(config);

        //s referencom na funkciju convert
        //enumConverter = this::convert;



        enumConverter = array -> new Pair<>((Integer) array[0], (String) array[1]);
    }

//    private Pair<Integer, String> convert(Object[] array){
//        return new Pair<>((Integer)array[0], (String)array[1]);
//    }

    @Override
    public List<Doctor> getDoctors() {
        List<Patient> patients = getPatients();
        List<Doctor> doctors = dbExecutor.executeProcedure("GetDoctors", Doctor::convert);
        List<Pair<Integer, String>> sexes = getSexes();
        for (Doctor doctor : doctors) {
            //postavi spol
            for (Pair<Integer, String> sex : sexes) {
                //tamo gdje je kljuc u doctoru jednak kljuc u sex id, prop sex na doctoru postavi na Male ili Female
                if (sex.getKey().equals(doctor.getSexID())) {
                    doctor.setSex(sex.getValue());
                }
            }

            //postavi pacijente
            List<Patient> doctorsPatients = new ArrayList<>();

            for (Patient patient : patients) {
                for (Appoitment appoitment : patient.getAppointments()) {
                    //ako ovaj pacijent ima appointment s trenutnim doktorom, dodaj ga u listu za trenutnog doktora
                    if (appoitment.getDoctorID().equals(doctor.getId())) {
                        doctorsPatients.add(patient);
                        //nasli smo da ovaj doktor postoji u jednom od njegovih appointmenta, ne trebas
                        //gledat dalje...
                        break;
                    }
                }
            }

            doctor.setPatients(doctorsPatients);
        }
        return doctors;
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> patients = dbExecutor.executeProcedure("GetPatients", Patient::convert);
        List<Pair<Integer, String>> sexes = getSexes();
        List<Pair<Integer, String>> predominantEatingOptions = getPredominantEatingOptions();

        for (Patient patient : patients) {
            Integer id = patient.getId();

            patient.setAppointments(getAppointments(id));
            patient.setBills(getBills(id));
            patient.setMedicine(getMedicine(id));

            for (Pair<Integer, String> predominantEatingOption : predominantEatingOptions) {
                if (predominantEatingOption.getKey().equals(patient.getPredominantEatingOptionID())) {
                    patient.setPredominantEatingOption(predominantEatingOption.getValue());
                }
            }

            for (Pair<Integer, String> sex : sexes) {
                if (sex.getKey().equals(patient.getSexID())) {
                    patient.setSex(sex.getValue());
                }
            }
        }

        return patients;
    }

    @Override
    public List<Appoitment> getAppointments(Integer patientID) {

        return dbExecutor.executeProcedure("GetAppointments", Appoitment::convert,
                new SQLParameter<>(patientID));
    }

    @Override
    public List<Bill> getBills(Integer patientID) {
        // isto ko get appointments
        return dbExecutor.executeProcedure("GetBills", Bill::convert,
                new SQLParameter<>(patientID));
    }

    @Override
    public List<Medicine> getMedicine(Integer patientID) {
        // isto ko get appointments
        List<Medicine> prescribedMedicine =
                dbExecutor.executeProcedure("GetMedicine", Medicine::convert,
                        new SQLParameter<>(patientID));

        List<Pair<Integer, String>> medicines = getMedicines();
        for(Medicine medicine : prescribedMedicine){
            for(Pair<Integer, String> medicineEnum : medicines){
                if(medicineEnum.getKey().equals(medicine.getMedicineID())){
                    medicine.setName(medicineEnum.getValue());
                    break;
                }
            }
        }

        return prescribedMedicine;
    }

    @Override
    public List<Pair<Integer, String>> getSexes() {
        //implementacija mape je hash map

        return dbExecutor.executeProcedure("GetSexes", enumConverter);
    }

    @Override
    public List<Pair<Integer, String>> getMedicines() {
        return dbExecutor.executeProcedure("GetAllMedicines", enumConverter);
    }

    @Override
    public List<Pair<Integer, String>> getPredominantEatingOptions() {
        return dbExecutor.executeProcedure("GetPredominantEatingOptions", enumConverter);
    }

    @Override
    public List<Pair<Integer, String>> getPaymentTypes() {
        return dbExecutor.executeProcedure("GetPaymentType",enumConverter);
    }

    @Override
    public Medicine insertMedicine(Double quantity, String medicineName, Integer patientID, Integer doctorID) {
        SQLParameter<Date> dateIssued = new SQLParameter<>(Types.DATE);

        Integer medicineID = null;
        List<Pair<Integer, String>> medicines = getMedicines();
        for (Pair<Integer, String> medicine : medicines) {
            if (medicine.getValue().equals(medicineName)) {
                medicineID = medicine.getKey();
                break;
            }
        }

        dbExecutor.executeProcedure("InsertPatientMedicine",
                new SQLParameter<>(quantity),
                new SQLParameter<>(medicineID),
                new SQLParameter<>(patientID),
                new SQLParameter<>(doctorID),
                dateIssued
        );

        Medicine medicine = new Medicine();
        medicine.setDateIssued(dateIssued.getValue());
        medicine.setQuantity(quantity);
        medicine.setName(medicineName);

        return medicine;
    }

    @Override
    public Boolean insertPatientMini(Patient p) {
        //output parametri
        SQLParameter<Integer> insertedID = new SQLParameter<>(Types.INTEGER);
        SQLParameter<Date> registrationDate = new SQLParameter<>(Types.DATE);

        boolean success = dbExecutor.executeProcedure("InsertPatientMini",
                new SQLParameter<>(p.getName()),
                new SQLParameter<>(p.getSexID()),
                new SQLParameter<>(p.getDateOfBirth()),
                new SQLParameter<>(p.getTelephoneNumberWork()),
                new SQLParameter<>(p.getTelephoneNumberHome()),
                new SQLParameter<>(p.getNextOfKinName()),
                new SQLParameter<>(p.getStatementOfComplaint()),
                insertedID,
                registrationDate
        ) > 0;
        //ako se vise od 0 redaka promjenilo vraca true, suprotno false

        p.setId(insertedID.getValue());
        p.setRegistrationDate(registrationDate.getValue());

        return success;
    }

    @Override
    public Boolean insertPatientFull(Patient p) {
        //output parametri
        SQLParameter<Integer> insertedID = new SQLParameter<>(Types.INTEGER);
        SQLParameter<Date> registrationDate = new SQLParameter<>(Types.DATE);

        boolean success = dbExecutor.executeProcedure("InsertPatientFull",
                new SQLParameter<>(p.getName()),
                new SQLParameter<>(p.getOib()),
                new SQLParameter<>(p.getSexID()),
                new SQLParameter<>(p.getDateOfBirth()),
                new SQLParameter<>(p.getTelephoneNumberWork()),
                new SQLParameter<>(p.getTelephoneNumberHome()),
                new SQLParameter<>(p.getMobile()),
                new SQLParameter<>(p.getFax()),
                new SQLParameter<>(p.getEmail()),
                new SQLParameter<>(p.getPager()),
                new SQLParameter<>(p.getMaritalStatus()),
                new SQLParameter<>(p.getNumberOfDependents()),
                new SQLParameter<>(p.getHeight()),
                new SQLParameter<>(p.getWeight()),
                new SQLParameter<>(p.getBloodTypeRH()),
                new SQLParameter<>(p.getOccupation()),
                new SQLParameter<>(p.getGrossAnnualIncome()),
                new SQLParameter<>(p.getVegetarian()),
                new SQLParameter<>(p.getSmoker()),
                new SQLParameter<>(p.getConsumesAlcoholicBeverage()),
                new SQLParameter<>(p.getUsesStimulants()),
                new SQLParameter<>(p.getStimulantsUsed()),
                new SQLParameter<>(p.getCoffeeConsumptionPerDay()),
                new SQLParameter<>(p.getTeaConsumptionPerDay()),
                new SQLParameter<>(p.getSoftDrinkConsumptionPerDay()),
                new SQLParameter<>(p.getRegularMeals()),
                new SQLParameter<>(p.getPredominantEatingOptionID()),
                new SQLParameter<>(p.getStatementOfComplaint()),
                new SQLParameter<>(p.getHistoryOfPreviousTreatment()),
                new SQLParameter<>(p.getPhysicianOrHospitalTreated()),
                new SQLParameter<>(p.getDiabetic()),
                new SQLParameter<>(p.getHypertensive()),
                new SQLParameter<>(p.getCardiacCondition()),
                new SQLParameter<>(p.getRespiratoryCondition()),
                new SQLParameter<>(p.getDigestiveCondition()),
                new SQLParameter<>(p.getOrthopedicCondition()),
                new SQLParameter<>(p.getMuscularCondition()),
                new SQLParameter<>(p.getNeurologicalCondition()),
                new SQLParameter<>(p.getKnownAllergies()),
                new SQLParameter<>(p.getKnownAdverseReactionToSpecificDrugs()),
                new SQLParameter<>(p.getMajorSurgeries()),
                new SQLParameter<>(p.getNextOfKinName()),
                new SQLParameter<>(p.getNextOfKinRelationshipWithOutpatient()),
                new SQLParameter<>(p.getNextOfKinContactAddress()),
                new SQLParameter<>(p.getNextOfKinWorkTelephone()),
                new SQLParameter<>(p.getNextOfKinHomeTelephone()),
                new SQLParameter<>(p.getNextOfKinMobile()),
                new SQLParameter<>(p.getNextOfKinPager()),
                new SQLParameter<>(p.getNextOfKinFax()),
                new SQLParameter<>(p.getNextOfKinEmail()),
                insertedID,
                registrationDate
        ) > 0;

        p.setId(insertedID.getValue());
        p.setRegistrationDate(registrationDate.getValue());

        return success;


    }

    @Override
    public Boolean insertPatientsAppointment(Appoitment a) {
        boolean success = dbExecutor.executeProcedure("InsertPatientsAppointments",
                new SQLParameter<>(a.getPatientID()),
                new SQLParameter<>(a.getDoctorID()),
                new SQLParameter<>(a.getName()),
                new SQLParameter<>(a.getDetails()),
                new SQLParameter<>(a.getDateTime())
        ) > 0;

        return success;
    }

    @Override
    public Boolean insertPatientsBill(Bill b) {
        //pokusao sam doci do imena da upiso
//        Integer paymentID = null;
//        List<Pair<Integer, String>> paymentMethods = getPaymentTypes();
//        for (Pair<Integer, String> paymentType : paymentMethods) {
//            if (paymentType.getValue().equals(paymentName)) {
//                paymentID = paymentType.getKey();
//                break;
//            }
//        }


        boolean success = dbExecutor.executeProcedure("InsertPatientBill",
                new SQLParameter<>(b.getPaymentTypeID()),
                new SQLParameter<>(b.getPatientID()),
                new SQLParameter<>(b.getAmount()),
                new SQLParameter<>(b.getDateIssued())
        ) > 0;

        return success;
    }



    @Override
    public Boolean updateAppointment(Integer appointmentID, String details, String name) {
        return dbExecutor.executeProcedure(
                "UpdateAppointment",
                new SQLParameter<>(appointmentID),
                new SQLParameter<>(details),
                new SQLParameter<>(name)
        ) > 0;
    }


}
