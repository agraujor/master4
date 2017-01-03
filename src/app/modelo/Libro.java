package app.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Libro implements Serializable{
	//ATRIBUTOS DE LA CLASE
	private Long id;
	private String titulo;
	private Set<Autor> autores	= new HashSet<Autor>();
	private Editorial editorial;
	private String isbn;
	private int publicacion;
	private double precio;
	private String descripcion;
	//CONSTRUCTORES
	public Libro(){
		
	}
	public Libro (String titulo, String isbn, int publicacion, double precio, String descripcion){
		this.titulo 		= titulo;
		this.isbn 			= isbn;
		this.publicacion	= publicacion;
		this.precio			= precio;
		this.descripcion 	= descripcion;
	}
	
	//MÉTODOS ACCESORES
	public void addAutor(Autor autor){	//MÉTODO DE SINCRONIZACIÓN
		autores.add(autor);
		autor.getLibros().add(this);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Set<Autor> getAutores() {
		return autores;
	}
	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}
	public Editorial getEditorial() {
		return editorial;
	}
	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(int publicacion) {
		this.publicacion = publicacion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//OTROS MÉTODOS
	@Override
	public String toString() {
		String aut = System.lineSeparator();
		
		for (Autor autor: this.autores){
			aut = aut+autor.getNombre()+ System.lineSeparator();
		}
		String resultado =	"\tId: " + this.id + System.lineSeparator() +
							"Título\t: " + this.titulo + System.lineSeparator() +
							"Autor\t: " + aut +
							"Editorial\t: "	+ this.editorial.getNombre() + System.lineSeparator() +
							"Isbn\t: " + this.isbn + System.lineSeparator() +
							"Publicación\t: " + this.publicacion + System.lineSeparator() +
							"Precio\t: " + this.precio + System.lineSeparator() +
							"Descripción\t: " + this.descripcion + System.lineSeparator() + System.lineSeparator();
		return resultado;
	}
	
}
