package tiles;

import java.util.List;
import java.util.Optional;

import map.PointInt;
import parser.Lexer;
import parser.Result;

public class Element {
	public Optional <String> name;
	public Optional<String> skin;
	public Optional<PointInt> position;
	public Optional <Integer> health;
	public Optional<String> kind;
	private Optional<PointInt> zoneDep;
	private Optional<PointInt> zoneVar;
	private Optional<String> behavior;
	public Optional<Integer> damage;
	public Optional<String> text;
	public Optional<List<String>> steal;
	public Optional<List<String>> trade;
	private Optional<Boolean> locked;
	public Optional<PointInt> flow;
	public Optional<Boolean> phantomized;
	public Optional<PointInt> teleport;
	
	
	public void getData (Result res, Lexer lexer){
		
		while((res = lexer.nextResult()) != null) {
			switch(res.token()) {
			
			case LEFT_BRACKET : break;
			
			case IDENTIFIER :
				switch(res.content()) {
				case "name" : break;
				case "skin" : break;
				case "position" : break;
				case "health" : break;
				case "zone" : break;
				case "behavior" : break;
				case "damage" : break;
				case "text" : break;
				case "steal" : break;
				case "trade" : break;
				case "flow" : break;
				case "phantomized" : break;
				case "teleport" : break;
				}
				
			default : break;
			
			}
		}
		
	}
	
	
	
}
