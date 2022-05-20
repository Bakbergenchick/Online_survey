package com.atymtay.online_survey.repository.impls;

import com.atymtay.online_survey.entity.Users;
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
public class UserRepositoryImpl implements GeneralRepo<Users> {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Users> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Users> liveCourse = session.createQuery("from Users ", Users.class)
                .getResultList();

        return liveCourse;
    }

    @Override
    public Optional<Users> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();

        return Optional.of(session.get(Users.class, id));
    }

    @Override
    public void delete(Users object) {
        Session session = sessionFactory.getCurrentSession();

        session.delete(object);
    }

    @Override
    public Users save(Users object) {
        Session session = sessionFactory.getCurrentSession();

        session.save(object);

        return object;
    }

    @Override
    public Users update(Users new_object) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(new_object);

        return new_object;
    }

    @PreDestroy
    @Override
    public void truncateTable() {
        Session session = sessionFactory.openSession();

        session.createSQLQuery("truncate table users").executeUpdate();

        session.close();

    }
}
