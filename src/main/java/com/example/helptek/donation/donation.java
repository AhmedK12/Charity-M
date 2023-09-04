package com.example.helptek.donation;

import com.example.helptek.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "donation")
public class donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User donner;

    private boolean isSuccessful;

    private String referenceUID;

    private String paymentMethod;

    private String paymentProvider;
 
}
