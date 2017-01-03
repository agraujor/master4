package app.persistencia;

import app.modelo.Autor;
import app.modelo.Editorial;
import app.modelo.Libro;
/**
 * Clase que implementa una factoría de DAOS, dependiendo de la base de datos y el modo, devolverá un dao u otro
 * @author alex
 *
 */
public class FactoriaDao{
	
	
	private int 						bdd;
	private int							modo;
	/*private IntfzCrudDao<Autor> 		autoresDao;
	private CrudLibrosDao				librosDao;
	private IntfzCrudDao<Editorial>		editorialesDao;*/
	private IntfzCrudDao				libreriaDao;

	 /**
	 * Constructor
    * Los parámetros indican el tipo de sesión que se creará
    * @param bdd tipo de base de datos: SesionHibernate.MYSQL SesionHibernate.ORACLE ...
    * @param modo de lectura/escritura: SesionHibernate.MODO_LECTURA/SesionHibernate.MODO_CREACION
    */ 
	public FactoriaDao(int bdd,int modo){
		this.bdd = bdd;
		this.modo = modo;
	
	}
	public IntfzCrudDao getLibreriaDao() {
	    
		if (libreriaDao == null)
			this.libreriaDao = new CrudDao (bdd,modo);
		
	    return libreriaDao; 
	}
	
	/*
	public IntfzCrudDao getAutoresDao() {
	    
		if (autoresDao == null)
			this.autoresDao = new CrudDao<Autor>(sesionF);
		
	    return autoresDao; 
	}
	public CrudLibrosDao getLibrosDao() {
		if (librosDao == null)
			this.librosDao = new CrudLibrosDao(sesionF);
		
	    return librosDao; 
	}
	public IntfzCrudDao getEditorialesDao() {
		if (editorialesDao == null)
			this.editorialesDao =  new CrudDao<Editorial>(sesionF);
		
	    return editorialesDao; 
	}*/
}