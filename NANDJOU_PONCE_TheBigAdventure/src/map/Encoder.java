package map;

import java.util.HashMap;

public class Encoder {
	public HashMap<Character, String> codes;
	
	public void add (Character code, String tile) {
		codes.put(code, tile);
	}
	
	
}
