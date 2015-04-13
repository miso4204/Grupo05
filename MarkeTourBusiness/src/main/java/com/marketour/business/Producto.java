

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:39 a. m.
 */
public class Producto extends ItemCompra {

	private boolean activo;
	private int capacidad;
	private int cordenadas;
	private String descripcion;
	private String nombre;
	private double valor;
	private Categoria m_Categoria;
	private Ciudad m_Ciudad;
	private ItemCompra m_ItemCompra;
	private Contenido m_Contenido;
	private Disponibilidad m_Disponibilidad;
	private Publicacion m_Publicacion;

	public Producto(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Categoria getCategoria(){
		return m_Categoria;
	}

	public Ciudad getCiudad(){
		return m_Ciudad;
	}

	public Contenido getContenido(){
		return m_Contenido;
	}

	public Disponibilidad getDisponibilidad(){
		return m_Disponibilidad;
	}

	public ItemCompra getItemCompra(){
		return m_ItemCompra;
	}

	public Publicacion getPublicacion(){
		return m_Publicacion;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCategoria(Categoria newVal){
		m_Categoria = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCiudad(Ciudad newVal){
		m_Ciudad = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setContenido(Contenido newVal){
		m_Contenido = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDisponibilidad(Disponibilidad newVal){
		m_Disponibilidad = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setItemCompra(ItemCompra newVal){
		m_ItemCompra = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPublicacion(Publicacion newVal){
		m_Publicacion = newVal;
	}

}