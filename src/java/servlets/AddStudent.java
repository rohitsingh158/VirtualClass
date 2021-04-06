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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author rohit
 */
public class AddStudent extends HttpServlet {

    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession();
         try{
            int r=Integer.parseInt(request.getParameter("roll"));
            String n=request.getParameter("name");
            String f=request.getParameter("fname");
            String e=request.getParameter("email");
            String c=request.getParameter("course");
            String p=request.getParameter("phone");
            String pw= "";
            String msg="";
            db.DbConnection db=new db.DbConnection();
            HashMap st=db.getStudent(r);
            if(st==null){
                int PASSWORD_LENGTH = 8;
                String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789@";
                for (int i=0; i<PASSWORD_LENGTH; i++)
                {
                    int index = 0 + (int)(Math.random() * (letters.length()-1)); 
                    pw += letters.charAt(index);
                }    
            
                String s= db.insertStudent(r,n,f,e,c,p,pw);
                if(s.equals("success")){
                        session.setAttribute("msg","Student Added Successfully.");
                        String subject="Student Successfully Registered";
                        String body="You are Registered Successfullly and Your User ID: "+r+" and Password: "+pw;
                        String ms= mail.Mail.sendMail(subject,body, e);
                        if(!ms.equals("success")){
                            session.setAttribute("msg","Student Added Successfully but mail sending failed.");
                        }
                }
                else
                    session.setAttribute("msg","Student not add.");    
             }else
                session.setAttribute("msg","Roll No. Already Exist.");   
            
            response.sendRedirect("a-student.jsp");
        }catch(Exception ex){
            session.setAttribute("msg","Student not add.");
            ex.printStackTrace();
             response.sendRedirect("a-student.jsp");
        }
    }
    
}
