<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	<jsp:include page="css\bootstrap.css"></jsp:include>
	<jsp:include page="css\index.css"></jsp:include>
</style>
<title>Login</title>
</head>

<body>

<div class="container text-center" style="width:100%;height:100%">

  <div class="row" style="width:100%;height:100%">
  
    <div class="col">
      	<div>
			<h1 class="titulo">Iniciar sesión</h1>
		</div>

		<form action="IndexServlet" method="post" name="login" class="form-login">
			  <div class="form-group">
			    <input type="text" class="form-control" name="user" aria-describedby="emailHelp" placeholder="Usuario">
			  </div>
			  <div class="form-group">
			    <input type="password" class="form-control" name="password" placeholder="Contraseña">
	    		<%if(request.getAttribute("loginFail") != null){ %>
    				<label style="color:red;"><b>Usuario y/o contraseña incorrectos</b></label>
    			<%}%>
			  </div>
				<input type="submit" value="Ingresar" class="btn btn-primary" name="btnIngresar">
		</form>
    </div>

    <div class="col col-background"></div>
    
  </div>
  
</div>


	

	
<jsp:include page="./includes/Footer.jsp"></jsp:include>
</body>
</html>