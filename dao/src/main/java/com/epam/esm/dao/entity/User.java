package com.epam.esm.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
public class User extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    long userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "user_surname")
    String userSurname;
    @OneToMany(mappedBy = "user", orphanRemoval = true,fetch = FetchType.LAZY)
    List<Purchase> listPurchase;
}
