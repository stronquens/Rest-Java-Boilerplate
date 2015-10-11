/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stronquens.service;

import com.stronquens.beans.ProfesorBean;
import com.stronquens.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author stronquens
 */
@Service("profesorService")
public class ProfesorService {

    public ProfesorService() {
    }

    /**
     * Metodo que devuelve un profesor
     *
     * @param id
     * @return
     */
    public ProfesorBean get(int id) {
        // Transaccion 
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ProfesorBean profesor = new ProfesorBean();
        try {
            profesor = (ProfesorBean) session.get(ProfesorBean.class, id);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
        }
        return profesor;
    }

    /**
     * Metodo que crea o actualiza un profesor
     *
     * @param bean
     * @return
     */
    public ProfesorBean saveOrUpdate(ProfesorBean bean) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(bean);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
        }
        return bean;
    }

    /**
     * Metodo que borra un profesor
     *
     * @param bean
     */
    public void delete(ProfesorBean bean) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(bean);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
        }
    }

    /**
     * Devuelve todos los campos de una tabla
     *
     * @return
     */
    public List<ProfesorBean> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ProfesorBean> entities = null;
        try {
            Query query = session.createQuery("SELECT * FROM profesor");
            entities = query.list();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
        }
        return entities;
    }
}
