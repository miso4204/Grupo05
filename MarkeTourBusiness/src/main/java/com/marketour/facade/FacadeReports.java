package com.marketour.facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.marketour.business.Categoria;
import com.marketour.business.Ciudad;
import com.marketour.business.Compra;
import com.marketour.business.Departamento;
import com.marketour.business.PlanAlimentacion;
import com.marketour.business.Producto;
import com.marketour.business.TipoContenido;
import com.marketour.business.functions.FunctionsReports;
import com.marketour.domain.Alimentacion;
import com.marketour.domain.Alojamiento;
import com.marketour.domain.Contenido;
import com.marketour.domain.Tour;
import com.marketour.domain.Trasporte;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryCompra;
import com.marketour.persistence.RepositoryProduct;
public class FacadeReports {
	
	public static List<com.marketour.business.Compra> ConsultarCompraXFechas(Date fechaInicio, Date fechaFin) 
	{
		if(tieneReporteVentasXPeriodo())
		{
			return FunctionsReports.ConsultarCompraXFechas(fechaInicio, fechaFin);
		}
		else
		{
			return null;
		}
		
	}
	public static List<com.marketour.business.Compra> ConsultarCompraXLocalizacion(int idciudad) 
	{		
		if(tieneReporteVentasXUbicacion())
		{
			return FunctionsReports.ConsultarCompraXLocalizacion(idciudad);
		}
		else
		{
			return null;
		}
	}
	public static List<com.marketour.business.Compra> ConsultarCompraXLocalizacionCalificada(int idciudad) 
	{		
		if(true)
		{
			return FunctionsReports.ConsultarCompraXLocalizacionCalificada(idciudad);
		}
		else
		{
			return null;
		}
	}
	public static List<com.marketour.business.Compra> ConsultarCompraXPaqueteCalificada(int idpaquete) 
	{		
		if(tieneReportePaquetes())
		{
			return FunctionsReports.ConsultarCompraXPaqueteCalificada(idpaquete);
		}
		else
		{
			return null;
		}
	}

	private static boolean tieneReporteVentasXUbicacion(){

		boolean ventas = false;
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures");
			System.out.println("Path config desde facade: " + System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures"));
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				
				if(line.trim().equalsIgnoreCase("ReportByLocation")){
					System.out.println("Permite VentasXUbicacion!");
					ventas = true;
				}
			}
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ventas){
			System.out.println("Permite VentasXUbicacion!");
			return true;
		} else {
			System.out.println("No Permite VentasXUbicacion!");
			return false;
		}
	}
	private static boolean tieneReporteVentasXPeriodo(){

		boolean ventas = false;
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures");
			System.out.println("Path config desde facade: " + System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures"));
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				
				if(line.trim().equalsIgnoreCase("ReportByPeriod")){
					System.out.println("Permite ReportByPeriod!");
					ventas = true;
				}
			}
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ventas){
			System.out.println("Permite ReportByPeriod!");
			return true;
		} else {
			System.out.println("No Permite ReportByPeriod!");
			return false;
		}
	}
	private static boolean tieneReportePaquetes(){

		boolean ventas = false;
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures");
			System.out.println("Path config desde facade: " + System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures"));
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				
				if(line.trim().equalsIgnoreCase("Package")){
					System.out.println("Permite Paquetes!");
					ventas = true;
				}
			}
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ventas){
			System.out.println("Permite Paquetes!");
			return true;
		} else {
			System.out.println("No Permite Paquetes!");
			return false;
		}
	}

}
