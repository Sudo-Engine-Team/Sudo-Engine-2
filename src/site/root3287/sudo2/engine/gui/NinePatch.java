package site.root3287.sudo2.engine.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Loader;

public class NinePatch {
	private Vector2f offset, position, scale;
	private String file;
	private GuiTexture BACKGROUND, TOP, BOTTOM, LEFT, RIGHT, TOP_LEFT, BOTTOM_LEFT, TOP_RIGHT, BOTTOM_RIGHT; 
	
	/**
	 * Generate a Nine Stiched texture using a X*X size texture atlas that have at least a 3*3 grid.
	 * 
	 * @param file 		- The textureAtlas to use.
	 * @param position 	- placement of the item.
	 * @param scale 	- The scale of the item.
	 * @param rows 		- The number of row that the TextureAtlas have.
	 * @param size 		- The size in pixels per square.
	 */
	public NinePatch(String file, Vector2f position, Vector2f scale, Vector2f offset, int rows, int size){
		this.setFile(file);
		this.setOffset(offset);
		position = new Vector2f();
		scale = new Vector2f();
		
		int textureID = Loader.getInstance().loadTexture(file);
		
		BACKGROUND = new GuiTexture(textureID, position, scale);
		BACKGROUND.offset = new Vector2f(offset.x + 1, offset.y +1);
		BACKGROUND.textureAtlas = true;
		BACKGROUND.rows = rows;
		
		Vector2f tempPosTOP = new Vector2f(position.x, position.y);
		tempPosTOP.y -= scale.y;
		Vector2f tempScaleTOP = new Vector2f(size, size);
		tempScaleTOP.x = scale.x;
		TOP = new GuiTexture(textureID, tempPosTOP, tempScaleTOP);
		TOP.rows = rows;
		TOP.textureAtlas = true;
		TOP.offset = new Vector2f(offset.x + 1, offset.y + 0);
		
		Vector2f tempPosBOTTOM = new Vector2f(position.x, position.y);
		tempPosBOTTOM.y += scale.y;
		Vector2f tempScaleBOTTOM = new Vector2f(size, size);
		tempScaleBOTTOM.x = scale.x;
		BOTTOM = new GuiTexture(textureID, tempPosBOTTOM , tempScaleBOTTOM);
		BOTTOM.rows = rows;
		BOTTOM.textureAtlas = true;
		BOTTOM.offset = new Vector2f(offset.x + 1, offset.y + 2);
		
		Vector2f tempPosLEFT = new Vector2f(position.x, position.y);
		tempPosLEFT.x += scale.x;
		Vector2f tempScaleLEFT = new Vector2f(size, size);
		tempScaleLEFT.y = scale.y;
		LEFT = new GuiTexture(textureID, tempPosLEFT , tempScaleLEFT);
		LEFT.rows = rows;
		LEFT.textureAtlas = true;
		LEFT.offset = new Vector2f(offset.x + 0, offset.y + 1);
		
		Vector2f tempPosRIGHT = new Vector2f(position.x, position.y);
		tempPosRIGHT.x -= scale.x;
		Vector2f tempScaleRIGHT = new Vector2f(size, size);
		tempScaleRIGHT.y = scale.y;
		RIGHT = new GuiTexture(textureID, tempPosRIGHT , tempScaleRIGHT);
		RIGHT.rows = rows;
		RIGHT.textureAtlas = true;
		RIGHT.offset = new Vector2f(offset.x + 2, offset.y + 1);
		
		Vector2f tempPosTOPLEFT= new Vector2f(position.x, position.y);
		tempPosTOPLEFT.x += scale.x;
		tempPosTOPLEFT.y -= scale.y;
		Vector2f tempScaleTOPLEFT = new Vector2f(size, size);
		TOP_LEFT = new GuiTexture(textureID, tempPosTOPLEFT , tempScaleTOPLEFT);
		TOP_LEFT.rows = rows;
		TOP_LEFT.textureAtlas = true;
		TOP_LEFT.offset = new Vector2f(offset.x + 0, offset.y + 0);
	
		Vector2f tempPosBOTTOMLEFT= new Vector2f(position.x, position.y);
		tempPosBOTTOMLEFT.x += scale.x;
		tempPosBOTTOMLEFT.y += scale.y;
		Vector2f tempScaleBOTTOMLEFT = new Vector2f(size, size);
		BOTTOM_LEFT = new GuiTexture(textureID, tempPosBOTTOMLEFT , tempScaleBOTTOMLEFT);
		BOTTOM_LEFT.rows = rows;
		BOTTOM_LEFT.textureAtlas = true;
		BOTTOM_LEFT.offset = new Vector2f(offset.x + 0, offset.y + 2);
		

		Vector2f tempPosTOPRIGHT= new Vector2f(position.x, position.y);
		tempPosTOPRIGHT.x -= scale.x;
		tempPosTOPRIGHT.y -= scale.y;
		Vector2f tempScaleTOPRIGHT = new Vector2f(size, size);
		TOP_RIGHT = new GuiTexture(textureID, tempPosTOPRIGHT , tempScaleTOPRIGHT);
		TOP_RIGHT.rows = rows;
		TOP_RIGHT.textureAtlas = true;
		TOP_RIGHT.offset = new Vector2f(offset.x + 2, offset.y + 0);
	
		Vector2f tempPosBOTTOMRIGHT= new Vector2f(position.x, position.y);
		tempPosBOTTOMRIGHT.x -= scale.x;
		tempPosBOTTOMRIGHT.y += scale.y;
		Vector2f tempScaleBOTTOMRIGHT = new Vector2f(size, size);
		BOTTOM_RIGHT = new GuiTexture(textureID, tempPosBOTTOMRIGHT , tempScaleBOTTOMRIGHT);
		BOTTOM_RIGHT.rows = rows;
		BOTTOM_RIGHT.textureAtlas = true;
		BOTTOM_RIGHT.offset = new Vector2f(offset.x + 2, offset.y + 2);
	}
	
