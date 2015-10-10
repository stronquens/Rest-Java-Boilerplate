/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stronquens.service;

import com.stronquens.beans.ProfesorBean;
import com.stronquens.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author stronquens
 */
public class ProfesorService {

    public ProfesorService() {
    }
    
    /**
     * Metodo que devuelve un profesor
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
            session.getTransaction().rollback();
            session.close();
        }
        return profesor;
    }

    /**
     * Metodo que crea o actualiza un profesor
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
            session.getTransaction().rollback();
            session.close();
        }
        return bean;
    }

    /**
     * Metodo que borra un profesor
     * @param bean
     * @return 
     */
    public ProfesorBean delete(ProfesorBean bean) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(bean);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
        }
        return bean;
    }
}
