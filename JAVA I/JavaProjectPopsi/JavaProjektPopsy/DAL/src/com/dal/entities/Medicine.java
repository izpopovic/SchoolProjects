package com.dal.entities;

import java.util.Date;

public class Medicine {
    private Integer medicineID;
    private Date dateIssued;
    private Double quantity;
    private String name;

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(Integer medicineID) {
        this.medicineID = medicineID;
    }
    //PatientMedicine u sebi ima idMedicine, taj Medicine ima naziv u sebi, kako dobiti onda naziv medicina?
    public static Medicine convert(Object[] row){
        Medicine medicine = new Medicine();

        //medicine.setName((String) row[]); kako ??
        medicine.setDateIssued((Date) row[1]);
        medicine.setQuantity((Double) row[2]);
        medicine.setMedicineID((Integer)row[3]);

        return medicine;
    }


}
