package org.baranbatur.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    String firstName;
    String lastName;
    String email;
    String phone;
    String sector;

    private LocalDate registrationDate;
    private List<Invoice> invoices;


    public Customer(String firstName, String lastName, String phoneNumber, String email, LocalDate registrationDate, String sector) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phoneNumber;
        this.email = email;
        this.sector = sector;
        this.registrationDate = registrationDate;
        this.invoices = new ArrayList<>();
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
