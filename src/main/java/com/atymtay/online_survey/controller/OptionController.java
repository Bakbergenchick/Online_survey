package com.atymtay.online_survey.controller;

import com.atymtay.online_survey.entity.Option;
import com.atymtay.online_survey.entity.Question;
import com.atymtay.online_survey.service.GeneralService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OptionController {

    private final GeneralService<Option> optionService;


    @GetMapping("/option")
    @ApiOperation(
            value = "Get all Options",
            response = Option.class,
            responseContainer = "List")
    public ResponseEntity<?> getAllOption(){

        return new ResponseEntity<>(optionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/option/{id}")
    @ApiOperation(
            value = "Get Option By Id",
            notes = "Fetching Option by given Id from DB",
            response = Option.class
    )
    public ResponseEntity<?> getOptionById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(optionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/option")
    @ApiOperation(
            value = "Adding Option",
            notes = "adds some Option to DB",
            response = Option.class
    )
    public ResponseEntity<?> addOption(
            @RequestBody Option option
    ){
        Option option1 = optionService.save(option);

        return new ResponseEntity<>(option1, HttpStatus.CREATED);

    }

    @PutMapping("/option")
    @ApiOperation(
            value = "Update Option",
            notes = "changes some parameters of Option by its Id",
            response = Option.class
    )
    public ResponseEntity<?> updateOption(
            @RequestBody Option option
    ){
        Option option1 = optionService.update(option);

        return new ResponseEntity<>(option1, HttpStatus.CREATED);

    }
}
