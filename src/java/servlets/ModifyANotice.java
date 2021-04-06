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
public class ModifyANotice extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession();
        try{
            int nid=Integer.parseInt(request.getParameter("nid"));
            String ns=request.getParameter("nsub");
            String nb=request.getParameter("nbody");
            String ed=request.getParameter("edate");
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            db.DbConnection db =new db.DbConnection();
            if(fileName.trim().equals("")){
                db.updateNotice(ns,nb,ed,nid);  
            }else{
                InputStream pis = filePart.getInputStream();
               db.updateNoticeWithFile(ns, nb,pis,fileName,ed,nid);
            }
            session.setAttribute("msg","Notice Modified Successfully.");
            response.sendRedirect("admin.jsp");
        }catch(Exception ex){
            session.setAttribute("msg","Exception: "+ex);
            ex.printStackTrace();
            response.sendRedirect("admin.jsp");
        }
    }
}
