package com.epam.esm.dao.entity;

import com.epam.esm.model.dto.RoleUser;
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
@Table(name = "user_gift")
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
    RoleUser roleUser;
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    List<Purchase> listPurchase;

    public User(long userId, String userName, String userSurname, List<Purchase> listPurchase) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.listPurchase = listPurchase;
    }
}
