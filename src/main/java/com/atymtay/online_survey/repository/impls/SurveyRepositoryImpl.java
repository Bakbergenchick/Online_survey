package com.atymtay.online_survey.repository.impls;

import com.atymtay.online_survey.entity.Survey;
import com.atymtay.online_survey.repository.GeneralRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class SurveyRepositoryImpl implements GeneralRepo<Survey> {

    private final SessionFactory sessionFactory;

    @Autowired
    public SurveyRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Survey> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Survey> surveyList = session.createQuery("from Survey", Survey.class).getResultList();

        return surveyList;
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return Optional.of(sessionFactory.getCurrentSession().get(Survey.class, id));
    }

    @Override
    public void delete(Survey object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    @Override
    public Survey save(Survey object) {
        Session session = sessionFactory.getCurrentSession();

        session.save(object);

        return object;
    }

    @Override
    public Survey update(Survey new_object) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(new_object);

        return new_object;
    }

    @PreDestroy
    @Override
    public void truncateTable() {
        sessionFactory.getCurrentSession().createSQLQuery("truncate table Survey").executeUpdate();
    }
}
