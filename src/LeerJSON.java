import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class LeerJSON {
	
	/***
	 * Obtenemos el contenido del archivo y creamos el PuzzleProblem con los atributos que contenga el problema elegido
	 * @param nombre
	 * @return
	 * @throws Exception
	 */
	public PuzzleProblem getProblemFromFile(String nombre) throws Exception {
    	String data = readFile(nombre);
    	JSONObject problemJSON = new JSONObject(data);
    	System.out.print("\nEstado inicial: "+problemJSON.getJSONArray("initState"));
    	System.out.print("\nTamaño de las botellas: "+problemJSON.getInt("bottleSize"));
    	List<Estado> bottles = getStateFromJSONArray(problemJSON.getJSONArray("initState"), problemJSON.getInt("bottleSize"));
    	
    	PuzzleProblem problem = new PuzzleProblem();
    	problem.setId(problemJSON.getString("id"));
    	problem.setBottleSize(problemJSON.getInt("bottleSize"));
    	problem.setInicial(bottles.get(0));
    	
    	return problem;
	}
	
	
	/***
	 * Transfroma una JSONArray en una lista de botellas con sus respectivos liquidos (Estado)
	 * @param path
	 * @return List<Bottle>
	 * @throws Exception
	 */
	public static List<Estado> getStateFromJSONArray(JSONArray bottlesArray, int bottleSize) throws Exception {
    	List<Estado> inicial = new ArrayList<Estado>();
    	Estado botellas = new Estado();
    	
    	for (int contador = 0; contador < bottlesArray.length(); contador++) {
			JSONArray array = bottlesArray.getJSONArray(contador);
			Bottle botella = new Bottle(bottleSize);
			for (int i = 0; i < array.length(); i++) {
				JSONArray subArray = array.getJSONArray(i);
				if(subArray.getInt(0)<0 || subArray.getInt(1)<0) {
//					throw new CapacityException("Capacity exceeded");
				}
				BottleLiquid liquid = new BottleLiquid(subArray.getInt(0), subArray.getInt(1));
				botella.addLiquid(liquid);
			}
			botellas.addBottle(botella);
		}
    	
    	inicial.add(botellas);
    	return inicial;
    	
    }
    
	/***
	 * Lee un archivo y devuelve su contenido
	 * @param path
	 * @return String
	 * @throws IOException
	 */
    public static String readFile(String nombre) throws IOException {
    	return new String(Files.readAllBytes(new File(nombre).toPath()), StandardCharsets.US_ASCII);
    }
    

}