	/**
	 * Make a NinePatched Image using only three textures using a TextureAtlas image.
	 * @param file 			- the image to read;
	 * @param postion 		- where to place the image
	 * @param scale			- The size in total.
	 * @param corrner		- The offset of the corner piece;
	 * @param edge			- The offset of the edge piece;
	 * @param backgorund	- The offset of the background
	 * @param rows 			- The number of rows in the TextureAtlas
	 * @param size			- The size of each box in pixels.
	 */
	public NinePatch(String file, Vector2f postion, Vector2f scale, Vector2f corrner, Vector2f edge, Vector2f background, int rows, int size){
		this.setFile(file);
		this.position = postion;
		this.setScale(scale);
		
		int textureID = Loader.getInstance().loadTexture(file);
		
		BACKGROUND = new GuiTexture(textureID, position, scale);
		BACKGROUND.offset = background;
		BACKGROUND.textureAtlas = true;
		BACKGROUND.rows = rows;
		
		GuiTexture edgeTexture = new GuiTexture(textureID, position, new Vector2f(size, size));
		edgeTexture.rows = rows;
		edgeTexture.textureAtlas = true;
		edgeTexture.offset = edge;
		
		GuiTexture corrnerTexture = new GuiTexture(textureID, position, new Vector2f(size, size));
		corrnerTexture.rows = rows;
		corrnerTexture.textureAtlas = true;
		corrnerTexture.offset = corrner;
		
		TOP = edgeTexture.copy();
		TOP.setPosition(new Vector2f(postion.x, postion.y - scale.y));
		TOP.setScale(new Vector2f(size, scale.x));
		TOP.setRotation(-90);
		
		BOTTOM = edgeTexture.copy();
		BOTTOM.setPosition(new Vector2f(postion.x, postion.y + scale.y));
		BOTTOM.setRotation(90);
		BOTTOM.setScale(new Vector2f(size, scale.x));
		
		LEFT = edgeTexture.copy();
		LEFT.setPosition(new Vector2f(postion.x + scale.x, postion.y));
		LEFT.setScale(new Vector2f(size, scale.y));
		
		RIGHT = edgeTexture.copy();
		RIGHT.setPosition(new Vector2f(postion.x - scale.x, postion.y));
		RIGHT.setScale(new Vector2f(size, scale.y));
		RIGHT.setRotation(180);
		
		TOP_LEFT = corrnerTexture.copy();
		TOP_LEFT.setPosition(new Vector2f(postion.x + scale.x, postion.y - scale.y));
		
		BOTTOM_LEFT = TOP_LEFT.copy();
		BOTTOM_LEFT.setRotation(90);
		BOTTOM_LEFT.setPosition(new Vector2f(postion.x + scale.x, postion.y + scale.y));
		
		TOP_RIGHT = corrnerTexture.copy();
		TOP_RIGHT.setPosition(new Vector2f(postion.x - scale.x, postion.y - scale.y));
		TOP_RIGHT.setRotation(-90);
		
		BOTTOM_RIGHT = TOP_RIGHT.copy();
		BOTTOM_RIGHT.setRotation(-180);
		BOTTOM_RIGHT.setPosition(new Vector2f(postion.x - scale.x, postion.y + scale.y));
	}
	
