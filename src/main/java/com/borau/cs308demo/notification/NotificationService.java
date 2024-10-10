package com.borau.cs308demo.notification;

import com.borau.cs308demo.product.Product;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyUsersAboutPriceChange(Product product) {
        //Mailservisi çağrılacak ! (mail.MailService)

        //Bunun kendisi ise Salesmanager'in price change metodunda çağrılacak yani delegation var (salesmanagercontroller -> notificationservice -> emailservice)
    }

    public void notifyUsersAboutDiscount(Product product) {
        //Mailservisi çağrılacak ! (mail.MailService)
    }
}