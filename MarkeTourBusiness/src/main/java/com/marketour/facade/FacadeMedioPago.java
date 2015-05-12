package com.marketour.facade;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.marketour.business.Ciudad;
import com.marketour.business.Cliente;
import com.marketour.business.Compra;
import com.marketour.business.functions.FunctionsReports;
import com.marketour.domain.FormaPago;
import com.marketour.domain.ItemCompra;
import com.marketour.domain.MedioPago;
import com.marketour.domain.Paquete;
import com.marketour.domain.Producto;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryCompra;

public class FacadeMedioPago 
{
	public static List<com.marketour.business.MedioPago> ConsultarMediodePagoXCliente(int idCliente)
	{
		List<com.marketour.business.MedioPago> lstMedioPago=new ArrayList<com.marketour.business.MedioPago>();
		List<com.marketour.domain.MedioPago> lstdbFormapago=new ArrayList<com.marketour.domain.MedioPago>();
		
		com.marketour.persistence.Repository<com.marketour.domain.MedioPago> repository= new com.marketour.persistence.Repository<com.marketour.domain.MedioPago>(com.marketour.domain.MedioPago.class);
		lstdbFormapago=repository.FindByColumn("cliente = " + idCliente );
		for (com.marketour.domain.MedioPago medioPago : lstdbFormapago) 
		{			
			lstMedioPago.add(com.marketour.business.MedioPago.ConvertToBMedioPago(medioPago));
			
		}
		return lstMedioPago;
	}
	public static List<com.marketour.business.MedioPago> ConsultarMedioPagoXClienteForma(int idCliente, int idFormaPago)
	{
		List<com.marketour.business.MedioPago> lstMedioPago=new ArrayList<com.marketour.business.MedioPago>();
		List<MedioPago> lstdbMedioPago=new ArrayList<MedioPago>();
		
		com.marketour.persistence.Repository<MedioPago> repository= new com.marketour.persistence.Repository<MedioPago>(MedioPago.class);
		lstdbMedioPago=repository.FindByColumn("cliente = " + idCliente + " and forma = " + idFormaPago);
		for (MedioPago medioPago : lstdbMedioPago) 
		{
			com.marketour.business.MedioPago mp=new com.marketour.business.MedioPago();
			mp.setId(medioPago.getId());
			mp.setCliente(medioPago.getCliente().getId());
			if (medioPago.getTarjetaCredito()!=null){
				com.marketour.business.TarjetaCredito tc=new com.marketour.business.TarjetaCredito();
				tc.setId(medioPago.getTarjetaCredito().getId());
				tc.setNombre(medioPago.getTarjetaCredito().getNombre());
				tc.setNumero(medioPago.getTarjetaCredito().getNumero());
				tc.setCodigo(medioPago.getTarjetaCredito().getCodigo());
				tc.setVencimiento(medioPago.getTarjetaCredito().getVencimiento());
				mp.setTarjetaCredito(tc);	
			}
			
			if (medioPago.getContraEntrega()!=null){
				com.marketour.business.ContraEntrega co=new com.marketour.business.ContraEntrega();
				co.setId(medioPago.getContraEntrega().getId());
				co.setDireccion(medioPago.getContraEntrega().getDireccion());
				mp.setContraEntrega(co);
			}
						

			lstMedioPago.add(mp);
		}
		return lstMedioPago;
	}
	public static Boolean CrearMediodePago(com.marketour.business.MedioPago mediopago)
	{
		Repository repositoryc = new com.marketour.persistence.Repository<com.marketour.domain.Cliente>(com.marketour.domain.Cliente.class);
		Repository repositoryfp = new com.marketour.persistence.Repository<FormaPago>(FormaPago.class);
		Repository repository = new com.marketour.persistence.Repository<MedioPago>(MedioPago.class);
		MedioPago bMedioPago=new com.marketour.domain.MedioPago();
		bMedioPago.setCliente((com.marketour.domain.Cliente)repositoryc.FindById((int)mediopago.getCliente()));
		bMedioPago.setEstado(mediopago.getEstado());
		bMedioPago.setFormaPago((FormaPago)repositoryfp.FindById(mediopago.getFormaPago()));
		repository.Persist(bMedioPago);
		return true;
	}


}
