package com.dal.entities;


import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

public class Doctor {
    private List<Patient> patients;

    //kad radim u DALu sve varijable su klase(sql paramater je generic, a u javi moras imat klase za generic)
    private Integer id;
    private String oib;
    private String name;
    private Integer sexID;
    private String sex;
    private Date dateOfBirth;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    //ctrl + shift + -
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSexID() {
        return sexID;
    }

    public void setSexID(Integer sexID) {
        this.sexID = sexID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static Doctor convert (Object[] row){
        Doctor doctor = new Doctor();
        doctor.setId((Integer)row[0]);
        doctor.setOib((String)row[1]);
        doctor.setName((String)row[2]);
        doctor.setSexID((Integer)row[3]);
        doctor.setDateOfBirth((Date)row[4]);

        return doctor;
    }

    @Override
    public String toString() {
//        return "Doctor{" +
//                "id=" + id +
//                ", oib='" + oib + '\'' +
//                ", name='" + name + '\'' +
//                ", sex='" + sex + '\'' +
//                ", dateOfBirth=" + dateOfBirth +
//                '}';
        return name;
    }
}
