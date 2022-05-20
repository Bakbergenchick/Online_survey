package com.atymtay.online_survey.repository.impls;

import com.atymtay.online_survey.entity.Question;
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
public class QuestionRepositoryImpl implements GeneralRepo<Question> {

    private final SessionFactory sessionFactory;

    @Autowired
    public QuestionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Question> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Question> questionList = session.createQuery("from Question ", Question.class)
                .getResultList();

        return questionList;
    }

    @Override
    public Optional<Question> findById(Long id) {
        return Optional.of(sessionFactory
                .getCurrentSession()
                .get(Question.class, id));
    }

    @Override
    public void delete(Question object) {
        sessionFactory.getCurrentSession()
                .delete(object);
    }

    @Override
    public Question save(Question object) {
        Session session = sessionFactory.getCurrentSession();

        session.save(object);

        return object;
    }

    @Override
    public Question update(Question new_object) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(new_object);

        return new_object;
    }

    @PreDestroy
    @Override
    public void truncateTable() {
        sessionFactory.getCurrentSession().createSQLQuery("truncate table Question").executeUpdate();
    }
}
