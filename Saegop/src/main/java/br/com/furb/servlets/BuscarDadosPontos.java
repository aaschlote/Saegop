package br.com.furb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.inject.spi.InterceptionType;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;
import br.com.furb.model.NaturezaOcorrencia;

@WebServlet("/saegopBuscarPontos")
public class BuscarDadosPontos extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8"); 

		ConnectionDB conection = new ConnectionDB();
		Gson gson = new Gson();
		
		Calendar dtInicio;
		Calendar dtFim;
		
		NaturezaOcorrencia natureza = null;
		
		if	(!request.getParameter("id-Crime").equalsIgnoreCase("")){
			natureza = NaturezaOcorrencia.getNatureza(Integer.parseInt(request.getParameter("id-Crime")));
		}

		try {
			dtInicio = getData(request.getParameter("dt-inicio"));
			dtFim = getData(request.getParameter("dt-fim"));
		} catch (ParseException e) {
			e.printStackTrace();
			return;
		}
		
		String sqlNatureza = "";
		
		if	(natureza != null){
			sqlNatureza = " and a.naturezaOcorrencia = :natureza";
		}
	
		String sql = "FROM AtividadePolicial a where 1=1 " +
		" and a.dtOcorrencia between :dt_inicio and :dt_fim " +
		" and a.latitude <> 0  and a.longitude <> 0" +
		sqlNatureza;
		
		Query query = conection.getManager().createQuery(sql);
		
		query.setParameter("dt_inicio", dtInicio);
		query.setParameter("dt_fim",dtFim);
		
		if	(!sqlNatureza.equalsIgnoreCase("")){
			query.setParameter("natureza", natureza);
		}
		
		List<AtividadePolicial> atividadePoliciais = query.getResultList();
		
		String json = gson.toJson(atividadePoliciais);
		PrintWriter out = response.getWriter();
		out.print(json); 
		
	}
	
	public Calendar getData(String dtInfo) throws ParseException{
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");    
		Calendar dtInfoCal = Calendar.getInstance();
		dtInfoCal.setTime(formatter.parse(dtInfo));
		return dtInfoCal;
	}
}
