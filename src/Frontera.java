import java.util.PriorityQueue;
import java.util.Queue;
import lombok.Getter;
import lombok.Setter;

public class Frontera {
	
	@Getter @Setter
	Queue<Nodo> frontera;
	

	public Frontera() {
		frontera = new PriorityQueue<Nodo>();
	}

	public void insertarNodo(Nodo nodo) {
			frontera.add(nodo);
	}

	public Nodo primerNodo() {
		Nodo n = frontera.poll();
		return n;
	}

	public Nodo eliminarNodo() {
		Nodo n = frontera.remove();
		return n;
	}

	//Nodos ordenados de la queue
	public void recorrerCola() {
		for (Nodo n : frontera) {
			System.out.println(n.toString());
		}
	}
	
	public boolean esVacia() {
		return frontera.isEmpty();
	}
	
}