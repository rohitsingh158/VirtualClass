/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
@MultipartConfig
public class AddFNotice extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session=request.getSession();
            try{
              HashMap facultyInfo=(HashMap)session.getAttribute("facultyInfo");
              if(facultyInfo!=null){
            String fid=(String)facultyInfo.get("fid");
            String ns=request.getParameter("nsub");
            String nb=request.getParameter("nbody");
            String ed=request.getParameter("edate");
            Part filePart = request.getPart("file");
            String c[]=request.getParameterValues("course");
            String cr="";
            for(int i = 0; i < c.length; i++) 
            {
                cr=cr+" "+c[i];
            }
            String fileName = filePart.getSubmittedFileName();
            InputStream pis = filePart.getInputStream();
             LocalDate cDate = LocalDate.now();
            String cd=cDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
             db.DbConnection db=new db.DbConnection();
             String s=db.insertFNotice(ns,nb,cd,ed,cr,fid,pis,fileName);
            if(s.equals("success")){
            session.setAttribute("msg","Notice Added Successfully.");
            }else{
                session.setAttribute("msg","Notice not Added.");
            }
            response.sendRedirect("faculty.jsp");
              }else{
                  session.setAttribute("msg","Please Login First");
            
            response.sendRedirect("index.jsp");

                  }
              }catch(Exception ex){
            session.setAttribute("msg","Exception: "+ex);
            ex.printStackTrace();
            response.sendRedirect("faculty.jsp");
            
                
            }
        
        
        
        }
    }