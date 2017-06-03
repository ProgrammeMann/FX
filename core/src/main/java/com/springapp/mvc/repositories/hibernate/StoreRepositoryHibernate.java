package com.springapp.mvc.repositories.hibernate;

import com.springapp.mvc.common.hibernateEntity.GoodInStore;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.common.hibernateEntity.Store;
import com.springapp.mvc.repositories.StoreRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreRepositoryHibernate implements StoreRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return current Session from Session factory
     */
    private Session curSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Store store) {
        curSession().save(store);
    }

    @Override
    public void addGoodInStore(GoodInStore goodInStore) {
        curSession().save("GoodInStore", goodInStore);
    }

    @Override
    public List<Store> getAll() {
        return curSession().createCriteria(Store.class).list();
    }

    @Override
    public Store getStoreById(Long storeId) {
        return (Store) curSession().get(Store.class, storeId);
    }

    @Override
    public GoodInStore getGoodInStoreById(Long goodInStoreId) {
        return (GoodInStore) curSession().get(GoodInStore.class, goodInStoreId);
    }

    @Override
    public GoodInStore getGoodInStoreByGood(GoodInfo goodInfo) {
        Criteria criteria = curSession().createCriteria(GoodInStore.class);

        criteria.add(Restrictions.eq("goodInfo", goodInfo));
        return (GoodInStore) criteria.uniqueResult();
    }

    @Override
    public GoodInStore getGoodInStoreByGoodIdAndStoreId(Long goodId, Long storeId) {
        Criteria criteria = curSession().createCriteria(GoodInStore.class);
        criteria.add(Restrictions.eq("goodInfo", goodId));
        criteria.add(Restrictions.eq("store", storeId));
        return (GoodInStore) criteria.uniqueResult();
    }

    @Override
    public List<GoodInStore> getGoodInStoreByStore(Store store) {
        Criteria criteria = curSession().createCriteria(GoodInStore.class);
        criteria.add(Restrictions.eq("store", store));
        criteria.setFirstResult(0).setMaxResults(50);
        return (List<GoodInStore>) criteria.list();
    }

    @Override
    public void updateGoodInStore(GoodInStore goodInStore) {
        Session session = null;
        try {
            session = curSession();
            session.beginTransaction();
            session.update(goodInStore);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in updateGoodInStore(): " + e.getMessage());
            if (session != null && session.getTransaction() != null)
                session.getTransaction().rollback();
        }
    }

}
