package com.atymtay.online_survey.repository;

import java.util.List;
import java.util.Optional;

public interface GeneralRepo<T> {

    List<T> getAll();

    Optional<T> findById(Long id);

    void delete(T object);

    T save(T object);

    T update(T new_object);

    void truncateTable();


}
