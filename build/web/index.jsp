

<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
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
            	<div class=" col-sm-4 pull-left">
                	<div class="box">
                    	<h5 class="text-center"> Login Form </h5>
                        <br>
                        <form action="Login" method="post" data-toggle="validator" class="form-horizontal" role="form">
              				<div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  	<select name="utype" class="form-control">
                                        <option value="Student">Student</option>
                                        <option value="Faculty">Faculty</option>
                                        <option value="Admin">Admin</option>
                                    </select>                                
                            	</div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1">
                                  <input type="text" class="form-control" name="uid" class="form-control" placeholder="Enter User Id" required>
                                </div>
                                <span class=" text-center help-block with-errors"></span>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-10 col-sm-offset-1"> 
                                  <input type="password" class="form-control" name="pwd" placeholder="Enter password" required>
                                </div>
                                <span class=" text-center help-block with-errors"></span>
                            </div>
                            <div class="form-group"> 
                                <div class="col-sm-offset-1 col-sm-10">
                                  <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class=" col-md-8 pull-right">
                	<div class="notice-box">
                    	<h5 class="text-center"> Notice Updates </h5>
                        <marquee class="marquee text-center" behavior="scroll" direction="down" scrollamount="2" onmouseover="this.stop();" onmouseout="this.start();">
                            <%
                            LocalDate cDate = LocalDate.now();
                            String cd=cDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); 
                            db.DbConnection db=new db.DbConnection();
                            java.util.ArrayList<HashMap> allNotice=db.getAllANotice(cd);
                            Iterator i=allNotice.iterator();
                            while(i.hasNext()){
                                HashMap notice=(HashMap)i.next();
                            int nid=(int)notice.get("nid");
                            %>
                            <p><b><%= notice.get("sub") %> : &nbsp;&nbsp;</b>
                        <%= notice.get("body")%>
                        <%
                            String filename=(String)notice.get("filename");
                            if(!(filename.trim().equals("")))
                            {
                        %>
                        &nbsp;&nbsp;Download-> <b><a href="DownloadFile?nid=<%=nid%>"><%= filename %></a></p></b>
                        <%        
                            }
                          }
                        %>
                        </marquee>
                    </div>
                </div>
            </div>
            <div class="row">
            	<div class=" col-md-12 ">
                	<div class="about-row text-center">
                    	<h5 class="text-center"> About Us </h5>
                        <p>Welcome to Virtual Classes.Our mission is to make things simpler yet advanced and user friendly.</p></br>
                        <p>Mail Us:-virtualclass2k17@gmail.com</p></br>
                        <p>Contact Us:-7065137360</p>
                        </div>
                </div>
                
            </div>
        </div>
        <br>
        <div class="footer">
            <div class="container text-center">
                 Design & Develop By Rohit Singh, Himanshu Singh,Bharti Singh
            </div>
        </div>
    </div>
	

	<!-- All Javascript at the bottom of the page for faster page loading -->
		
	<!-- First try for the online version of jQuery-->
	<script src="http://code.jquery.com/jquery.js"></script>
	
	<!-- If no online access, fallback to our hardcoded version of jQuery -->
	<script>window.jQuery || document.write('<script src="includes/js/jquery-1.8.2.min.js"><\/script>')</script>
	
	<!-- Bootstrap JS -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<!-- Custom JS -->
	
	<script src="includes/js/script.js"></script>
	<script src="includes/js/validator.js"></script>
	</body>
</html>

