/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stronquens.control;

import com.stronquens.beans.ProfesorBean;
import com.stronquens.service.ProfesorService;
import com.stronquens.util.HibernateUtil;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Armando
 */
@Controller
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @RequestMapping(value = "/get/{id}")
    public @ResponseBody
    HashMap<String, Object> get(@PathVariable("id") int id) {
        HibernateUtil.createSessionFactory();
        return profesorService.get(id);
    }

    @RequestMapping(value = "/saveorupdate", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public @ResponseBody
    HashMap<String, Object> saveOrUpdate(@RequestBody ProfesorBean oBean) {
        HibernateUtil.createSessionFactory();
        return profesorService.saveOrUpdate(oBean);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    HashMap<String, Object> remove(@PathVariable("id") int id) {
        HibernateUtil.createSessionFactory();
        ProfesorBean oProfesor = new ProfesorBean(id, null, null, null);
        return profesorService.delete(oProfesor);
    }

    @RequestMapping("all")
    public @ResponseBody
    HashMap<String, Object> getALL() {
        HibernateUtil.createSessionFactory();
        return profesorService.findAll();
    }

    /**
     *
     * @param tamanyoPagina
     * @param paginAMostrar empieza desde la pagina 0
     * @return
     */
    @RequestMapping(value = "/page/{tamanyoPagina}/{paginAMostrar}")
    public @ResponseBody
    HashMap<String, Object> getPage(
            @PathVariable("tamanyoPagina") int tamanyoPagina,
            @PathVariable("paginAMostrar") int paginAMostrar) {
        HibernateUtil.createSessionFactory();
        return profesorService.getPage(tamanyoPagina, paginAMostrar);
    }
    
        @RequestMapping(value = "/pages/{tamanyoPagina}")
    public @ResponseBody
    HashMap<String, Object> getPages(
            @PathVariable("tamanyoPagina") int tamanyoPagina) {
        HibernateUtil.createSessionFactory();
        return profesorService.getPages(tamanyoPagina);
    }

}
