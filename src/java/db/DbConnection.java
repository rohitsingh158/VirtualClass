package db;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DbConnection {
    private Connection con;
    private Statement st;
    public DbConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/virtualclass","root","rohit");
        st=con.createStatement();
            
    }
    public Statement getStmt(){
        return st;
    }
    public String checkALogin(String id,String pass)throws SQLException{
            PreparedStatement checkALogin=con.prepareStatement("select * from admin where aid=? and pass=?");
            checkALogin.setString(1, id);
            checkALogin.setString(2, pass);
            ResultSet rs=checkALogin.executeQuery();
            if(rs.next()){
               return rs.getString("name");
            }else{
                return null;
            }
           
    }
     public HashMap checkSLogin(String id, String pass)throws SQLException{
            PreparedStatement checkSLogin=con.prepareStatement("select * from student where roll=? and pass=?");
            checkSLogin.setString(1, id);
            checkSLogin.setString(2, pass);
            ResultSet rs=checkSLogin.executeQuery();
            if(rs.next()){
                HashMap studentInfo =new HashMap();
                studentInfo.put("roll", rs.getInt("roll"));
                studentInfo.put("name", rs.getString("name"));
                studentInfo.put("fname", rs.getString("fname"));
                studentInfo.put("email", rs.getString("email"));
                studentInfo.put("course", rs.getString("course"));
                studentInfo.put("phone", rs.getString("phone"));
                studentInfo.put("status", rs.getString("status"));
               return studentInfo;
            }else{
                return null;
            }
     
     }
      public HashMap checkFLogin(String id, String pass)throws SQLException{
            PreparedStatement checkFLogin=con.prepareStatement("select * from faculty where email=? and pass=?");
            checkFLogin.setString(1, id);
            checkFLogin.setString(2, pass);
            ResultSet rs=checkFLogin.executeQuery();
            if(rs.next()){
                HashMap facultyInfo =new HashMap();
                facultyInfo.put("fid", rs.getString("fid"));
                facultyInfo.put("name", rs.getString("name"));
                facultyInfo.put("phone", rs.getString("phone"));
                facultyInfo.put("email", rs.getString("email"));
               return facultyInfo;
            }else{
                return null;
            }
    }
      public HashMap getNotice(int nid)throws SQLException{
            PreparedStatement getNotice=con.prepareStatement("select * from anotice where nid=?");
            getNotice.setInt(1,nid);
            ResultSet rs=getNotice.executeQuery();
            if(rs.next()){
                HashMap notice =new HashMap();
                notice.put("nid", rs.getInt("nid"));
                notice.put("sub", rs.getString("sub"));
                notice.put("body", rs.getString("body"));
                notice.put("filename", rs.getString("filename"));
                notice.put("pdate", rs.getString("pdate"));
                notice.put("expdate", rs.getString("expdate"));
               return notice;
            }else{
                return null;
            }
    }
       public HashMap getFNotice(int nid)throws SQLException{
            PreparedStatement getFNotice=con.prepareStatement("select * from fnotice where nid=?");
            getFNotice.setInt(1,nid);
            ResultSet rs=getFNotice.executeQuery();
            if(rs.next()){
                HashMap notice =new HashMap();
                notice.put("nid", rs.getInt("nid"));
                notice.put("sub", rs.getString("sub"));
                notice.put("body", rs.getString("body"));
                notice.put("filename", rs.getString("filename"));
                notice.put("pdate", rs.getString("pdate"));
                notice.put("expdate", rs.getString("expdate"));
                notice.put("fid", rs.getString("fid"));
                notice.put("course", rs.getString("course"));
               return notice;
            }else{
                return null;
            }
    }
     public ArrayList<HashMap> getAllANotice(String cd)throws SQLException{
            
           ResultSet rs;
            if(cd!=null){
             PreparedStatement getAllANotice=con.prepareStatement("select * from anotice where expdate>=?");
            getAllANotice.setString(1, cd);
            rs=getAllANotice.executeQuery();
           }else{
           PreparedStatement getAllANotice=con.prepareStatement("select * from anotice ");
            rs=getAllANotice.executeQuery();
           }
            ArrayList allNotice =new ArrayList();
            while(rs.next()){
                HashMap notice =new HashMap();
                notice.put("nid", rs.getInt("nid"));
                notice.put("sub", rs.getString("sub"));
                notice.put("body", rs.getString("body"));
                
                notice.put("filename", rs.getString("filename"));
                notice.put("pdate", rs.getString("pdate"));
                notice.put("expdate", rs.getString("expdate"));
                allNotice.add(notice);
                   
            }
            return allNotice;
     }
     public ArrayList<HashMap> getAllFNotice(String cd)throws SQLException{
            
           ResultSet rs;
            if(cd!=null){
            PreparedStatement getAllFNotice=con.prepareStatement("select * from fnotice where expdate>=?");
            getAllFNotice.setString(1, cd);
            rs=getAllFNotice.executeQuery();
           }else{
           PreparedStatement getAllFNotice=con.prepareStatement("select * from fnotice ");
            rs=getAllFNotice.executeQuery();
           }
            ArrayList allNotice =new ArrayList();
            while(rs.next()){
                HashMap notice =new HashMap();
                notice.put("nid", rs.getInt("nid"));
                notice.put("sub", rs.getString("sub"));
                notice.put("body", rs.getString("body"));
                
                notice.put("filename", rs.getString("filename"));
                notice.put("pdate", rs.getString("pdate"));
                notice.put("expdate", rs.getString("expdate"));
                notice.put("fid", rs.getString("fid"));
                notice.put("course", rs.getString("course"));
                allNotice.add(notice);
                   
            }
            return allNotice;
     }
      public ArrayList<HashMap> getAllFNoticeFID(String fid)throws SQLException{
            PreparedStatement getAllFNoticeFID=con.prepareStatement("select * from fnotice where fid=?");
            getAllFNoticeFID.setString(1, fid);
            ResultSet rs=getAllFNoticeFID.executeQuery();
            ArrayList allNotice =new ArrayList();
            while(rs.next()){
                HashMap notice =new HashMap();
                notice.put("nid", rs.getInt("nid"));
                notice.put("sub", rs.getString("sub"));
                notice.put("body", rs.getString("body"));
                notice.put("filename", rs.getString("filename"));
                notice.put("pdate", rs.getString("pdate"));
                notice.put("expdate", rs.getString("expdate"));
                notice.put("fid", rs.getString("fid"));
                notice.put("course", rs.getString("course"));
                allNotice.add(notice);
            }
            return allNotice;
     }
    public String deleteANotice(int nid) throws SQLException {
             PreparedStatement deleteANotice=con.prepareStatement("delete from anotice where nid=?");
             deleteANotice.setInt(1, nid);
            int x= deleteANotice.executeUpdate();
            if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
     public String deleteFNotice(int nid) throws SQLException {
             PreparedStatement deleteFNotice=con.prepareStatement("delete from fnotice where nid=?");
             deleteFNotice.setInt(1, nid);
            int x= deleteFNotice.executeUpdate();
            if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
    public String deleteStudent(int roll) throws SQLException {
             PreparedStatement deleteStudent=con.prepareStatement("delete from student where roll=?");
             deleteStudent.setInt(1, roll);
            int x= deleteStudent.executeUpdate();
            if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
     public String deleteFaculty(String fid) throws SQLException {
            PreparedStatement deleteFaculty=con.prepareStatement("delete from faculty where fid=?");
             deleteFaculty.setString(1, fid);
            int x= deleteFaculty.executeUpdate();
            if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }

    public String updateNotice(String ns, String nb, String ed, int nid) throws SQLException {
        PreparedStatement updateANotice1=con.prepareStatement("update anotice set sub=?,body=?,expdate=? where nid=?");
        updateANotice1.setString(1,ns);
        updateANotice1.setString(2,nb);
        updateANotice1.setString(3,ed);
        updateANotice1.setInt(4,nid);
        int x=updateANotice1.executeUpdate();
          if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
    
     public String updateNoticeWithFile(String ns, String nb,InputStream pis,String fileName, String ed, int nid) throws SQLException {
          
            PreparedStatement updateANotice2=con.prepareStatement("update anotice set sub=?,body=?,ufile=?,filename=?,expdate=? where nid=?");
                updateANotice2.setString(1,ns);
                updateANotice2.setString(2,nb);
                updateANotice2.setBinaryStream(3, pis);
                updateANotice2.setString(4,fileName);
                updateANotice2.setString(5,ed);
                updateANotice2.setInt(6,nid);
               int x= updateANotice2.executeUpdate();
                  if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
    public String updateFNotice(String ns, String nb, String ed, int nid) throws SQLException {
        PreparedStatement updateFNotice1=con.prepareStatement("update fnotice set sub=?,body=?,expdate=? where nid=?");
        updateFNotice1.setString(1,ns);
        updateFNotice1.setString(2,nb);
        updateFNotice1.setString(3,ed);
        updateFNotice1.setInt(4,nid);
        int x=updateFNotice1.executeUpdate();
          if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
    
     public String updateFNoticeWithFile(String ns, String nb,InputStream pis,String fileName, String ed, int nid) throws SQLException {
          
            PreparedStatement updateFNotice2=con.prepareStatement("update fnotice set sub=?,body=?,ufile=?,filename=?,expdate=? where nid=?");
                updateFNotice2.setString(1,ns);
                updateFNotice2.setString(2,nb);
                updateFNotice2.setBinaryStream(3, pis);
                updateFNotice2.setString(4,fileName);
                updateFNotice2.setString(5,ed);
                updateFNotice2.setInt(6,nid);
               int x= updateFNotice2.executeUpdate();
                  if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }

public String updateFNoticeWithCourse(String ns, String nb,String  cr, String ed, int nid) throws SQLException {
            PreparedStatement updateFNotice3=con.prepareStatement("update fnotice set sub=?,body=?,course=?,expdate=? where nid=?");
                updateFNotice3.setString(1,ns);
                updateFNotice3.setString(2,nb);
                updateFNotice3.setString(3, cr);
                updateFNotice3.setString(4,ed);
                updateFNotice3.setInt(5,nid);
               int x= updateFNotice3.executeUpdate();
                  if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
    public String updateFNoticeWithFileAndCourse(String ns, String nb,InputStream pis,String fileName, String ed,String cr, int nid) throws SQLException {
          
            PreparedStatement updateFNotice4=con.prepareStatement("update fnotice set sub=?,body=?,ufile=?,filename=?,expdate=?,course=? where nid=?");
                updateFNotice4.setString(1,ns);
                updateFNotice4.setString(2,nb);
                updateFNotice4.setBinaryStream(3, pis);
                updateFNotice4.setString(4,fileName);
                updateFNotice4.setString(5,ed);
                updateFNotice4.setString(6,cr);
                updateFNotice4.setInt(7,nid);
               int x= updateFNotice4.executeUpdate();
                  if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
  public String insertANotice(String ns, String nb,InputStream pis,String fileName, String ed, int nid ,String cd) throws SQLException {
        PreparedStatement insertANotice=con.prepareStatement("insert into anotice (sub,body,ufile,filename,pdate,expdate)values(?,?,?,?,?,?)");
            insertANotice.setString(1, ns);
            insertANotice.setString(2, nb);
            insertANotice.setBinaryStream(3, pis);
            insertANotice.setString(4, fileName);
            insertANotice.setString(5, cd);
            insertANotice.setString(6, ed);
            int x=insertANotice.executeUpdate();
                  if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
  public ArrayList<HashMap> getAllStudent()throws SQLException{
       PreparedStatement getAllStudent=con.prepareStatement("select * from Student");
        ResultSet rs=getAllStudent.executeQuery();
         ArrayList allStudent =new ArrayList();
         while(rs.next()){
             HashMap student =new HashMap();
             student.put("roll", rs.getInt("roll"));
             student.put("name", rs.getString("name"));
             student.put("fname", rs.getString("fname"));
             student.put("email", rs.getString("email"));
             student.put("course", rs.getString("course"));
             student.put("phone", rs.getString("phone"));
             student.put("status", rs.getString("status"));
             student.put("pass", rs.getString("pass"));
             allStudent.add(student);
         }
         return allStudent;
     }
  public ArrayList<HashMap> getAllStudentByCourse(String c)throws SQLException{
        PreparedStatement getStudentByCourse=con.prepareStatement("select * from student where course=?");
        getStudentByCourse.setString(1, c);
        ResultSet rs=getStudentByCourse.executeQuery();
         ArrayList allStudent =new ArrayList();
         while(rs.next()){
             HashMap student =new HashMap();
             student.put("roll", rs.getInt("roll"));
             student.put("name", rs.getString("name"));
             student.put("fname", rs.getString("fname"));
             student.put("email", rs.getString("email"));
             student.put("course", rs.getString("course"));
             student.put("phone", rs.getString("phone"));
             student.put("status", rs.getString("status"));
             student.put("pass", rs.getString("pass"));
             allStudent.add(student);
         }
         return allStudent;
     }
  
  
  public HashMap getStudent(String roll)throws SQLException{
            PreparedStatement getStudent=con.prepareStatement("select * from Student where roll=?");
            getStudent.setString(1,roll);
            ResultSet rs=getStudent.executeQuery();
            if(rs.next()){
               HashMap student =new HashMap();
             student.put("roll", rs.getInt("roll"));
             student.put("name", rs.getString("name"));
             student.put("fname", rs.getString("fname"));
             student.put("email", rs.getString("email"));
             student.put("course", rs.getString("course"));
             student.put("phone", rs.getString("phone"));
             student.put("status", rs.getString("status"));
             student.put("pass", rs.getString("pass"));
             return student;
            }else{
                return null;
            }
    }
public HashMap getStudent(int roll)throws SQLException{
            PreparedStatement getStudent=con.prepareStatement("select * from Student where roll=?");
            getStudent.setInt(1,roll);
            ResultSet rs=getStudent.executeQuery();
            if(rs.next()){
               HashMap student =new HashMap();
             student.put("roll", rs.getInt("roll"));
             student.put("name", rs.getString("name"));
             student.put("fname", rs.getString("fname"));
             student.put("email", rs.getString("email"));
             student.put("course", rs.getString("course"));
             student.put("phone", rs.getString("phone"));
             student.put("status", rs.getString("status"));
             student.put("pass", rs.getString("pass"));
             return student;
            }else{
                return null;
            }
    }
    public String insertStudent(int r, String n, String f, String e, String c, String p, String pw) throws SQLException {
      
        PreparedStatement insertStudent=con.prepareStatement("insert into Student values(?,?,?,?,?,?,?,?)");
        insertStudent.setInt(1,r);
        insertStudent.setString(2,n);
        insertStudent.setString(3,f);
        insertStudent.setString(4,e);
        insertStudent.setString(5,c);
        insertStudent.setString(6,p);
        insertStudent.setString(7,"Current");
        insertStudent.setString(8,pw);
        int x=insertStudent.executeUpdate();
           if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    
    }

    public String updateStudent(int or, int r, String n, String f, String e, String c, String p, String s) throws SQLException {
         PreparedStatement updateStudent=con.prepareStatement("update Student set roll=?,name=?,fname=?,email=?,course=?,phone=?,status=? where roll=?");
         updateStudent.setInt(1,r);
         updateStudent.setString(2,n);
        updateStudent.setString(3,f);
        updateStudent.setString(4,e);
        updateStudent.setString(5,c);
        updateStudent.setString(6,p);
        updateStudent.setString(7,s);
        updateStudent.setInt(8,or);
        int x=updateStudent.executeUpdate();
        if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }
     public ArrayList<HashMap> getAllFaculty()throws SQLException{
        PreparedStatement getAllFaculty=con.prepareStatement("select * from faculty");
        ResultSet rs=getAllFaculty.executeQuery();
         ArrayList allFaculty =new ArrayList();
         while(rs.next()){
             HashMap faculty =new HashMap();
             faculty.put("fid", rs.getString("fid"));
             faculty.put("name", rs.getString("name"));
             faculty.put("email", rs.getString("email"));
             faculty.put("phone", rs.getString("phone"));
             faculty.put("pass", rs.getString("pass"));
             allFaculty.add(faculty);
         }
         return allFaculty;
     }
     public String getFacultyCourse(String fid) throws SQLException{
      PreparedStatement getFacultyCourse=con.prepareStatement("select * from faculty_course where fid=?");
         getFacultyCourse.setString(1,fid);
        ResultSet rs=getFacultyCourse.executeQuery();
        String course="";
        while(rs.next())
        {
          course=course+","+rs.getString("course");
        }
        return course;
     }
     
     
       public HashMap getFaculty(String fid)throws SQLException{
            PreparedStatement getFaculty=con.prepareStatement("select * from faculty where fid=?");
            getFaculty.setString(1,fid);
            ResultSet rs=getFaculty.executeQuery();
            if(rs.next()){
             HashMap faculty =new HashMap();
             faculty.put("fid", rs.getString("fid"));
             faculty.put("name", rs.getString("name"));
             faculty.put("email", rs.getString("email"));
             faculty.put("phone", rs.getString("phone"));
             faculty.put("pass", rs.getString("pass"));
             return faculty;
            }else{
                return null;
            }
    }

    public String insertFaculty(String fid, String n, String e, String p, String pw) throws SQLException {
        PreparedStatement insertFaculty=con.prepareStatement("insert into faculty values(?,?,?,?,?)");
         insertFaculty.setString(1,fid);
        insertFaculty.setString(2,n);
        insertFaculty.setString(3,e);
        insertFaculty.setString(4,p);
        insertFaculty.setString(5,pw);
        int x=insertFaculty.executeUpdate();
        if(x!=0){
        return "success";
    }else{
        return "failed";
    }
    }
    public String insertFacultyCourse(String fid,String c[]) throws SQLException{
        PreparedStatement insertFacultyCourse=con.prepareStatement("insert into faculty_course values(?,?)");
        int x=0;
        for(int i = 0; i < c.length; i++){
        insertFacultyCourse.setString(1,fid);
        insertFacultyCourse.setString(2,c[i]);
        x=insertFacultyCourse.executeUpdate();
        }
        if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    
    }
     public ArrayList<String> getAllFacultyIDByCourse(String c)throws SQLException{
        PreparedStatement getFacultyID=con.prepareStatement("select fid from faculty_course where course=?");
        getFacultyID.setString(1, c);
        ResultSet rs=getFacultyID.executeQuery();
         ArrayList allFaculty =new ArrayList();
         while(rs.next()){
             allFaculty.add(rs.getString("fid"));
         }
         return allFaculty;
     }
      public ArrayList<String> getFacultyAllCourse(String fid)throws SQLException{
            PreparedStatement getFacultyCourse=con.prepareStatement("select * from faculty_course where fid=?");
        getFacultyCourse.setString(1, fid);
        ResultSet rs=getFacultyCourse.executeQuery();
         ArrayList facultyAllCourse =new ArrayList();
         while(rs.next()){
             facultyAllCourse.add(rs.getString("course"));
         }
         return facultyAllCourse;
     }
     public ArrayList<HashMap> getSUpload(int roll)throws SQLException{
            PreparedStatement getSUpload=con.prepareStatement("select * from suploads where roll=?");
            getSUpload.setInt(1,roll);
            ResultSet rs=getSUpload.executeQuery();
            ArrayList sUpload= new ArrayList();
            while(rs.next()){
             HashMap upload =new HashMap();
             upload.put("roll", rs.getInt("roll"));
             upload.put("suid", rs.getInt("suid"));
             upload.put("sub", rs.getString("sub"));
             upload.put("fnames", rs.getString("fnames"));
             upload.put("filename", rs.getString("filename"));
             upload.put("udate", rs.getString("udate"));
             sUpload.add(upload);
            }
             return sUpload;
            
    }
      public ArrayList<HashMap> getAllSUpload()throws SQLException{
            PreparedStatement getAllSUpload=con.prepareStatement("select * from suploads ");
            ResultSet rs=getAllSUpload.executeQuery();
            ArrayList sUpload= new ArrayList();
            while(rs.next()){
             HashMap upload =new HashMap();
             upload.put("roll", rs.getInt("roll"));
             upload.put("suid", rs.getInt("suid"));
             upload.put("sub", rs.getString("sub"));
             upload.put("fnames", rs.getString("fnames"));
             upload.put("filename", rs.getString("filename"));
             upload.put("udate", rs.getString("udate"));
             sUpload.add(upload);
            }
             return sUpload;
            
    }

    public String insertSUpload(int roll, String s, String fns, InputStream pis, String fileName, String cd) throws SQLException {
        PreparedStatement insertSUpload=con.prepareStatement("insert into suploads (roll,sub,fnames,ufile,filename,udate) values(?,?,?,?,?,?)");

            insertSUpload.setInt(1, roll);
            insertSUpload.setString(2, s);
            insertSUpload.setString(3, fns);
            insertSUpload.setBinaryStream(4, pis);
            insertSUpload.setString(5, fileName);
            insertSUpload.setString(6, cd);
            int x=insertSUpload.executeUpdate();
            if(x!=0){
                return "success";
            }else{
                return "failed";
            }
    }

    public String insertChat(String i, String r, String m, String dt) throws SQLException {
            PreparedStatement insertChat=con.prepareStatement("insert into chat (sid,rid,msg,mdate) values(?,?,?,?)");
            insertChat.setString(1, i);
            insertChat.setString(2, r);
            insertChat.setString(3, m);
            insertChat.setString(4, dt);
            int x=insertChat.executeUpdate();
            if(x!=0){
            return "success";
            }else{
                return "failed";
            }
    }

    public String insertFNotice(String ns, String nb, String cd, String ed, String cr, String fid, InputStream pis, String fileName) throws SQLException {
        PreparedStatement insertFNotice=con.prepareStatement("insert into fnotice (sub,body,ufile,filename,pdate,expdate,course,fid)values(?,?,?,?,?,?,?,?)");
            insertFNotice.setString(1, ns);
            insertFNotice.setString(2, nb);
            insertFNotice.setString(5, cd);
            insertFNotice.setString(6, ed);
            insertFNotice.setString(7, cr);
            insertFNotice.setString(8, fid);
            insertFNotice.setBinaryStream(3, pis);
            insertFNotice.setString(4, fileName);
            int x=insertFNotice.executeUpdate();
            if(x!=0){
            return "success";
            }else{
                return "failed";
            }
    }
    
    
    public ArrayList getChat(String roll,String name) throws SQLException{
        PreparedStatement getChat=con.prepareStatement("select * from chat where sid=? or rid=? ");
        getChat.setString(1, roll);
        getChat.setString(2, name);
         ResultSet rs=getChat.executeQuery();
            ArrayList allChat= new ArrayList();
            while(rs.next()){
             HashMap chat =new HashMap();
             chat.put("cid", rs.getInt("cid"));
             chat.put("sid", rs.getString("sid"));
             chat.put("rid", rs.getString("rid"));
             chat.put("msg", rs.getString("msg"));
             chat.put("mdate", rs.getString("mdate"));
             allChat.add(chat);
            }
             return allChat;
    }

    public String updateFaculty(String fid, String n, String e, String p, String ofid) throws SQLException {
        PreparedStatement updateFaculty=con.prepareStatement("update faculty set fid=?,name=?,email=?,phone=? where fid=?");

        updateFaculty.setString(2,n);
        updateFaculty.setString(3,e);
        updateFaculty.setString(4,p);
        updateFaculty.setString(5,ofid);
        updateFaculty.setString(1,fid);
       int x= updateFaculty.executeUpdate();
       if(x!=0){
            return "success";
            }else{
                return "failed";
            }
    }

    public String updateFacultyCourseFid(String fid, String ofid) throws SQLException {
        PreparedStatement updateFacultyCourseFid=con.prepareStatement("update faculty_course set fid=? where fid=?");
        updateFacultyCourseFid.setString(1, fid);
        updateFacultyCourseFid.setString(2, ofid);
        int x= updateFacultyCourseFid.executeUpdate();
        if(x!=0){
        return "success";
        }else{
            return "failed";
        }
    }

    public String deleteFacultyCourse(String fid) throws SQLException {
       PreparedStatement deleteFacultyCourse=con.prepareStatement("delete from faculty_course where fid=?");

            deleteFacultyCourse.setString(1, fid);
           int x= deleteFacultyCourse.executeUpdate(); 
           if(x!=0){
        return "success";
        }else{
            return "failed";
        }
    }
}


 
