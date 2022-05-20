package com.atymtay.online_survey.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    @Id
    @Column(name = "survey_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "surv_name")
    private String name;

    @Column(name = "surv_desc")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "surv_updated")
    private Date updated;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    },fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_survey_join",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    @JsonIgnoreProperties("surveyList")
    private List<Users> usersList;


    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, mappedBy = "survey")
    @JsonManagedReference("question_survey")
    private List<Question> questions;


    public Survey(String name, String description, Date updated) {
        this.name = name;
        this.description = description;
        this.updated = updated;
    }
}
