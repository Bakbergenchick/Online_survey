package com.atymtay.online_survey.service.impls;

import com.atymtay.online_survey.entity.Input_types;
import com.atymtay.online_survey.entity.Option;
import com.atymtay.online_survey.repository.GeneralRepo;
import com.atymtay.online_survey.service.GeneralService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InputTypesServiceImpl implements GeneralService<Input_types> {

    private final GeneralRepo<Input_types> inputTypesRepo;

    @Autowired
    public InputTypesServiceImpl(GeneralRepo<Input_types> inputTypesRepo) {
        this.inputTypesRepo = inputTypesRepo;
    }

    @Override
    public List<Input_types> findAll() {
        return inputTypesRepo.getAll();
    }

    @Cacheable(cacheNames = "type", key="#id")
    @Override
    public Optional<Input_types> findById(Long id) {
        log.info("fetching the data...");

        return inputTypesRepo.findById(id);
    }

    @CacheEvict(cacheNames = "type", key = "#id")
    @Override
    public String deleteById(Long id) {
        log.info("deleting the data...");

        Optional<Input_types> inputTypesOptional = inputTypesRepo.findById(id);

        if (inputTypesOptional.isPresent()){
            inputTypesRepo.delete(inputTypesOptional.get());
        } else{
            throw new IllegalArgumentException("Illegal argument");
        }

        return "DELETED!";
    }

    @Override
    public Input_types save(Input_types object) {
        inputTypesRepo.save(object);

        return object;
    }

    @CachePut(cacheNames = "type", key = "#new_object.id")
    @Override
    public Input_types update(Input_types new_object) {
        log.info("updating the data...");

        inputTypesRepo.update(new_object);

        return new_object;
    }
}
