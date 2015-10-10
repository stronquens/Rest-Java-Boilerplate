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

    
    public ProfesorService(){
    }
    public ProfesorBean get(int id) {
        // Transaccion 
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ProfesorBean profesor = (ProfesorBean) session.get(ProfesorBean.class, id);

        session.getTransaction().commit();
        session.close();

        return profesor;
    }

    public ProfesorBean save(ProfesorBean bean) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(bean);

        session.getTransaction().commit();
        session.close();
        return bean;
    }
    
}
