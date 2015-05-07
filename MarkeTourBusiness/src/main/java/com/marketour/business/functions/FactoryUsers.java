package com.marketour.business.functions;

import java.util.ArrayList;
import java.util.List;

import com.marketour.business.Cliente;

public class FactoryUsers 
{
	FunctionsUsers functionEntidad;
	public FactoryUsers(String entidad)
	{
		if(entidad=="Cliente")
		{
			functionEntidad=new FunctionsClientes();
		}
		else if(entidad=="Proveedor")
		{
			functionEntidad=new FunctionsProveedores();
		}
		else if(entidad=="Administrador")
		{
			functionEntidad=new FunctionsAdministrador();
		}
	}
	public Object Consultar(int id) 
	{
		return functionEntidad.Consultar(id);
	}

	public List<Object> ConsultarLista() 
	{
		return functionEntidad.ConsultarLista();
	}


}
