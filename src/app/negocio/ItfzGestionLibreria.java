/**
 * 
 */
package app.negocio;

import app.modelo.Autor;
import app.modelo.Editorial;
/**
 * @author alex
 *
 */
import app.modelo.Libro;
import java.util.List;

public interface ItfzGestionLibreria {
	final static int LIBROS = 0;
	final static int EDITORIALES = 1;
	final static int AUTORES = 2;
	
	public boolean alta(Object objeto);
	public boolean baja(Long id, int tabla);
	public List<Libro> consultarTodosLibros();
	public List<Autor> consultarTodosAutores();
	public List<Editorial> consultarTodosEditoriales();
	public Libro consultarISBN(String isbn);
	public Autor consultarAutor(String nombre);
	public Editorial consultarEditorial(String nombre);
	public List<Libro> consultarTitulo(String titulo);
	public boolean modificarPrecio(Libro libro, double precio);

}
