package com.dal.entities;


import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private Integer id;
    private Date dateIssued;
    private Integer paymentTypeID;
    private Integer patientID;
    private BigDecimal amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPatientID(Integer patientID) {
        this.patientID = patientID;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPaymentTypeID() {
        return paymentTypeID;
    }

    public void setPaymentTypeID(Integer paymentTypeID) {
        this.paymentTypeID = paymentTypeID;
    }


    public static Bill convert(Object[] row) {
        Bill bill = new Bill();
        bill.setId((Integer) row[0]);
        bill.setDateIssued((Date) row[1]);
        bill.setPaymentTypeID((Integer) row[2]);
        bill.setPatientID((Integer) row[3]);
        bill.setAmount((BigDecimal) row[4]);
        return bill;
    }

    //kak zaokruzit amount na 2 decimale? big decimal je
    @Override
    public String toString() {
        return "Bill: \n" +
                "Date issued: " + dateIssued +
                ",\nPayment type: " + paymentTypeID +
                ",\nPatient ID: " + patientID +
                ",\nAmount: " + amount +
                '}';
    }
}
