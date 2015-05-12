package com.marketour.business.functions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.marketour.business.Compra;
import com.marketour.persistence.RepositoryCompra;

public class FunctionsReports {
	
	public static List<com.marketour.business.Compra> ConsultarCompraXFechas(Date fechaInicio, Date fechaFin) 
	{
		com.marketour.persistence.Repository<com.marketour.domain.Compra> repository = new com.marketour.persistence.Repository<com.marketour.domain.Compra>(com.marketour.domain.Compra.class);

		List<com.marketour.domain.Compra> lstsCompras =repository.FindByColumn("fecha>'"+fechaInicio+"' AND fecha<'"+fechaFin+"'");
		//List<com.marketour.domain.Compra> lstsCompras =repository.FindByColumn("fecha>'"+fechaInicio.getYear()+"-"+fechaInicio.getMonth()+"-"+fechaInicio.getDay()+"' AND fecha<'"+fechaFin.getYear()+"-"+fechaFin.getMonth()+"-"+fechaFin.getDay()+"'");
																				//"fecha>'"+ano+"-"+mes+"-1'"+ ", fecha<'"+ano+"-"+mes+"-30'"
		List<com.marketour.business.Compra> lstCompras= new ArrayList<Compra>();
		for (com.marketour.domain.Compra compra : lstsCompras) 
		{
			lstCompras.add(Compra.ConvertToBCompra(compra));
		}
		return lstCompras;
	}
	public static List<com.marketour.business.Compra> ConsultarCompraXLocalizacion(int idciudad) 
	{		
		RepositoryCompra repository=new RepositoryCompra();
		List<com.marketour.domain.Compra> lstsCompras =repository.FindByUbicacion(idciudad);

		List<com.marketour.business.Compra> lstCompras= new ArrayList<Compra>();
		for (com.marketour.domain.Compra compra : lstsCompras) 
		{
			lstCompras.add(Compra.ConvertToBCompra(compra));
		}
		return lstCompras;
	}
	public static List<com.marketour.business.Compra> ConsultarCompraXLocalizacionCalificada(int idciudad) 
	{		
		RepositoryCompra repository=new RepositoryCompra();
		List<com.marketour.domain.Compra> lstsCompras =repository.FindByUbicacionCalificada(idciudad);

		List<com.marketour.business.Compra> lstCompras= new ArrayList<Compra>();
		for (com.marketour.domain.Compra compra : lstsCompras) 
		{
			lstCompras.add(Compra.ConvertToBCompra(compra));
		}
		return lstCompras;
	}
	public static List<com.marketour.business.Compra> ConsultarCompraXPaqueteCalificada(int idpaquete) 
	{		
		RepositoryCompra repository=new RepositoryCompra();
		List<com.marketour.domain.Compra> lstsCompras =repository.FindByPaqueteCalificado(idpaquete);

		List<com.marketour.business.Compra> lstCompras= new ArrayList<Compra>();
		for (com.marketour.domain.Compra compra : lstsCompras) 
		{
			lstCompras.add(Compra.ConvertToBCompra(compra));
		}
		return lstCompras;
	}

}
