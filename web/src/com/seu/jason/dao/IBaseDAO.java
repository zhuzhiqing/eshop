package com.seu.jason.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ToZhu on 2015/11/18.
 */
public interface IBaseDAO {
    public void saveOrUpdate(Object obj);

    public Object loadById(Class clazz, Serializable id);

    public void delById(Class clazz, Serializable id);

    public void del(Object obj);

    public Object find(String clazz, String[] columns, String[] args);

    public List<Object> listAll(String clazz, String[] columns, String[] args);

    public List<Object> listAll(String clazz, Serializable id, int pageNum, int pageSize);

    public List<Object> listAll(String clazz);
}
