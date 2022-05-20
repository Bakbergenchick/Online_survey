package com.atymtay.online_survey.service.impls;

import com.atymtay.online_survey.entity.Option;
import com.atymtay.online_survey.repository.GeneralRepo;
import com.atymtay.online_survey.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements GeneralService<Option> {

    private final GeneralRepo<Option> optionRepo;

    @Autowired
    public OptionServiceImpl(GeneralRepo<Option> optionRepo) {
        this.optionRepo = optionRepo;
    }

    @Override
    public List<Option> findAll() {
        return optionRepo.getAll();
    }

    @Override

    public Optional<Option> findById(Long id) {
        return optionRepo.findById(id);
    }

    @Override
    public String deleteById(Long id) {
        Optional<Option> optionOptional = optionRepo.findById(id);

        if (optionOptional.isPresent()){
            optionRepo.delete(optionOptional.get());
        } else{
            throw new IllegalArgumentException("Illegal argument");
        }

        return "DELETED!";
    }

    @Override
    public Option save(Option object) {
        optionRepo.save(object);

        return object;
    }

    @Override
    public Option update(Option new_object) {
        optionRepo.update(new_object);

        return new_object;
    }
}
