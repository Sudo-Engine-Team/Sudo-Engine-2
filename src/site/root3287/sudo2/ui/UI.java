package site.root3287.sudo2.ui;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.camera.Camera;

public class UI {
	public static Vector2f DISPLAY_SIZE;
	public static Camera CAMERA;
	
	public static boolean mouseInBounds(UIElement w, Vector2f mouse){
		Vector2f pos = w.getAbsolutePosition();
		Vector2f size = w.getSize();
		if(!((mouse.y > pos.y - (size.y)) && (mouse.y < pos.y + (size.y))))
			return false;
		if(!((mouse.x > pos.x - (size.x)) && (mouse.x < pos.x + (size.x))))
			return false;
		return true;
}
}
