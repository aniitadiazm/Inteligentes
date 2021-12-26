import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscribirSolucion {

	public void Escribir(ArrayList<Nodo> solucion, PuzzleProblem problem, String estrategia) throws IOException {
		String ruta = estrategia + ".txt";
		File archivo = new File(ruta);

		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		bw.write("[id][coste,estado,padre,accion,profundidad,heuristica,valor]");
		for (int i = solucion.size() - 1; i >= 0; i--) {
			String linea = imprimirNodo(solucion.get(i), estrategia);
			bw.write(linea);
		}
		bw.close();

	}

	private String imprimirNodo(Nodo nodo, String estrategia) {
		String linea = "";
		if (estrategia.equals("DEPTH")) {
			linea = "\n[" + nodo.getId() + "][" + nodo.getCoste() + "," + nodo.getEstado().toString() + ","
					+ ponerPadre(nodo) + "," + ponerAccion(nodo) + "," + nodo.getProfundidad() + ","
					+ nodo.getHeuristica() + "," + nodo.getValor() + "]";
		} else {
			linea = "\n[" + nodo.getId() + "][" + nodo.getCoste() + "," + nodo.getEstado().toString() + ","
					+ ponerPadre(nodo) + "," + ponerAccion(nodo) + "," + nodo.getProfundidad() + ","
					+ nodo.getHeuristica() + "," + (int) nodo.getValor() + "]";
		}
		return linea;
	}
	
	private String ponerPadre(Nodo nodo) {
		String idPadre = "";
		if (nodo.getPadre() == null) {
			idPadre = "None";
		} else
			idPadre = "" + nodo.getPadre().getId();
		return idPadre;
	}
	
	private String ponerAccion(Nodo nodo) {
		String Accion = "";
		if (nodo.getAccion() == null) {
			Accion = "None";
		} else
			Accion = "" + nodo.getAccion();
		return Accion;
	}


}