	public NinePatch(Vector2f position, Vector2f scale, String background, String edge, String corners, int size){
		int backgroundID = Loader.getInstance().loadTexture(background);
		int edgeID = Loader.getInstance().loadTexture(edge);
		int corrnerID = Loader.getInstance().loadTexture(corners);
		
		BACKGROUND = new GuiTexture(backgroundID, position, scale);
		GuiTexture edgeTexture = new GuiTexture(edgeID, position, new Vector2f(size, size));
		GuiTexture corrnerTexture = new GuiTexture(corrnerID, position, new Vector2f(size, size));
		
		TOP = edgeTexture.copy();
		TOP.setPosition(new Vector2f(position.x, position.y - scale.y));
		TOP.setScale(new Vector2f(size, scale.x));
		TOP.setRotation(-90);
		
		BOTTOM = edgeTexture.copy();
		BOTTOM.setPosition(new Vector2f(position.x, position.y + scale.y));
		BOTTOM.setRotation(90);
		BOTTOM.setScale(new Vector2f(size, scale.x));
		
		LEFT = edgeTexture.copy();
		LEFT.setPosition(new Vector2f(position.x + scale.x, position.y));
		LEFT.setScale(new Vector2f(size, scale.y));
		
		RIGHT = edgeTexture.copy();
		RIGHT.setPosition(new Vector2f(position.x - scale.x, position.y));
		RIGHT.setScale(new Vector2f(size, scale.y));
		RIGHT.setRotation(180);
		
		TOP_LEFT = corrnerTexture.copy();
		TOP_LEFT.setPosition(new Vector2f(position.x + scale.x, position.y - scale.y));
		
		BOTTOM_LEFT = TOP_LEFT.copy();
		BOTTOM_LEFT.setRotation(90);
		BOTTOM_LEFT.setPosition(new Vector2f(position.x + scale.x, position.y + scale.y));
		
		TOP_RIGHT = corrnerTexture.copy();
		TOP_RIGHT.setPosition(new Vector2f(position.x - scale.x, position.y - scale.y));
		TOP_RIGHT.setRotation(-90);
		
		BOTTOM_RIGHT = TOP_RIGHT.copy();
		BOTTOM_RIGHT.setRotation(-180);
		BOTTOM_RIGHT.setPosition(new Vector2f(position.x - scale.x, position.y + scale.y));
	}
	
	public List<GuiTexture> getNinePatch(){
		List<GuiTexture> buttons = new ArrayList<>();
		buttons.add(BACKGROUND);
		buttons.add(TOP_LEFT);
		buttons.add(BOTTOM_LEFT);
		buttons.add(TOP_RIGHT);
		buttons.add(BOTTOM_RIGHT);
		buttons.add(TOP);
		buttons.add(BOTTOM);
		buttons.add(LEFT);
		buttons.add(RIGHT);
		return buttons;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Vector2f getOffset() {
		return offset;
	}

	public void setOffset(Vector2f offset) {
		this.offset = offset;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
}
