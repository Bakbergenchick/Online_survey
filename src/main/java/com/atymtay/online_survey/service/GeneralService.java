package com.atymtay.online_survey.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    String deleteById(Long id);

    T save(T object);

    T update(T new_object);

}
