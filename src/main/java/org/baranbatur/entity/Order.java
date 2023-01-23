package org.baranbatur.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Order {

    private int orderNumber;
    private List<String> products;
    private double amount;
    private Customer customer;

    public Order(int orderNumber, List<String> products, double amount, Customer customer) {
        this.orderNumber = orderNumber;
        this.products = products;
        this.amount = amount;
        this.customer = customer;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
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

    public Invoice createInvoice() {
        int invoiceNumber = (int) (Math.random() * 10000);
        LocalDate invoiceDate = LocalDate.now();
        Invoice invoice = new Invoice(invoiceNumber, invoiceDate, amount, customer);
        customer.addInvoice(invoice);
        return invoice;
    }
}
