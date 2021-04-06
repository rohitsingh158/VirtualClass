/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author rohit
 */

@MultipartConfig(maxFileSize = 16177215)
public class SUploads extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            HttpSession session=request.getSession();
        try{
            int roll=Integer.parseInt(request.getParameter("roll"));
            String s=request.getParameter("sub");
            String fn[]=request.getParameterValues("fname");
            String fns="";
            for(int i = 0; i < fn.length; i++) 
            {
                fns=fns+","+fn[i];
            }
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            InputStream pis = filePart.getInputStream();
            db.DbConnection db= new db.DbConnection();
            LocalDate cDate = LocalDate.now();
            String cd=cDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String t=db.insertSUpload(roll,s,fns,pis,fileName,cd);
            if(t.equals("success")){
                session.setAttribute("msg","File Uploaded Successfully.");
           
            }else{
            session.setAttribute("msg","File Uploading Failed.");
            }
            response.sendRedirect("student.jsp");
        }catch(Exception ex){
            session.setAttribute("msg",""+ex);
            ex.printStackTrace();
            response.sendRedirect("student.jsp");
        }
    }
}
