package com.example.e_book.service.customer;

import com.example.e_book.entity.Customer;
import com.example.e_book.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer editCustomer(String customerId, Customer customerDetails) {
        int result = customerRepository.updateCustomerInfo(
                customerId,
                customerDetails.getCustomerName(),
                customerDetails.isGender(),
                customerDetails.getBirthday(),
                customerDetails.getAddress(),
                customerDetails.getPhoneNumber(),
                customerDetails.getEmail()
        );

        if (result == 0) {
            throw new RuntimeException("Failed to update customer information");
        }
        return customerDetails;
    }
    @Override
    public Customer findCustomerById(String customerId) {
        return customerRepository.findCustomerById(customerId);
    }

    @Override
    public boolean changePassword(int accountId, String oldPassword, String newPassword) {
        // Lấy mật khẩu cũ từ database
        String currentPassword = customerRepository.findPasswordByAccountId(accountId);
        System.out.println("Current Password: " + currentPassword);

        // Kiểm tra mật khẩu cũ
        if (currentPassword.equals(oldPassword)) {
            // Nếu đúng, cập nhật mật khẩu mới
            customerRepository.updatePassword(accountId, newPassword);
            System.out.println("Password updated successfully.");
            return true;
        } else {
            // Nếu sai, thông báo lỗi
            System.out.println("Old password does not match.");
            return false;
        }
    }

}