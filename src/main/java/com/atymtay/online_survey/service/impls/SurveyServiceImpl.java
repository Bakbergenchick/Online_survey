package com.atymtay.online_survey.service.impls;

import com.atymtay.online_survey.entity.Question;
import com.atymtay.online_survey.entity.Survey;
import com.atymtay.online_survey.repository.GeneralRepo;
import com.atymtay.online_survey.service.GeneralService;
import com.atymtay.online_survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyServiceImpl implements GeneralService<Survey>, SurveyService {

    private final GeneralRepo<Survey> surveyRepo;

    private final GeneralRepo<Question> questionRepo;

    @Autowired
    public SurveyServiceImpl(GeneralRepo<Survey> surveyRepo, GeneralRepo<Question> questionRepo) {
        this.surveyRepo = surveyRepo;
        this.questionRepo = questionRepo;
    }

    @Override
    public List<Survey> findAll() {
        return surveyRepo.getAll();
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return surveyRepo.findById(id);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Survey> surveyOptional = surveyRepo.findById(id);

        if (surveyOptional.isPresent()){
            surveyRepo.delete(surveyOptional.get());
        }
        else {
            throw new IllegalArgumentException("Illegal argument");
        }


        return "Deleted!";
    }

    @Override
    public Survey save(Survey object) {
        surveyRepo.save(object);

        return object;
    }

    @Override
    public Survey update(Survey new_object) {
        surveyRepo.update(new_object);

        return new_object;
    }

    @Override
    public void addQuestionToSurvey(Long qst_id, Long survey_id) {
        Survey survey = surveyRepo.findById(survey_id).get();

        Question question = questionRepo.findById(qst_id).get();

        List<Question> questionList = survey.getQuestions();

        questionList.add(question);

        question.setSurvey(survey);

        questionRepo.save(question);

        surveyRepo.save(survey);
    }
}
