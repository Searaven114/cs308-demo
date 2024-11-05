package com.borau.cs308demo.payment;

import com.borau.cs308demo.util.EncryptionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@Document("cards")
public class CreditCard {

    @Id
    private String id;

    private String cardNumberEncrypted;
    private String cardHolderName;
    private String expirationDate;
    private String cvvEncrypted;
    private String billingAddress;


    public void setCardNumber(String cardNumber) {
        try {

            this.cardNumberEncrypted = EncryptionUtil.encrypt(cardNumber);
            log.info("Encrypted cardNumber: {} to {}", cardNumber, this.cardNumberEncrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting card number", e);
        }
    }

    public String getCardNumber_plain() {
        try {
            return EncryptionUtil.decrypt(this.cardNumberEncrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting card number", e);
        }
    }

    public void setCvv(String cvv) {
        try {
            this.cvvEncrypted = EncryptionUtil.encrypt(cvv);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting CVV", e);
        }
    }

    public String getCvv_plain() {
        try {
            return EncryptionUtil.decrypt(this.cvvEncrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting CVV", e);
        }
    }
}
