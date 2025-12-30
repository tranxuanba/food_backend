package com.company.freshfood.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "M_ROLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * ADMIN / SELLER / CUSTOMER
     */
    @Column(name = "ROLE_CODE", length = 20)
    private String roleCode;

    @Column(name = "ROLE_NAME")
    private String roleName;

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
