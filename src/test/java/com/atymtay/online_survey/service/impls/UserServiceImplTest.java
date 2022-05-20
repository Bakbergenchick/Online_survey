package com.atymtay.online_survey.service.impls;

import com.atymtay.online_survey.entity.*;
import com.atymtay.online_survey.repository.GeneralRepo;
import com.atymtay.online_survey.service.GeneralService;
import com.atymtay.online_survey.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.*;

import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    GeneralRepo<Users> userRepo;
    @Mock
    GeneralRepo<Survey> surveyRepo;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        when(userRepo.getAll())
                .thenReturn(
                        List.of(
                                new Users("Bak", "123"),
                                new Users("Talga", "@3423")));

        List<Users> usersList = userService.findAll();

        Assertions.assertNotNull(usersList);
    }

    @Test
    void testFindById() {
        Users user =
                new Users(1L, "Bak", 15, "dgrdrg@dfrf", "5635453", new ArrayList<>());

        when(userRepo.findById(user.getId()))
                .thenReturn(Optional.of(user));

        Optional<Users> users = userService.findById(1L);

        Assertions.assertEquals(Optional.of(user), users);
    }

    @Test
    void testDeleteById() {
        Users user =
                new Users(1L, "Bak", 15, "dgrdrg@dfrf", "5635453", new ArrayList<>());

        when(userRepo.findById(user.getId()))
                .thenReturn(Optional.of(user));

        userService.deleteById(user.getId());

        verify(userRepo, times(1)).delete(user);

    }

    @Test
    void testSave() {
        Users user =
                new Users(1L, "Bak", 15, "dgrdrg@dfrf", "5635453", new ArrayList<>());

        when( userRepo.save(any(Users.class)))
                .thenReturn( user );

        Users result = userService.save( user );

        Assertions.assertEquals(user, result);
    }

    @Test
    void testUpdate() {
        Users user =
                new Users(1L, "Bak", 15, "dgrdrg@dfrf", "5635453", new ArrayList<>());

        user.setPassword("234234");


        when( userRepo.update(any(Users.class)))
                .thenReturn( user );

        Users result = userService.update( user );

        Assertions.assertEquals(user, result);
    }

    @Test
    void testAddSurveyToUser() {
    }

    @Test
    void testScheduleTheUser() {
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme