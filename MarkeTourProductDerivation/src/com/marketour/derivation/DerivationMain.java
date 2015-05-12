package com.marketour.derivation;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.marketour.derivation.spoonannotations.Invoker;

public class DerivationMain extends JFrame{
	
	 private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JPanel controlPanel;
	   private static String productConfig;
      
	   //JCheckBox reportesCheckBox = new JCheckBox();
       //JLabel reporteslbl = new JLabel("Reportes");
       
       //JCheckBox promoCheckBox = new JCheckBox();
       //JLabel promolbl = new JLabel("Promociones");
      
       private JTextField pathPomServices = new JTextField(15);
       
	   public DerivationMain(){
	      prepareGUI();
	   }

	
	public static boolean incluirPromos = false; //Indica si se desean incluir las promos en la compilacion (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirReportes = false; //Indica si se desean incluir los reportes en la compilacion (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirBusquedaPorCiudad = false; //Indica si se desean incluir la busqueda de productos y paquete por ciudad (spoon) (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirCreatePromo = false; //Indica si se pueden crear promos (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirUpdatePromo = false; //Indica si se pueden actualizar promos (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirAdminMoneda = false; //Indica si se incluye la administracion de moneda (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirPerformance = false; //Indica si se incluye el atributo de calidad performance alto. (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirAdminMonedaEuro = false; //Indica si se incluye la moneda euro (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)
	public static boolean incluirAdminMonedaPeso = false; //Indica si se incluye la moneda Peso colombiano (SE MODIFICA SEGUN LOS REQUERIM. DEL CLIENTE)

	
	public static String filepath; //= "C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourServices\\pom.xml"; //Ruta en el pc del archivo MarkeTourServices/pom.xml 
	
	private void prepareGUI(){
	      mainFrame = new JFrame("MarkeTour Product Derivation");
	      mainFrame.setSize(500,730);
	      mainFrame.setLayout(new GridLayout(1, 1));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      headerLabel = new JLabel("", JLabel.CENTER);        
	      statusLabel = new JLabel("",JLabel.CENTER);    

	      headerLabel.setSize(500,730);
	      statusLabel.setSize(350,100);

	      controlPanel = new JPanel();
	      //controlPanel.setLayout(new GridLayout(6,2));
	      controlPanel.setLayout(new FlowLayout());
	     
	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }
	
	// The listener for the textfield.
	   private class TextFieldListener implements ActionListener
	   {  public void actionPerformed(ActionEvent evt)
	      {  String inputString = pathPomServices.getText();
	         
	      	filepath = inputString;
	         System.out.println(filepath);
	      }
	   }
	   

