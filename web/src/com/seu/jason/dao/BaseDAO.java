package com.seu.jason.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ToZhu on 2015/11/18.
 */
public class BaseDAO implements  IBaseDAO{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Object obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(obj);
        transaction.commit();
        session.close();
    }

    @Override
    public Object loadById(Class clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        Object object = session.get(clazz, id);
        session.close();
       return  object;
    }

    @Override
    public void delById(Class clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(clazz, id));
        transaction.commit();
        session.close();
    }

    @Override
    public void del(Object obj) {
        sessionFactory.openSession().delete(obj);
    }

    /***
     * 按列查询
     * @param clazz
     *          对象名（实体类名）
     * @param columns
     *          sql里面的列
     * @param args
     *          列对应的值
     * @return
     *          查询结果
     */
    @Override
    public List<Object> listAll(String clazz, String[] columns, String []args) {
        List<Object> list = null;

        StringBuilder hql = new StringBuilder("from "+clazz +"where ");
        int i=0;
        for(i=0; i<columns.length-1; i++){
            hql.append(columns[i]+" = ? and");
        }
        hql.append(columns[i]+" = ? ");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery(hql.toString());
        for(i=0; i<columns.length; i++){
            q.setSerializable(i,args[i]);
        }

        list = q.list();

        return list;
    }

    @Override
    public List<Object> listAll(String clazz, Serializable id, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Object> listAll(String clazz) {
        return null;
    }
}
