package tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import utilitaires.PointInt;
import parser.Lexer;
import parser.Result;
import parser.Token;

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
				case "name" : 
					res=lexer.nextResult();// Itll be ":"
					res=lexer.nextResult();
					name.of(res.content());
					break;
				case "skin" : 
					res=lexer.nextResult();
					res=lexer.nextResult();
					skin.of(res.content());
					break;
				case "position" : 
					var pos=new PointInt(0,0);
					res=lexer.nextResult();
					res=lexer.nextResult();
					res=lexer.nextResult();
					pos.x=Integer.parseInt(res.content());
					res=lexer.nextResult();
					res=lexer.nextResult();
					pos.y=Integer.parseInt(res.content());
					position.of(pos);
					break;
				case "health" : 
					res=lexer.nextResult();
					res=lexer.nextResult();
					int hp=Integer.parseInt(res.content());
					health.of(hp);
					break;
				case "zone" : 
					res=lexer.nextResult();
					res=lexer.nextResult();
					res=lexer.nextResult();
					var zoneD=new PointInt(0,0);
					zoneD.x=Integer.parseInt(res.content());
					res=lexer.nextResult();
					res=lexer.nextResult();
					zoneD.y=Integer.parseInt(res.content());
					var zoneV=new PointInt(0,0);
					res=lexer.nextResult();
					res=lexer.nextResult();
					res=lexer.nextResult();
					zoneV.x=Integer.parseInt(res.content());
					res=lexer.nextResult();
					res=lexer.nextResult();
					zoneV.y=Integer.parseInt(res.content());
					zoneDep.of(zoneD);
					zoneVar.of(zoneV);
					break;
				case "behavior" : 
					res=lexer.nextResult();
					res=lexer.nextResult();
					behavior.of(res.content());
					break;
				case "damage" : 
					res=lexer.nextResult();
					res=lexer.nextResult();
					damage.of(Integer.parseInt(res.content()));
					break;
				case "text" : 
					res=lexer.nextResult();
					res=lexer.nextResult();
					text.of(res.content());//Will need the """ again
					break;
				case "steal" : 
					res=lexer.nextResult();
					res=lexer.nextResult();
					var stl = new ArrayList<String>();
					while (res.token() != Token.RIGHT_BRACKET) {
						stl.add(res.content());
						res=lexer.nextResult();
						res=lexer.nextResult();
					}
					steal.of(stl);
					break;
				case "trade" : break;
				case "locked": break;
				case "flow" : break;
				case "phantomized" : break;
				case "teleport" : break;
				}
				
			default : break;
			
			}
		}
		
	}
	
	
	
}
