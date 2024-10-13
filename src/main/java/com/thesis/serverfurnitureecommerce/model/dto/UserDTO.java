package com.thesis.serverfurnitureecommerce.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Long id;
    String username;
    String fullName;
    String email;
    String phone;
    LocalDateTime otpExpired;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    RoleDTO role;
    CartDTO cart;
    Set<OrderDTO> orders;
    Set<WishlistDTO> wishlist;
    Set<AddressDTO> address;
}