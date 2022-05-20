package com.atymtay.online_survey.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qt_id", nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "Label must be filled")
    @Size(min = 8, max=100, message = "Label size between 8 and 100")
    private String label;

    @Column(columnDefinition = "boolean default true")
    private Boolean isOpen;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "survey_id")
    @JsonBackReference("question_survey")
    private Survey survey;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "input_type_id")
    private Input_types input_type;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    },
            mappedBy = "question")
    @JsonManagedReference("option_qstn")
    private List<Option> optionList;

    public Question(String label, Boolean isOpen) {
        this.label = label;
        this.isOpen = isOpen;
    }
}
