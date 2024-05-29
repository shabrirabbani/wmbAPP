package com.wmb.wmbApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wmb.wmbApp.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = ConstantTable.BILL)
@Builder
public class Bill {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Temporal(TemporalType.DATE)
    @Column(name = "trans_date", updatable = false)
    private Date transDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables tables;

    @ManyToOne
    @JoinColumn(name = "trans_type_id")
    private Transtype transtype;

    @OneToMany(mappedBy = "bill")
    @JsonManagedReference
    private List<BillDetail> billDetails;
}
