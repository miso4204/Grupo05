package template;

import java.util.List;

import org.hibernate.Session;

import com.marketour.domain.Producto;
import com.marketour.hibernate.HibernateUtil;

import spoon.template.Local;
import spoon.template.Parameter;
import spoon.template.Template;
//import spoon.template.Value;
   
   public class SimpleTemplate implements Template {
       // template parameter fields 
        @Parameter String _parameter_;
   
       // parameters binding
        @Local
       public SimpleTemplate() {
           
       }
   
       // template method
       public int simpleTemplateMethod(String nombre,String apellido) {
           System.out.println(_parameter_+" "+nombre+" "+apellido);
           String lometio="";
           return 7;
       }
    
       
       
       
       
       
       //Filtro productos con ciudad
       public List<Producto> FindProductsConCiudad(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad) {
   		String rangoFechas="";
   		String innerDisponibilidad="";
   		if (!fechaInicio.equals("0") && !fechaFin.equals("0")){
   			innerDisponibilidad=" INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
   			rangoFechas=" AND dp.fechaInicio>='"+fechaInicio+"' AND dp.fechaFin<='"+fechaFin+"' ";
   		}
   		String rangoPrecios="";
   		if (precio1>0 && precio2>0){
   			rangoPrecios=" AND prod.valor>="+precio1+" and prod.valor<="+precio2;
   		}
   		String ciudad="";
   		if (idCiudad>0){
   			ciudad=" and prod.ubicacion="+idCiudad;
   		}
   		Session session = HibernateUtil.getSessionFactory().openSession();
   		session.beginTransaction();
   		List<Producto> list = session
   				.createSQLQuery(
   						"SELECT prod.* FROM Producto prod "+innerDisponibilidad+" WHERE 1=1 "+rangoFechas+
   						rangoPrecios+ciudad).addEntity(Producto.class).list();
   		session.getTransaction().commit();
   		return list;
   	}
       
       //Filtro productos sin ciudad
       public List<Producto> FindProductsSinCiudad(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad) {
   		String rangoFechas="";
   		String innerDisponibilidad="";
   		if (!fechaInicio.equals("0") && !fechaFin.equals("0")){
   			innerDisponibilidad=" INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
   			rangoFechas=" AND dp.fechaInicio>='"+fechaInicio+"' AND dp.fechaFin<='"+fechaFin+"' ";
   		}
   		String rangoPrecios="";
   		if (precio1>0 && precio2>0){
   			rangoPrecios=" AND prod.valor>="+precio1+" and prod.valor<="+precio2;
   		}
   		
   		Session session = HibernateUtil.getSessionFactory().openSession();
   		session.beginTransaction();
   		List<Producto> list = session
   				.createSQLQuery(
   						"SELECT prod.* FROM Producto prod "+innerDisponibilidad+" WHERE 1=1 "+rangoFechas+
   						rangoPrecios).addEntity(Producto.class).list();
   		session.getTransaction().commit();
   		return list;
   	}
       
     //Filtro productos por paquete con ciudad
       public List<Producto> FindProductsPorPaqueteConCiudad(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad,int idPaquete) {
   		String rangoFechas="";
   		String innerDisponibilidad="";
   		if (!fechaInicio.equals("0") && !fechaFin.equals("0")){
   			innerDisponibilidad=" INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
   			rangoFechas=" AND dp.fechaInicio>='"+fechaInicio+"' AND dp.fechaFin<='"+fechaFin+"' ";
   		}
   		String rangoPrecios="";
   		if (precio1>0 && precio2>0){
   			rangoPrecios=" AND prod.valor>="+precio1+" and prod.valor<="+precio2;
   		}
   		rangoPrecios=""; //El filtro sel valor se hace en el paquete, por ahora esta variable se deja vacia con posibles modificaciones en el futuro.
   		String ciudad="";
   		if (idCiudad>0){
   			ciudad=" and prod.ubicacion="+idCiudad;
   		}
   		Session session = HibernateUtil.getSessionFactory().openSession();
   		session.beginTransaction();
   		List<Producto> list = session
   				.createSQLQuery(
   						"SELECT prod.* FROM Producto prod "+innerDisponibilidad+" INNER JOIN Paquete_Producto pp ON prod.id = pp.producto "+
   				        "INNER JOIN Paquete pqt ON pqt.id = pp.paquete WHERE pqt.id = "+idPaquete+rangoFechas+
   						rangoPrecios+ciudad).addEntity(Producto.class).list();
   		session.getTransaction().commit();
   		return list;
   	}
     //Filtro productos por paquete sin ciudad
       public List<Producto> FindProductsPorPaqueteSinCiudad(String fechaInicio,String fechaFin,double precio1,double precio2,int idCiudad,int idPaquete) {
   		String rangoFechas="";
   		String innerDisponibilidad="";
   		if (!fechaInicio.equals("0") && !fechaFin.equals("0")){
   			innerDisponibilidad=" INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
   			rangoFechas=" AND dp.fechaInicio>='"+fechaInicio+"' AND dp.fechaFin<='"+fechaFin+"' ";
   		}
   		String rangoPrecios="";
   		if (precio1>0 && precio2>0){
   			rangoPrecios=" AND prod.valor>="+precio1+" and prod.valor<="+precio2;
   		}
   		rangoPrecios=""; //El filtro sel valor se hace en el paquete, por ahora esta variable se deja vacia con posibles modificaciones en el futuro.
   		
   		Session session = HibernateUtil.getSessionFactory().openSession();
   		session.beginTransaction();
   		List<Producto> list = session
   				.createSQLQuery(
   						"SELECT prod.* FROM Producto prod "+innerDisponibilidad+" INNER JOIN Paquete_Producto pp ON prod.id = pp.producto "+
   				        "INNER JOIN Paquete pqt ON pqt.id = pp.paquete WHERE pqt.id = "+idPaquete+rangoFechas+
   						rangoPrecios).addEntity(Producto.class).list();
   		session.getTransaction().commit();
   		return list;
   	}
   }