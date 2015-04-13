

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:32 a. m.
 */
public class Compra {

	private int calificacion;
	private Date fechaCompra;
	private MedioPago m_MedioPago;

	public Compra(){

	}

	public void finalize() throws Throwable {

	}

	public MedioPago getMedioPago(){
		return m_MedioPago;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setMedioPago(MedioPago newVal){
		m_MedioPago = newVal;
	}

}