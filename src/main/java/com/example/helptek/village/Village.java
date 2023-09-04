package com.example.helptek.village;

import com.example.helptek.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "village")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    private VillageRepresentative representative;

    @OneToMany(mappedBy="village")
    private Set<User> users;
    private String district;
    private String pinCode;

}
