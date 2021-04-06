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
public class AddANotice extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
         try
        {
            String ns=request.getParameter("nsub");
            String nb=request.getParameter("nbody");
            String ed=request.getParameter("edate");
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            InputStream pis = filePart.getInputStream();
            LocalDate cDate = LocalDate.now();
            String cd=cDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            db.DbConnection db=new db.DbConnection();
            String s= db.insertANotice(ns, nb, pis, fileName, ed, 0, cd);
            if(s.equals("success"))
                     session.setAttribute("msg","Notice Added Successfully.");
            else
                session.setAttribute("msg","Notice not add.");    
            
            
            response.sendRedirect("admin.jsp");
        }
        catch(Exception ex)
        {
            session.setAttribute("msg","Notice not add.");
            ex.printStackTrace();
            response.sendRedirect("admin.jsp");
        }
    }

}