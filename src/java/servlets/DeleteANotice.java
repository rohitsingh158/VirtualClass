/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteANotice extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        try {
            int nid=Integer.parseInt(request.getParameter("nid").trim());
            
            db.DbConnection db=new db.DbConnection();
            String s=db.deleteANotice(nid);
                    if(s.equals("success")){
                        
                        session.setAttribute("msg","Notice Deleted Successfully.");
                    }
                    else
                    {
                        session.setAttribute("msg"," Notice ID does not Exist.");
                    }
                    response.sendRedirect("admin.jsp");
        } catch (Exception ex) {
             session.setAttribute("msg","Exception."+ex);
             response.sendRedirect("admin.jsp");
        }
        
        
    }
}
