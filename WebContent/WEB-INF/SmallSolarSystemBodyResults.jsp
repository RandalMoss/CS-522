<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="resources/css/tooplate_style.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="resources/css/nivo-slider.css" type="text/css" media="screen">
<script type="text/javascript">
window.onload=function() {
	   document.getElementById("anchor").onclick=function() {
	     var myForm = document.createElement("form");
	     myForm.action=this.href;// the href of the link
	     myForm.target="myFrame";
	     myForm.method="POST";
	     myForm.submit();
	     return false; // cancel the actual link
	   };
	 };
</script>
</head>
<body>
<div id="tooplate_body_wrapper">
    <div id="tooplate_wrapper">
            
        <div id="tooplate_header">
            
            <div id="tooplate_menu">
                <ul>
                    <li><a href="MainPage" class="current">Home</a></li>
                    <li><a href="about.html">About Us</a></li>
                    <li><a href="blog.html">Blog</a></li>
                    <li><a href="gallery.html">Gallery</a></li>
                    <li><a href="contact.html">Contact</a></li>
                </ul>    	
            </div> <!-- end of tooplate_menu -->
        </div> <!-- end of forever header -->
        
        
         <!-- end of middle -->
        
         <!-- end of forever fp services -->
        
        <div id="tooplate_main">
        
            <div class="col_w580 float_l">
                <h2>Results</h2> 
                
				Name: ${object.name} <br/>
				Type: ${object.type } <br/>
				Orbits: <a id = "anchor" href="SearchResults?select=stars&query=${object.starName}">${object.starName}</a> <br/>
				<a href=" ${object.url }">More Information</a>
                
                
                <div class="cleaner"></div>
                
            </div>
            
            <div class="col_w280 float_r">
                <h2>Search</h2>
                <form method="post" action = "SearchResults">
                	<select name="select">
                		<option value="blackholes">Black Hole</option>
                		<option value="galaxies">Galaxy</option>
                		<option value="solar_systems">Solar System</option>
                		<option value="stars">Star</option>
                		<option value="planets">Planet</option>
                		<option value="moons">Moon</option>
                		<option value="Small_Solar_System_Bodies">Small Solar System Bodies</option>
                	</select>
                <input type="text" name = "query" />
                <input type="submit" name = "submit" value="Search" />
                </form>
            </div>
            
            <div class="cleaner"></div>
        </div> <!-- end of main -->
        
        <div id="tooplate_main_bottom"></div>
        <div class="cleaner"></div>
        
        <div id="tooplate_footer">
        
            Copyright © 2048 <a href="#">Company Name</a> | Design: <a href="http://www.tooplate.com">tooplate</a>
            
        </div>
    </div> <!-- end of forever wrapper -->
</div> <!-- end of forever body wrapper -->
</body>
</html>