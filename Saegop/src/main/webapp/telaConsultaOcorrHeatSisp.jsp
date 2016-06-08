<%@page import="java.util.Arrays"%>
<%@page import="br.com.furb.model.NaturezaOcorrenciaSisp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Consulta de ocorrências policiais</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/dashboard.css" rel="stylesheet">
<link href="css/datepicker.css" rel="stylesheet">
<link href="css/infoWindowsGmaps.css" rel="stylesheet">
</head>
<body>

	<div class="wrapper" role="main">
		
		<jsp:include page="header.jsp" />
	
		<div class="container-fluid">		
			<div class="row">
	        	<div class="col-sm-3 col-md-2 sidebar" id="tela-lateral">
	        		<h1 class="page-header">Filtros
	        		</h1>
        			<label>Dt inicio:</label>
		        	<input type="text" id="dtInicio" style=" width: 120px;" >
		        	
		        	<p> </p>
		        	
		        	<label>Data fim:</label>
		        	<input type="text" id="dtFim" style=" width: 120px;" >
		        	
		        	<p> </p>
		        	
		        	<select class="form-control" id="idCrime">
						<option value=""  selected>Crimes</option>
						<%
						List<NaturezaOcorrenciaSisp> naturezas = Arrays.asList(NaturezaOcorrenciaSisp.values());
							for (NaturezaOcorrenciaSisp naturza : naturezas) {
								out.print("<option value='" + naturza.getNatureza() + "'>" + naturza.toString() + "</option>");
							}
						%>
					</select>
		        	
		        	<p> </p>
		        	
		        	<div style="text-align: center">
	        			<button class="btn btn-lg btn-primary btn-block"
							id="buscarDadosHeatsSisp" >Buscar dados
						</button>
					</div>
					
					
	        	</div>
		        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		         	<h1 class="page-header">Mapa de ocorrências</h1>
		         	<div class="table-responsive">
			          	<div id="mapa" style="height: 500px; width: 100%; position: relative;">
			          	</div>
			          	<!-- Maps API Javascript -->
			        	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDmBHVNZkiSU8JyL16GF85EGE8qv3D42HM&libraries=visualization&sensor=true_or_false"></script>
			        	<div id="map-canvas"></div>
			        	
					</div>				        			          		
		         </div>
	      </div>	
		</div>
	</div>
	
	<!-- Arquivo de inicialização do mapa -->
    <script src="js/jquery.min.js"></script>
    <script src="js/mapa.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    
    <script>
    	var data = new Date();
    	$(function(){
    		$('#dtInicio').datepicker({
    			 format: "dd/mm/yyyy",
    	         language: "pt-BR"
    		});
    		$('#dtFim').datepicker({
   			 format: "dd/mm/yyyy",
   	         language: "pt-BR"
   		});
    		
    		var dia    = data.getDate();
    		var mes    = data.getMonth()+1;
    		var ano    = data.getFullYear();
    		
    		if(dia<10){
    			dia='0'+dia;
    	    } 
    	    if(mes<10){
    	    	mes='0'+mes;
    	    } 
    		
    		document.getElementById('dtInicio').value = '01/'+ mes+'/'+ ano;
    		document.getElementById('dtFim').value = dia+'/'+ mes+'/'+ ano;
    		
    	});
    
    </script>

</body>
</html>