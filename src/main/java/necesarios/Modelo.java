package necesarios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import maven.chrysler.com.Proyecto1.HibernateUtil;
import maven.chrysler.com.Proyecto1.Pokemon;
import maven.chrysler.com.Proyecto1.Tipo;
import maven.chrysler.com.Proyecto1.Usuario;

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
		System.out.println(pokemon.getTipos());
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
	        for(Pokemon poke: tipo.getPokemones()) {
	        	poke.setTipo(null);
	        	session.update(poke);
	        }
	        	
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
	 public List<Tipo> getTipoLibres(Pokemon pokemon){
		 Session session = HibernateUtil.getCurrentSession();
	        ArrayList<Tipo> tipos =(ArrayList<Tipo>) session.createQuery("FROM Tipo").list();
	        session.close();
	        tipos.remove((Tipo)pokemon.getTipos());
	        return tipos;
	    }
	 public boolean iniciarSesion(String usuario, String contrasena) throws SQLException {
			Session sesion = HibernateUtil.getCurrentSession();
			Query query = sesion.createQuery("FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = SHA1(:contrasena)");
					query.setParameter("usuario", usuario);
					query.setParameter("contrasena", contrasena);
					Usuario cliente = (Usuario) query.uniqueResult();
					return (cliente != null);
		}


}
