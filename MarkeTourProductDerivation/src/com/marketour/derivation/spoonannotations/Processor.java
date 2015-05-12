package com.marketour.derivation.spoonannotations;



import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtAnnotation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.template.Substitution;
import template.SimpleTemplate;


public class Processor extends AbstractProcessor<CtAnnotation<?>> {
	@Override
	public void init() {
		
		// TODO Auto-generated method stub
		super.init();
	}
	
	//@Override 
	public void process(CtAnnotation<?> annotation) {
		if (annotation.toString().contains("SearchByLocation")){
			CtClass<?> target=(CtClass<?>) annotation.getParent();
			SimpleTemplate te =new SimpleTemplate();
			int manejaBusquedaCiudad=this.getEnvironment().getComplianceLevel();
			String ciudadProducto="";
			String ciudadPaquete="";
			
			if (manejaBusquedaCiudad==6){
				ciudadProducto="FindProductsConCiudad";
				ciudadPaquete="FindProductsPorPaqueteConCiudad";
			}else{
				ciudadProducto="FindProductsSinCiudad";
				ciudadPaquete="FindProductsPorPaqueteSinCiudad";
			}
			
			//Substitution.insertAll(annotation, te);
			CtTypeReference<String> tString = getFactory().Type().createReference(String.class);
			CtTypeReference<Double> tDouble = getFactory().Type().createReference(double.class);
			CtTypeReference<Integer> tInteger = getFactory().Type().createReference(int.class);
			for (CtMethod<?> c : target.getAllMethods()) {
				
				if(c.getSimpleName().toString().equals("FindProducts")){					
					c.getBody().replace((CtStatement)			
							Substitution.substituteMethodBody(target, te,ciudadProducto,tString,tString,tDouble,tDouble,tInteger));	
				}
				if(c.getSimpleName().toString().equals("FindProductsPorPaquete")){					
					c.getBody().replace((CtStatement)			
							Substitution.substituteMethodBody(target, te,ciudadPaquete,tString,tString,tDouble,tDouble,tInteger,tInteger));	
				}
				
			}
		}else if (annotation.toString().contains("Moneda")){
			CtClass<?> target=(CtClass<?>) annotation.getParent();
			SimpleTemplate te =new SimpleTemplate();
			int tipoMoneda=this.getEnvironment().getComplianceLevel();
			String metodo="";			
			if (tipoMoneda==7){
				metodo="ConsultarMonedaTodos";				
			}else if (tipoMoneda==6){
				metodo="ConsultarSoloDolar";				
			}else if (tipoMoneda==5){
				metodo="ConsultarSoloEuro";				
			}else if (tipoMoneda==4){
				metodo="ConsultarSoloPeso";				
			}
			
			//Substitution.insertAll(annotation, te);			
			for (CtMethod<?> c : target.getAllMethods()) {				
				if(c.getSimpleName().toString().equals("ConsultarMonedaTodos")){					
					c.getBody().replace((CtStatement)			
							Substitution.substituteMethodBody(target, te,metodo));	
				}
				
			}
		}

		 
		
		
		
	}	

	@Override
	public void processingDone() {
		// TODO Auto-generated method stub
		//jaxbWriter(fm, "./resources/model.xml", "./resources/featureide.xsd");
		//super.processingDone();
	}
	
	
	/**
	 * Writes the contents of a JAXB model in an xml file with identation and
	 * blank spaces
	 * 
	 * @param root
	 *            the root of the object to write
	 * @param path
	 *            destination of the file to create
	 */
	private void jaxbWriter(Object root, String path, String schema) {
		try {
			JAXBContext context = JAXBContext.newInstance(root.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schema);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(root, new FileWriter(path));
		} catch (JAXBException e) {
			System.err
					.println("Error while preparing to write the JAXB model in: "
							+ path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error while writting the JAXB model in: "
					+ path);
			e.printStackTrace();
		}
	}
}
