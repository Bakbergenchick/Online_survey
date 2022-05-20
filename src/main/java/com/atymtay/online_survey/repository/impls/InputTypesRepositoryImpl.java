package com.atymtay.online_survey.repository.impls;

import com.atymtay.online_survey.entity.Input_types;
import com.atymtay.online_survey.repository.GeneralRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class InputTypesRepositoryImpl implements GeneralRepo<Input_types> {

    private SessionFactory sessionFactory;

    @Autowired
    public InputTypesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Input_types> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Input_types> inputTypesList = session
                .createQuery("from Input_types ", Input_types.class)
                .getResultList();

        return inputTypesList;
    }

    @Override
    public Optional<Input_types> findById(Long id) {
        return Optional.of(sessionFactory.getCurrentSession()
                .get(Input_types.class, id));
    }

    @Override
    public void delete(Input_types object) {
        sessionFactory.getCurrentSession()
                .delete(object);
    }

    @Override
    public Input_types save(Input_types object) {
        Session session = sessionFactory.getCurrentSession();

        session.save(object);

        return object;
    }

    @Override
    public Input_types update(Input_types new_object) {
        Session session = sessionFactory.getCurrentSession();

        session.save(new_object);

        return new_object;
    }

    @PreDestroy
    @Override
    public void truncateTable() {
        sessionFactory.getCurrentSession().createSQLQuery("truncate table input_types").executeUpdate();
    }

//    @PostConstruct
//    @Transactional
//    public void batchAdd(){
//        Session currentSession = sessionFactory.getCurrentSession();
//
//        for (int i = 1 ; i <= 100; i++) {
//            Input_types input_type = new Input_types("Closed");
//
//            currentSession.save(input_type);
//
//            if (i % 5 == 0) {
//                currentSession.flush();
//                currentSession.clear();
//            }
//        }
//    }

}
