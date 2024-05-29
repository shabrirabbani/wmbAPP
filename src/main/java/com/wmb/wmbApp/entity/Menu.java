package com.wmb.wmbApp.entity;

import com.wmb.wmbApp.constant.ConstantTable;
import jakarta.persistence.*;

@Entity
@Table (name = ConstantTable.MENU)
public class Menu {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id;

    @Column(name = "menu_name", nullable = false)
    private String name;

    @Column(name = "price")
    private long price;
}
