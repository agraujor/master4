/**
 * 
 */
package app.negocio;

import app.persistencia.FactoriaDao;
import app.persistencia.IntfzCrudDao;
import app.persistencia.SesionHibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import app.modelo.Autor;
import app.modelo.Editorial;
import app.modelo.Libro;
import app.modelo.ElementoNoEncontradoException;

/**
 * Clase que implementa la Interface ItfzGestionLibreria. Esta clase es la encargada de gestionar la
 * librería. Esta clase accede a la capa de persistencia a traves de la interface ItfzCrudDao.
 * @author alex
 *
 */

public class GestionLibreria implements ItfzGestionLibreria {
	
	//ATRIBUTOS DE LA CLASE
	
	IntfzCrudDao libreriaDao;//Utilizando este atributo accederemos a la capa de persistencia.
	/**
	 * Constructor para GestionLibreria.
	 */
	//METODOS DE LA CLASE
	public GestionLibreria(int bdd, int modo){
		
		FactoriaDao objetosDao = new FactoriaDao(bdd, modo);
		this.libreriaDao = objetosDao.getLibreriaDao();
	}
	
    /**
     * Método que solicita a la capa de persistencia el registro de un nuevo elemento
     * @param objeto elemento a dar de alta.
     * 
     * @return Devuelve True si se ha podido dar de alta.   
     */
	public boolean alta(Object objeto) {
		boolean alta= true;
		//DEPENDIENDO DE LA AUTÉNTICA NATURALEZA DEL OBJETO, HAREMOS UNAS COMPROBACIONES Y OTRAS Y LLAMAREMOS A UN MÉTODO U OTRO.
		
		Transaction tran = libreriaDao.getSession().beginTransaction();
		try{
			System.out.println("Dando de alta "+objeto.getClass()+"= "+alta+"."+ System.lineSeparator());

			if (objeto instanceof Libro){
				Libro libro = (Libro)objeto;
				if ((libro!=null)&&(libro.getPrecio()>0)){
					
					libreriaDao.alta(libro);
				}
			}	
			if (objeto instanceof Editorial){
				Editorial editorial  = (Editorial)objeto;
				libreriaDao.alta(editorial);
			}
			if (objeto instanceof Autor){
				Autor autor = (Autor)objeto;
				libreriaDao.alta(autor);
			}
			tran.commit();
		}catch(HibernateException he) {
			alta = false;
			tran.rollback();
		}
		
		libreriaDao.getSession().close();
		return alta;
	}

	/**
	 *Método que solicita a la capa de persistencia la eliminación un libro dada una id
	 *@param id Id del libro a borrar
	 *@return Devuelve True si se ha podido borrar  
	 */
	public boolean baja(Long id, int tabla) {

		boolean borrado = true;
		Transaction tran = libreriaDao.getSession().beginTransaction();
		try{
			switch(tabla){
			case LIBROS:
				libreriaDao.bajaLibro(id);
				System.out.println("Eliminando libro con id "+id+"."+ System.lineSeparator());
				break;
			case AUTORES:
				libreriaDao.bajaAutor(id);
				System.out.println("Eliminando autor con id "+id+"."+ System.lineSeparator());
				break;
			case EDITORIALES:
				libreriaDao.bajaEditorial(id);
				System.out.println("Eliminando editorial con id "+id+"."+ System.lineSeparator());
				break;
			default:
			}

			tran.commit();
		}
		catch(ElementoNoEncontradoException ex){
			System.out.println("LibroNoEncontradoException <Eliminando libro> :"+ex.toString()+"."+ System.lineSeparator());
			borrado = false;
		}catch (HibernateException he) {
			borrado = false;
			tran.rollback();
		}
		libreriaDao.getSession().close();
	    return borrado;
	}

