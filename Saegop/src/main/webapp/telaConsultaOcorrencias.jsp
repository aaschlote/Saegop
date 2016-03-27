<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Consulta de ocorrências policiais</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/datepicker.css" rel="stylesheet">
</head>
<body>

	<div class="container-fluid">
	
		<div class="row">
        	<div class="col-sm-3 col-md-3 sidebar">
        	
        		<h1 class="page-header">Filtros</h1>
        		
        		<div clas="form-group">
        			<label class="col-sm-5 control-label">Data inicio:</label>
        		 	<input type="text" id="dtInicio" style=" width: 120px;" >
        		</div>
        		
        		<br>
        			
        			
        		<div clas="form-group">
        			<label class="col-sm-5 control-label">Data fim:</label>
        		 	<input type="text" id="dtFim" style=" width: 120px;" >
        		</div>
          
        	</div>
	        <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-3 main">
	         	<h1 class="page-header">Mapa de ocorrências</h1>
	          
	          	
	          	<div id="mapa" style="height: 500px; width: 100%; position: relative;">
	          	</div>
	          	
	          
	          	<!-- Maps API Javascript -->
	        	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDmBHVNZkiSU8JyL16GF85EGE8qv3D42HM&amp;sensor=false"></script>
	          	
	         </div>
      </div>	
	</div>
	
	<!-- Arquivo de inicialização do mapa -->
    <script src="js/jquery.min.js"></script>
    <script src="js/mapa.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    
    <script>
    	var today = new Date();
    	var todayFomart = new
    	$(function(){
    		$('#dtInicio').datepicker({
    			 format: "dd/mm/yyyy",
    	         language: "pt-BR"
    		});
    		$('#dtFim').datepicker({
   			 format: "dd/mm/yyyy",
   	         language: "pt-BR"
   		});
    		
    		
    	});
    
    </script>

</body>
</html>