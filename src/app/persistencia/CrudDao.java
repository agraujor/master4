/**
 * 
 */
package app.persistencia;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import app.modelo.Autor;
import app.modelo.Editorial;
import app.modelo.Libro;
import app.modelo.ElementoNoEncontradoException;

/**
 * Clase que implementa la Interface ItfzCrudDao. Esta clase es la encargada de acceder a la BDD
 * para dar de alta,baja o listar libros.
 * @author alex
 *
 */
public class CrudDao implements IntfzCrudDao {

	private SesionHibernate sesionF;  //SESION HIBERNATE
	 /**
	 * Constructor
     * Los parámetros indican el tipo de sesión que se creará
     * @param bdd tipo de base de datos: SesionHibernate.MYSQL SesionHibernate.ORACLE ...
     * @param modo de lectura/escritura: SesionHibernate.MODO_LECTURA/SesionHibernate.MODO_CREACION
     */ 
	
	public CrudDao(int bdd, int modo) {
		// TODO Auto-generated constructor stub		
		sesionF = new SesionHibernate(bdd, modo);
	}
    /**
     * Método que crea un nuevo registro en la tabla de la BDD
     * @param objeto Datos del elemento a dar de alta.
     * 
     * @return Devuelve True si se ha podido dar de alta.   
     */ 
	@Override
	public void alta(Object objeto) {

        try {
        	sesionF.getSession().save(objeto); // DEPENDIENDO DE LA NATURALEZA DEL OBJETO, LO INSERTARÁ EN UNA TABLA U OTRA (HIBERNATE SE ENCARGA DE ELLO)       
        } catch (RuntimeException ex) {
            throw ex;
        } 
        
	}
	/**
	 *Método que elimina un libro de la tabla dado un id
	 *@param id Id del libro a borrar
	 *@return Devuelve True si se ha podido borrar
	 *@throws ElementoNoEncontradoException Si no hay ningun libro al que corresponda esa id.  
	 */
	@Override
	public void bajaLibro(Long id)  throws ElementoNoEncontradoException {
		boolean flag = true;
		Libro objeto = null;
        try {
            objeto = (Libro)sesionF.getSession().createCriteria(Libro.class).add(Restrictions.eq("id", id)).uniqueResult();
            if (objeto == null)
    	    	throw new ElementoNoEncontradoException("ElementoException <No encontrado libro borrando>",id);//Llamamos al constructor cuyos atribs son message e id ya que hemos buscado por Id
            sesionF.getSession().delete(objeto); 
        } catch (RuntimeException ex) {
            throw ex;
        } 
	}
	
	/**
	 *Método que elimina un AUTOR de la tabla dado un id
	 *@param id Id del AUTOR a borrar
	 *@return Devuelve True si se ha podido borrar
	 *@throws ElementoNoEncontradoException Si no hay ningun libro al que corresponda esa id.  
	 */
	@Override
	public void bajaAutor(Long id)  throws ElementoNoEncontradoException  {
		boolean flag = true;
		 Autor objeto = null;
        try {   
            objeto = (Autor)sesionF.getSession().createCriteria(Autor.class).add(Restrictions.eq("classId", id)).uniqueResult();
            if (objeto == null)
    	    	throw new ElementoNoEncontradoException("ElementoException <No encontrado autor borrando>",id);//Llamamos al constructor cuyos atribs son message e id ya que hemos buscado por Id

            sesionF.getSession().delete(objeto);  
        } catch (RuntimeException ex) {
            throw ex;
        }
	}
	
	/**
	 *Método que elimina una editorial de la tabla dado un id
	 *@param id Id del editorial a borrar
	 *@return Devuelve True si se ha podido borrar
	 *@throws ElementoNoEncontradoException Si no hay ningun libro al que corresponda esa id.  
	 */
	@Override
	public void bajaEditorial(Long id)  throws ElementoNoEncontradoException {
		Editorial objeto = null;
        try {
            objeto = (Editorial)sesionF.getSession().createCriteria(Editorial.class).add(Restrictions.eq("classId", id)).uniqueResult();
            if (objeto == null)
    	    	throw new ElementoNoEncontradoException("ElementoException <No encontrada editorial borrando>",id);//Llamamos al constructor cuyos atribs son message e id ya que hemos buscado por Id
            sesionF.getSession().delete(objeto); 
        } catch (RuntimeException ex) {
            throw ex;
        } 

	}

