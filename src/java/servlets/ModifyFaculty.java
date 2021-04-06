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
public class ModifyFaculty extends HttpServlet {

    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session =request.getSession();
        try{
         String ofid=request.getParameter("ofid");
        String fid=request.getParameter("fid");
        String n=request.getParameter("name");
        String e=request.getParameter("email");
        String c[]=request.getParameterValues("course");
        String p=request.getParameter("phone");
        db.DbConnection db =new db.DbConnection();
        
        int x=0;
        if(!fid.equals(ofid)) {   
            HashMap h=db.getFaculty(fid);
            if(h==null){
                String s=  db.updateFaculty(fid,n,e,p,ofid);
                String s1=db.updateFacultyCourseFid(fid,ofid);
                session.setAttribute("msg","Faculty Details Successfully Updated.");
            }
            else
            {
                x=1;
                session.setAttribute("msg","New Faculty ID Already Exist.");
            }
        }else{
            String s=  db.updateFaculty(fid,n,e,p,ofid);
            session.setAttribute("msg","Faculty Details Successfully Updated.");
        }if(c!=null && x==0){
            String s=db.deleteFacultyCourse(fid);
            String s1= db.insertFacultyCourse(fid,c);
        }
        response.sendRedirect("a-faculty.jsp");
        }catch(Exception ex){
             session.setAttribute("msg",""+ex);
             ex.printStackTrace();
            response.sendRedirect("a-faculty.jsp");
        }
    }
}
