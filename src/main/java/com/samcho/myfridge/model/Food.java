package com.samcho.myfridge.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String foodName;

    @Enumerated(EnumType.STRING)
    private Type foodType;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;

    public enum Type {
        LEFTOVER, INGREDIENTS, DRINKS, SNACKS
    }
}
