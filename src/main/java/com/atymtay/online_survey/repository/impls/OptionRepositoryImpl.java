package com.atymtay.online_survey.repository.impls;

import com.atymtay.online_survey.entity.Option;
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
public class OptionRepositoryImpl implements GeneralRepo<Option> {

    private final SessionFactory sessionFactory;

    @Autowired
    public OptionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Option> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Option> optionList = session.createQuery("from Option ", Option.class).getResultList();

        return optionList;
    }

    @Override
    public Optional<Option> findById(Long id) {
        return Optional.of(sessionFactory.getCurrentSession()
                .get(Option.class, id));
    }

    @Override
    public void delete(Option object) {
        sessionFactory.getCurrentSession()
                .delete(object);
    }

    @Override
    public Option save(Option object) {
        Session session = sessionFactory.getCurrentSession();

        session.save(object);

        return object;
    }

    @Override
    public Option update(Option new_object) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(new_object);

        return new_object;
    }

    @PreDestroy
    @Override
    public void truncateTable() {
        sessionFactory.getCurrentSession().createSQLQuery("truncate table Option").executeUpdate();
    }
}
