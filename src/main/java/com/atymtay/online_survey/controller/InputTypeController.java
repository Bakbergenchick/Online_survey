package com.atymtay.online_survey.controller;

import com.atymtay.online_survey.entity.Input_types;
import com.atymtay.online_survey.entity.Option;
import com.atymtay.online_survey.service.GeneralService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InputTypeController {

    private final GeneralService<Input_types> inputTypesService;

    @GetMapping("/type")
    @ApiOperation(
            value = "Get all Type of question",
            response = Input_types.class,
            responseContainer = "List")
    public ResponseEntity<?> getAllTypes(){

        return new ResponseEntity<>(inputTypesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/type/{id}")
    @ApiOperation(
            value = "Get Type of question By Id",
            notes = "Fetching Type of question by given Id from DB",
            response = Input_types.class
    )
    public ResponseEntity<?> getTypeById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(inputTypesService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/type")
    @ApiOperation(
            value = "Adding Type of question",
            notes = "adds some Type of question to DB",
            response = Input_types.class
    )
    public ResponseEntity<?> addInputType(
            @RequestBody Input_types inputType
    ){
        Input_types inputType1 = inputTypesService.save(inputType);

        return new ResponseEntity<>(inputType1, HttpStatus.CREATED);

    }

    @PutMapping("/type")
    @ApiOperation(
            value = "Update Input_types",
            notes = "changes some parameters of Input_types by its Id",
            response = Input_types.class
    )
    public ResponseEntity<?> updateInputType(
            @RequestBody Input_types inputType
    ){
        Input_types inputType1 = inputTypesService.update(inputType);

        return new ResponseEntity<>(inputType1, HttpStatus.CREATED);

    }


    @DeleteMapping("/type/{id}")
    @ApiOperation(
            value = "Delete Input_types",
            notes = "removing Input_types by given id"
    )
    public ResponseEntity<?> deleteInputType(
            @PathVariable Long id
    ){

        inputTypesService.deleteById(id);

        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}
