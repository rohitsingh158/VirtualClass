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
public class GetStudent extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session =request.getSession();
        try {
            int roll=Integer.parseInt(request.getParameter("roll").trim());
            db.DbConnection db =new db.DbConnection();
            HashMap uStudent=db.getStudent(roll);
            if(uStudent!=null){
                session.setAttribute("uStudent",uStudent);
            }
            else {
                session.setAttribute("msg","Student Roll does not Exist.");
            }
            response.sendRedirect("a-student.jsp");
        } catch (Exception ex) {
            session.setAttribute("msg","Exception."+ex);
            response.sendRedirect("a-student.jsp");
        }
        }


}
