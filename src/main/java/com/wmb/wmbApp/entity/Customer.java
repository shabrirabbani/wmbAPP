package com.wmb.wmbApp.entity;

import com.wmb.wmbApp.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.CUSTOMER)
@Builder
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column (name = "customer_name")
    private String name;

    @Column(name = "mobile_phone_no")
    private String mobilePhone;

    @Column(name = "is_member")
    private Boolean isMember;

    @OneToOne
    @JoinColumn(name = "user_account_id", unique = true)
    private UserAccount userAccount;
}
