package com.marketour.facade;


@com.marketour.persistence.Annotation(tipo = "Moneda")
public class FacadeUsuarios {
    public static java.lang.Boolean Autenticar(com.marketour.business.Credenciales credenciales) {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("Usuario");
        return users.Autenticar(credenciales.getUsuario() ,credenciales.getContrasena());
    }
    
    public static java.lang.Boolean CambiarContrasena(com.marketour.business.Credenciales credenciales) {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("Usuario");
        return users.CambiarContrasena(credenciales.getUsuario() ,credenciales.getContrasena());
    }
    
    public static java.lang.Boolean CambiarDireccion(com.marketour.business.Usuario usuario) {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("Usuario");
        return users.CambiarDireccion(usuario);
    }
    
    public static com.marketour.business.Administrador ConsultarAdministrador(int id) {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("Administrador");
        return ((com.marketour.business.Administrador)(users.Consultar(id)));
    }
    
    public static com.marketour.business.Cliente ConsultarCliente(int id) {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("Cliente");
        return ((com.marketour.business.Cliente)(users.Consultar(id)));
    }
    
    public static java.util.List<com.marketour.business.Cliente>  ConsultarClientes() {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("Cliente");
        return ((java.util.List<com.marketour.business.Cliente> )((java.util.List<?> )(users.ConsultarLista())));
    }
    
    public static java.lang.Object ConsultarMonedaTodos() {
        java.util.List<com.marketour.business.Moneda>  business = new java.util.ArrayList<com.marketour.business.Moneda> ();
        java.util.List<com.marketour.domain.Moneda>  domain = new java.util.ArrayList<com.marketour.domain.Moneda> ();
        com.marketour.persistence.Repository<com.marketour.domain.Moneda>  repository = new com.marketour.persistence.Repository<com.marketour.domain.Moneda> (com.marketour.domain.Moneda.class);
        domain = repository.FindAll();
        for (com.marketour.domain.Moneda item : domain) {
            business.add(com.marketour.business.Moneda.ConvertToBMoneda(item));
        }
        return business;
    }
    
    public static com.marketour.business.Proveedor ConsultarProveedor(int id) {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("Proveedor");
        return ((com.marketour.business.Proveedor)(users.Consultar(id)));
    }
    
    public static com.marketour.business.Usuario ConsultarUsuario(int id) {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("");
        return ((com.marketour.business.Usuario)(users.Consultar(id)));
    }
    
    public static java.util.List<com.marketour.business.Usuario>  ConsultarUsuarios() {
        com.marketour.business.functions.FactoryUsers users = new com.marketour.business.functions.FactoryUsers("");
        return ((java.util.List<com.marketour.business.Usuario> )((java.util.List<?> )(users.ConsultarLista())));
    }
    
    public static com.marketour.business.Cliente RegistrarCliente(com.marketour.business.Cliente business) {
        com.marketour.persistence.Repository<com.marketour.domain.Cliente>  repository = new com.marketour.persistence.Repository<com.marketour.domain.Cliente> (com.marketour.domain.Cliente.class);
        com.marketour.persistence.Repository<com.marketour.domain.Usuario>  repositoryUser = new com.marketour.persistence.Repository<com.marketour.domain.Usuario> (com.marketour.domain.Usuario.class);
        com.marketour.domain.Usuario domainUser = repositoryUser.FindById(business.getId());
        com.marketour.domain.Cliente domain = domainUser.getCliente();
        boolean update = domain != null;
        domain = repository.FindById(business.getId());
        domain = com.marketour.business.Cliente.ConvertToBDCliente(business);
        domain.setUsuario(domainUser);
        if (update)
            repository.Update(domain);
        else
            repository.Save(domain);
        
        return com.marketour.facade.FacadeUsuarios.ConsultarCliente(domain.getId());
    }
    
    public static java.lang.Object RegistrarProveedor(com.marketour.business.Proveedor business) {
        com.marketour.persistence.Repository<com.marketour.domain.Proveedor>  repository = new com.marketour.persistence.Repository<com.marketour.domain.Proveedor> (com.marketour.domain.Proveedor.class);
        com.marketour.persistence.Repository<com.marketour.domain.Usuario>  repositoryUser = new com.marketour.persistence.Repository<com.marketour.domain.Usuario> (com.marketour.domain.Usuario.class);
        com.marketour.domain.Usuario domainUser = repositoryUser.FindById(business.getId());
        com.marketour.domain.Proveedor domain = domainUser.getProveedor();
        boolean update = domain != null;
        domain = repository.FindById(business.getId());
        domain = com.marketour.business.Proveedor.ConvertToBDProveedor(business);
        domain.setUsuario(domainUser);
        if (update)
            repository.Update(domain);
        else
            repository.Save(domain);
        
        return com.marketour.facade.FacadeUsuarios.ConsultarProveedor(domain.getId());
    }
    
    public static com.marketour.business.Usuario RegistrarUsuario(com.marketour.business.Usuario business) {
        com.marketour.persistence.Repository<com.marketour.domain.Usuario>  repository = new com.marketour.persistence.Repository<com.marketour.domain.Usuario> (com.marketour.domain.Usuario.class);
        com.marketour.persistence.Repository<com.marketour.domain.Moneda>  repoCurrency = new com.marketour.persistence.Repository<com.marketour.domain.Moneda> (com.marketour.domain.Moneda.class);
        com.marketour.domain.Usuario domain = null;
        if ((business.getId()) > 0) {
            domain = repository.FindById(business.getId());
            domain = com.marketour.business.Usuario.ConvertToBDUsuario(business ,domain);
            if ((business.getIdMoneda()) > 0) {
                domain.setMoneda(repoCurrency.FindById(business.getIdMoneda()));
            } 
            repository.Update(domain);
            com.marketour.business.Credenciales credential = new com.marketour.business.Credenciales();
            credential.setContrasena(business.getPassword());
            credential.setUsuario(business.getLogin());
            com.marketour.facade.FacadeUsuarios.CambiarContrasena(credential);
            com.marketour.facade.FacadeUsuarios.CambiarDireccion(business);
        } else {
            domain = com.marketour.business.Usuario.ConvertToBDUsuario(business);
            if ((business.getIdMoneda()) > 0) {
                domain.setMoneda(repoCurrency.FindById(business.getIdMoneda()));
            } 
            repository.Save(domain);
        }
        return com.marketour.facade.FacadeUsuarios.ConsultarUsuario(domain.getId());
    }
    
}

