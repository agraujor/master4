/**
 * 
 */
package app.modelo;

/**Clase que extiende la clase Exception para implementar ElementoNoEncontradoException
 * @author alex
 *
 */
public class ElementoNoEncontradoException extends Exception {
	
	/**
	 * 
	 */
	//Atributos de la clase
	private Long idLibro = -1L;	//id del libro no encontrado en el caso de que utilicemos la id.
	private String isbn = null; //isbn del libro no encontrado en el caso de que utilicemos el ISBN.
	
	/**
	 * Constructor para ElementoNoEncontradoException.
	 * @param message Mensaje a discreción del desarrollador.
	 * @param id Id del libro que no se ha encontrado.
	 */
	public ElementoNoEncontradoException(String message, Long idLibro){
		
		super(message);
		this.idLibro = idLibro;
	}
	/**
	 * Constructor para ElementoNoEncontradoException.
	 * @param message Mensaje a discreción del desarrollador.
	 * @param isbn ISBN del libro que no se ha encontrado.
	 */
	public ElementoNoEncontradoException(String message, String isbn){
		
		super(message);
		this.isbn = isbn;
	}
	
	@Override
	public String toString(){
		if (idLibro==-1)
			return getMessage()+" ISBN= "+isbn+" no encontrada";
		else
			return getMessage()+" Id= "+idLibro+" no encontrada";
	}
	
	/**
	 * Método Get para la id del elemento no encontrado.
	 * @return la id del libro. Un valor de -1 significa que la excepción se produjo buscando por ISBN.
	 */	
	public Long getIdLibro(){
		
		return idLibro;
	}
	/**
	 * Método Get para el ISBN del libro no encontrado.
	 * @return la id del libro. Un valor de null significa que la excepción se produjo buscando por id.
	 */	
	public String getIsbnLibro(){
		
		return isbn;
	}
}
