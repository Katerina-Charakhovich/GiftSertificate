package com.epam.esm.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "purchase")
public class Purchase extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    long purchaseId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "purchase_price")
    BigDecimal price;
    @Column(name = "create_date")
    LocalDateTime createDate;
    @Column(name = "last_update_date")
    LocalDateTime lastUpdateTime;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "purchase_certificate", joinColumns = {
            @JoinColumn(name = "purchase_id")},
            inverseJoinColumns = {@JoinColumn(name = "certificate_id")})
    List<GiftCertificate> listGiftCertificate;

    @PrePersist
    private void prePersistFunction() {
        this.createDate = LocalDateTime.now();
        this.lastUpdateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdateFunction() {
        this.lastUpdateTime = LocalDateTime.now();
    }
}
