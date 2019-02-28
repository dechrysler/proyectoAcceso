package maven.chrysler.com.Proyecto1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tipo")
public class Tipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private long id;
	@Column(name= "nombre")
	private String nombre;
	@Column(name="debilidad")
	private String debilidad;
	@Column(name="ataque_especial")
	private String ataque_especial;
	@ManyToOne
    @JoinColumn(name = "id_pokemon")
    private Pokemon pokemon;
	
	public Tipo() {
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
	public String getDebilidad() {
		return debilidad;
	}
	public void setDebilidad(String debilidad) {
		this.debilidad = debilidad;
	}
	public String getAtaque_especial() {
		return ataque_especial;
	}
	public void setAtaque_especial(String ataque_especial) {
		this.ataque_especial = ataque_especial;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	public void setPokemon(Pokemon pokemon){
		this.pokemon=pokemon;
	}
	public Pokemon getPersonaje(){return pokemon;}

}