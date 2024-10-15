package com.borau.cs308demo.notification;

import com.borau.cs308demo.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class NotificationService {

    //private final EmailService emailService;

    public void notifyUsersAboutPriceChange(Product product) {
        //Mailservisi çağrılacak ! (mail.MailService)

        // Bunun kendisi ise Salesmanager'in price change metodunda çağrılacak yani delegation var
        // (salesmanagercontroller -> productService ->  notificationservice -> emailservice)
    }

    public void notifyUsersAboutDiscount(Product product, int discountRate) {
        //Mailservisi çağrılacak ! (mail.MailService)
    }
}