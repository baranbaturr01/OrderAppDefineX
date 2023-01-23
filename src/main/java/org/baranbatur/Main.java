package org.baranbatur;

import org.baranbatur.entity.Customer;
import org.baranbatur.entity.Invoice;
import org.baranbatur.entity.Order;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Main {

    private static Map<String, Customer> customers = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Customer");
            System.out.println("2. Add Order");
            System.out.println("3. Show Invoice");
            System.out.println("4. Show Customers List");
            System.out.println("5. Show Customers Contains C");
            System.out.println("6. Total Amount of Invoices in June");
            System.out.println("7. List All Invoices");
            System.out.println("8. List All Invoices Over 1500 amount");
            System.out.println("9. Average amount of invoices over 1500 amount");
            System.out.println("10. List Customer With Invoices Under 500 amount ");
            System.out.println("11. list Sectors Of Companies With Invoices Average Under 750 Amount And In June ");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    createOrder();
                    break;
                case 3:
                    showInvoice();
                    break;
                case 4:
                    listCustomers();
                    break;
                case 5:
                    listCustomerWithC();
                    break;
                case 6:
                    totalInvoiceAmountAmountInJune();
                    break;
                case 7:
                    listAllInvoices();
                    break;
                case 8:
                    listInvoicesOverAmount(1500);
                    break;
                case 9:
                    averageAmountOfInvoicesOverAmount(1500);
                    break;
                case 10:
                    listCustomersWithInvoicesUnderAmount(500);
                    break;
                case 11:
                    listSectorsOfCompaniesWithInvoicesAverageUnderAmountAndInJune(750);
                case 12:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter customer last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer sector: ");
        String sector = scanner.nextLine();

        Customer customer = new Customer(firstName, lastName, phone, email, LocalDate.of(2019, Month.JANUARY, 1), sector);
        customers.put(phone, customer);
        System.out.println("Customer added successfully");
    }

    public static void createOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();
        Customer customer = customers.get(phone);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        System.out.print("Enter order number: ");
        int orderNumber = scanner.nextInt();
        System.out.print("Enter products ,separating with commas(,) : ");
        scanner.nextLine();
        String[] products = scanner.nextLine().split(",");
        System.out.print("Tutar: ");
        double amount = scanner.nextDouble();

        Order order = new Order(orderNumber, Arrays.asList(products), amount, customer);
        Invoice invoice = order.createInvoice();
        System.out.println("Invoice created successfully");
        System.out.println("Invoice number: " + invoice.getInvoiceNumber());
    }

    public static void showInvoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();
        Customer customer = customers.get(phone);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        List<Invoice> invoices = customer.getInvoices();
        if (invoices == null || invoices.isEmpty()) {
            System.out.println("No invoice found");
            return;
        }

        invoices.stream().forEach(invoice -> {
            System.out.println("Invoice number: " + invoice.getInvoiceNumber());
            System.out.println("Invoice date: " + invoice.getInvoiceDate());
            System.out.println("Invoice amount: " + invoice.getAmount());
            System.out.println("Customer: " + invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getLastName());
            System.out.println("------------------------------------------------");
        });
    }

    public static void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customer found");
            return;
        }
        customers.values().stream().forEach(customer -> {
            System.out.println("Customer name: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Customer phone: " + customer.getPhone());
            System.out.println("Customer email: " + customer.getEmail());
            System.out.println("------------------------------------------------");
        });
    }

    public static void listCustomerWithC() {
        if (customers.isEmpty()) {
            System.out.println("No customer found");
            return;
        }
        customers.values().stream().filter(customer -> customer.getFullName().toUpperCase().contains("C")).forEach(customer -> {
            System.out.println("Customer name: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Customer phone: " + customer.getPhone());
            System.out.println("Customer email: " + customer.getEmail());
            System.out.println("------------------------------------------------");
        });
    }

    public static void totalInvoiceAmountAmountInJune() {
        if (customers.isEmpty()) {
            System.out.println("No customer found");
            return;
        }
        double totalAmount = customers.values().stream().filter(customer -> customer.getRegistrationDate().getMonth() == Month.JUNE).map(customer -> customer.getInvoices()).flatMap(List::stream).mapToDouble(Invoice::getAmount).sum();

        System.out.println("Haziran ayında kayıt olan müşterilerin faturalarının toplam tutarı: " + totalAmount);
    }

    public static void listAllInvoices() {

        if (customers.isEmpty()) {
            System.out.println("No customer found");
            return;
        }

        customers.values().stream().map(customer -> customer.getInvoices()).flatMap(List::stream).forEach(invoice -> System.out.println("Invoice Number: " + invoice.getInvoiceNumber() + " Invoice Date: " + invoice.getInvoiceDate() + " Amount: " + invoice.getAmount() + " Customer: " + invoice.getCustomer().getFullName() + "----------------------------"));

    }

    public static void listInvoicesOverAmount(double amount) {
        if (customers.isEmpty()) {
            System.out.println("No customer found");
            return;
        }
        customers.values().stream().map(customer -> customer.getInvoices()).flatMap(List::stream).filter(invoice -> invoice.getAmount() > amount).forEach(invoice -> System.out.println("Invoices Number: " + invoice.getInvoiceNumber() + "Invoice Date: " + invoice.getInvoiceDate() + " Amount: " + invoice.getAmount() + " Customer: " + invoice.getCustomer().getFullName() + "----------------------------"));
    }

    private static void averageAmountOfInvoicesOverAmount(double amount) {
        DoubleSummaryStatistics statistics = customers.values().stream().map(customer -> customer.getInvoices()).flatMap(List::stream).filter(invoice -> invoice.getAmount() > amount).mapToDouble(Invoice::getAmount).summaryStatistics();
        System.out.println("Average amount of invoices over " + amount + " is " + statistics.getAverage());
    }

    private static void listCustomersWithInvoicesUnderAmount(double amount) {
        customers.values().stream().filter(customer -> customer.getInvoices().stream().anyMatch(invoice -> invoice.getAmount() < amount)).forEach(customer -> System.out.println("Customer: " + customer.getFullName()));
    }

    private static void listSectorsOfCompaniesWithInvoicesAverageUnderAmountAndInJune(double amount) {
        customers.values().stream().filter(customer -> customer.getInvoices().stream().filter(invoice -> invoice.getInvoiceDate().getMonth().equals(Month.JUNE)).mapToDouble(Invoice::getAmount).summaryStatistics().getAverage() < amount).forEach(customer -> System.out.println("Firma Adı: " + customer.getFullName() + " Sektör: " + customer.getSector()));
    }
}