/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rohit
 */
public class GetFaculty extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        try {
            String fid=request.getParameter("fid");
            db.DbConnection db =new db.DbConnection();
            HashMap uFaculty=db.getFaculty(fid);
            if(uFaculty!=null){
                session.setAttribute("uFaculty",uFaculty);
            }else {
                session.setAttribute("msg","Faculty ID does not Exist.");
            }
            response.sendRedirect("a-faculty.jsp");
        } catch (Exception ex) {
            session.setAttribute("msg","Exception."+ex);
            response.sendRedirect("a-faculty.jsp");
        }
        }


}
