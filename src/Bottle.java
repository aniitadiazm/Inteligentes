import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Bottle {
	@Getter @Setter
	private int id;
	@Getter @Setter
	private int size;
	@Getter @Setter
	private List<BottleLiquid> Liquids = new ArrayList<>();
	
	
	
	public Bottle(List<BottleLiquid> Liquids, int size) {
		this.Liquids = Liquids;
		this.size = size;
	}
	
	public Bottle(int size) {
		this.size = size;
	}
	
	public Bottle() {
		
	}

	@Override
	public String toString() {
		String cadena = "[";
		for (int i = 0; i < Liquids.size(); i++) {
			if (i != Liquids.size() - 1) {
				cadena = cadena + Liquids.get(i).toString() + ",";
			} else {
				cadena = cadena + Liquids.get(i).toString();
			}
		}
		cadena = cadena + "]";
		return cadena;
	}
	
	/***
	 * Añadir una cantidad de líquido a una botella
	 * @param liquid
	 */
	public void addLiquid(BottleLiquid liquid) {
		// Same color extract and then read it with new quantity
		if ((getLiquidQuantity() + liquid.getSize()) <= size) {
			if (Liquids.isEmpty()) {
				Liquids.add(liquid);
			} else {
				if (liquid.getColor() == Liquids.get(Liquids.size()-1).getColor()) {
					Liquids.get(Liquids.size()-1).setSize(liquid.getSize() + Liquids.get(Liquids.size()-1).getSize());
				} else {
					Liquids.add(liquid);
				}
			}
		}
	}
	
	public void add(BottleLiquid liquid) {
		
		Liquids.add(0,liquid);
	}
	
	/***
	 * Dado una cantidad absoluta de liquido devuelve el conjunto de BottleLiquid
	 * @param quantity
	 * @return
	 */
	
//	public List<BottleLiquid> getLiquid(int quantity) {
//		int extractedLiquid = 0;
//		List<BottleLiquid> liquidList = new LinkedList<>();
//		
//		while(extractedLiquid < quantity) {
//			BottleLiquid liquid = Liquids.remove(0);
//			int liquidQuantity = liquid.getSize();
//	
//			// This liquid will fill our objective
//			if(extractedLiquid + liquidQuantity <= quantity) {
//				liquidList.add(liquid);
//				extractedLiquid += liquidQuantity;
//			}else if(extractedLiquid + liquidQuantity > quantity) { // This liquid is more than our objective add necessary and then back the rest to the bottle
//				BottleLiquid liquidExcess = new BottleLiquid(liquid.getColor(),(extractedLiquid+liquidQuantity) - quantity); 
//				extractedLiquid += (extractedLiquid+liquidQuantity) - quantity;
//				
//				// Set extracted liquid size to size necessary to fill the quantity requested
//				liquid.setSize(liquid.getSize() - liquidExcess.getSize());
//				liquidList.add(liquid);
//				
//				this.addLiquid(liquidExcess);
//			}
//
//		}
//		
//		return liquidList;
//	}
	

	/***
	 * Obtener la cantidad de líquido total que contiene una botella 
	 * @return
	 */
	public int getLiquidQuantity() {
		int quantity = 0;
		
		for(int i = 0; i < Liquids.size(); i++) {
			quantity += Liquids.get(i).getSize();
		}
		
		return quantity;
	}
	
	
	/**
	 * If stack is not empty returns 
	 * @return
	 */
//	public int getTopColor() {
//		return Liquids.size() > 0 ? Liquids.get(0).getColor():-1;
//	}
//	
//	
//	public BottleLiquid getTopLiquid() {
//		return Liquids.size() > 0 ? Liquids.get(0):null;
//	}
//	
//	
//	public int getTopSize() {
//		return Liquids.size() > 0 ? Liquids.get(0).getSize():-1;
//	}
	
	
	public boolean unColor() {
		boolean unColor = false;
		
		if(Liquids.size() == 0 || Liquids.size() == 1) {
			unColor = true;
		}
		return unColor;
	}
	
	
//	public boolean equals(Bottle b) {
//		boolean equal = true;
//		
//		if(b.getLiquidQuantity() == getLiquidQuantity()) {
//			for(int i=0; i < getLiquidQuantity(); i++) {
//				List<BottleLiquid> liquidsA = getLiquid(i);
//				List<BottleLiquid> liquidsB = b.getLiquid(i);
//				for(int a=0; i < liquidsA.size(); a++) {
//					BottleLiquid liquidA = liquidsA.get(a);
//					BottleLiquid liquidB = liquidsB.get(a);
//					if(liquidA.getColor() != liquidB.getColor() || liquidA.getSize() != liquidB.getSize()) {
//						equal = false;
//						break;
//					}
//				}
//				if(!equal) break;
//			}
//		}else { equal = false; }
//		return equal;
//	}

		
	
}
