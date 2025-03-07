package com.example.e_book.controller;

import com.example.e_book.entity.Book;
import com.example.e_book.entity.Contain;
import com.example.e_book.entity.Customer;
import com.example.e_book.service.book.BookService;
import com.example.e_book.service.cart.CartService;
import com.example.e_book.service.customer.CustomerService;
import com.example.e_book.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CartService cartService;

    //    @GetMapping("")
//    public ResponseEntity<Page<Book>> findAll(@PageableDefault(page = 0 , size = 4)Pageable pageable){
//        Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
//        Page<Book> list = homeService.findAllBook(pageable1);
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
    @GetMapping("")
    public ResponseEntity<List<Book>> findAll() {
        List<Book> list = homeService.findAllBook();
        homeService.findAllBook();
        System.out.println("Books retrieved: " + list.size());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
//      KHI LÀM CHỨC NĂNG ĐĂNG NHẬP
//    @GetMapping("/customer/{id}")
//    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") String customerId){
//
//        return new ResponseEntity<>( customerService.findCustomerById(customerId),HttpStatus.OK);
//    }

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerInfo() {
        String customerIdDefault = "C001";
        Customer customer = customerService.findCustomerById(customerIdDefault);
        System.out.println("Customer: " + customer.getCustomerName());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // Chỉnh sửa thông tin cá nhân
    @PutMapping("/customer/edit")
    public ResponseEntity<Customer> updateCustomerInfo(@RequestBody Customer customerDetails) {
        String customerId = "C001";
        Customer updatedCustomer = customerService.editCustomer(customerId, customerDetails);
        System.out.println("Sửa thành công");
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    //    Đổi mật khẩu
    @PutMapping("/customer/changePassword")
    public ResponseEntity<String> changePasswordCustomer(@RequestParam String oldPassword, @RequestParam String newPassword) {
        int accountId = 1; // Lấy accountId tương ứng của khách hàng từ đối tượng `Customer` hoặc từ phiên đăng nhập
        boolean isPasswordChanged = customerService.changePassword(accountId, oldPassword, newPassword);
        if (isPasswordChanged) {
            return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mật khẩu cũ không đúng", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cart/add")
    public ResponseEntity<String> addBookToCart(@RequestParam String bookId) {
        try {
            int cartIdDefault = 1;
            List<Contain> currentBooksInCart = cartService.findAllBookInCart(cartIdDefault);
            for (Contain contain : currentBooksInCart) {
                if (contain.getBook().getBookId().equals(bookId)) {
                    return new ResponseEntity<>("Sách đã có trong giỏ hàng!", HttpStatus.BAD_REQUEST);
                }
            }
            // Nếu sách chưa có trong giỏ hàng, thêm vào giỏ hàng
            Book book = bookService.findBookById(bookId);
            System.out.println(book.getBookName());
            cartService.addBookInToCart(book);
            return new ResponseEntity<>("Book added to cart successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cart")
    public ResponseEntity<List<Contain>> getAllBookInCart(){
        int cartIdDefault = 1;
        List<Contain> list = cartService.findAllBookInCart(cartIdDefault);
        System.out.println("có " + list.size() + " sách trong giỏ hàng !");
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/cart/delete")
    public ResponseEntity<Void> deleteBookInCart(@RequestParam int cartId){
        System.out.println("Deleting book with cartId: " + cartId);
        cartService.deleteBookInCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

