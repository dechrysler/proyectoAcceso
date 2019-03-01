package necesarios;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import maven.chrysler.com.Proyecto1.HibernateUtil;
import maven.chrysler.com.Proyecto1.Pokemon;
import maven.chrysler.com.Proyecto1.Tipo;

public class Modelo {
	public Modelo() {
		conectar();
	}
	@Override
	public void finalize() {
		desconectar();
	}
	public void conectar() {
		HibernateUtil.buildSessionFactory();
	}
	
	public void desconectar() {
		HibernateUtil.closeSessionFactory();
	}
	/**
	 * Guardamos arma en la base de datos
	 * @param pokemon
	 */
	public void guardar(Pokemon pokemon) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		sesion.save(pokemon);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public void modificar(Pokemon pokemon) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		sesion.update(pokemon);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public Pokemon eliminar(Pokemon pokemon){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.delete(pokemon);
        session.getTransaction().commit();
        session.close();

        return pokemon;
    }
	/**
	 * Guarda armas que se pasa como parametro
	 * @param tipo
	 */
	public void guardar(Tipo tipo) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		sesion.save(tipo);
		for(Pokemon poke : tipo.getPokemones()) {
			poke.setTipo(tipo);
		sesion.save(tipo);
		}
		sesion.getTransaction().commit();
		sesion.close();
		
	}
	
	public void modificar(Tipo tipo) {
		 Session session = HibernateUtil.getCurrentSession();
	        session.beginTransaction();
	        tipo.getPokemones().clear();
	        session.save(tipo);
	    	for(Pokemon poke : tipo.getPokemones()) {
				poke.setTipo(tipo);
				session.save(tipo);
			}
	        session.getTransaction().commit();
	        session.close();
	}
	
	 public void eliminar(Tipo tipo){
	        Session session = HibernateUtil.getCurrentSession();
	        session.beginTransaction();
	        session.delete(tipo);
	        session.getTransaction().commit();
	        session.close();
	    }
	 
	 public void borrarTodo(){
	        Session session = HibernateUtil.getCurrentSession();
	        session.beginTransaction();
	        session.createSQLQuery("truncate table tipo").executeUpdate();
	        session.createSQLQuery("truncate table pokemon").executeUpdate();
	        session.getTransaction().commit();
	        session.close();
	    }
	public List<Pokemon>getPokemones(){
		Session sesion = HibernateUtil.getCurrentSession();
		ArrayList<Pokemon> pokemones = (ArrayList<Pokemon>)sesion.createQuery("FROM Pokemon").list();
		return pokemones;
	}
	public List<Tipo> getTipo(){
        Session session = HibernateUtil.getCurrentSession();
        ArrayList<Tipo> tipos =(ArrayList<Tipo>) session.createQuery("FROM Tipo").list();
        session.close();
        return tipos;
    }
	 public List<Tipo> getTipoLibres(){
	        Session session = HibernateUtil.getCurrentSession();
	        ArrayList<Tipo> tipos = (ArrayList<Tipo>) session.createQuery("FROM Pokemon a WHERE a.tipo IS NULL").list();
	        session.close();
	        return tipos;
	    }

}
