import lombok.Getter;
import lombok.Setter;


public class Sucesor {

	@Getter @Setter
	private float coste;
	@Getter @Setter
	private Accion accion;
	@Getter @Setter
	private String nuevoEstado;

	public Sucesor(Accion accion, String nuevoEstado, float d) {
		this.accion = accion;
		this.nuevoEstado = nuevoEstado;
		this.coste = d;
	}
}