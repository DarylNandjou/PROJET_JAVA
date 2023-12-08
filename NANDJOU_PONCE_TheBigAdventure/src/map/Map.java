package map;
import java.util.HashMap;
import java.lang.String;
import java.awt.geom.Point2D;
import tiles.Element;

public class Map {
	PointInt size;
	Point2D.Float playerPos;
	public HashMap<PointInt,String> terrain;
	public HashMap<PointInt,Element> elements;
	public Encoder encoder;
	
	public Map() {
		size.x=0; size.y=0;
		playerPos.x=0;
		playerPos.y=0;
		terrain.clear();
		elements.clear();
		encoder.codes.clear();
	}
	
	public void addTerrain(PointInt position, String tile) {
		terrain.put(position, tile);
	}
	
	public void addElement(Element element) {
		elements.put(element.position, element);
	}
	
	
	
}
