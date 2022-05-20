package com.atymtay.online_survey.service.impls;

import com.atymtay.online_survey.entity.Input_types;
import com.atymtay.online_survey.entity.Option;
import com.atymtay.online_survey.entity.Question;
import com.atymtay.online_survey.repository.GeneralRepo;
import com.atymtay.online_survey.service.GeneralService;
import com.atymtay.online_survey.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements GeneralService<Question>, QuestionService {

    private final GeneralRepo<Question> questionRepo;

    private final GeneralRepo<Option> optionRepo;

    private final GeneralRepo<Input_types> inputTypesRepo;

    @Autowired
    public QuestionServiceImpl(GeneralRepo<Question> questionRepo, GeneralRepo<Option> optionRepo, GeneralRepo<Input_types> inputTypesRepo) {
        this.questionRepo = questionRepo;
        this.optionRepo = optionRepo;
        this.inputTypesRepo = inputTypesRepo;
    }

    @Override
    public List<Question> findAll() {
        return questionRepo.getAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepo.findById(id);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Question> questionOptional = questionRepo.findById(id);

        if(questionOptional.isPresent()){
            questionRepo.delete(questionOptional.get());
        }
        else{
            throw new IllegalArgumentException("Illegal argument");
        }

        return "Deleted!";
    }

    @Override
    public Question save(Question object) {

        questionRepo.save(object);

        return object;
    }

    @Override
    public Question update(Question new_object) {

        questionRepo.update(new_object);

        return new_object;
    }

    @Override
    public void addOptionToQuestion(Long qstn_id, Long opt_id) {
        Question question = questionRepo.findById(qstn_id).get();

        Option option = optionRepo.findById(opt_id).get();

        List<Option> optionList = question.getOptionList();

        optionList.add(option);

        option.setQuestion(question);

        questionRepo.save(question);

        optionRepo.save(option);
    }

    @Override
    public void addTypeToQuestion(Long qstn_id, Long type_id) {
        Question question = questionRepo.findById(qstn_id).get();

        Input_types inputType = inputTypesRepo.findById(type_id).get();


        question.setInput_type(inputType);

        questionRepo.save(question);

    }


}
