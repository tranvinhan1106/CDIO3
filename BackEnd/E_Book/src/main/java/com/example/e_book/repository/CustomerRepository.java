package com.example.e_book.repository;

import com.example.e_book.entity.Book;
import com.example.e_book.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET customer_name = :customerName, gender = :gender, birthday = :birthday, address = :address, phone_number = :phoneNumber, email = :email WHERE customer_id = :customerId", nativeQuery = true)
    int updateCustomerInfo(@Param("customerId") String customerId, @Param("customerName") String customerName, @Param("gender") boolean gender, @Param("birthday") Date birthday, @Param("address") String address, @Param("phoneNumber") String phoneNumber, @Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE c.account.accountId = :accountId")
    Customer findCustomerById(@Param("accountId") int accountId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE account SET password = :newPassword WHERE account_id = :accountId", nativeQuery = true)
    int updatePassword(@Param("accountId") int accountId, @Param("newPassword") String newPassword);

    @Query(value = "SELECT password FROM account WHERE account_id = :accountId", nativeQuery = true)
    String findPasswordByAccountId(@Param("accountId") int accountId);

}
