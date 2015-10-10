/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stronquens.control;

import com.stronquens.beans.ProfesorBean;
import com.stronquens.service.ProfesorService;
import com.stronquens.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author stronquens
 */
public class Json extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String ob = request.getParameter("ob");
            String op = request.getParameter("op");

            HibernateUtil.createSessionFactory();

            if (ob.equalsIgnoreCase("profesor")) {
                if (op.equalsIgnoreCase("get")) {
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        ProfesorService oService = new ProfesorService();
                        ProfesorBean profe = oService.get(id);
                        out.println(profe.getNombre());
                    } catch (Exception e) {
                        out.println(e);
                    }
                }
                if (op.equalsIgnoreCase("save")) {
                    try {
                        String nombre = request.getParameter("nombre");
                        String ape1 = request.getParameter("ape1");
                        String ape2 = request.getParameter("ape2");
                        
                        ProfesorService oService = new ProfesorService();
                        ProfesorBean oBean = new ProfesorBean(0,nombre,ape1,ape2);
                        ProfesorBean profe = oService.save(oBean);
                        out.println(profe.getId());
                    } catch (Exception e) {
                        out.println(e);
                    }
                }
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
