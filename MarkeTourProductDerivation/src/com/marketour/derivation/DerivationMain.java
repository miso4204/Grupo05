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
		        	 
		        	 statusLabel.setText("Producto Generado!");
		            
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