package map;
import java.util.HashMap;
import java.lang.String;
import java.awt.geom.Point2D;
import tiles.Element;

public class Map {
	Point2D.Float playerPos;
	public HashMap<PointInt,String> terrain;
	public HashMap<PointInt,Element> elements;
	
	public void addTerrain(PointInt position, String tile) {
		terrain.put(position, tile);
	}
	
	public void addElement(Element element) {
		elements.put(element.position, element);
	}
	
	
	
}
