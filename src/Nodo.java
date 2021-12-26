import java.math.BigInteger;
import java.security.MessageDigest;

import lombok.Getter;
import lombok.Setter;

public class Nodo implements Comparable<Nodo> {
	@Getter @Setter
	private int id;
	@Getter @Setter
	private double coste;
	@Getter @Setter
	Estado estado;
	@Getter @Setter
	private Nodo padre;
	@Getter @Setter
	private Accion accion;
	@Getter @Setter
	private int profundidad;
	@Getter @Setter
	private float heuristica;
	@Getter @Setter
	private double valor;
		

	public Nodo (int id, double coste, Estado estado, Nodo padre, Accion accion, int profundidad, float heuristica, double valor) {
		this.id = id;
		this.coste = coste;
		this.estado = estado;
		this.padre = padre;
		this.accion = accion;
		this.profundidad = profundidad;
		this.heuristica = heuristica;
		this.valor = valor;
	}
	
	public Nodo() {
		
	}
	
	public int compareTo(Nodo n) {
		
		int menor = -1;
		// se ordenan por el valor del nodo.
		if (valor < n.valor) {
			menor = -1;
		} else if (valor > n.valor) {
			menor = 1;
		} else { // en caso de que tengan el mismo valor se ordenarán por el que tenga menor id.
			if (id < n.id) {
				menor = -1;
			} else if (id > n.id) {
				menor = 1;
			} 
		}
		
		return menor;
	}
	
	public String toString() {
		return String.format("[%d][%.2f,%s,%s,%s,%d,%.2f,%.2f]", id, coste, estado.toString(), padre == null ? "None":padre.getId(), accion == null ? "None" :accion.toString(), profundidad, heuristica, valor);
		
	}
	//[<ID>][<COSTO>,<ESTADO>,<ID_PADRE>,<ACCIÓN>,<PROFUNDIDAD>,<HEURISTICA>,<VALOR>]
	
}