	   private void showButtonDemo(){

	    headerLabel.setText(productConfig);

		      JButton okButton = new JButton("Generar Producto");  

		      okButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            
		 				variabilidadPerformance();
		        	 	variabilidadPromos();
		        	 	variabilidadCreatePromos();
		        	 	variabilidadUpdatePromos();
		 				variabilidadReportes();
		 				variabilidadAdminMoneda();
		        	    variabilidadBusquedaProductosPorCiudad();
		        	    variabilidadMonedaEuroPeso();
		 				updateProject();
		        	 

			        	 statusLabel.setText("Desplegando...");
	 
			        	 String despliegueServicios = "\"C:\\Program Files\\Java\\jdk1.7.0_75\\bin\\javaw.exe\" -Dfile.encoding=Cp1252 -classpath \"C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourServices\\target\\classes;C:\\eclipse\\plugins\\org.aspectj.runtime_1.8.5.20150128171000.jar;C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourBusiness\\target\\classes;C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourBusiness\\target\\test-classes;C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourPersistence\\target\\classes;C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourPersistence\\target\\test-classes;C:\\Users\\JUAN DAVID\\.m2\\repository\\junit\\junit\\4.12\\junit-4.12.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\hamcrest\\hamcrest-core\\1.3\\hamcrest-core-1.3.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\mysql\\mysql-connector-java\\5.1.35\\mysql-connector-java-5.1.35.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\hibernate\\hibernate-core\\4.3.8.Final\\hibernate-core-4.3.8.Final.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\jboss\\logging\\jboss-logging\\3.1.3.GA\\jboss-logging-3.1.3.GA.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\jboss\\logging\\jboss-logging-annotations\\1.2.0.Beta1\\jboss-logging-annotations-1.2.0.Beta1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\jboss\\spec\\javax\\transaction\\jboss-transaction-api_1.2_spec\\1.0.0.Final\\jboss-transaction-api_1.2_spec-1.0.0.Final.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\dom4j\\dom4j\\1.6.1\\dom4j-1.6.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\xml-apis\\xml-apis\\1.0.b2\\xml-apis-1.0.b2.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\hibernate\\common\\hibernate-commons-annotations\\4.0.5.Final\\hibernate-commons-annotations-4.0.5.Final.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\hibernate\\javax\\persistence\\hibernate-jpa-2.1-api\\1.0.0.Final\\hibernate-jpa-2.1-api-1.0.0.Final.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\javassist\\javassist\\3.18.1-GA\\javassist-3.18.1-GA.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\antlr\\antlr\\2.7.7\\antlr-2.7.7.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\jboss\\jandex\\1.1.0.Final\\jandex-1.1.0.Final.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\hibernate\\hibernate-entitymanager\\4.3.8.Final\\hibernate-entitymanager-4.3.8.Final.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\slf4j\\slf4j-api\\1.6.4\\slf4j-api-1.6.4.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\slf4j\\slf4j-log4j12\\1.6.4\\slf4j-log4j12-1.6.4.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\log4j\\log4j\\1.2.16\\log4j-1.2.16.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\commons-logging\\commons-logging\\1.1.1\\commons-logging-1.1.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-databind\\2.5.2\\jackson-databind-2.5.2.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-core\\2.5.2\\jackson-core-2.5.2.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-annotations\\2.5.2\\jackson-annotations-2.5.2.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\fasterxml\\jackson\\datatype\\jackson-datatype-hibernate4\\2.5.2\\jackson-datatype-hibernate4-2.5.2.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-server\\9.2.10.v20150310\\jetty-server-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\javax\\servlet\\javax.servlet-api\\3.1.0\\javax.servlet-api-3.1.0.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-http\\9.2.10.v20150310\\jetty-http-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-util\\9.2.10.v20150310\\jetty-util-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-io\\9.2.10.v20150310\\jetty-io-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-webapp\\9.2.10.v20150310\\jetty-webapp-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-xml\\9.2.10.v20150310\\jetty-xml-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-servlet\\9.2.10.v20150310\\jetty-servlet-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-security\\9.2.10.v20150310\\jetty-security-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\sun\\jersey\\jersey-server\\1.8\\jersey-server-1.8.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\asm\\asm\\3.1\\asm-3.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\sun\\jersey\\jersey-core\\1.8\\jersey-core-1.8.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\sun\\jersey\\jersey-json\\1.8\\jersey-json-1.8.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\codehaus\\jettison\\jettison\\1.1\\jettison-1.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\stax\\stax-api\\1.0.1\\stax-api-1.0.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\com\\sun\\xml\\bind\\jaxb-impl\\2.2.3-1\\jaxb-impl-2.2.3-1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\javax\\xml\\bind\\jaxb-api\\2.2.2\\jaxb-api-2.2.2.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\javax\\xml\\stream\\stax-api\\1.0-2\\stax-api-1.0-2.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\javax\\activation\\activation\\1.1\\activation-1.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\codehaus\\jackson\\jackson-core-asl\\1.7.1\\jackson-core-asl-1.7.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\codehaus\\jackson\\jackson-mapper-asl\\1.7.1\\jackson-mapper-asl-1.7.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\codehaus\\jackson\\jackson-jaxrs\\1.7.1\\jackson-jaxrs-1.7.1.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\codehaus\\jackson\\jackson-xc\\1.7.1\\jackson-xc-1.7.1.jar\" com.marketour.services.MarkeTourServices";
			        	 String despliegueFronEnd = "\"C:\\Program Files\\Java\\jdk1.7.0_75\\bin\\javaw.exe\" -Dfile.encoding=Cp1252 -classpath \"C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourPresentation\\target\\classes;C:\\Users\\JUAN DAVID\\.m2\\repository\\junit\\junit\\4.12\\junit-4.12.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\hamcrest\\hamcrest-core\\1.3\\hamcrest-core-1.3.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-server\\9.2.10.v20150310\\jetty-server-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\javax\\servlet\\javax.servlet-api\\3.1.0\\javax.servlet-api-3.1.0.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-http\\9.2.10.v20150310\\jetty-http-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-util\\9.2.10.v20150310\\jetty-util-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-io\\9.2.10.v20150310\\jetty-io-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-webapp\\9.2.10.v20150310\\jetty-webapp-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-xml\\9.2.10.v20150310\\jetty-xml-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-servlet\\9.2.10.v20150310\\jetty-servlet-9.2.10.v20150310.jar;C:\\Users\\JUAN DAVID\\.m2\\repository\\org\\eclipse\\jetty\\jetty-security\\9.2.10.v20150310\\jetty-security-9.2.10.v20150310.jar\" com.marketour.presentation.MarkeTourPresentation";
			        
			        	 String filepathServices = System.getProperty("user.dir").replace("MarkeTourProductDerivation", "MarkeTourServices");
			        	 String filepathFrontEnd = System.getProperty("user.dir").replace("MarkeTourProductDerivation", "MarkeTourPresentation");
			         
			        	 System.out.println(filepathServices + "  " + filepathFrontEnd);
			        	 
			        	 statusLabel.setText("Producto Generado!");
			        	 
			        	 ProcessBuilder builder = new ProcessBuilder(
						            "cmd.exe", "/c", "cd " + filepathFrontEnd + " && " + despliegueFronEnd);
						        builder.redirectErrorStream(true);
						        Process p;
								try {
									p = builder.start();
								        
								} catch (IOException excp) {
									// TODO Auto-generated catch block
									excp.printStackTrace();
								} 
								
								ProcessBuilder builder2 = new ProcessBuilder(
							            "cmd.exe", "/c", "cd " + filepathServices + " && " + despliegueServicios);
							        builder2.redirectErrorStream(true);
							        Process p2;
									try {
										p2 = builder2.start();
										
									} catch (IOException exc) {
										// TODO Auto-generated catch block
										exc.printStackTrace();
									}
								
								try {
									Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome http://localhost:8081/index.html"});
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								System.exit(0);
		            
		         }          
		      });

		  /*
	      promoCheckBox.addItemListener(new ItemListener() {
	          public void itemStateChanged(ItemEvent e) {
	              System.out.println("promoCheckBox? " + promoCheckBox.isSelected());
	              if(promoCheckBox.isSelected()){
	            	  incluirPromos = true;
	              } else {
	            	  incluirPromos = false;
	              }
	            }
	          });
	      
	      reportesCheckBox.addItemListener(new ItemListener() {
	          public void itemStateChanged(ItemEvent e) {
	              System.out.println("reportesCheckBox? " + reportesCheckBox.isSelected());
	              if(reportesCheckBox.isSelected()){
	            	  incluirReportes = true;
	              } else {
	            	  incluirReportes = false;
	              }
	            }
	          });
		      */
	      
	       TextFieldListener tfListener = new TextFieldListener();
		   pathPomServices.addActionListener(tfListener);

	      //controlPanel.add(pathPomServices); 
	      //controlPanel.add(reporteslbl);
	      //controlPanel.add(reportesCheckBox);
	      //controlPanel.add(promolbl);
	      //controlPanel.add(promoCheckBox);
	      controlPanel.add(okButton);
	     
	      mainFrame.setVisible(true);  
	   }
	
	
	   
	public static void main(String argv[]) {
			//variabilidadPromos();
			//variabilidadReportes();
		filepath = System.getProperty("user.dir").replace("MarkeTourProductDerivation", "");
		System.out.println("Filepath: " + filepath);
		
		try {
			
			String pathConfigFile = System.getProperty("user.dir").replace("MarkeTourProductDerivation", "MarkeTourFeatures");
			pathConfigFile = pathConfigFile + File.separator + "configs" + File.separator + "default.config";
			BufferedReader in = new BufferedReader(new FileReader(pathConfigFile));
		
			String line;
			productConfig = "<html>Configuración: <br><br>";
			
			while((line = in.readLine()) != null)
			{
				System.out.println(line);
				productConfig = productConfig + line + "<br>";
				
				if(line.equalsIgnoreCase("SpecialOffers")){
					incluirPromos = true;
				} 
				if(line.equalsIgnoreCase("CreatePromo")){
					incluirCreatePromo = true;
				}
				if(line.equalsIgnoreCase("UpdatePromo")){
					incluirUpdatePromo = true;
				}
				if(line.equalsIgnoreCase("Reports")){
					incluirReportes = true;
				}
				if(line.equalsIgnoreCase("ByLocation")){
					incluirBusquedaPorCiudad = true;
				} 
				if(line.equalsIgnoreCase("AdminMoneda")){
					incluirAdminMoneda = true;
				}
				if(line.equalsIgnoreCase("Performance")){
					incluirPerformance = true;
				}
				if(line.equalsIgnoreCase("Euro")){
					incluirAdminMonedaEuro = true;
				}
				if(line.equalsIgnoreCase("Colombian")){
					incluirAdminMonedaPeso = true;
				}

				
			}
			
			productConfig = productConfig + "</html>";
		
			in.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DerivationMain  swingDerivationMain = new DerivationMain();      
		swingDerivationMain.showButtonDemo();
		       
		}
	
	public static boolean variabilidadPerformance(){
		
		boolean performance = false; //Variable que indicará si el servidor jetty se usara en version 7 o 9 en el pom.xml actual (NO MODIFICAR)

		   try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath + "MarkeTourServices" + File.separator + "pom.xml");
	 
			// Elemento Principal en el xml
			Node project = doc.getFirstChild();
	 
			// Elementos de interés 
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			Node build = doc.getElementsByTagName("build").item(0);
	 
			NodeList dependenciesList = dependencies.getChildNodes();
			
			NodeList versionList = doc.getElementsByTagName("version");
			Node versionNode = null;
			
			System.out.println("VersionList: " + versionList.getLength());
			
			for(int k = 0; k < versionList.getLength(); k++){
				System.out.println(versionList.item(k).getTextContent());
				if(versionList.item(k).getTextContent().equals("9.2.10.v20150310")){
					System.out.println("Jetty Equals 9.2 ");
					performance = true;
					versionNode = versionList.item(k);
					
					if(!incluirPerformance){
						Node jettyNode = versionNode.getParentNode();
						jettyNode.removeChild(versionNode);
						
						Element versionNew = doc.createElement("version");
						versionNew.appendChild(doc.createTextNode("7.6.16.v20140903"));
						jettyNode.appendChild(versionNew);
					} else {
						//No se hace nada porque ya esta con performance...
					}
					
				}
				
				if(versionList.item(k).getTextContent().equals("7.6.16.v20140903")){
					System.out.println("Jetty Equals 7.6 ");
					performance = false;
					versionNode = versionList.item(k);
					
					if(incluirPerformance){
						Node jettyNode = versionNode.getParentNode();
						jettyNode.removeChild(versionNode);
						
						Element versionNew = doc.createElement("version");
						versionNew.appendChild(doc.createTextNode("9.2.10.v20150310"));
						jettyNode.appendChild(versionNew);
					} else {
						//No se hace nada porque ya esta sin performance...
					}
					
				}
			}
			
			
			// Se escribe el contenido en el pom.xml 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + "MarkeTourServices" + File.separator + "pom.xml"));
			transformer.transform(source, result);
	 
			System.out.println("Se actualizó el pom con performance!!");
			
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
		return true;
	}
	
	public static boolean variabilidadAdminMoneda(){
		
		boolean adminMonedaExcluded = false; //Variable que indicará si el admin moneda se encuentra excluido o no en el pom.xml actual (NO MODIFICAR)

		   try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath + "MarkeTourServices" + File.separator + "pom.xml");
	 
			// Elemento Principal en el xml
			Node project = doc.getFirstChild();
	 
			// Elementos de interés 
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			Node build = doc.getElementsByTagName("build").item(0);
	 
			NodeList buildList = build.getChildNodes();
			
			NodeList excludeList = doc.getElementsByTagName("exclude");
			Node excludesNode = doc.getElementsByTagName("excludes").item(0);
			Node excludePromoNode = null;
			
			for(int k = 0; k < excludeList.getLength(); k++){
				System.out.println(excludeList.item(k).getTextContent());
				if(excludeList.item(k).getTextContent().equals("com/marketour/aspectos/AspectoMoneda.aj")){
					adminMonedaExcluded = true;
					excludePromoNode = excludeList.item(k);
				}
			}
			
			if(incluirAdminMoneda == false && adminMonedaExcluded == true){
				//No hay que hacer nada
			} else if (incluirAdminMoneda == true && adminMonedaExcluded == false) {
				//No hay que hacer nada
			} else if (incluirAdminMoneda == false && adminMonedaExcluded == false) {
				//Hay que insertar la exclusion en el xml
				System.out.println("Quiero quitar admin moneda pero no estan excluido...");
				Element exclude = doc.createElement("exclude");
				exclude.appendChild(doc.createTextNode("com/marketour/aspectos/AspectoMoneda.aj"));
				excludesNode.appendChild(exclude);
			} else if (incluirAdminMoneda == true && adminMonedaExcluded == true) {
				//Hay que quitar la exclusion en el xml
				System.out.println("Quiero incluir admin moneda pero esta excluido...");
				if(excludePromoNode != null){
					excludePromoNode.getParentNode().removeChild(excludePromoNode);
				}
			}  

			
			// Se escribe el contenido en el pom.xml 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + "MarkeTourServices" + File.separator + "pom.xml"));
			transformer.transform(source, result);
	 
			System.out.println("Se actualizó el pom con adminMoneda!!");
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
		return true;
	}
	
	public static boolean variabilidadPromos(){
		
		boolean promosExcluded = false; //Variable que indicará si las promociones se encuentran excluidas o no en el pom.xml actual (NO MODIFICAR)

		   try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath + "MarkeTourServices" + File.separator + "pom.xml");
	 
			// Elemento Principal en el xml
			Node project = doc.getFirstChild();
	 
			// Elementos de interés 
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			Node build = doc.getElementsByTagName("build").item(0);
	 
			NodeList buildList = build.getChildNodes();
			
			NodeList excludeList = doc.getElementsByTagName("exclude");
			Node excludesNode = doc.getElementsByTagName("excludes").item(0);
			Node excludePromoNode = null;
			
			for(int k = 0; k < excludeList.getLength(); k++){
				System.out.println(excludeList.item(k).getTextContent());
				if(excludeList.item(k).getTextContent().equals("com/marketour/services/PromoService.java")){
					promosExcluded = true;
					excludePromoNode = excludeList.item(k);
				}
			}
			
			if(incluirPromos == false && promosExcluded == true){
				//No hay que hacer nada
			} else if (incluirPromos == true && promosExcluded == false) {
				//No hay que hacer nada
			} else if (incluirPromos == false && promosExcluded == false) {
				//Hay que insertar la exclusion en el xml
				System.out.println("Quiero quitar promos pero no estan excluidas...");
				Element exclude = doc.createElement("exclude");
				exclude.appendChild(doc.createTextNode("com/marketour/services/PromoService.java"));
				excludesNode.appendChild(exclude);
			} else if (incluirPromos == true && promosExcluded == true) {
				//Hay que quitar la exclusion en el xml
				System.out.println("Quiero incluir promos pero estan excluidas...");
				if(excludePromoNode != null){
					excludePromoNode.getParentNode().removeChild(excludePromoNode);
				}
			}  

			
			// Se escribe el contenido en el pom.xml 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + "MarkeTourServices" + File.separator + "pom.xml"));
			transformer.transform(source, result);
	 
			System.out.println("Se actualizó el pom con promos!!");
			
			
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
		return true;
	}
	
	public static boolean variabilidadCreatePromos(){
		
		boolean createPromosExcluded = false; //Variable que indicará si las promociones se encuentran excluidas o no en el pom.xml actual (NO MODIFICAR)

		   try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath + "MarkeTourServices" + File.separator + "pom.xml");
	 
			// Elemento Principal en el xml
			Node project = doc.getFirstChild();
	 
			// Elementos de interés 
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			Node build = doc.getElementsByTagName("build").item(0);
	 
			NodeList buildList = build.getChildNodes();
			
			NodeList excludeList = doc.getElementsByTagName("exclude");
			Node excludesNode = doc.getElementsByTagName("excludes").item(0);
			Node excludePromoNode = null;
			
			for(int k = 0; k < excludeList.getLength(); k++){
				System.out.println(excludeList.item(k).getTextContent());
				if(excludeList.item(k).getTextContent().equals("com/marketour/services/PromoCreateService.java")){
					createPromosExcluded = true;
					excludePromoNode = excludeList.item(k);
				}
			}
			
			if(incluirCreatePromo == false && createPromosExcluded == true){
				//No hay que hacer nada
			} else if (incluirCreatePromo == true && createPromosExcluded == false) {
				//No hay que hacer nada
			} else if (incluirCreatePromo == false && createPromosExcluded == false) {
				//Hay que insertar la exclusion en el xml
				System.out.println("Quiero quitar cerate promos pero no estan excluidas...");
				Element exclude = doc.createElement("exclude");
				exclude.appendChild(doc.createTextNode("com/marketour/services/PromoCreateService.java"));
				excludesNode.appendChild(exclude);
			} else if (incluirCreatePromo == true && createPromosExcluded == true) {
				//Hay que quitar la exclusion en el xml
				System.out.println("Quiero incluir create promos pero estan excluidas...");
				if(excludePromoNode != null){
					excludePromoNode.getParentNode().removeChild(excludePromoNode);
				}
			}  

			
			// Se escribe el contenido en el pom.xml 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + "MarkeTourServices" + File.separator + "pom.xml"));
			transformer.transform(source, result);
	 
			System.out.println("Se actualizó el pom con create promos!!");
			
			
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
		return true;
	}
	
	public static boolean variabilidadUpdatePromos(){
		
		boolean createPromosExcluded = false; //Variable que indicará si las promociones se encuentran excluidas o no en el pom.xml actual (NO MODIFICAR)

		   try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath + "MarkeTourServices" + File.separator + "pom.xml");
	 
			// Elemento Principal en el xml
			Node project = doc.getFirstChild();
	 
			// Elementos de interés 
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			Node build = doc.getElementsByTagName("build").item(0);
	 
			NodeList buildList = build.getChildNodes();
			
			NodeList excludeList = doc.getElementsByTagName("exclude");
			Node excludesNode = doc.getElementsByTagName("excludes").item(0);
			Node excludePromoNode = null;
			
			for(int k = 0; k < excludeList.getLength(); k++){
				System.out.println(excludeList.item(k).getTextContent());
				if(excludeList.item(k).getTextContent().equals("com/marketour/services/PromoUpdateService.java")){
					createPromosExcluded = true;
					excludePromoNode = excludeList.item(k);
				}
			}
			
			if(incluirUpdatePromo == false && createPromosExcluded == true){
				//No hay que hacer nada
			} else if (incluirUpdatePromo == true && createPromosExcluded == false) {
				//No hay que hacer nada
			} else if (incluirUpdatePromo == false && createPromosExcluded == false) {
				//Hay que insertar la exclusion en el xml
				System.out.println("Quiero quitar update promos pero no estan excluidas...");
				Element exclude = doc.createElement("exclude");
				exclude.appendChild(doc.createTextNode("com/marketour/services/PromoUpdateService.java"));
				excludesNode.appendChild(exclude);
			} else if (incluirUpdatePromo == true && createPromosExcluded == true) {
				//Hay que quitar la exclusion en el xml
				System.out.println("Quiero incluir update promos pero estan excluidas...");
				if(excludePromoNode != null){
					excludePromoNode.getParentNode().removeChild(excludePromoNode);
				}
			}  

			
			// Se escribe el contenido en el pom.xml 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + "MarkeTourServices" + File.separator + "pom.xml"));
			transformer.transform(source, result);
	 
			System.out.println("Se actualizó el pom con update promos!!");
			
			
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
		return true;
	}	
	
	public static boolean variabilidadReportes(){
		
		boolean reportesExcluded = false; //Variable que indicará si los reportes se encuentran excluidos o no en el pom.xml actual (NO MODIFICAR)

		   try {
			//String filepath = "C:\\Users\\JUAN DAVID\\workspace\\MarkeTour\\Grupo05\\MarkeTourServices\\pom.xml"; //Ruta en el pc del archivo MarkeTourServices/pom.xml 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath + "MarkeTourServices" + File.separator + "pom.xml");
	 
			// Elemento Principal en el xml
			Node project = doc.getFirstChild();
	 
			// Elementos de interés 
			Node dependencies = doc.getElementsByTagName("dependencies").item(0);
			Node build = doc.getElementsByTagName("build").item(0);
	 
			NodeList buildList = build.getChildNodes();
			
			NodeList excludeList = doc.getElementsByTagName("exclude");
			Node excludesNode = doc.getElementsByTagName("excludes").item(0);
			Node excludePromoNode = null;
			
			for(int k = 0; k < excludeList.getLength(); k++){
				System.out.println(excludeList.item(k).getTextContent());
				if(excludeList.item(k).getTextContent().equals("com/marketour/services/ReporteServices.java")){
					reportesExcluded = true;
					excludePromoNode = excludeList.item(k);
				}
			}
			
			if(incluirReportes == false && reportesExcluded == true){
				//No hay que hacer nada
			} else if (incluirReportes == true && reportesExcluded == false) {
				//No hay que hacer nada
			} else if (incluirReportes == false && reportesExcluded == false) {
				//Hay que insertar la exclusion en el xml
				System.out.println("Quiero quitar los reportes pero no estan excluidos...");
				Element exclude = doc.createElement("exclude");
				exclude.appendChild(doc.createTextNode("com/marketour/services/ReporteServices.java"));
				excludesNode.appendChild(exclude);
			} else if (incluirReportes == true && reportesExcluded == true) {
				//Hay que quitar la exclusion en el xml
				System.out.println("Quiero incluir reportes pero estan excluidos...");
				if(excludePromoNode != null){
					excludePromoNode.getParentNode().removeChild(excludePromoNode);
				}
			}  

			
			// Se escribe el contenido en el pom.xml 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + "MarkeTourServices" + File.separator + "pom.xml"));
			transformer.transform(source, result);
	 
			System.out.println("Se actualizó el pom para variabilidad de reportes!!");
			
	 
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
		return true;
	}
	@SuppressWarnings("unused")
	public void variabilidadBusquedaProductosPorCiudad(){
		if (incluirBusquedaPorCiudad==true){
			Invoker i = new Invoker("6","busequedaCiudad");
		}else{
			Invoker i = new Invoker("7","busequedaCiudad");
		}
	}

	@SuppressWarnings("unused")
	public void variabilidadMonedaEuroPeso(){
		//7 peso+euro
		//6 ninguna
		//5 euro
		//4 peso
		if (incluirAdminMonedaEuro==true && incluirAdminMonedaPeso==true){
			Invoker i = new Invoker("7","moneda");
		}else if (incluirAdminMonedaEuro==false && incluirAdminMonedaPeso==false){
			Invoker i = new Invoker("6","moneda");
		}else if (incluirAdminMonedaEuro==true){
			Invoker i = new Invoker("5","moneda");
		}else if (incluirAdminMonedaPeso==true){
			Invoker i = new Invoker("4","moneda");
		}
	}



	public static boolean updateProject() {
		
		ProcessBuilder builder = new ProcessBuilder(
	            "cmd.exe", "/c", "cd " + filepath + " && mvn clean && mvn install && mvn eclipse:eclipse"
	            		+ " && cd " + filepath + "MarkeTourPresentation" + " && mvn eclipse:eclipse"
	            		+ " && cd " + filepath + "MarkeTourPersistence" + " && mvn eclipse:eclipse"
	            		+ " && cd " + filepath + "MarkeTourBusiness" + " && mvn eclipse:eclipse"
	            		+ " && cd " + filepath + "MarkeTourServices" + " && mvn eclipse:eclipse");
	        builder.redirectErrorStream(true);
	        Process p;
			try {
				p = builder.start();
				BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			        String line;
			        while (true) {
			            line = r.readLine();
			            if (line == null) { break; }
			            System.out.println(line);
			        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return true;
	}
}