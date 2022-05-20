package com.atymtay.online_survey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @Column(name = "choice_name", columnDefinition = "varchar(1000)")
    private String name;

    @Column(columnDefinition = "boolean default false")
    private Boolean isChoosen;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "qstn_id")
    @JsonBackReference("option_qstn")
    private Question question;

    public Option(String name, Boolean isChoosen) {
        this.name = name;
        this.isChoosen = isChoosen;
    }
}
