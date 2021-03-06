
package com.marketour.business;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;






/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:37 a. m.
 */
public class MedioPago implements Serializable {

	private Integer id;
    private Integer formaPago;
    private Integer cliente;
    private Integer estado;
    private Pse pse;
    private Set<Compra> compras = new HashSet<Compra>(0);
    private TarjetaCredito tarjetaCredito;
    private ContraEntrega contraEntrega;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(Integer formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Pse getPse() {
		return pse;
	}

	public void setPse(Pse pse) {
		this.pse = pse;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public TarjetaCredito getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	public ContraEntrega getContraEntrega() {
		return contraEntrega;
	}

	public void setContraEntrega(ContraEntrega contraEntrega) {
		this.contraEntrega = contraEntrega;
	}

	public MedioPago(){

	}

	public void finalize() throws Throwable {

	}

	public static MedioPago ConvertToBMedioPago(com.marketour.domain.MedioPago medioPago )
	{
		
		MedioPago mp=new MedioPago();
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
		
		
		return mp;
	}


}
