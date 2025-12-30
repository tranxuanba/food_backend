package com.company.freshfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "M_CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long cartId;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

    @Column(name = "DELETED_FLAG", nullable = false, length = 1)
    @Builder.Default
    private String deletedFlag = "0";

    @Column(name = "UNUSABLE_FLAG", nullable = false, length = 1)
    @Builder.Default
    private String unusableFlag = "0";

    /**
     * Optimistic Lock (chuẩn hệ thống doanh nghiệp)
     */
    @Version
    @Column(name = "UPDATE_COUNT", nullable = false)
    @Builder.Default
    private Integer updateCount = 1;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;

    @Column(name = "CREATE_USER_NAME")
    private String createUserName;

    @Column(name = "CREATE_TIMESTAMP")
    private LocalDateTime createTimestamp;

    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;

    @Column(name = "UPDATE_USER_NAME")
    private String updateUserName;

    @Column(name = "UPDATE_TIMESTAMP")
    private LocalDateTime updateTimestamp;

    /**
     * Tự động set timestamp
     */
    @PrePersist
    public void prePersist() {
        this.createTimestamp = LocalDateTime.now();
        this.updateTimestamp = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTimestamp = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
