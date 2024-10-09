package com.borau.cs308demo.invoice;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {
        Invoice invoice = invoiceService.createInvoice(invoiceRequest.getUserId(), invoiceRequest.getProductIds());
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Invoice>> getInvoicesByUser(@PathVariable String userId) {
        List<Invoice> invoices = invoiceService.getInvoicesByUserId(userId);
        return ResponseEntity.ok(invoices);
    }
}

class InvoiceRequest {
    @NotEmpty(message = "User ID is required")
    private String userId;
    @NotEmpty(message = "Product IDs are required")
    private List<String> productIds;

    // getters and setters
}*/
