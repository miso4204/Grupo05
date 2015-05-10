package com.marketour.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.marketour.business.Ciudad;
import com.marketour.business.Cliente;
import com.marketour.business.Compra;
import com.marketour.domain.FormaPago;
import com.marketour.domain.ItemCompra;
import com.marketour.domain.MedioPago;
import com.marketour.domain.Paquete;
import com.marketour.domain.Producto;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;
import com.marketour.persistence.RepositoryCompra;

public class FacadeCompra 
{
	public static com.marketour.domain.Compra RegistrarCompra(Compra compra) 
	{

		com.marketour.persistence.Repository<com.marketour.domain.Compra> repository = new com.marketour.persistence.Repository<com.marketour.domain.Compra>(
				com.marketour.domain.Compra.class);

		com.marketour.domain.Compra dbCompra = new com.marketour.domain.Compra();
		com.marketour.domain.Cliente dbCliente = new com.marketour.domain.Cliente();
		com.marketour.domain.MedioPago dbMedioPago = new com.marketour.domain.MedioPago();

		dbCliente.setId(compra.getCliente().getId());
		dbCompra.setCliente(dbCliente);
		dbMedioPago.setId(compra.getMedioPago().getId());
		dbCompra.setMedioPago(dbMedioPago);

		dbCompra.setCalificacion(compra.getCalificacion());
		dbCompra.setFecha(compra.getFechaCompra());
		dbCompra.setEstado(compra.getEstado());

		Set<com.marketour.business.ItemCompra> listaItems = compra.getItemCompras();
		Set<ItemCompra> dbItemCompras = new HashSet<ItemCompra>(0);
		for (int i = 0; i < listaItems.size(); i++) {
			ItemCompra dbItem = new ItemCompra();

			dbItem.setCantidad(((com.marketour.business.ItemCompra) listaItems.toArray()[i]).getCantidad());
			if (((com.marketour.business.ItemCompra) listaItems.toArray()[i]).getPaquete() != null) {
				Paquete pa = new Paquete();
				pa.setId(((com.marketour.business.ItemCompra) listaItems.toArray()[i]).getPaquete());
				dbItem.setPaquete(pa);
			}
			if (((com.marketour.business.ItemCompra) listaItems.toArray()[i]).getProducto() != null) {
				Producto pro = new Producto();
				pro.setId(((com.marketour.business.ItemCompra) listaItems.toArray()[i]).getProducto());
				dbItem.setProducto(pro);
			}
			dbItem.setValor(((com.marketour.business.ItemCompra) listaItems.toArray()[i]).getValor());

			dbItemCompras.add(dbItem);
		}

		dbCompra.setItemCompras(dbItemCompras);
		try {
			repository.Persist(dbCompra);

		} catch (Exception ex) {

		}
		return dbCompra;

	}
	
	public static List<com.marketour.business.Compra> ConsultarCompra(int mes, int ano) 
	{
		com.marketour.persistence.Repository<com.marketour.domain.Compra> repository = new com.marketour.persistence.Repository<com.marketour.domain.Compra>(com.marketour.domain.Compra.class);

		List<com.marketour.domain.Compra> lstsCompras =repository.FindByColumn("fecha>'"+ano+"-"+mes+"-1'");
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
	
	public static List<com.marketour.business.FormaPago> ConsultarFormasdePago()
	{
		List<com.marketour.business.FormaPago> lstFormaPago=new ArrayList<com.marketour.business.FormaPago>();
		List<FormaPago> lstdbFormapago=new ArrayList<FormaPago>();
		
		com.marketour.persistence.Repository<FormaPago> repository= new com.marketour.persistence.Repository<FormaPago>(FormaPago.class);
		lstdbFormapago=repository.FindAll();
		for (FormaPago formaPago : lstdbFormapago) 
		{
			com.marketour.business.FormaPago fp=new com.marketour.business.FormaPago();
			fp.setId(formaPago.getId());
			fp.setDescripcion(formaPago.getDescripcion());
			fp.setEstado(formaPago.getEstado());
			fp.setRecargo(formaPago.getRecargo());
			fp.setId(formaPago.getId());
			lstFormaPago.add(fp);
		}
		return lstFormaPago;
	}
	
	public static List<com.marketour.business.MedioPago> ConsultarMedioPagoXCliente(int idCliente, int idFormaPago)
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
}
