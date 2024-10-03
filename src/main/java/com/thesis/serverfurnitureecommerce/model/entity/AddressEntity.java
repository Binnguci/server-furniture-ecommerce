package com.thesis.serverfurnitureecommerce.model.entity;

import com.thesis.serverfurnitureecommerce.constant.DatabaseConstant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = DatabaseConstant.ADDRESS)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "address_line")
    String addressLine;
    String ward;
    String district;
    String province;
    String country;
    Boolean isDefault;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;
    @OneToMany(mappedBy = "address")
    Set<OrderEntity> orderEntities;
}
