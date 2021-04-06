/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        try {
            String ut=request.getParameter("utype");
            String u=request.getParameter("uid");
            String p=request.getParameter("pwd");
            db.DbConnection db=new db.DbConnection();
            if(ut.equals("Student")){
                System.out.println("sdgjngsg");
                HashMap studentInfo= db.checkSLogin(u, p);
                        if(studentInfo!=null){
                            session.setAttribute("studentInfo", studentInfo);
                            response.sendRedirect("student.jsp");
                        }else{
                            session.setAttribute("msg","Wrong Entries!!");
                            response.sendRedirect("index.jsp");
                        }
                        
            }
            else if(ut.equals("Faculty")){
                HashMap facultyInfo=db.checkFLogin(u, p);
                if(facultyInfo!=null){
                    session.setAttribute("facultyInfo", facultyInfo);
                    response.sendRedirect("faculty.jsp");
                }else{
                    session.setAttribute("msg","Wrong Entries!!");
                    response.sendRedirect("index.jsp");
                }
            }else{
                String aname=db.checkALogin(u, p);
                if(aname!=null){
                    session.setAttribute("aid", u);
                    session.setAttribute("aname",aname);
                    response.sendRedirect("admin.jsp");
                }else{
                    session.setAttribute("msg","Wrong Entries!!");
                    response.sendRedirect("index.jsp");
                }
            }
        } catch (Exception ex) {
                    session.setAttribute("msg","Exception: !!"+ex);
                    response.sendRedirect("index.jsp");
        }
    
    
    }
}
