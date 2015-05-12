package com.marketour.business;

public class Cliente extends Usuario implements java.io.Serializable {

	private String descripcion = "";
	private int id = 0;

	private CarritoCompra m_CarritoCompra = new CarritoCompra();
	private Compra m_Compra = new Compra();
	private MedioPago m_MedioPago = new MedioPago();

	public static Cliente ConvertToBCliente(com.marketour.domain.Cliente domain) {
		Cliente business = new Cliente();
		if (domain != null) {
			business.setId(domain.getId());
			business.setDescripcion(domain.getDescripcion());
		}
		return business;
	}

	public static com.marketour.domain.Cliente ConvertToBDCliente(
			Cliente business) {
		com.marketour.domain.Cliente domain = new com.marketour.domain.Cliente();
		if (business != null) {
			domain.setId(business.getId());
			domain.setDescripcion(business.getDescripcion());
		}
		return domain;
	}

	public CarritoCompra getCarritoCompra() {
		return m_CarritoCompra;
	}

	public Compra getCompra() {
		return m_Compra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getId() {
		return id;
	}

	public MedioPago getMedioPago() {
		return m_MedioPago;
	}

	public void setCarritoCompra(CarritoCompra newVal) {
		m_CarritoCompra = newVal;
	}

	public void setCompra(Compra newVal) {
		m_Compra = newVal;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMedioPago(MedioPago newVal) {
		m_MedioPago = newVal;
	}
}
