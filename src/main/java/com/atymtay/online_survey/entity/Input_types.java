package com.atymtay.online_survey.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Input_types {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "c_generator", sequenceName = "TYPE_SEQUENCE")
    @Column(name = "input_type_id")
    private Long id;

    @Column(name = "input_type",  nullable = false, length = 20)
    private String type;


    public Input_types(String type) {
        this.type = type;
    }
}
