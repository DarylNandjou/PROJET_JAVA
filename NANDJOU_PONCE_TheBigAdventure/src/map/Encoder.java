package map;

import java.util.HashMap;

public class Encoder {
	public HashMap<String, String> codes;
	
	public void add (String code, String tile) {
		codes.put(code, tile);
	}
	
	
}
