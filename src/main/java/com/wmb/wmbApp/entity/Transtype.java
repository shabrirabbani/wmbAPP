package com.wmb.wmbApp.entity;

import com.wmb.wmbApp.constant.ConstantTable;
import com.wmb.wmbApp.constant.TransType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = ConstantTable.TRANSTYPE)
public class Transtype {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "description", nullable = false)
    private TransType type;
}
