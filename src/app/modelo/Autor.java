package app.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Autor implements Serializable{
	
	//ATRIBUTOS DE LA CLASE
	private Long id;
	private String nombre;
	private String nacionalidad;
	private String comentarios;
	private Set<Libro> libros 	= new HashSet<Libro>();
	
	//CONSTRUCTORES
	public Autor(){
	}
	public Autor(String nombre,String nacionalidad, String comentarios){
		this.nombre 		= nombre;
		this.nacionalidad 	= nacionalidad;
		this.comentarios 	= comentarios;
	}
	//MÉTODOS ACCESORES
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void addLibro(Libro libro){      //MÉTODO DE SINCRONIZACIÓN
		libros.add(libro);
		libro.getAutores().add(this);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Set<Libro> getLibros() {
		return libros;
	}
	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}
	//OTROS MÉTODOS
	@Override
	public String toString() {
		String lib = System.lineSeparator();
		
		for (Libro libro: this.libros){
			lib = lib+"\t"+libro.getTitulo()+ System.lineSeparator();
		}
		
		String resultado =	"Id\t\t: " + this.id + System.lineSeparator() +
							"Nombre\t\t: " + this.nombre + System.lineSeparator() +
							"Nacionalidad\t: " + this.nacionalidad.toString() + System.lineSeparator() +
							"Comentarios\t: "	+ this.comentarios.toString() + System.lineSeparator() +
							"Libros\t\t:"+lib;
		return resultado;
	}
	
}
