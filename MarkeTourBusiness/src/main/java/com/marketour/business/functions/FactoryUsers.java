package com.marketour.business.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.marketour.business.Usuario;

public class FactoryUsers {

	private FunctionsUsers functionEntidad;

	public FactoryUsers(String entidad) {
		if (entidad == "Cliente") {
			functionEntidad = new FunctionsClientes();
		} else if (entidad == "Proveedor") {
			functionEntidad = new FunctionsProveedores();
		} else if (entidad == "Administrador") {
			functionEntidad = new FunctionsAdministrador();
		} else {
			functionEntidad = new FunctionsUsuarios();
		}
	}

	public Boolean Autenticar(String usuario, String contrasena) {
		if (true) {
			return (Boolean) functionEntidad.Autenticar(usuario, contrasena);
		} else {
			return false;
		}
	}

	public Boolean CambiarContrasena(String usuario, String contrasena) {
		if (tieneCambioContrasena()) {
			return functionEntidad.CambiarContrasena(usuario, contrasena);
		} else {
			return false;
		}
	}

	public Boolean CambiarDireccion(Usuario usuario) {
		if (tieneCambioDireccion()) {
			return functionEntidad.CambiarDireccion(usuario);
		} else {
			return false;
		}
	}

	public Object Consultar(int id) {
		return functionEntidad.Consultar(id);
	}

	public List<Object> ConsultarLista() {
		return functionEntidad.ConsultarLista();
	}
	private boolean tieneCambioContrasena(){

		boolean CambioContrasena = false;
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures");
			System.out.println("Path config desde facade: " + System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures"));
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				
				if(line.trim().equalsIgnoreCase("ChangePassword")){
					System.out.println("Permite cambio contrasena!");
					CambioContrasena = true;
				}
			}
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(CambioContrasena){
			System.out.println("Permite cambio contrasena!");
			return true;
		} else {
			System.out.println("No Permite cambio contrasena!");
			return false;
		}
	}
	private boolean tieneCambioDireccion(){

		boolean CambioDireccion = false;
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures");
			System.out.println("Path config desde facade: " + System.getProperty("user.dir").replace("MarkeTourServices", "MarkeTourFeatures"));
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				
				if(line.trim().equalsIgnoreCase("ChangeAdress")){
					System.out.println("Permite cambio direccion!");
					CambioDireccion = true;
				}
			}
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(CambioDireccion){
			System.out.println("Permite cambio direccion!");
			return true;
		} else {
			System.out.println("No Permite cambio direccion!");
			return false;
		}
	}
	
}
