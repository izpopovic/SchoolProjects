package com.dal.entities;

import java.util.Date;

public class Appoitment {
    private Integer id;
    private Integer patientID;
    private Integer doctorID;
    private String name;
    private String details;
    private Date dateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public Integer getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(Integer doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public static Appoitment convert(Object[] row){
        Appoitment appoitment = new Appoitment();
        appoitment.setId((Integer)row[0]);
        appoitment.setPatientID((Integer)row[1]);
        appoitment.setDoctorID((Integer)row[2]);
        appoitment.setName((String)row[3]);
        appoitment.setDetails((String)row[4]);
        appoitment.setDateTime((Date)row[5]);
        return appoitment;
    }

    @Override
    public String toString() {
        return
                //"id=" + id +
//                ", PatientID: " + patientID +
//                ", DoctorID: " + doctorID +
                "Name: '" + name + '\'' +
                ", Details: '" + details + '\'' +
                ", Time: " + dateTime;
    }
}
