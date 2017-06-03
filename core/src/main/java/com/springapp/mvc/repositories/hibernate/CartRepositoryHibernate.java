package com.springapp.mvc.repositories.hibernate;


import com.springapp.mvc.common.hibernateEntity.CartRow;
import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.repositories.CartRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CartRepositoryHibernate implements CartRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return current Session from Session factory
     */
    private Session curSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * To get all the rows in the table CartRows
     *
     * @return list of CartRows
     */
    @Override
    public List<CartRow> getAll() {
        return (List<CartRow>) curSession().createCriteria(CartRow.class).list();
    }

    /**
     * To get all the cart rows made by exact customer
     *
     * @param customer - current user
     * @return list of CartRows
     */
    @Override
    public List<CartRow> getCartsByUser(Customer customer) {
        Criteria criteria = curSession().createCriteria(CartRow.class);

        criteria.add(Restrictions.eq("customer", customer));
        return (List<CartRow>) criteria.list();
    }

    /**
     * To update the column Count in exact cart row
     *
     * @param cartRowId exact cart row id
     * @param count     to increase or decrease count column (1/-1)
     */
    @Transactional
    @Override
    public void updateCartRow(Long cartRowId, Integer count) {
        try {
            Query query = curSession().createQuery("update CartRow c set c.count = c.count + :COUNT where id = :ID");
            query.setParameter("COUNT", count);
            query.setParameter("ID", cartRowId);
            query.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error in update(): " + e.getMessage());
        }
    }

    /**
     * To delete the cart row from the cart
     *
     * @param cartRowId of row to be deleted
     */
    @Override
    public void removeCartRowById(Long cartRowId) {
        try {
            Query query = curSession().createQuery("delete CartRow where id = :ID");
            query.setParameter("ID", cartRowId);
            query.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error in delete(): " + e.getMessage());
        }
    }

    @Override
    public CartRow getCartRowById(Long cartRowId) {
        return (CartRow) curSession().get(CartRow.class, cartRowId);
    }

    /**
     * To add thee cart row in the cart
     *
     * @param cartRow of row to be added
     */

    @Override
    @Transactional
    public void addCartRow(CartRow cartRow) {
        curSession().save(cartRow);
    }

}
