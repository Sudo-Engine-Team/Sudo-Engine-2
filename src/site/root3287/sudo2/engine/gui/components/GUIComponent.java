package site.root3287.sudo2.engine.gui.components;

import org.lwjgl.util.vector.Vector2f;

public class GUIComponent {
	private Vector2f position, scale;

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
}
