

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:29 a. m.
 */
public class CarritoCompra {

	private ItemCompra m_ItemCompra;
	private Compra m_Compra;

	public CarritoCompra(){

	}

	public void finalize() throws Throwable {

	}

	public Compra getCompra(){
		return m_Compra;
	}

	public ItemCompra getItemCompra(){
		return m_ItemCompra;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCompra(Compra newVal){
		m_Compra = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setItemCompra(ItemCompra newVal){
		m_ItemCompra = newVal;
	}

}