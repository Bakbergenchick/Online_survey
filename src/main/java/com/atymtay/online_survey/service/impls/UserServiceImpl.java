package com.atymtay.online_survey.service.impls;

import com.atymtay.online_survey.entity.Survey;
import com.atymtay.online_survey.entity.Users;
import com.atymtay.online_survey.exception.CustomErrorException;
import com.atymtay.online_survey.repository.GeneralRepo;
import com.atymtay.online_survey.service.GeneralService;
import com.atymtay.online_survey.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements GeneralService<Users>, UserService {

    private final GeneralRepo<Users> userRepo;

    private final GeneralRepo<Survey> surveyRepo;

    @Autowired
    public UserServiceImpl(GeneralRepo<Users> userRepo, GeneralRepo<Survey> surveyRepo) {
        this.userRepo = userRepo;
        this.surveyRepo = surveyRepo;
    }

    @Override
    public List<Users> findAll() {
        return userRepo.getAll();
    }

    @Override
    public Optional<Users> findById(Long id) {

        Users user = null;
        try {
            user = userRepo.findById(id).get();

        }catch (NullPointerException e){
            throw new CustomErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "User with id=" + id + " not found",
                    (Long) id
            );
        }


        return Optional.of(user);

    }

    @Override
    public String deleteById(Long id) {


        Optional<Users> usersOptional = null;
        try{
            usersOptional = userRepo.findById(id);
            userRepo.delete(usersOptional.get());
        } catch (Exception e){
            throw new CustomErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "User with id=" + id + " not found",
                    (Long) id
            );
        }


        return "Deleted!";
    }

    @Override
    public Users save(Users object) {
        userRepo.save(object);

        return object;
    }

    @Override
    public Users update(Users new_object) {
        userRepo.update(new_object);

        return new_object;
    }

    @Override
    public void addSurveyToUser(Long user_id, Long survey_id) {


        Users users = userRepo.findById(user_id).get();

        Survey survey = surveyRepo.findById(survey_id).get();

        List<Survey> surveyList = users.getSurveyList();

        surveyList.add(survey);

        userRepo.save(users);
    }

    @Override
//    @Scheduled(cron = "*/5 * * * * ?")
//    @Scheduled(initialDelay = 1000L, fixedDelay = 2000L)
    public void scheduleTheUser() {
        List<Users> users = userRepo.getAll();

        log.info("Scheduling user...");

        users.forEach( u -> {
                    if(u.getPassword().length() < 4) {
                        u.setPassword("12345");
                    }

                    userRepo.update(u);
                });


    }
}
