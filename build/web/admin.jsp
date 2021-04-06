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
            		<h4 class="text-center">Notice Details</h4><hr/>
                	<table id="mydata" class=" table table-striped table-bordered hover" cellspacing="0" width="100%">
                    	<thead>
                          <tr>
                              <th>ID</th>
                              <th>Subject</th>
                              <th>Body</th>
                              <th>File Name</th>
                              <th>Posted Date</th>
                              <th>Expiry Date</th>
                          </tr>
                      	</thead>
                      
                      	<tbody>
                         <%
                          String cd=null; 
                            db.DbConnection db=new db.DbConnection();
                            java.util.ArrayList<HashMap> allNotice=db.getAllANotice(cd);
                            Iterator i=allNotice.iterator();
                            while(i.hasNext()){
                                HashMap notice=(HashMap)i.next();
                              int nid=(int)notice.get("nid");
                        %>
                            <tr>
                              <td><%= nid %></td>
                              <td><%=notice.get("sub") %></td>
                              <td><%= notice.get("body")%></td>
                              <td><a href="DownloadFile?nid=<%=nid%>"><%= notice.get("filename") %></a></td>
                              <td><%= notice.get("pdate") %></td>
                              <td><%= notice.get("expdate") %></td>
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
                    <div class="box text-center"><br>
                            <h4 class="text-center">Update Notice</h4><hr/>
                    	<br>
                        <form action="GetANotice" method="post"  class="form-inline" data-toggle="validator" role="form">
                            <div class="form-group">
                              <input type="text" class="form-control" name="nid" placeholder="Notice ID" required>
                            </div>
                            <button type="submit" class="btn btn-primary">GO</button>
                      	</form>
                       <%
                           HashMap uNotice=(HashMap)session.getAttribute("uNotice");
                           if(uNotice!=null){
                       %> 
                        <hr>
                        <form action="ModifyANotice" enctype="multipart/form-data" method="post" data-toggle="validator" class="form-horizontal" role="form">   
                        	<br><b>Notice ID: </b> <%=uNotice.get("nid")%><br> <br>                         
                        	<div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                    <input type="text" class="form-control" value="<%=uNotice.get("sub")%>" name="nsub" placeholder="Notice Subject" required >
                                    <input type="hidden" value="<%=uNotice.get("nid")%>" name="nid" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <textarea class="form-control"  rows="4" name="nbody" required ><%=uNotice.get("body")%></textarea>
                                </div>
                            </div>
                            <div class="form-group">                                
                            	<div class="col-sm-10 col-sm-offset-1">
                            		<b>File Uploaded: </b> <br>
                                  <input type="file" class="form-control" name="file" >
                                </div>
                            </div>
                            <b>Expiry Date: </b><%=uNotice.get("expdate")%> <br>
                            <div class='input-group date col-sm-10 col-sm-offset-1' id='datepicker1'>
                              <input type='text' value="<%=uNotice.get("expdate")%>" class="form-control" name='edate' placeholder="Change Notice Expiry Date" required/>
                              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <br/>
                            
                            <div class="form-group"> 
                                <div class="col-sm-offset-1 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Update</button> &nbsp;&nbsp;&nbsp;
                                   <a href="DelANotice.jsp?nid=<%=uNotice.get("nid")%>" class="btn btn-danger">Delete</a>
                                </div>
                            </div>
                        </form>
                        <%}
                        session.setAttribute("uNotice",null);
                        %>
                    </div>
                </div>
               
                <div class=" col-sm-4 pull-right">
                	<div class="box">
                    	<h5 class="text-center"> New Notice </h5>
                        <br>
                        <form action="AddANotice" enctype="multipart/form-data" method="post" data-toggle="validator" class="form-horizontal" role="form">
              				
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <input type="text" class="form-control" name="nsub" placeholder="Notice Subject" required>
                                </div>
                                <span class=" text-center help-block with-errors"></span>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <textarea class="form-control" rows="4" name="nbody" placeholder="Notice Body" required ></textarea>
                                </div>
                                <span class=" text-center help-block with-errors"></span>
                            </div>
                            <div class="form-group">                                
                            	<div class="col-sm-10 col-sm-offset-1">
                                	Upload File:<br>
                                  <input type="file" class="form-control" name="file" >
                                </div>
                            </div>
                            <div class='input-group date col-sm-10 col-sm-offset-1' id='datepicker2'>
                              <input type='text' class="form-control" name='edate' placeholder="Enter Notice Expiry Date" required/>
                              <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <span class=" text-center help-block with-errors"></span>
                            <br/>
                            
                            <div class="form-group"> 
                                <div class="col-sm-offset-1 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
       
        <div class="footer">
            <div class="container text-center">
                 Design & Develop By Rohit Singh, Himanshu Singh,Bharti Singh
            </div>
        </div>
    </div>
	

  <script type="text/javascript" src="includes/js/jquery-1.8.2.min.js"></script>
  <script src="DataTables/datatables.min.js"></script>
  <script src="includes/js/validator.js"></script>
  <script src="includes/js/jquery.multiselect.js"></script>
  <script src="datetimepicker/js/moment.min.js"></script>
  <script src="datetimepicker/js/datetimepicker.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script type="text/javascript">

      $(document).ready(function() {
      $('#mydata').dataTable();
    });

    $(function () {
      $('#datepicker1').datetimepicker({
        format: 'DD/MM/YYYY',
        minDate: new Date()
      });
      $('#datepicker2').datetimepicker({
        format: 'DD/MM/YYYY',
        minDate: new Date()
      });
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