import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Accion {
	@Getter @Setter
	private int botellaOrigen;
	@Getter @Setter
	private int botellaDestino;
	@Getter @Setter
	private int cantidad;
	
	public String toString() {
		return String.format("(%d, %d, %d)", botellaOrigen, botellaDestino, cantidad);
	}

	public Accion() {
	}
}
