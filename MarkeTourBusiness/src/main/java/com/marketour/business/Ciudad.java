

/**
 * @author Andres
 * @version 1.0
 * @created 13-abr.-2015 9:52:31 a. m.
 */
public class Ciudad {

	private String ciudad;
	private Departamento m_Departamento;

	public Ciudad(){

	}

	public void finalize() throws Throwable {

	}

	public Departamento getDepartamento(){
		return m_Departamento;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDepartamento(Departamento newVal){
		m_Departamento = newVal;
	}

}