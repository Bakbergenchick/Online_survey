package com.atymtay.online_survey.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotEmpty(message = "Doesn't be empty")
    private String login;

    @Min(value = 12, message = "Age must be greater than 12")
    private int age;

    @Email(message = "Invalid email")
    private String email;

    @Column(length = 30)
    private String password;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    },fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_survey_join",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="survey_id")
    )
    @JsonIgnoreProperties("usersList")
    private List<Survey> surveyList;


    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Users(String login, int age, String email, String password) {
        this.login = login;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}
