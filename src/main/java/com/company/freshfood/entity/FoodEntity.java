package com.company.freshfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "M_FOOD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FOOD_ID")
    private Long foodId;

    @Column(name = "SELLER_ID")
    private Long sellerId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Column(name = "FOOD_NAME")
    private String foodName;
    
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE", precision = 10, scale = 0)
    private BigDecimal price;

    @Column(name = "DISCOUNT_PRICE", precision = 10, scale = 0)
    private BigDecimal discountPrice;

    @Column(name = "QUANTITY")
    private Integer quantity;

    /**
     * ACTIVE / OUT_OF_STOCK / INACTIVE
     */
    @Column(name = "STATUS", length = 20)
    private String status;

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
        this.createTimestamp = LocalDateTime.now();
        this.updateTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTimestamp = LocalDateTime.now();
    }
}
