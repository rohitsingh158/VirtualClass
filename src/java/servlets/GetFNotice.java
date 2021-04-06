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
public class GetFNotice extends HttpServlet {

    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session =request.getSession();
        try {
            int nid=Integer.parseInt(request.getParameter("nid").trim());
            db.DbConnection db =new db.DbConnection();
            HashMap fNotice=db.getFNotice(nid);
            if(fNotice!=null){
                session.setAttribute("fNotice",fNotice);
            }
            else {
                session.setAttribute("msg","Notice ID does not Exist.");
            }
            response.sendRedirect("faculty.jsp");
        } catch (Exception ex) {
            session.setAttribute("msg","Exception."+ex);
            response.sendRedirect("faculty.jsp");
        }
        }
}