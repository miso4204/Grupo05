package com.marketour.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.marketour.business.Cliente;
import com.marketour.business.Compra;
import com.marketour.domain.FormaPago;
import com.marketour.domain.ItemCompra;
import com.marketour.domain.Paquete;
import com.marketour.domain.Producto;
import com.marketour.hibernate.HibernateUtil;
import com.marketour.persistence.Repository;

public class FacadeCompra {

	public static com.marketour.domain.Compra RegistrarCompra(Compra compra) {

		com.marketour.persistence.Repository<com.marketour.domain.Compra> repository = new com.marketour.persistence.Repository<com.marketour.domain.Compra>(
				com.marketour.domain.Compra.class);

		com.marketour.domain.Compra dbCompra = new com.marketour.domain.Compra();
		com.marketour.domain.Cliente dbCliente = new com.marketour.domain.Cliente();
		com.marketour.domain.MedioPago dbMedioPago = new com.marketour.domain.MedioPago();

		dbCliente.setId(compra.getCliente());
		dbCompra.setCliente(dbCliente);
		dbMedioPago.setId(compra.getMedioPago());
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
}
