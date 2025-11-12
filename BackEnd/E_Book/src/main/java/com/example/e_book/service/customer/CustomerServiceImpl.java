package com.example.e_book.service.customer;

import com.example.e_book.entity.Customer;
import com.example.e_book.repository.CustomerRepository;
import com.example.e_book.service.account.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer editCustomer(int accountId, Customer customerDetails) {
        int result = customerRepository.updateCustomerInfo(
                customerDetails.getCustomerId(),
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
        return customerRepository.findCustomerById(getCurrentAccountId());
    }
    @Override
    public Customer findCustomerById(int accountId) {
        return customerRepository.findCustomerById(accountId);
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

    @Override
    public int getCurrentAccountId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getAccount().getAccountId();
        }
        throw new RuntimeException("Không tìm thấy thông tin đăng nhập");
    }
}