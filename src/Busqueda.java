import java.io.StringReader;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

public class Busqueda {
	
	private static int id = 1;

	public ArrayList<Nodo> getSolution(PuzzleProblem problem) throws Exception {
		int profundidad = 1000000;
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
		Scanner teclado = new Scanner(System.in);
		boolean salir = false;
		int opcion;
		EscribirSolucion esc = new EscribirSolucion();
		
		while (!salir) {

			System.out.println("\n\nPor favor, introduce el algoritomo de búsqueda a aplicar o pulse 6 para salir.");
			System.out.println("\t1. Anchura (BREADTH)");
			System.out.println("\t2. Profundidad Acotada (DEPTH)");
			System.out.println("\t3. Coste uniforme (UNIFORM)");
			System.out.println("\t4. Voraz (GREEDY)");
			System.out.println("\t5. A* (A)");
			System.out.println("\t6. Salir");
			System.out.println("\n\nOpción elegida: ");
			try {

				opcion = teclado.nextInt();
				switch (opcion) {
				
					case 1:
						solucion = AlgoritmoBusqueda(problem, "BREADTH", profundidad);
						esc.Escribir(solucion, problem, "BREADTH");
						salir = true;
						break;
				
					case 2:
						solucion = AlgoritmoBusqueda(problem, "DEPTH", profundidad);
						esc.Escribir(solucion, problem, "DEPTH");
						salir = true;
						break;
					
					case 3:
						solucion = AlgoritmoBusqueda(problem, "UNIFORM", profundidad);
						esc.Escribir(solucion, problem, "UNIFORM");
						salir = true;
						break;
					
					case 4:
						solucion = AlgoritmoBusqueda(problem, "GREEDY", profundidad);
						esc.Escribir(solucion, problem, "GREEDY");
						salir = true;
						break;
					
					case 5:
						solucion = AlgoritmoBusqueda(problem, "A", profundidad);
						esc.Escribir(solucion, problem, "A");
						salir = true;
						break;
					
					default:
						System.out.println("Opción no válida./n/n");
				
				}
			
			} catch (InputMismatchException e) {
				System.out.println("Por favor, introduce un número.\n\n");
				teclado.next();
			}
		}
		return solucion;
	}
	
	private static ArrayList<Nodo> AlgoritmoBusqueda(PuzzleProblem problem, String estrategia, int profundidad) throws Exception{
		
	//	Visitado visitados = new Visitado();
		ArrayList<String> visit = new ArrayList<String>();
		Frontera frontera = new Frontera();
		ArrayList<Nodo> arbol = new ArrayList<Nodo>();
		
		boolean solucion = false;
	//	visitados.crear_vacio();
		Nodo nodo = new Nodo();
		nodo.setId(0);
		nodo.setPadre(null);
		nodo.setEstado(problem.getInicial());
		nodo.setCoste(0.0);
		nodo.setProfundidad(0);
		nodo.setAccion(null);
		nodo.setHeuristica(Heuristica(nodo.getEstado()));
		nodo.setValor(Valor(estrategia, nodo)); 
		frontera.insertarNodo(nodo);
		
		while(!frontera.esVacia() && !solucion) {
			nodo = frontera.primerNodo();
			
			if(Objetivo(nodo.getEstado())) {
				solucion = true;
				
			} else if (!visit.contains(nodo.getEstado().toString()) && (nodo.getProfundidad() < profundidad)) {
				visit.add(nodo.getEstado().toString());
				arbol.add(nodo);
				System.out.println(nodo);
				ArrayList<Nodo> listaNodosHijos = expandirNodo(problem, nodo, estrategia);
				
				for (int i = 0; i < listaNodosHijos.size(); i++) {
						
					//Insertamos los nodos hijos que se han generado de ese nodo
					frontera.insertarNodo(listaNodosHijos.get(i));
				}
			}	
		}
		
		return Camino(nodo);
	}
	
	/***
	 * Realizar el cálculo de la heurística
	 * @param estado
	 * @return
	 */
	public static int Heuristica (Estado estado) {
		int h = 0;
		ArrayList<Integer> Visitados = new ArrayList<>();
		for(Bottle botella : estado.getBottles()) {
			if(botella.getLiquids().isEmpty()) {
				h+=botella.getLiquids().size()+1;
			} else {
				h += botella.getLiquids().size();
				
				if(!Visitados.contains(botella.getLiquids().get(0).getColor())) {
					Visitados.add(botella.getLiquids().get(0).getColor());
				}
			}
		}

		return h - estado.getBottles().size();
	}
	
