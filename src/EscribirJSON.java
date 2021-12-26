import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Stack;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

public class EscribirJSON {
	
	static Writer wr = null;

	/***
     * Dada una lista de las botellas devuelve el JSONArray con sus respectivos liquidos
     * @param bottles
     * @return JSONArray
     * @throws Exception
     */
    public static void getJSONFromBottles(List<Estado> bottles) throws Exception {
    	JsonArrayBuilder subArray = null;
		Estado Bottles = null;
		BottleLiquid Liquid = null;
		JsonArrayBuilder array = null;
		JsonArrayBuilder superArray = null;

		for (int i = 0; i < bottles.size(); i++) {
			superArray = Json.createArrayBuilder();
			Bottles = bottles.get(i);
			for (int j = 0; j < Bottles.size(); j++) {
				array = Json.createArrayBuilder();
				while (!Bottles.get(j).getLiquids().isEmpty()) {
					subArray = Json.createArrayBuilder();
					Liquid = Bottles.get(j).getLiquids().get(Bottles.get(j).getLiquids().size()-1);
					subArray = subArray.add(Liquid.getSize()).add(Liquid.getColor());
					array.add(subArray);
				}
				superArray.add(array);
			}
			String superArraySrt = superArray.build().toString();
			StringWriter sw = new StringWriter();
			sw.write(superArraySrt + "\n");
			wr.write(sw.toString());
		}
//		return superArray;
		wr.close();    	
    
    }
    
    public static Stack<BottleLiquid> reverse(Stack<BottleLiquid> Bottle) {
		Stack<BottleLiquid> result=new Stack<BottleLiquid>();
		while(!Bottle.isEmpty()) {
			result.push(Bottle.pop());
		}
		return result;
	}
	public void writeClose() throws Exception {
		wr.close();
	}

}
