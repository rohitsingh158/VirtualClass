/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rohit
 */
public class DeleteStudent extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        try {
            int roll=Integer.parseInt(request.getParameter("roll").trim());
            
            db.DbConnection db=new db.DbConnection();
            String s=db.deleteStudent(roll);
                    if(s.equals("success")){
                        
                        session.setAttribute("msg","Student Deleted Successfully.");
                    }
                    else
                    {
                        session.setAttribute("msg"," Student Roll does not Exist.");
                    }
                    response.sendRedirect("a-student.jsp");
        } catch (Exception ex) {
             session.setAttribute("msg","Exception."+ex);
             response.sendRedirect("a-student.jsp");
        }
    }

}
