/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.InputStream;
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
public class ModifyStudent extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           HttpSession session=request.getSession();
        try{
            int or=Integer.parseInt(request.getParameter("oroll"));
            int r=Integer.parseInt(request.getParameter("roll"));
            String n=request.getParameter("name");
            String f=request.getParameter("fname");
            String e=request.getParameter("email");
            String c=request.getParameter("course");
            String p=request.getParameter("phone");
            String s=request.getParameter("status");
            db.DbConnection db =new db.DbConnection();
            if(r!=or){
              HashMap stu=db.getStudent(r);
              if(stu==null){
                String st= db.updateStudent(or,r,n,f,e,c,p,s);
                if(st.equals("success")){
                     session.setAttribute("msg","Student Updated Successfully.");
                }
            }else{
                  session.setAttribute("msg","Roll No. Already Exist.");
              }
            }
           
            response.sendRedirect("a-student.jsp");
        }catch(Exception ex){
            session.setAttribute("msg","Exception: "+ex);
            ex.printStackTrace();
            response.sendRedirect("a-student.jsp");
        }
    }
}
