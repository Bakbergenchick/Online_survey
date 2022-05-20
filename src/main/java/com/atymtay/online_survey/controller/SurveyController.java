package com.atymtay.online_survey.controller;

import com.atymtay.online_survey.entity.Survey;
import com.atymtay.online_survey.entity.Users;
import com.atymtay.online_survey.service.GeneralService;
import com.atymtay.online_survey.service.SurveyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final GeneralService<Survey> surveyGeneralService;

    private final SurveyService surveyService;

    @PostConstruct
    public void init() throws ParseException {
        Survey survey1 = new Survey("How to safe ecology?", "rfudhr",
                new SimpleDateFormat("yyyy/MM/dd")
                .parse("2018/11/23"));

        Survey survey2 = new Survey("Need of shopping and entertainment center", "rfudhr",
                new SimpleDateFormat("yyyy/MM/dd")
                        .parse("2020/06/12"));

        surveyGeneralService.save(survey1);
        surveyGeneralService.save(survey2);

    }

    @GetMapping("/survey")
    @ApiOperation(
            value = "Get all Surveys",
            response = Survey.class,
            responseContainer = "List")
    public ResponseEntity<?> getAllSurvey(){

        return new ResponseEntity<>(surveyGeneralService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/survey/{id}")
    @ApiOperation(
            value = "Get Survey By Id",
            notes = "Fetching Survey by given Id from DB",
            response = Survey.class
    )
    public ResponseEntity<?> getSurveyById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(surveyGeneralService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/survey")
    @ApiOperation(
            value = "Adding Survey",
            notes = "adds some Survey to DB",
            response = Survey.class
    )
    public ResponseEntity<?> addSurvey(
            @RequestBody Survey survey
    ){
        Survey user1 = surveyGeneralService.save(survey);

        return new ResponseEntity<>(user1, HttpStatus.CREATED);

    }

    @PostMapping("/survey/{survey_id}/{qst_id}")
    @ApiOperation(
            value = "Add Question To Survey",
            notes = "adding question by given id to survey, " +
                    "It's operation during the survey when user choose question"
    )
    public ResponseEntity<?> addQuestionToSurvey(
            @PathVariable Long qst_id,
            @PathVariable Long survey_id
    ){
        surveyService.addQuestionToSurvey(qst_id, survey_id);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/survey")
    @ApiOperation(
            value = "Update Survey",
            notes = "changes some parameters of survey by its Id",
            response = Survey.class
    )
    public ResponseEntity<?> updateSurvey(
            @RequestBody Survey user
    ){
        Survey upUser = surveyGeneralService.update(user);

        return new ResponseEntity<>(upUser, HttpStatus.CREATED);

    }


}
