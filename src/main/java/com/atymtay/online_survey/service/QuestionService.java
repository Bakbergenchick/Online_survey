package com.atymtay.online_survey.service;

public interface QuestionService {

    void addOptionToQuestion(Long qstn_id, Long opt_id);

    void addTypeToQuestion(Long qstn_id, Long type_id);

}
