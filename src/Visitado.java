import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Visitado {

	@Getter @Setter
	List<Estado> visitados = new ArrayList<Estado>();
	@Getter @Setter
	private Bottle es;

	public Visitado() {
		visitados = new ArrayList<Estado>();
	}

	public void insertar(Estado estado) {
		visitados.add(estado);
	}

	public void crear_vacio() {
		visitados.clear();
	}

	
		
}