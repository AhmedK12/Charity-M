package com.example.helptek.village;

import com.example.helptek.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "villages")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    private User representative;
    private String district;
    private String pinCode;

}
