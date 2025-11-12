package com.example.e_book.service.customer;

import com.example.e_book.entity.Customer;

public interface CustomerService {
    Customer editCustomer(int accountId, Customer customerDetails);
    Customer findCustomerById(int accountId);
    boolean changePassword(int accountId, String oldPassword, String newPassword);
    int getCurrentAccountId();
}
