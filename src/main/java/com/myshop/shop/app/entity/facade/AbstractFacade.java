/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myshop.shop.app.entity.facade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
/**    
 *
 * @author kavar
 */
public abstract class AbstractFacade<E> {

    private Class<E> entityClass;

    public AbstractFacade(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(E entity) {
        getEntityManager().persist(entity);
    }

    public void edit(E entity) {
        getEntityManager().merge(entity);
    }

    public void remove(E entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public E find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<E> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
public int count() {
        CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<E> rt = criteriaQuery.from(entityClass);
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query query = getEntityManager().createQuery(criteriaQuery);
        return ((Long) query.getSingleResult()).intValue();
    } 

}
        