	/***
	 * Realizar el cálculo del valor
	 * @param estrategia
	 * @param nodo
	 * @return
	 */
	private static double Valor(String estrategia, Nodo nodo) {
		double valor = 0;
		switch (estrategia) {
		case "BREADTH":
			valor = nodo.getProfundidad();
			break;
		case "DEPTH":
			valor = redondeo((double) 1 / (nodo.getProfundidad() + 1));
			break;
		case "UNIFORM":
			valor = nodo.getCoste();
			break;
		case "GREEDY":
			valor = nodo.getHeuristica();
			break;
		case "A":
			valor = nodo.getCoste() + nodo.getHeuristica();
			break;
		}

		return valor;
	}

	
	private static double redondeo(double d) {
		return Math.round(d * Math.pow(10, 17)) / Math.pow(10, 17);
	}
	
	/***
	 * Comprobar si el estado actual es el estado objetivo que corresponde con la solución al problema
	 * @param estado
	 * @return
	 */
	public static boolean Objetivo(Estado estado) {
		boolean alcanzado = true;

		for (int i = 0; i < estado.size(); i++) {
			if (!estado.get(i).unColor()) {
				alcanzado = false;
				break;
			}
		}
		for (int i = 0; i < estado.size(); i++) {
			for (int j = 0; j < estado.size(); j++) {
				if (i!=j && estado.get(i).getLiquidQuantity() != 0 && estado.get(j).getLiquidQuantity() != 0 &&
					estado.get(i).getLiquids().get(0).getColor() == estado.get(j).getLiquids().get(0).getColor() &&
					(estado.get(i).getLiquidQuantity() + estado.get(j).getLiquidQuantity()) <= estado.get(i).getSize()) {
						alcanzado = false;
						return alcanzado;
				}
			}
		}
		

		return alcanzado;
	}
	
	/***
	 * Expandir uno de los nodos del arbol
	 * @param problem
	 * @param nodo
	 * @param estrategia
	 * @return
	 * @throws Exception
	 */
	private static ArrayList<Nodo> expandirNodo(PuzzleProblem problem, Nodo nodo, String estrategia) throws Exception {
		ArrayList<Nodo> nodosHijos = new ArrayList<Nodo>();
		List<Sucesor> sucesores = problem.Sucesores(nodo.getEstado());
		for (int i = 0; i < sucesores.size(); i++) {
			Nodo nodohijo = new Nodo();
			nodohijo.setId(id);
			nodohijo.setEstado(obtenerEstado((sucesores.get(i)).getNuevoEstado(), nodo.getEstado().get(0).getSize()));
			nodohijo.setPadre(nodo);
			nodohijo.setAccion(sucesores.get(i).getAccion());
			nodohijo.setProfundidad(nodo.getProfundidad() + 1);
			nodohijo.setCoste(nodo.getCoste() + sucesores.get(i).getCoste());
			nodohijo.setHeuristica(Heuristica(obtenerEstado((sucesores.get(i)).getNuevoEstado(), nodo.getEstado().get(0).getSize())));
			nodohijo.setValor(Valor(estrategia, nodohijo));
			nodosHijos.add(nodohijo);
			id++;
		}
		
		return nodosHijos;
	}
	
	/***
	 * A partir de un estado de tipo string obtenemos las botellas y los líquidos correspondientes
	 * @param estado
	 * @param size
	 * @return
	 */
	public static Estado obtenerEstado(String estado, int size) {

		Estado botellas = new Estado();
		BottleLiquid liquid;
		Bottle botella;
		try {
			JsonReader reader = Json.createReader(new StringReader(estado));
			JsonArray jsonObj = reader.readArray();
			for (int contador = 0; contador < jsonObj.size(); contador++) {
				JsonArray array = jsonObj.getJsonArray(contador);
				botella = new Bottle(size);
				for (int i = 0; i < array.size(); i++) {
					JsonArray subArray = array.getJsonArray(i);
					liquid = new BottleLiquid(subArray.getInt(0), subArray.getInt(1));
					botella.addLiquid(liquid);
				}
				botellas.addBottle(botella);
			}
		} catch (Exception ex) {
//				System.out.println("Exception");
		}
		return botellas;
	}
	
	/***
	 * Sacar el camino solución del árbol
	 * @param nodo
	 * @return
	 */
	private static ArrayList<Nodo> Camino(Nodo nodo) {
		ArrayList<Nodo> caminoSolucion = new ArrayList<>();
		caminoSolucion.add(nodo);
		System.out.println("\n\tSOLUCIÓN:\n");
		while (nodo.getPadre() != null) {
			nodo = nodo.getPadre();
			caminoSolucion.add(nodo);
		}
	
		for(int i=0; i<caminoSolucion.size(); i++) {
			System.out.println(caminoSolucion.get(i));
		}
		
		return caminoSolucion;
	}
	
	
}
