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
@Table (name = ConstantTable.TABLES)
public class Tables {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column (name = "table_name")
    private String name;
}
