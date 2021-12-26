import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Estado {
	
	@Getter @Setter
	private List<Bottle> bottles = new ArrayList<>();

	public Estado(List<Bottle> bottles) {
		this.bottles = bottles;
	}

	public Estado() {
	}

	public void addBottle(Bottle bottle) {
		bottles.add(bottle);
	}

	
	@Override
	public String toString() {
		String botellas = "[";
		for (int i = 0; i < bottles.size(); i++) {
			if (i != bottles.size() - 1) {
				botellas = botellas + bottles.get(i).toString() + ",";
			} else {
				botellas = botellas + bottles.get(i).toString();
			}
		}
		botellas = botellas + "]";
		return botellas;
	}

	
	public int size() {
		return bottles.size();
	}

	public Bottle get(int posicion) {
		return bottles.get(posicion);
	}


}