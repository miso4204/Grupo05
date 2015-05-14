package com.marketour.persistence;


@com.marketour.persistence.Annotation(tipo = "SearchByLocation")
public class RepositoryProduct extends com.marketour.persistence.Repository<com.marketour.domain.Producto>  {
    public RepositoryProduct() {
        super(com.marketour.domain.Producto.class);
    }
    
    public java.util.List<com.marketour.domain.Producto>  FindByPackage(int id) {
        org.hibernate.Session session = com.marketour.hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        java.util.List<com.marketour.domain.Producto>  list = session.createSQLQuery(("SELECT prod.* FROM Producto prod INNER JOIN PaqueteProducto pp ON prod.id = pp.producto INNER JOIN Paquete pqt ON pqt.id = pp.paquete WHERE pqt.id = " + id)).addEntity(com.marketour.domain.Producto.class).list();
        session.getTransaction().commit();
        return list;
    }
    
    public java.util.List<com.marketour.domain.Producto>  FindProducts(java.lang.String fechaInicio, java.lang.String fechaFin, double precio1, double precio2, int idCiudad) {
        java.lang.String rangoFechas = "";
        java.lang.String innerDisponibilidad = "";
        if ((!(fechaInicio.equals("0"))) && (!(fechaFin.equals("0")))) {
            innerDisponibilidad = " INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
            rangoFechas = (((" AND dp.fechaInicio>='" + fechaInicio) + "' AND dp.fechaFin<='") + fechaFin) + "' ";
        } 
        java.lang.String rangoPrecios = "";
        if ((precio1 > 0) && (precio2 > 0)) {
            rangoPrecios = ((" AND prod.valor>=" + precio1) + " and prod.valor<=") + precio2;
        } 
        java.lang.String ciudad = "";
        if (idCiudad > 0) {
            ciudad = " and prod.ubicacion=" + idCiudad;
        } 
        org.hibernate.Session session = com.marketour.hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        java.util.List<com.marketour.domain.Producto>  list = session.createSQLQuery(((((("SELECT prod.* FROM Producto prod " + innerDisponibilidad) + " WHERE 1=1 ") + rangoFechas) + rangoPrecios) + ciudad)).addEntity(com.marketour.domain.Producto.class).list();
        session.getTransaction().commit();
        return list;
    }
    
    public java.util.List<com.marketour.domain.Producto>  FindProductsPorPaquete(java.lang.String fechaInicio, java.lang.String fechaFin, double precio1, double precio2, int idCiudad, int idPaquete) {
        java.lang.String rangoFechas = "";
        java.lang.String innerDisponibilidad = "";
        if ((!(fechaInicio.equals("0"))) && (!(fechaFin.equals("0")))) {
            innerDisponibilidad = " INNER JOIN Disponibilidad dp ON dp.producto=prod.id ";
            rangoFechas = (((" AND dp.fechaInicio>='" + fechaInicio) + "' AND dp.fechaFin<='") + fechaFin) + "' ";
        } 
        java.lang.String rangoPrecios = "";
        if ((precio1 > 0) && (precio2 > 0)) {
            rangoPrecios = ((" AND prod.valor>=" + precio1) + " and prod.valor<=") + precio2;
        } 
        rangoPrecios = "";
        java.lang.String ciudad = "";
        if (idCiudad > 0) {
            ciudad = " and prod.ubicacion=" + idCiudad;
        } 
        org.hibernate.Session session = com.marketour.hibernate.HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        java.util.List<com.marketour.domain.Producto>  list = session.createSQLQuery(((((((("SELECT prod.* FROM Producto prod " + innerDisponibilidad) + " INNER JOIN Paquete_Producto pp ON prod.id = pp.producto ") + "INNER JOIN Paquete pqt ON pqt.id = pp.paquete WHERE pqt.id = ") + idPaquete) + rangoFechas) + rangoPrecios) + ciudad)).addEntity(com.marketour.domain.Producto.class).list();
        session.getTransaction().commit();
        return list;
    }
    
}

