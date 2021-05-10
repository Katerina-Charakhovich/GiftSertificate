package com.epam.esm.dao.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    Long userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "user_surname")
    String userSurname;
    @Column(name = "login")
    String login;
    @Column(name = "password")
    String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    List<Purchase> listPurchase;
}
