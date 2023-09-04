package com.example.helptek.village;

import com.example.helptek.user.User;
import com.example.helptek.village.Village;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "village_representative")
public class VillageRepresentative {
    @OneToOne()
    private User user;

    private Long village_id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