	/**
	 * Método que devuelve todos los libros de la BDD
	 * @return Lista de todos los libros
	 */
	@Override
	public List<Libro> consultarTodosLibros() {
		List<Libro> lista = null;  
        try {    
            lista = sesionF.getSession().createCriteria(Libro.class).list();
        } catch (RuntimeException ex) {
            throw ex;
        }
		return lista;
	}
	/**
	 * Método que devuelve todos los autores de la BDD
	 * @return Lista de todos los autores
	 */
	@Override
	public List<Autor> consultarTodosAutores() {
		List<Autor> lista = null;    
        try {    
            lista = sesionF.openSession().createCriteria(Autor.class).list();
        } catch (RuntimeException ex) {
            throw ex;
        }
		return lista;
	}
	/**
	 * Método que devuelve todos los editoriales de la BDD
	 * @return Lista de todos los editoriales
	 */
	@Override
	public List<Editorial> consultarTodosEditoriales() {
		List<Editorial> lista = null;
        try {    
            lista = sesionF.getSession().createCriteria(Editorial.class).list();
        } catch (RuntimeException ex) {
            throw ex;
        } 
		return lista;
	}
	/**
	 *Método que consulta un libro en la BDD a partir de un ISBN
	 *@param isbn ISBN del libro a buscar
	 *@return Libro Devuelve el libro al que corresponde el ISBN
	 *@throws ElementoNoEncontradoException Si no hay ningun libro al que corresponda ese ISBN. 
	 */
	@Override
	public Libro consultarISBN(String isbn) throws ElementoNoEncontradoException {
		Libro libro = null;

        try {    
            libro= (Libro)sesionF.getSession().createCriteria(Libro.class).add(Restrictions.eq("isbn", isbn)).uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } 
        if (libro == null)
	    	throw new ElementoNoEncontradoException("ElementoException <No encontrado libro consultando>",isbn);//Llamamos al constructor cuyos atribs son message e id ya que hemos buscado por Id

		return libro;
	}
	/**
	 *Método que busca libros cuyos nombres contengan un titulo dado.
	 *@param titulo Parte del titulo de los libros que se buscaran.
	 *@return Lista de Libros que contienen el título del argumento
	 */
	@Override
	public List<Libro> consultarTitulo(String titulo) {
		List<Libro> lista = null;
		
        try {    
            lista = sesionF.getSession().createCriteria(Libro.class).add(Restrictions.ilike("titulo", "%"+titulo+"%")).list();
        } catch (RuntimeException ex) {
            throw ex;
        } 
		return lista;
	}

	/**
	 * Método que modifica el precio de un libro dado su isbn
	 * @param Libro libro a modificar su precio
	 * @param precio Nuevo valor del precio del libro.
	 * @return True si se ha cambiado.
	 * @throws ElementoNoEncontradoException Si no se ha encontrado un libro al que corresponda el ISBN dado.
	 */
	@Override
	public void modificar(Object libro){
       try {   
            sesionF.getSession().merge(libro);
        } catch (RuntimeException ex) {
        	throw ex;
        }     	
	}
	
	
	
	
	/**
	 *Método que consulta un autor en la BDD a partir de un nombre
	 *@param nombre del autor a buscar
	 *@return Autor Devuelve el autor al que corresponde el nombre
	 *@throws ElementoNoEncontradoException Si no hay ningun autor al que corresponda ese nombre. 
	 */
	@Override
	public Autor consultarAutor(String nombre)  throws ElementoNoEncontradoException {
		Autor autor= null;
	
        try {   
            autor = (Autor)sesionF.getSession().createCriteria(Autor.class).add(Restrictions.eq("nombre", nombre)).uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        }
        if (autor == null)
	    	throw new ElementoNoEncontradoException("ElementoException <No encontrado autor consultando>",nombre);//Llamamos al constructor cuyos atribs son message e id ya que hemos buscado por Id
		return autor;
	}
	/**
	 *Método que consulta una editorial en la BDD a partir de un nombre
	 *@param nombre de la editorial a buscar
	 *@return Editorial Devuelve la editorial a la que corresponde el nombre
	 *@throws ElementoNoEncontradoException Si no hay ningun editorial al que corresponda ese nombre. 
	 */
	@Override
	public Editorial consultarEditorial(String nombre)  throws ElementoNoEncontradoException{
		Editorial editorial= null;
        try {    
            editorial = (Editorial)sesionF.getSession().createCriteria(Editorial.class).add(Restrictions.eq("nombre", nombre)).uniqueResult();
        } catch (RuntimeException ex) {
            throw ex;
        } 
        if (editorial == null)
	    	throw new ElementoNoEncontradoException("ElementoException <No encontrada editorial consultando>",nombre);//Llamamos al constructor cuyos atribs son message e id ya que hemos buscado por Id

		
		return editorial;
	}
	@Override
	public SesionHibernate getSession() {
		
		return sesionF;
	}


}
