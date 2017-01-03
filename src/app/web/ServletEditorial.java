package app.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.modelo.Autor;
import app.modelo.Editorial;
import app.modelo.Libro;
import app.negocio.GestionLibreria;
import app.persistencia.SesionHibernate;

/**
 * Servlet implementation class ServletEditorial
 */
@WebServlet(name = "ServletEditorial", urlPatterns = {"/ServletEditorial"})
public class ServletEditorial extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Devolvemos la respuesta en xml
    	response.setContentType("text/xml;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        	out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        	String textoXML = "<?xml version='1.0' encoding='UTF-8'?>";
        	String seleccionado = request.getParameter("indice");
            
            GestionLibreria gl = new GestionLibreria(SesionHibernate.MYSQL,SesionHibernate.MODO_LECTURA);

     	   if (seleccionado==null){

 	           ArrayList<Editorial> editoriales = (ArrayList<Editorial>) gl.consultarTodosEditoriales();
 	
 	    	   
 	          // out.println("<div class='select'>");
 	           //out.println("<select id='nombre' onchange='buscaEditorial();'>");
 	        
 	          out.append("<editoriales>");
 	           for (Editorial e : editoriales){
 	        	   /*textoXML +="<editorial>";
 	        	   textoXML +="<nombre>"+e.getNombre()+"</nombre>";
 	        	   textoXML +="</editorial>";*/
 	        	   out.append("<editorial>");
 	        	   out.append("<nombre>").append(e.getNombre()).append("</nombre>");
 	        	   out.append("</editorial>");
 	           }
 	          System.out.println(response.getContentType()+" "+textoXML);
 	         out.append("</editoriales>");
 	        //   out.println("</select>");
 	          
     	   }else{
     		   Editorial e =gl.consultarEditorial(seleccionado); 
            
     		  out.append("<editorial>");
     		  out.append("<nombre>").append(e.getNombre()).append("</nombre>");
     		 /* Set<Libro> libros = e.getLibros();
     		  if(libros != null){
	               for(Libro l :libros){
	            	   out.append("<libro>").append(l.getTitulo()).append("</libro>");
	               }
     		   }*/
               out.append("<direccion>").append(e.getDireccion().toString()).append("</direccion>");
               out.append("</editorial>");
               
               //System.out.println(textoXML);
               
              
     	   }   
        	
     	  //out.println(textoXML);
        out.flush();
        	
    
            

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
