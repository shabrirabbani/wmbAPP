package com.wmb.wmbApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wmb.wmbApp.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = ConstantTable.BILL_DETAIL)
@Builder
public class BillDetail {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    @JsonBackReference
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "menu_id", updatable = false, nullable = false)
    private Menu menuId;

    @Column(name = "qty", nullable = false)
    private int qty;
}
