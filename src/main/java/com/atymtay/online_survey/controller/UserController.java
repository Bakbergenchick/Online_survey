package com.atymtay.online_survey.controller;

import com.atymtay.online_survey.entity.Users;
import com.atymtay.online_survey.service.GeneralService;
import com.atymtay.online_survey.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.xml.transform.Result;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final GeneralService<Users> usersGeneralService;

    private final UserService userService;

    @PostConstruct
    public void init(){
//        Users user1 = new Users("Bakbergen", "789");
        Users user1 = new Users("Bakbergen", 20, "atymtaevbak@bk.ru", "1244rferf");
        Users user2 = new Users("Talgat", 22, "talgat02@bk.ru", "1234rferf");
//        Users user2 = new Users("Talgat", "1234");

        usersGeneralService.save(user1);

        usersGeneralService.save(user2);
    }


    @GetMapping("/user")
    @ApiOperation(
            value = "Get all Users",
            response = Users.class,
            responseContainer = "List")
    public ResponseEntity<?> getAllUsers(){

        return new ResponseEntity<>(usersGeneralService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    @ApiOperation(
            value = "Get User By Id",
            notes = "Fetching User by given Id from DB",
            response = Users.class
    )
    public ResponseEntity<?> getUserById(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(usersGeneralService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/user")
    @ApiOperation(
            value = "Adding User",
            notes = "adds some user to DB",
            response = Users.class
    )
    public ResponseEntity<?> addUser(
            @Valid @RequestBody  Users user,
            BindingResult bindingResult
    ){



//        if(bindingResult.hasErrors()){
//            bindingResult
//                    .getFieldErrors()
//                    .forEach( fieldError ->
//                            log.info(fieldError.getField() + ": " + fieldError.getDefaultMessage()));
//
//            return ResponseEntity.badRequest().body("Error!");
//        }

        Users user1 = usersGeneralService.save(user);

        return ResponseEntity.ok(user1);

    }


    @PostMapping("/user/{user_id}/{survey_id}")
    @ApiOperation(
            value = "Add Survey To User",
            notes = "adding survey by given Id to User, " +
                    "It's operation executed when user pass survey"

    )
    public ResponseEntity<?> addSurveyToUser(
            @PathVariable Long user_id,
            @PathVariable Long survey_id
    ){
        userService.addSurveyToUser(user_id, survey_id);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }



    @PutMapping("/user")
    @ApiOperation(
            value = "Update User",
            notes = "changes some parameters of user by Id",
            response = Users.class
    )
    public ResponseEntity<?> updateUser(
            @RequestBody Users user
    ){
        Users upUser = usersGeneralService.update(user);

        return new ResponseEntity<>(upUser, HttpStatus.CREATED);

    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(
            value = "Delete User",
            notes = "removing user by given id"
    )
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id
    ){
        usersGeneralService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
