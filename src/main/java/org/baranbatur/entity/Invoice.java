package org.baranbatur.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Invoice {

    private int invoiceNumber;
    private Customer customer;
    private double amount;
    private LocalDate invoiceDate;


    public Invoice(int invoiceNumber, LocalDate invoiceDate, double amount, Customer customer) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
        this.customer = customer;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
