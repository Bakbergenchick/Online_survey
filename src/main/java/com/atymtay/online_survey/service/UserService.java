package com.atymtay.online_survey.service;

public interface UserService {

    void addSurveyToUser(Long user_id, Long survey_id);

    void scheduleTheUser();
}
