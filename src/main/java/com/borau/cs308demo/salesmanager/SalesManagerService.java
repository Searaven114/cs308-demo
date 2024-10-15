package com.borau.cs308demo.salesmanager;

import com.borau.cs308demo.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class SalesManagerService {

    private final ProductService productService;

    // The sales managers are responsible for setting the prices of the products.

    // They shall set a discount on the selected items. When the discount rate and the products are given,
    //     the system automatically sets the new price and notify the users, whose wish list include the discounted product, about the discount.

    // They shall also view all the invoices in a given date range, can print them or save them as “pdf” files.

    // Last but not least, they shall calculate the revenue and loss/profit in between given dates and view a chart of it.







}
