import java.util.InputMismatchException;
import java.util.Scanner;

public class clasePrincipal {

	
	public static void main(String[] args) throws Exception {
		menu();
	}

	private static void menu() throws Exception {
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		String nombre;
		int opcion; // Guardaremos la opcion del usuario
		while (!salir) {
			System.out.println("\n\nElige entre las opciones disponibles:");
			System.out.println("\t1. Resolver un problema");
			System.out.println("\t0. Salir");
			try {

				opcion = sn.nextInt();
				switch (opcion) {
				
					case 1:
											
						System.out.println("\nIntroduzca el nombre del Problema que desea resolver (nombre.json):");
						nombre = sn.next();
						
						LeerJSON l = new LeerJSON();
						PuzzleProblem problem = l.getProblemFromFile(nombre);
						
						Busqueda b = new Busqueda();
						b.getSolution(problem);									
				
					case 0:
						salir = true;
						System.out.println("Saliendo..");
						break;
						
					default:
						System.out.println("Opción no válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número.");
				sn.next();
			}
				
		}
	}
}