	/**
	 * Método que solicita a la capa de persistencia todos los libros
	 * @return Lista de todos los libros
	 */
	public List<Libro> consultarTodosLibros() {
		List<Libro> libros =libreriaDao.consultarTodosLibros();
		System.out.println("Consultando todos los libros."+ System.lineSeparator());
		libreriaDao.getSession().close();
		return libros;

	}
	/**
	 * Método que solicita a la capa de persistencia todos los libros
	 * @return Lista de todos los libros
	 */
	public List<Autor> consultarTodosAutores() {
		List<Autor> autores = libreriaDao.consultarTodosAutores();
		System.out.println("Consultando todos los autores."+ System.lineSeparator());
		libreriaDao.getSession().close();
		return autores;
	
	}
	/**
	 * Método que solicita a la capa de persistencia todos los libros
	 * @return Lista de todos los libros
	 */
	public List<Editorial> consultarTodosEditoriales() {
		List<Editorial> editoriales = libreriaDao.consultarTodosEditoriales();	
		System.out.println("Consultando todas las editoriales."+ System.lineSeparator());
		libreriaDao.getSession().close();
		return editoriales;
	}

	/**
	 *Método que solicita a la capa de persistencia la consulta de un libro dado un ISBN
	 *@param isbn ISBN del libro a buscar
	 *@return Libro Devuelve el libro al que corresponde el ISBN
	 */	
	public Libro consultarISBN(String isbn) {

		Libro libro = null;
		try{
			libro = libreriaDao.consultarISBN(isbn);
			System.out.println("Consultando libro a partir de ISBN = "+isbn+"."+ System.lineSeparator());
		}
		catch(ElementoNoEncontradoException ex){
			System.out.println("ElementoNoEncontradoException <Consultando libro> :"+ex.toString()+"."+ System.lineSeparator());
		}
		libreriaDao.getSession().close();
		return libro;
	}
	/**
	 *Método que solicita a la capa de persistencia la consulta de un Autor dado un nombre
	 *@param nombre del autor a buscar
	 *@return Autor Devuelve el Autor al que corresponde el nombre
	 */
	public Autor consultarAutor(String nombre) {

		Autor autor = null;
	
		try {
			autor = libreriaDao.consultarAutor(nombre);
			System.out.println("Consultando Autor con nombre = "+nombre+"."+ System.lineSeparator());
		} catch (ElementoNoEncontradoException ex) {
			System.out.println("ElementoNoEncontradoException <Consultando autor> :"+ex.toString()+"."+ System.lineSeparator());
		}
		libreriaDao.getSession().close();
		return autor;
	}
	
	/**
	 *Método que solicita a la capa de persistencia la consulta de un Autor dado un nombre
	 *@param nombre de la editorial a buscar
	 *@return Editorial Devuelve la editorial a la que corresponde el nombre
	 */
	public Editorial consultarEditorial(String nombre) {

		Editorial editorial= null;

		try {
			editorial = libreriaDao.consultarEditorial(nombre);
			System.out.println("Consultando Editorial con nombre = "+nombre+"."+ System.lineSeparator());
		} catch (ElementoNoEncontradoException ex) {
			System.out.println("ElementoNoEncontradoException <Consultando editorial> :"+ex.toString()+"."+ System.lineSeparator());	
		}
		libreriaDao.getSession().close();
		return editorial;
	}
	
	/**
	 *Método que pide a la capa de persistencia la busqueda de libros cuyos nombres contengan un titulo dado.
	 *@param titulo Parte del titulo de los libros que se buscaran.
	 *@return Lista de Libros que contienen el título del argumento
	 */
	public List<Libro> consultarTitulo(String titulo) {
		List<Libro> libros = libreriaDao.consultarTitulo(titulo);
		System.out.println("Consultando libros que contengan en el título = "+titulo+"."+ System.lineSeparator());
		libreriaDao.getSession().close();
		return libros;
	}

	/**
	 * Método que solicita a la capa de persistencia la modificación del precio de un libro dado su ISBN
	 * @param Libro libro a modificar su precio
	 * @param precio Nuevo valor del precio del libro.
	 * @return True si se ha cambiado.
	 */
	public boolean modificarPrecio(Libro libro, double precio) {

		boolean modificado = true;
		if (precio>0){
			Transaction tran = libreriaDao.getSession().beginTransaction();
			try{
				libro.setPrecio(precio);
				libreriaDao.modificar(libro);
				System.out.println("Modificando ISBN = "+libro.getIsbn()+" con precio = "+precio+"."+ System.lineSeparator());
				tran.commit();
			}catch(HibernateException he){
				modificado = false;					
				tran.rollback();
			}
			libreriaDao.getSession().close();
		}
		else{
			System.out.println("Precio incorrecto.");
			modificado =false;
		}
		return modificado;
	}
}
