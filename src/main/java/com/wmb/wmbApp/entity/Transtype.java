package com.wmb.wmbApp.entity;

import com.wmb.wmbApp.constant.ConstantTable;
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

    @Column(name = "description")
    private String description;
}
