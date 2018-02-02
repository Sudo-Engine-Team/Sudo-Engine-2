package site.root3287.sudo2.events.event;

import site.root3287.sudo2.events.Event;
import site.root3287.sudo2.events.types.WindowResizeEventType;

public class WindowResizeEvent extends Event{

	public float width, height, pixelWidth, pixelHeight, scaleWidth, scaleHeight;
	
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getPixelWidth() {
		return pixelWidth;
	}
	public void setPixelWidth(float pixelWidth) {
		this.pixelWidth = pixelWidth;
	}
	public float getPixelHeight() {
		return pixelHeight;
	}
	public void setPixelHeight(float pixelHeight) {
		this.pixelHeight = pixelHeight;
	}
	
	public WindowResizeEvent(float width, float height, float pixelWidth, float pixelHeight, float scaleWidth, float scaleHeight) {
		super(new WindowResizeEventType());
		this.width = width;
		this.height = height;
		this.pixelWidth = pixelWidth;
		this.pixelHeight = pixelHeight;
		this.scaleHeight= scaleHeight;
		this.scaleWidth = scaleWidth;
	}

}
