package com.riskcare.forums.server.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;

public abstract class GenericDAO<T> {

    private static final Logger LOG = LoggerFactory.getLogger(GenericDAO.class);
    
    //--------------------------- FIELDS ---------------------------------    
    private SessionFactory sessionFactory = null;
    private Class<T> genericType;

    /**
     * Deletes an item
     * @param item
     * @param session
     * @throws DatabaseAccessException
     */
    public void delete(T item, Session session) throws Exception {
        Session session1 = null;
        try {
            if( session == null || !session.isOpen() ){
                session1 = getSession();
            }else{
                session1 = session;
            }
            session1.delete(item);
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        }
        
        /*
        Session session = getSession();
        Object root = null;
        try {
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.eq("categoryName",RCF_CATEGORY_ROOT));
            root = criteria.uniqueResult(); 
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
        return root;        
        */
    }

    /**
     * Deletes all items
     * @param itemList
     * @param session
     * @throws DatabaseAccessException
     */
    public void deleteAll(List<T> itemList, Session session) throws Exception {
        Session session1 = null;
        try {
            if( session == null || !session.isOpen() ){
                session1 = getSession();
            }else{
                session1 = session;
            }
            for(T item: itemList){
                session1.delete(item);
            }
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        }
    } 
    
    /**
     * Finds all items with the session
     * @param session
     * @return
     * @throws DatabaseAccessException
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(Session session) throws Exception {
        List<T> result = null;
        try {
            this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDAO.class);
            final Query query = session.createQuery("from " + genericType.getName());
            result = query.list();
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        } 
        return result;
    }


    /**
     * Finds all snapshot file with a specified prefix
     * @param prefix
     * @return
     * @throws DatabaseAccessException
     */
    @SuppressWarnings("unchecked")
    public List<T> findAllFiles(String prefix) throws Exception {
        List<T> result = null;
        try {
            this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDAO.class);
            Session session = getSession();
            String LIKE_RULE ="%";
            
            Criteria criteria = session.createCriteria(genericType);
                                criteria.add(Restrictions.like("fileName", LIKE_RULE+prefix+LIKE_RULE))
                                .add(Restrictions.eq("official",true))
                                .addOrder(Order.desc("snapshotDate"))
                                 .addOrder(Order.desc("id"));
            result = criteria.list(); 

            criteria = session.createCriteria(genericType);
                        criteria.add(Restrictions.like("fileName", LIKE_RULE+prefix+LIKE_RULE))
                        .add(Restrictions.eq("official",false))
                        .addOrder(Order.desc("snapshotDate"))
                        .addOrder(Order.desc("id"));
            List<T> result2 = criteria.list();
            result.addAll(result2);
            
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Saves an item
     * @param item
     * @param session
     * @throws DatabaseAccessException
     */
    public void save(T item, Session session) throws Exception {
        try {
            session.save(item);
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        } 
    }
    
    
    public void update(T item, Session session) throws Exception {
    	try {
    		session.update(item);
    	} catch(final HibernateException e) {
    		throw new Exception(e.getMessage(), e);
    	}
    }
    
    public void updateAll(List<T> itemList, Session session) throws Exception {
        Session session1 = null;
        try {
            if( session == null || !session.isOpen() ){
                session1 = getSession();
            }else{
                session1 = session;
            }
            for(T item: itemList){
                session1.update(item);
            }
            session1.flush();
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        }
    }
    /**
     * Saves all items in the argument list
     * @param item
     * @param session
     * @throws DatabaseAccessException
     */
    public void saveAll(List<T> item, Session session) throws Exception {
        try {
            for (int i = 0; i < item.size(); i++) {
                session.save(item.get(i));
                if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                }
            }
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        } 
    }
    
    /**
     * Save all items in the argument set
     * @param item
     * @param session
     * @throws DatabaseAccessException
     */
    public void saveAll(Set<T> item, Session session) throws Exception {
        try {
            int i =0;
            for(T t: item){
                session.save(t);
                if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                }
                i++;
            }
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        } 
    }

    /**
     * Saves or updates an item
     * @param item
     * @param session
     * @throws DatabaseAccessException
     */
    public void saveOrUpdate(T item, Session session) throws Exception {
        try {
            session.saveOrUpdate(item);
        } catch (final HibernateException e) {
            throw new Exception(e.getMessage(), e);
        } 
    }    
    
    /**
     * Gets the session
     * @return Session
     * @throws DatabaseAccessException
     */
    public Session getSession() throws Exception {
        try{
            Session session = HibernateSession.currentSession(sessionFactory);
            if(session == null){
                throw new Exception("Hibernate Session is null");
            }
            return session;
        }catch (final HibernateException he) {
            throw new Exception(he.getMessage(), he);
        } catch(Exception e){
            throw new Exception(e.getMessage(), e);
        }
    }

    /**
     * Starts the transaction
     * @param session
     * @return Transaction
     * @throws DatabaseAccessException
     */
    public Transaction startTransaction(Session session) throws Exception {
        Transaction transaction = session.getTransaction();
        if(transaction.isActive()){
            throw new Exception(" ** Trying to Open nested transaction, either commit/rollback the previous transaction");
        }else{
            transaction.begin();
        }
        return transaction;
    }

    /**
     * Commits a transaction
     * @param transaction
     * @throws DatabaseAccessException
     */
    public void commitTransaction(Transaction transaction) throws Exception {
        if (transaction == null) {
            new Exception("No active transaction available to commit");
        }else{
            if (transaction.isActive()) {
                transaction.commit();
                transaction = null;
            } else {
                new Exception("No active transaction available to commit");
            }
        }
    }

    /**
     * Rolls back a transaction
     * @param transaction
     * @throws DatabaseAccessException
     */
    public void rollbackTransaction(Transaction transaction) throws Exception {
        if (transaction == null) {
            new Exception("Transaction is null", null);
        }else{
            if (transaction.isActive()) {
                transaction.rollback();
                LOG.debug(" HibernateSession is closed.");
                clearSession();
                transaction = null;
            } else {
                new Exception("No active transaction available to rollback", null);
            }
        }
    }
    
    /**
     * Closes the session
     */
    public void closeSession(){
        HibernateSession.closeSession();
    }
    
    public void clearSession(){
        HibernateSession.clearSesssion();
    }

    /**
     * Gets the session factory
     * @return SessionFactory
     */
    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    /**
     * Sets the Session factory
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }    
    
}
