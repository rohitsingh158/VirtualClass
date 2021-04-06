<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%
    String aid=(String)session.getAttribute("aid");
    String aname=(String)session.getAttribute("aname");
    if(aid!=null && aname!=null)  
    {%>
<html>
    <head>
		
    <!-- Website Title & Description for Search Engine purposes -->
    <title></title>
    <meta name="description" content="">

    <!-- Mobile viewport optimized -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Bootstrap CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-glyphicons.css" rel="stylesheet">
    <link href="datetimepicker/css/datetimepicker.min.css" rel="stylesheet"  />
    <link href="DataTables/datatables.min.css" rel="stylesheet"/>
    
    <!-- Custom CSS -->
    <link href="includes/css/styles.css" rel="stylesheet">
    
    <!-- Include Modernizr in the head, before any other Javascript -->
    <script src="includes/js/modernizr-2.6.2.min.js"></script>
		
	</head>
	<body>
	
	<div class="bg">
    	<div class="header">
            <div class="container  text-center">
                <a class="logo-hyper" href="index.jsp"><b>Virtual Class</b></a>
            </div>
        </div>
        <div class="container ">
        	<div class="row">
                    <div class="menu col-md-12 ">
                            <div class="menu1 pull-left">
                            <a href='a-student.jsp'>Student</a> &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href='a-faculty.jsp'>Faculty</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href='admin.jsp'>Notice</a>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href='Logout'>Logout</a>
                        </div>
                        <div class="menu1 pull-right">
                            <p>Welcome &nbsp;&nbsp; <b><%=aname%></b></p>
                        </div>
                    </div>
                </div>
               
                 <%
                    String msg=(String)session.getAttribute("msg");
                    if(msg!=null)  
                    {
                %>
                <div class="panel panel-primary">
                    <div class="panel-heading text-center">
                        <p><%=msg%></p>
                    </div>
                </div>
                <%
                    session.setAttribute("msg", null);
                    }
                %>
               
        	<div class="row">
            	<div class="notice-table col-md-12 pull-left">
            		<h4 class="text-center">Student Details</h4><hr/>
                	
                            
                        <table id="mydata" class=" table table-striped table-bordered hover" cellspacing="0" width="100%">
                    	<thead>
                          <tr>
                              <th>Roll</th>
                              <th>Name</th>
                              <th>Father Name</th>
                              <th>Email</th>
                              <th>Course</th>
                              <th>Phone</th>
                              <th>Status</th>
                          </tr>
                      	</thead>
                        <tbody>
                            <%
                            db.DbConnection db =new db.DbConnection();
                             java.util.ArrayList<HashMap> allStudent=db.getAllStudent();
                            Iterator i=allStudent.iterator();
                            while(i.hasNext()){
                                HashMap student=(HashMap)i.next();
                              int roll=(int)student.get("roll");
                        %>
                            <tr>
                              <td><%=student.get("roll")%></td>
                              <td><%=student.get("name")%></td>
                              <td><%=student.get("fname")%></td>
                              <td><%=student.get("email")%></td>
                              <td><%=student.get("course")%></td>
                              <td><%=student.get("phone")%></td>
                              <td><%=student.get("status")%></td>
                            </tr>
                       <%
                       }
                       %>
                          
                      	</tbody>
                  	</table>
              	</div>
            </div>
      
            <div class="row"> 
            	<div class=" col-md-8 ">
                    <div class="box text-center"><br><br>
                         <h4 class="text-center">Update Student</h4><hr/>
                        
                    <br>
                    <form action="GetStudent" method="post" class="form-inline" data-toggle="validator" role="form">
                        <div class="form-group">
                          <input type="text" class="form-control" name="roll" pattern="^[_0-9 ]{1,}$" value="" required>
                        </div>
                        <button type="submit" class="btn btn-primary">GO</button>
                    </form>
                        <%
                           HashMap uStudent=(HashMap)session.getAttribute("uStudent");
                           if(uStudent!=null){
                       %> 
                    <hr>
                    <form action="ModifyStudent" method="post" data-toggle="validator" class="form-horizontal" role="form">                          
                        <br><b>Student RollNo.: </b> <%=uStudent.get("roll")%><br> <br>       
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-1">
                                <input type="hidden" name="oroll" value="<%=uStudent.get("roll")%>"/>
                                 <p align="left"><font size="3"><b>Roll No.</b></font></p>
                              <input type="text" class="form-control" name="roll" pattern="^[_0-9 ]{1,}$" value="<%=uStudent.get("roll")%>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-1">
                                 <p align="left"><font size="3"><b>Name</b></font></p>
                              <input type="text" class="form-control" name="name" pattern="^[_A-z ]{1,}$" value="<%=uStudent.get("name")%>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-1">
                                
                                 <p align="left"><font size="3"><b>Father's Name</b></font></p>
                              <input type="text" class="form-control" name="fname" pattern="^[_A-z ]{1,}$" value="<%=uStudent.get("fname")%>" required>
                            </div>
                        </div>
                           
                            <div class="form-group">
                          
                            <div class="col-sm-10 col-sm-offset-1">
                                 
                                <p align="left"><font size="3"><b>Email</b></font></p>
                              <input type="email" class="form-control" name="email" value="<%=uStudent.get("email")%>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-1">
                                 <p align="left"><font size="3"><b>Course</b></font></p>
                                    <select name="course" class="form-control">
                                        <option value="<%=uStudent.get("course")%>"><%=uStudent.get("course")%></option>
                                        <option value="BTech-CS1">BTech-CS1</option>
                                        <option value="BTech-CS2">BTech-CS2</option>
                                        <option value="BTech-CS3">BTech-CS3</option>
                                        <option value="BTech-CS4">BTech-CS4</option>
                                        <option value="BTech-IT1">BTech-IT1</option>
                                        <option value="BTech-IT2">BTech-IT2</option>
                                        <option value="BTech-IT3">BTech-IT3</option>
                                        <option value="BTech-IT4">BTech-IT4</option>
                                        <option value="BTech-EC1">BTech-EC1</option>
                                        <option value="BTech-EC2">BTech-EC2</option>
                                        <option value="BTech-EC3">BTech-EC3</option>
                                        <option value="BTech-EC4">BTech-EC4</option>
                                        <option value="BTech-EE1">BTech-EE1</option>
                                        <option value="BTech-EE2">BTech-EE2</option>
                                        <option value="BTech-EE3">BTech-EE3</option>
                                        <option value="BTech-EE4">BTech-EE4</option>
                                        <option value="BTech-ME1">BTech-ME1</option>
                                        <option value="BTech-ME2">BTech-ME2</option>
                                        <option value="BTech-ME3">BTech-ME3</option>
                                        <option value="BTech-ME4">BTech-ME4</option>
                                        <option value="MCA1">MCA1</option>
                                        <option value="MCA2">MCA2</option>
                                        <option value="MCA3">MCA3</option>
                                        <option value="MBA1">MBA1</option>
                                        <option value="MBA2">MBA2</option>
                                </select>                                
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-1">
                                 <p align="left"><font size="3"><b>Phone</b></font></p>
                              <input type="text" class="form-control" name="phone" pattern="^[_0-9 ]{1,}$" minlength="10" maxlength="10" value="<%=uStudent.get("phone")%>" required>
                            </div>
                        </div>
                            
                             
                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-1">
                                 <p align="left"><font size="3"><b>Status :</b> <%=uStudent.get("status")%></font></p>
                                <select name="status" class="form-control">
                                        <option value="Current">Current</option>
                                            <option value="Passout">Passout</option>
                                            <option value="Current">Current</option>
                                            <option value="Passout">Passout</option>
                                    
                                </select>                                
                            </div>
                          </div>
                        <div class="form-group"> 
                            <div class="col-sm-offset-1 col-sm-10">
                              <button type="submit" class="btn btn-primary">Update</button> &nbsp;&nbsp;&nbsp;&nbsp;
                              <a href="DeleteStudent?roll=<%=uStudent.get("roll")%>" class="btn btn-danger">Delete</a>
                            </div>
                        </div>
                    </form>
                    <%}
                        session.setAttribute("uStudent",null);
                        %>
                </div>
            </div>
       
            <div class="row"> 
            <div class=" col-sm-4 pull-right">
                	<div class="box">
                    	<h5 class="text-center"> New Student </h5>
                        <br>
                        <form action="AddStudent" method="post" data-toggle="validator" class="form-horizontal" role="form">
              				
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <input type="text" class="form-control" name="roll" pattern="^[_0-9 ]{1,}$"placeholder="Roll No." required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <input type="text" class="form-control" name="name" pattern="^[_A-z ]{1,}$"placeholder="Name" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <input type="text" class="form-control" name="fname" pattern="^[_A-z ]{1,}$"placeholder="Father Name" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <input type="email" class="form-control" name="email" placeholder="Email ID" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  	<select name="course" class="form-control">
                                        <option value="BTech-CS1">BTech-CS1</option>
                                        <option value="BTech-CS2">BTech-CS2</option>
                                        <option value="BTech-CS3">BTech-CS3</option>
                                        <option value="BTech-CS4">BTech-CS4</option>
                                        <option value="BTech-IT1">BTech-IT1</option>
                                        <option value="BTech-IT2">BTech-IT2</option>
                                        <option value="BTech-IT3">BTech-IT3</option>
                                        <option value="BTech-IT4">BTech-IT4</option>
                                        <option value="BTech-EC1">BTech-EC1</option>
                                        <option value="BTech-EC2">BTech-EC2</option>
                                        <option value="BTech-EC3">BTech-EC3</option>
                                        <option value="BTech-EC4">BTech-EC4</option>
                                        <option value="BTech-EE1">BTech-EE1</option>
                                        <option value="BTech-EE2">BTech-EE2</option>
                                        <option value="BTech-EE3">BTech-EE3</option>
                                        <option value="BTech-EE4">BTech-EE4</option>
                                        <option value="BTech-ME1">BTech-ME1</option>
                                        <option value="BTech-ME2">BTech-ME2</option>
                                        <option value="BTech-ME3">BTech-ME3</option>
                                        <option value="BTech-ME4">BTech-ME4</option>
                                        <option value="MCA1">MCA1</option>
                                        <option value="MCA2">MCA2</option>
                                        <option value="MCA3">MCA3</option>
                                        <option value="MBA1">MBA1</option>
                                        <option value="MBA2">MBA2</option>
                                    </select>                                 
                            	</div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <input type="text" class="form-control" name="phone" minlength="10" maxlength="10" placeholder="Mobile No." required>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <div class="col-sm-offset-1 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
                       
            </div>
               
        </div>
        <div class="footer">
            <div class="container text-center">
                 Design & Develop By Rohit Singh, Himanshu Singh,Bharti Singh
            </div>
        </div>
    </div>
	

	<script type="text/javascript" src="js/jquery-2.2.2.min.js"></script>
  <script src="DataTables/datatables.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script src="includes/js/validator.js"></script>
  <script src="includes/js/jquery.multiselect.js"></script>  
  <script type="text/javascript">

      $(document).ready(function() {
      $('#mydata').dataTable();
    });

    $('select[multiple]').multiselect({
        columns: 2,
        placeholder: 'Select'
    });

  </script>
	
	</body>
</html>

<%}else{
        session.setAttribute("msg","Please Login First!!");
        response.sendRedirect("index.jsp");
    } 
%> 