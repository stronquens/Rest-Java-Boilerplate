/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stronquens.service;

import com.stronquens.beans.ProfesorBean;
import com.stronquens.util.HibernateUtil;
import java.util.HashMap;
import org.hibernate.HibernateException;
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
    public HashMap<String, Object> get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            result.put("Data", (ProfesorBean) session.get(ProfesorBean.class, id));
            result.put("Status", 200);
            result.put("Message", "succes");

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
            result.put("Status", 500);
            result.put("Message", this.getClass().getName() + ":set ERROR: " + e.getMessage());
        }
        return result;
    }

    /**
     * Metodo que crea o actualiza un profesor
     *
     * @param bean
     * @return
     */
    public HashMap<String, Object> saveOrUpdate(ProfesorBean bean) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            if (bean.getId() == 0) {
                Integer id = (Integer) session.save(bean);
                bean.setId(id);
            } else if (bean.getId() > 0) {
                session.update(bean);
            }
            result.put("Status", 200);
            result.put("Message", "The " + bean.getClass() + " insert with id: " + bean.getId());

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
            result.put("Status", 500);
            result.put("Message", this.getClass().getName() + ":set ERROR: " + e.getMessage());
        }
        return result;
    }

    /**
     * Metodo que borra un profesor
     *
     * @param bean
     * @return
     */
    public HashMap<String, Object> delete(ProfesorBean bean) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            session.delete(bean);
            result.put("Status", 200);
            result.put("Message", "The " + bean.getClass()
                    + " with id " + bean.getId() + " operation succes");

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
            result.put("Status", 500);
            result.put("Message", this.getClass().getName() + ":set ERROR: " + e.getMessage());
        }
        return result;
    }

    /**
     * Devuelve todos los campos de una tabla
     *
     * @return
     */
    public HashMap<String, Object> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            result.put("Data", session.createQuery("from ProfesorBean").list());
            result.put("Status", 200);
            result.put("Message", "succes");

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
            result.put("Satutus", 500);
            result.put("Message", this.getClass().getName() + ":set ERROR: " + e.getMessage());
        }
        return result;
    }

    /**
     * Devuelve los campos paginados
     *
     * @param tamanyoPagina
     * @param paginaAMostrar
     * @return
     */
    public HashMap<String, Object> getPage(int tamanyoPagina, int paginaAMostrar) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            Query query = session.createQuery("SELECT p FROM ProfesorBean p Order By p.id");
            query.setMaxResults(tamanyoPagina);
            query.setFirstResult(paginaAMostrar * tamanyoPagina);

            result.put("Data", query.list());
            result.put("Status", 200);
            result.put("Message", "succes");

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
            result.put("Satutus", 500);
            result.put("Message", this.getClass().getName() + ":set ERROR: " + e.getMessage());
        }
        return result;
    }

    /**
     * Devuelve el numero de paginas
     *
     * @param tamanyoPagina
     * @return
     */
    public HashMap<String, Object> getPages(int tamanyoPagina) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            Query query = session.createQuery("SELECT count(p.id)/" + tamanyoPagina + " FROM ProfesorBean p");
            query.setMaxResults(tamanyoPagina);

            result.put("Data", query.list());
            result.put("Status", 200);
            result.put("Message", "succes");

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                session.close();
            }
            result.put("Satutus", 500);
            result.put("Message", this.getClass().getName() + ":set ERROR: " + e.getMessage());
        }
        return result;
    }
}
