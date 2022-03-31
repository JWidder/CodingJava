package scene;

import java.util.ArrayList;

/**
 * Die Klasse Scene verwaltet die Elemente aus denen die Scene besteht.
 * 
 * @author Johannes Widder
 *
 */
public class Scene {

	private ArrayList<ISceneElement> sceneContent;
	private ArrayList<ILight> lights;

	/**
	 * 
	 */
	public Scene() {
		super();
		this.sceneContent = new ArrayList<ISceneElement>();
		this.lights = new ArrayList<ILight>();
	}

	/**
	 * @param inElement
	 */
	public void addElement(ISceneElement inElement) {
		this.sceneContent.add(inElement);
	}

	/**
	 * @param inLight
	 */
	public void addElement(ILight inLight) {
		this.lights.add(inLight);
	}

	public ArrayList<ISceneElement> getElements() {
		return this.sceneContent;
	}

	public ArrayList<ILight> getLights() {
		return this.lights;
	}
}
