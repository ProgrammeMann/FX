package com.springapp.mvc.repositories.hibernate;


import com.springapp.mvc.common.hibernateEntity.Categories;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.repositories.GoodRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodRepositoryHibernate implements GoodRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return current Session from Session factory
     */
    private Session curSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * To add the good into GoodInfo table
     *
     * @param goodInfo to be added
     */
    @Override
    public void addGood(GoodInfo goodInfo) {
        curSession().save(goodInfo);
    }


    /**
     * To get good by its Id
     *
     * @param goodId of good to be got
     * @return GoodInfo
     */
    @Override
    public GoodInfo getGoodById(Long goodId) {
        return (GoodInfo) curSession().get(GoodInfo.class, goodId);
    }

    /**
     * To get all the goods from GoodInfo table
     *
     * @return List of goods
     */
    @Override
    public List<GoodInfo> getAll() {
        return curSession().createCriteria(GoodInfo.class).list();
    }

    @Override
    public List<GoodInfo> getGoodsByCategory(Categories category) {
        Criteria crit = curSession().createCriteria(GoodInfo.class);
        crit.add(Restrictions.eq("category", category));
        return crit.list();
    }
}
