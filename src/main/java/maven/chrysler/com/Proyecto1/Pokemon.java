package maven.chrysler.com.Proyecto1;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="pokemon")
public class Pokemon  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private long id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="vida")
	private int vida;
	@Column(name="daño")
	private int daño;
	 @ManyToOne(cascade = CascadeType.DETACH)
	 @JoinColumn(name ="id_tipo")
	  private Tipo tipo ;
	 
	public  Pokemon (){
	
	}	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public int getDaño() {
		return daño;
	}
	public void setDaño(int daño) {
		this.daño = daño;
	}
	@Override
	public String toString() {
		return nombre;
	}
	
	    public void setTipo(Tipo tipo){
	        this.tipo = tipo;
	    }
	    public Tipo getTipos(){return  tipo;}
	
}
