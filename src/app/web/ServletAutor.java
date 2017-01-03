package app.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.modelo.Autor;
import app.modelo.Libro;
import app.negocio.GestionLibreria;
import app.persistencia.SesionHibernate;

/**
 * Servlet implementation class ServletAutor
 */
@WebServlet("/ServletAutor")
public class ServletAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAutor() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       try (PrintWriter out = response.getWriter()) {
            

           String seleccionado = request.getParameter("indice");
           
           GestionLibreria gl = new GestionLibreria(SesionHibernate.MYSQL,SesionHibernate.MODO_LECTURA);

    	   if (seleccionado==null){
	          
	           ArrayList<Autor> autores = (ArrayList<Autor>) gl.consultarTodosAutores();

	          // out.println("<div class='select'>");
	           out.println("<select id='nombre' onchange='buscaAutor();'>");
	           System.out.println("<select id='nombre' onchange='buscaAutor()'>");
	           out.println("<option value='nada'>-</option>");
	           for (Autor a : autores){
	        	   out.println("<option value='"+a.getNombre()+"'>"+a.getNombre()+"</option>");
	        	  
	           }
	           out.println("</select>");

    	   }else{
    		   Autor autor = gl.consultarAutor(seleccionado);
    		   String info ="";
               info = info + "<h5>Nombre</h5> ";
               info = info + "<div style='margin: 10px; color: darkmagenta'>";
               info = info + autor.getNombre()+"</div>";
               
               info = info + "<h5>Nacionalidad</h5> ";
               info = info + "<div style='margin: 10px; color: darkmagenta'>";
               info = info + autor.getNacionalidad()+"</div>";
               
               info = info + "<h5>Comentarios</h5> ";
               info = info + "<div style='margin: 10px; color: darkmagenta'>";
               info = info + autor.getComentarios()+"</div>";
               info = info +  "<h5>Libros:</h5>  "+"<ul type='circle'>";
               info = info + "<div style='margin: 10px; color: darkmagenta'>";
               for(Libro l:autor.getLibros()){
            	   info = info +"<li>"+l.getTitulo()+"</li>";
               }
               info = info +"</div>"+"</ul>";

    		   out.println(info);  		   
    	   }   
        }
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}