package com.wmb.wmbApp.entity;

import com.wmb.wmbApp.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = ConstantTable.MENU)
public class Menu {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(name = "price", nullable = false)
    private Long price;
}
