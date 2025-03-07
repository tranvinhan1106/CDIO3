package com.example.e_book.service.customer;

import com.example.e_book.entity.Customer;

public interface CustomerService {
    Customer editCustomer(String customerId, Customer customerDetails);
    Customer findCustomerById(String customerId);
    boolean changePassword(int accountId, String oldPassword, String newPassword);
}
