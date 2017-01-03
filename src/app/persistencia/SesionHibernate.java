/**
 * 
 */
package app.persistencia;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *  Clase que crea la sesión hibernate, de momento sólo esta implementado MYSQL, se pueden seguir creando archivos de configuración
 *  y mediante la clase Factory se obtendría el DAO seleccionado
 * @author agrau
 *
 */
public class SesionHibernate {
	public static final int MYSQL	= 0;
	public static final int ORACLE	= 1;
	public static final int MODO_LECTURA = 0;
	public static final int MODO_CREACION = 1;
	
	private static final String ARCHIVO_MYSQL 	= "hibernateMysql";
	private static final String ARCHIVO_ORACLE 	= "hibernateOracle";
	
	
	private String archivoConfCrear		= "";
	private String archivoConfLectura	= "";
	private SessionFactory sf;
	private Session session;
	private int modo ;
	
	public SesionHibernate(int bdd, int modo){
		switch(bdd){
		case MYSQL	:
			archivoConfCrear = ARCHIVO_MYSQL;
			break;
		case ORACLE :
			archivoConfCrear = ARCHIVO_ORACLE;
			break;
		default:
			System.out.println("Error: Base de Datos no soportada");
		
		}
		archivoConfLectura = archivoConfCrear+"_leer"+".cfg.xml";
		archivoConfCrear = archivoConfCrear+".cfg.xml";
		if( modo ==MODO_CREACION)
			sf = new Configuration().configure(archivoConfCrear).buildSessionFactory();
		else
			sf = new Configuration().configure(archivoConfLectura).buildSessionFactory();
	}
	

	
	public SessionFactory getSesionF(){

		return sf;
		
	}
	
	public Session openSession(){
		if ((session ==null)|| !session.isOpen())
			session = sf.openSession();
		
		return session;
	}
	public Session getSession(){
		if ((session ==null)|| !session.isOpen())
			session = sf.openSession();
		
		return session;
	}

	public Transaction beginTransaction(){
		if ((session ==null)|| !session.isOpen())
			session = sf.openSession();
		return session.beginTransaction();
	}
	
	public void close(){
        if (session != null) {
            session.close();
        }
	}
}
