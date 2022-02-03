import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class PuzzleProblem {
	@Getter @Setter
	private String id;
	@Getter @Setter
	private int bottleSize;
	@Getter @Setter
	private Estado inicial;
	@Getter @Setter
	private Estado objetivo;
	

	public PuzzleProblem(Estado inicial, String id, int bottleSize) {
		super();
		this.id= id;
		this.inicial = inicial;
		this.bottleSize = bottleSize;
		
	}

	
	public PuzzleProblem() {
		
	}
	
	
	public ArrayList<Sucesor> Sucesores(Estado inicial) throws Exception {

		ArrayList<Sucesor> sucesores = new ArrayList<Sucesor>();
		
		for (int i = 0; i < inicial.size(); i++) {
			for (int b = 0; b < inicial.size(); b++) {
					
				if (i != b &&  inicial.get(i).getLiquidQuantity() != 0) { // La botella que miramos es diferente a si misma y no movemos nada de botellas vacías
						
					BottleLiquid cambia = inicial.get(i).getLiquids().get(0);
					int cantidad = inicial.get(i).getLiquids().get(0).getSize();
					if(ES_AccionPosible(inicial.get(i), inicial.get(b), cantidad)) {
											
						if(inicial.get(b).getLiquidQuantity() == 0) {
											
							doAccion(inicial.get(i), inicial.get(b), cantidad);
							Accion accion = new Accion(i, b, cantidad);
							Sucesor sucesor = new Sucesor(accion, inicial.toString(), 1);
							sucesores.add(sucesor);
								
							inicial.get(i).add(cambia);
							inicial.get(b).getLiquids().remove(0);
						}
						
						if(inicial.get(b).getLiquidQuantity() != 0 && inicial.get(b).getLiquids().get(0).getColor() == cambia.getColor()) {
							
							BottleLiquid yaDestino = inicial.get(b).getLiquids().get(0);
							doAccion(inicial.get(i), inicial.get(b), cantidad);
							Accion accion = new Accion(i, b, cantidad);
							Sucesor sucesor = new Sucesor(accion, inicial.toString(), 1);
							sucesores.add(sucesor);
							
							inicial.get(i).add(cambia);
							inicial.get(b).getLiquids().get(0).setSize(
								inicial.get(b).getLiquids().get(0).getSize() - cambia.getSize());
						}
					}		
				}
			}
		}
			
		return sucesores;
	}
	

	/***
	 * Precondiciones: la botella de origen tiene más líquido o igual Cantidad y la botella de destino
	 * tiene espacio suficiente para recibir Cantidad.
	 * @param Botella_origen
	 * @param Botella_destino
	 * @param Cantidad
	 * @return
	 */
	public static boolean ES_AccionPosible(Bottle Botella_origen, Bottle Botella_destino, int Cantidad) {
		boolean posible = false;
		int cantidadOrigen = Botella_origen.getLiquidQuantity();
		int cantidadDestino = Botella_destino.getLiquidQuantity();
		int espacioDestino = Botella_destino.getSize() - cantidadDestino;
		if (cantidadOrigen >= Cantidad && espacioDestino >= Cantidad) {
			posible = true;
		}
		return posible;
	}
	
	
	/***
	 * Acción: La BotellaOrigen se queda sin dicha Cantidad de líquido y La BotellaDestino recibe
	 * la Cantidad en el orden correspondiente y sólo mezclando los líquidos de igual color. 
	 * @param Botella_origen
	 * @param Botella_destino
	 * @param Cantidad
	 * @throws Exception
	 */
	public static void doAccion(Bottle Botella_origen, Bottle Botella_destino, int Cantidad) throws Exception {
		
		BottleLiquid liquid = Botella_origen.getLiquids().get(0);
		
		if(Botella_destino.getLiquidQuantity()!=0 && liquid.getColor() == Botella_destino.getLiquids().get(0).getColor()) {
			
				Botella_destino.getLiquids().get(0).setSize(Botella_destino.getLiquids().get(0).getSize() +
						Botella_origen.getLiquids().get(0).getSize());
				Botella_origen.getLiquids().remove(0);
			
		} else {
			
			Botella_destino.add(liquid);
			Botella_origen.getLiquids().remove(0);
		}
		
	}
	
}