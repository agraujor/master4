package app.persistencia;

import java.util.List;

import app.modelo.Autor;
import app.modelo.Editorial;
import app.modelo.Libro;
import app.modelo.ElementoNoEncontradoException;

public interface IntfzCrudDao {
	
	public void alta(Object objeto);
	public void bajaLibro(Long id) throws ElementoNoEncontradoException;
	public void bajaAutor(Long id) throws ElementoNoEncontradoException;
	public void bajaEditorial(Long id) throws ElementoNoEncontradoException;
	public List<Libro> consultarTodosLibros();
	public List<Autor> consultarTodosAutores();
	public List<Editorial> consultarTodosEditoriales();
	
	public Libro consultarISBN(String isbn) throws ElementoNoEncontradoException;
	public List<Libro> consultarTitulo(String Titulo);
	public Autor consultarAutor(String nombre) throws ElementoNoEncontradoException ;
	public Editorial consultarEditorial(String nombre) throws ElementoNoEncontradoException ;
	public SesionHibernate getSession();
	public void modificar(Object libro);
}
