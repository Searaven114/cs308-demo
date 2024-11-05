package com.borau.cs308demo.invoice;

import com.borau.cs308demo.product.Product;
import com.borau.cs308demo.product.ProductService;
import com.borau.cs308demo.user.User;
import com.borau.cs308demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

//Chatgpt mahsulü

//    @Autowired
//    private InvoiceRepository invoiceRepository;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private UserService userService;
//
//    public Invoice createInvoice(String userId, List<String> productIds) {
//
//        // Fetch user and product details
//        User user = userService.getUserById(userId);
//
//        List<Product> products = productService.getProductsByIds(productIds);
//
//        // Calculate total amount, discounts, and taxes
//        double totalAmount = products.stream().mapToDouble(Product::getBasePrice).sum();
//        double discount = calculateDiscount(products);
//        double tax = calculateTax(totalAmount);
//
//        // Create and save invoice
//        Invoice invoice = new Invoice();
//
//        invoice.setUserId(userId);
//        invoice.setUserDetails(user.getName() + ", " + user.getHomeAddress());
//        invoice.setProductIds(productIds);
//        invoice.setTotalAmount(totalAmount - discount + tax);
//        invoice.setDiscount(discount);
//        invoice.setTax(tax);
//        invoice.setInvoiceDate(new Date());
//
//        return invoiceRepository.save(invoice);
//    }
//
//    public Invoice getInvoiceById(String invoiceId) {
//        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));
//    }
//
//    public List<Invoice> getInvoicesByUserId(String userId) {
//        return invoiceRepository.findByUserId(userId);
//    }
//
//    private double calculateDiscount(List<Product> products) {
//        // Implement discount calculation logic
//        return 0;
//    }
//
//    private double calculateTax(double amount) {
//        // Implement tax calculation logic
//        return amount * 0.1; // Example: 10% tax
//    }
}