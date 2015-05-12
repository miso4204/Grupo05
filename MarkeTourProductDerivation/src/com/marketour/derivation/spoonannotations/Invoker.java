package com.marketour.derivation.spoonannotations;

import spoon.Launcher;

public class Invoker {
	static String rut="";
	
	public Invoker(String tipo,String feature){
		String rutaFeature="";
		if(feature.equals("busequedaCiudad")){
			rut=System.getProperty("user.dir").replace("MarkeTourProductDerivation", "MarkeTourPersistence");
			rutaFeature="\\src\\main\\java\\com\\marketour\\persistence\\RepositoryProduct.java";
		}else if(feature.equals("moneda")){
			rut=System.getProperty("user.dir").replace("MarkeTourProductDerivation", "MarkeTourBusiness");
			rutaFeature="\\src\\main\\java\\com\\marketour\\facade\\FacadeUsuarios.java";
		}
		invokeSpoon(rut+rutaFeature, "com.marketour.derivation.spoonannotations.Processor",tipo);
	}
	
	 
	/** 
	 * Invokes a spoon processor individually
	 * 
	 * @param source
	 *            sources to be processed by spoon
	 * @param processor
	 *            spoon processor to executeq
	 */
	protected void invokeSpoon(String source, String processor,String tipo) {
		//Tipo 6=Tiene busqueda por ciudad; 7=no tiene
		// Invoke spoon processor for methods
		String[] spoonArgs = new String[10];
		spoonArgs[0] = "-i";
		spoonArgs[1] = source;
		spoonArgs[2] = "-p";
		spoonArgs[3] = processor;
		spoonArgs[4] = "--compliance";
		spoonArgs[5] = tipo;
		spoonArgs[6] = "-o";
		spoonArgs[7] = rut+"\\src\\main\\java\\";
		spoonArgs[8] = "-t";
		spoonArgs[9] = "./src/template/";
		try {
			//Launcher l =new Launcher(spoonArgs );
		
			Launcher.main(spoonArgs);
		} catch (Exception e) {
			System.err.println("Error while executing spoon launcher "
					+ e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}

	
}
