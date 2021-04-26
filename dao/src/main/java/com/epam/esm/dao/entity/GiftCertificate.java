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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "certificate")
public class GiftCertificate extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private long id;
    @Column(name = "certificate_name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "duration")
    private int duration;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "last_update_date")
    LocalDateTime lastUpdateDate;
    @Column(name = "create_date")
    LocalDateTime createDate;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "certificate_tag", joinColumns = {
            @JoinColumn(name = "certificate_id")},
            inverseJoinColumns = {@JoinColumn(name = "id_tag")})
    List<Tag> listTag;

    @PrePersist
    private void prePersistFunction() {
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdateFunction() {
        this.lastUpdateDate = LocalDateTime.now();
    }
}
