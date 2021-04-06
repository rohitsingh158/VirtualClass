/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rohit
 */
public class SUDownloadFile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            int a=Integer.parseInt(request.getParameter("suid"));
            db.DbConnection db =new db.DbConnection();
            Statement st=db.getStmt();
            ResultSet rs=st.executeQuery("select ufile,filename from suploads where suid="+a);
            if(rs.next())
            {
                String fname=rs.getString(2);
                response.setContentType("APPLICATION/OCTET-STREAM");   
                response.setHeader("Content-Disposition","attachment; filename="+fname); 
                response.getOutputStream().write(rs.getBytes(1)); 
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
