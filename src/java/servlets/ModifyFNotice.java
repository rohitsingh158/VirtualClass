/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class ModifyFNotice extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession();
        try{
            int nid=Integer.parseInt(request.getParameter("nid"));
            String ns=request.getParameter("nsub");
            String nb=request.getParameter("nbody");
            String ed=request.getParameter("edate");
            String c[]=request.getParameterValues("course");
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            db.DbConnection db =new db.DbConnection();
            if(fileName.trim().equals("") && c==null){
                db.updateFNotice(ns,nb,ed,nid);  
            }else if(!(fileName.trim().equals("")) && c==null){
                InputStream pis = filePart.getInputStream();
               db.updateFNoticeWithFile(ns, nb,pis,fileName,ed,nid);
            }
            else if(fileName.trim().equals("") && c!=null)
            {

                String cr="";
                for(int i = 0; i < c.length; i++) 
                {
                    cr=cr+" "+c[i];
                }
                db.updateFNoticeWithCourse(ns,nb,cr,ed,nid);
            }
            else
            {
                InputStream pis = filePart.getInputStream();
                String cr="";
                for(int i = 0; i < c.length; i++) 
                {
                    cr=cr+" "+c[i];
                }
               db.updateFNoticeWithFileAndCourse(ns, nb,pis,fileName,ed,cr,nid);
               
            }
            
            session.setAttribute("msg","Notice Modified Successfully.");
            response.sendRedirect("faculty.jsp");
        }catch(Exception ex){
            session.setAttribute("msg","Exception: "+ex);
            ex.printStackTrace();
            response.sendRedirect("faculty.jsp");
        }
    }
}
