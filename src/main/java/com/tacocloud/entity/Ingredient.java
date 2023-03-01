package com.tacocloud.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Ingredient")
@AllArgsConstructor
@RequiredArgsConstructor
public class Ingredient{
//??? page 116
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}


