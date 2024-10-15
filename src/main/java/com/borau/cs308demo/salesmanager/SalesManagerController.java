package com.borau.cs308demo.salesmanager;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RequestMapping("/api/pm")
@RestController
public class SalesManagerController {

    private final SalesManagerService salesManagerService;

    // The sales managers are responsible for setting the prices of the products.

    // They shall set a discount on the selected items. When the "discount rate" and the "products" are given,
    //     the system automatically sets the new price and notify the users, whose wish list include the discounted product, about the discount.

    // They shall also view all the invoices in a given date range, can print them or save them as “pdf” files.

    // Last but not least, they shall calculate the revenue and loss/profit in between given dates and view a chart of it.


//    @PostMapping(name = "/set-price", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> SetProductPrice ()


}
