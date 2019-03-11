package com.dal.repo;

import com.dal.entities.*;
import javafx.util.Pair;

import java.util.List;

public interface IRepo {
    //list je interfejs, moras pratiti njegovu implementaciju(ArrayList)
    List<Doctor> getDoctors();
    List<Patient> getPatients();
    List<Appoitment> getAppointments(Integer patientID);
    List<Bill> getBills(Integer patientID);
    List<Medicine> getMedicine(Integer patientID);

    List<Pair<Integer, String>> getSexes();
    List<Pair<Integer, String>> getMedicines();
    List<Pair<Integer, String>> getPredominantEatingOptions();
    List<Pair<Integer, String>> getPaymentTypes();

    Medicine insertMedicine(Double quantity, String medicineName, Integer patientID, Integer doctorID);
    Boolean insertPatientMini(Patient p);
    Boolean insertPatientFull(Patient p);
    Boolean insertPatientsAppointment(Appoitment appoitment);
    Boolean insertPatientsBill(Bill bill);
    Boolean updateAppointment(Integer appointmentID, String details, String name);

}
