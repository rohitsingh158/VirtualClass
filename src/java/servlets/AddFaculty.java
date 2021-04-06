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
public class AddFaculty extends HttpServlet {

    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session=request.getSession();
        try{
        String n=request.getParameter("name");
        String e=request.getParameter("email");
        String c[]=request.getParameterValues("course");
        String p=request.getParameter("phone");
        String pw= "";
        String fid= "";
    
        int FID_LENGTH = 6;
        HashMap faculty;
        String letters = n+p;
        db.DbConnection db =new db.DbConnection();
        do{
            for (int i=0; i<FID_LENGTH; i++){
            int index = 0 + (int)(Math.random() * (letters.length()-1)); 
            fid += letters.charAt(index);
            }
             faculty=db.getFaculty(fid);
    }
    while(faculty!=null);
    int PASSWORD_LENGTH = 8;
    letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789@";
    for (int i=0; i<PASSWORD_LENGTH; i++)
    {
        int index = 0 + (int)(Math.random() * (letters.length()-1)); 
        pw += letters.charAt(index);
    }
        String s=db.insertFaculty(fid,n,e,p,pw);
        String st=db.insertFacultyCourse(fid,c);
       if(s.equals("success")){
        String subject="Faculty Successfully Registered";
        String body="You are Registered Successfullly and Your User ID: "+fid+" and Password: "+pw;
        mail.Mail.sendMail(subject, body, e);
        session.setAttribute("msg","Faculty Successfully Registered. Faculty ID & Password Send to given Email ID.");
       }else{
           session.setAttribute("msg","Faculty Registration Failed");
       }
        response.sendRedirect("a-faculty.jsp");
       }catch(Exception ex){
      session.setAttribute("msg",""+ex);
      response.sendRedirect("a-faculty.jsp");
    }
   }
}
