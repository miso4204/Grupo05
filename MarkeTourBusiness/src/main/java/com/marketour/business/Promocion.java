package com.marketour.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import com.marketour.domain.Paquete;
import com.marketour.domain.Producto;

public class Promocion implements Serializable{

	private Integer id;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private String condiciones;
    private long descuento;
    private String codigo;
    private int estado;
    private Set<Paquete> paquetes = new HashSet<Paquete>(0);

   public Promocion() {
   }

	
   public Promocion(long descuento, int estado) {
       this.descuento = descuento;
       this.estado = estado;
   }
   public Promocion(String descripcion, Date fechaInicio, Date fechaFin, String condiciones, long descuento, String codigo, int estado, Set<Paquete> paquetes) {
      this.descripcion = descripcion;
      this.fechaInicio = fechaInicio;
      this.fechaFin = fechaFin;
      this.condiciones = condiciones;
      this.descuento = descuento;
      this.codigo = codigo;
      this.estado = estado;
      this.paquetes = paquetes;
   }
  
   public Integer getId() {
       return this.id;
   }
   
   public void setId(Integer id) {
       this.id = id;
   }

   public String getDescripcion() {
       return this.descripcion;
   }
   
   public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
   }

   public Date getFechaInicio() {
       return this.fechaInicio;
   }
   
   public void setFechaInicio(Date fechaInicio) {
       this.fechaInicio = fechaInicio;
   }

   public Date getFechaFin() {
       return this.fechaFin;
   }
   
   public void setFechaFin(Date fechaFin) {
       this.fechaFin = fechaFin;
   }

   public String getCondiciones() {
       return this.condiciones;
   }
   
   public void setCondiciones(String condiciones) {
       this.condiciones = condiciones;
   }

   public long getDescuento() {
       return this.descuento;
   }
   
   public void setDescuento(long descuento) {
       this.descuento = descuento;
   }

   public String getCodigo() {
       return this.codigo;
   }
   
   public void setCodigo(String codigo) {
       this.codigo = codigo;
   }

   public int getEstado() {
       return this.estado;
   }
   
   public void setEstado(int estado) {
       this.estado = estado;
   }

   public Set<Paquete> getPaquetes() {
       return this.paquetes;
   }
   
   public void setPaquetes(Set<Paquete> paquetes) {
       this.paquetes = paquetes;
   }
   
   public static Promocion ConvertToBPromocion(com.marketour.domain.Promocion promocion)
   {
   	Promocion pro=new Promocion();
   		
   	pro.setCodigo(promocion.getCodigo());
   	pro.setCondiciones(promocion.getCondiciones());
   	pro.setDescripcion(promocion.getDescripcion());
   	pro.setDescuento(promocion.getDescuento());
   	pro.setEstado(promocion.getEstado());
   	pro.setFechaFin(promocion.getFechaFin());
   	pro.setFechaInicio(promocion.getFechaInicio());
   	pro.setId(promocion.getId());
   	
   	if(promocion.getPaquetes() != null){
   		pro.setPaquetes(promocion.getPaquetes());
   	}
   	
   	return pro;
   }
	public static com.marketour.domain.Promocion ConvertToDBPromocion(Promocion promocion)
   {
		com.marketour.domain.Promocion pro=new com.marketour.domain.Promocion();
		pro.setCodigo(promocion.getCodigo());
	   	pro.setCondiciones(promocion.getCondiciones());
	   	pro.setDescripcion(promocion.getDescripcion());
	   	pro.setDescuento(promocion.getDescuento());
	   	pro.setEstado(promocion.getEstado());
	   	pro.setFechaFin(promocion.getFechaFin());
	   	pro.setFechaInicio(promocion.getFechaInicio());
	   	pro.setId(promocion.getId());
	   	
	   	if(promocion.getPaquetes() != null){
	   		pro.setPaquetes(promocion.getPaquetes());
	   	}
	   	
	   	return pro;
   }

}
