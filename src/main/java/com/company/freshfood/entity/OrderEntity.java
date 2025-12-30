package com.company.freshfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "TOTAL_AMOUNT", precision = 10, scale = 0)
    private BigDecimal totalAmount;

    /**
     * NEW / PAID / SHIPPED / COMPLETED / CANCELLED
     */
    @Column(name = "ORDER_STATUS", length = 20)
    private String orderStatus;

    /**
     * Thời điểm đặt hàng (business time)
     */
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

    @Column(name = "DELETED_FLAG", nullable = false, length = 1)
    @Builder.Default
    private String deletedFlag = "0";

    @Column(name = "UNUSABLE_FLAG", nullable = false, length = 1)
    @Builder.Default
    private String unusableFlag = "0";

    /**
     * Optimistic Lock
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
     * Auto timestamp
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.createTimestamp = LocalDateTime.now();
        this.updateTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTimestamp = LocalDateTime.now();
    }
}
