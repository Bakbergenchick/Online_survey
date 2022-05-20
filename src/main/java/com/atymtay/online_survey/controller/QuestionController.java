package com.atymtay.online_survey.controller;

import com.atymtay.online_survey.entity.Question;
import com.atymtay.online_survey.entity.Survey;
import com.atymtay.online_survey.service.GeneralService;
import com.atymtay.online_survey.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final GeneralService<Question> questionGeneralService;

    private final QuestionService questionService;


    @GetMapping("/question")
    @ApiOperation(
            value = "Get all Questions",
            response = Question.class,
            responseContainer = "List")
    public ResponseEntity<?> getAllQuestions(){

        return new ResponseEntity<>(questionGeneralService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/question/{id}")
    @ApiOperation(
            value = "Get Question By Id",
            notes = "Fetching Question by given Id from DB",
            response = Question.class
    )
    public ResponseEntity<?> getQuestionById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(questionGeneralService.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/question", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Adding Question",
            notes = "adds some Question to DB",
            response = Question.class
    )
    public ResponseEntity<?> addQuestion(
            @RequestBody Question question
    ){
        Question question1 = questionGeneralService.save(question);

        return new ResponseEntity<>(question1, HttpStatus.CREATED);

    }

    @PostMapping("/question/addOption/{qstn_id}/{optn_id}")
    @ApiOperation(
            value = "Add Option to Question",
            notes = "adding option to question by given their id"
    )
    public ResponseEntity<?> addOptionToQuestion(
            @PathVariable Long qstn_id,
            @PathVariable Long optn_id
    ){
        questionService.addOptionToQuestion(qstn_id, optn_id);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/question/addType/{qstn_id}/{tp_id}")
    @ApiOperation(
            value = "Add Type to Question",
            notes = "adding type of question like closed,open, etc. to question by their given id"
    )
    public ResponseEntity<?> addTypeToQuestion(
            @PathVariable Long qstn_id,
            @PathVariable Long tp_id
    ){
        questionService.addTypeToQuestion(qstn_id, tp_id);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/question")
    @ApiOperation(
            value = "Update Question",
            notes = "changes some parameters of Question by its Id",
            response = Question.class
    )
    public ResponseEntity<?> updateQuestion(
            @RequestBody Question question
    ){
        Question question1 = questionGeneralService.update(question);

        return new ResponseEntity<>(question1, HttpStatus.CREATED);

    }

}
