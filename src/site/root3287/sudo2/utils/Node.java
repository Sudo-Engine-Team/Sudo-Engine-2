package site.root3287.sudo2.utils;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class Node {
	private Node parent;
	private List<Node> children;
	private Vector3f position;
	
	public Node() {
		this.parent = null;
		this.children = new ArrayList<>();
		this.position = new Vector3f();
	}
	
	public void addChild(Node child) {
		child.setParent(this);
		children.add(child);
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getChildren() {
		return children;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
}
