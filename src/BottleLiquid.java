
import lombok.Getter;
import lombok.Setter;

public class BottleLiquid {
	@Getter @Setter
	private int color;
	@Getter @Setter
	private int size;
	
	
	public BottleLiquid(int color, int size) {
		this.color = color;
		this.size = size;
	}
	
	
//	public JSONArray getJSONArray() {
//		JSONArray json = new JSONArray();
//		json.put(color);
//		json.put(size);
//		return json;
//	}
	
	@Override
	public String toString() {
		return "[" + color+ "," + size + "]";
	}
	
}
