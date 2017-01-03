package app.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Editorial implements Serializable{
	//ATRIBUTOS DE LA CLASE
	private Long id;
	private String nombre;
	private Direccion direccion;
	private String nif;
	private Set<Libro> libros	= new HashSet<Libro>();
	//CONSTRUCTORES
	public Editorial(){
		
	}
	public Editorial (String nombre,Direccion direccion,String nif){
		this.nombre 	= nombre;
		this.direccion 	= direccion;
		this.nif 		= nif;
	}
	//MÉTODOS ACCESORES
	
	public void addLibro(Libro libro){  //MÉTODO DE SINCRONIZACIÓN
		libros.add(libro);
		libro.setEditorial(this);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
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
							"Dirección\t: " + this.direccion.toString() + System.lineSeparator() +
							"nif\t\t: "	+ this.nif + System.lineSeparator() +
							"Libros\t\t:"+lib;
		return resultado;
	}	
	 
}
