package tiles;

import java.util.List;
import java.util.Optional;
import map.PointInt;

public class Element {
	Optional <String> name;
	public String skin;
	public PointInt position;
	Optional <Integer> health;
	String kind;
	Optional<PointInt> zoneDep;
	Optional<PointInt> zoneVar;
	Optional<String> behavior;
	Optional<Integer> damage;
	Optional<String> text;
	Optional<List<String>> steal;
	Optional<List<String>> trade;
	Optional<Boolean> locked;
	Optional<PointInt> flow;
	Optional<Boolean> phantomized;
	Optional<PointInt> teleport;
	
	Element(String skin, PointInt position, String kind){
		
	}
	
}
