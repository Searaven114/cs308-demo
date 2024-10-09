package com.borau.cs308demo.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    private String productId;

    private String userId;

    private String content;

    private int rating; // 1-10, kesinlikle serverside sınırlama ve validation olmalı bunda

    private boolean approved;  // PatchMapping ile productmanager servicenin eline verilmeli bunun onay/reddi

    private LocalDateTime createdDate; //Tarihi backendde timestamp atacam ama dogru olan kullanıcının istegin icinde gonderip bizim onu kullanmamız mı yoksa bu yaptıgımız mı bilemiyorum (trivial)


}
