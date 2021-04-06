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
public class ChatProcess extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        try{
            java.util.Date d=new java.util.Date();
            java.text.SimpleDateFormat sd=new java.text.SimpleDateFormat("dd MMM yyyy h:mm:ss a");
            String dt=sd.format(d);
            String i=request.getParameter("sid");
            String m=request.getParameter("m");
            String r[]=request.getParameterValues("r");
            db.DbConnection db=new db.DbConnection();
            for(int x = 0; x < r.length; x++) 
            {
               db.insertChat(i,r[x],m,dt);
            }
            session.setAttribute("msg","Message Sent");
            java.util.HashMap fac=db.getFaculty(i);
            if(fac!=null){
                response.sendRedirect("fchat.jsp");
            }else{
                response.sendRedirect("chat.jsp");
            }
        }catch(Exception ex){
            session.setAttribute("msg", ""+ex);
            ex.printStackTrace();
            response.sendRedirect("chat.jsp");
}
    }
}