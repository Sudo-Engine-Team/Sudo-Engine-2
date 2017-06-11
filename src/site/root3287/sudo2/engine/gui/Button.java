package site.root3287.sudo2.engine.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Loader;

public class Button {
	private GuiTexture guiBG, guiTOP, guiBOTTOM, guiLEFT, guiRIGHT, guiTOPRIGHT, guiBOTTOMRIGHT, guiTOPLEFT, guiBOTTOMLEFT;
	private Vector2f position, scale; 
	public Button(){
		position = new Vector2f();
		scale = new Vector2f(200, 50);
		
		guiBG = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), position, scale);
		guiBG.rows = 64;
		guiBG.textureAtlas = true;
		guiBG.offset = new Vector2f(1, 1);
		
		Vector2f tempPosTOP = new Vector2f(position.x, position.y);
		tempPosTOP.y -= scale.y;
		guiTOP = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosTOP, scale);
		guiTOP.rows = 64;
		guiTOP.textureAtlas = true;
		guiTOP.offset = new Vector2f(1, 0);
		
		Vector2f tempPosBOTTOM = new Vector2f(position.x, position.y);
		tempPosBOTTOM.y += scale.y;
		guiBOTTOM = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosBOTTOM , scale);
		guiBOTTOM.rows = 64;
		guiBOTTOM.textureAtlas = true;
		guiBOTTOM.offset = new Vector2f(1, 2);
		
		Vector2f tempPosLEFT = new Vector2f(position.x, position.y);
		tempPosLEFT.x += scale.x;
		guiLEFT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosLEFT , scale);
		guiLEFT.rows = 64;
		guiLEFT.textureAtlas = true;
		guiLEFT.offset = new Vector2f(0, 1);
		
		Vector2f tempPosRIGHT = new Vector2f(position.x, position.y);
		tempPosRIGHT.x -= scale.x;
		guiRIGHT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosRIGHT , scale);
		guiRIGHT.rows = 64;
		guiRIGHT.textureAtlas = true;
		guiRIGHT.offset = new Vector2f(2, 1);
		
		Vector2f tempPosTOPLEFT= new Vector2f(position.x, position.y);
		tempPosTOPLEFT.x += scale.x;
		tempPosTOPLEFT.y -= scale.y;
		guiTOPLEFT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosTOPLEFT , scale);
		guiTOPLEFT.rows = 64;
		guiTOPLEFT.textureAtlas = true;
		guiTOPLEFT.offset = new Vector2f(0, 0);
	
		Vector2f tempPosBOTTOMLEFT= new Vector2f(position.x, position.y);
		tempPosBOTTOMLEFT.x += scale.x;
		tempPosBOTTOMLEFT.y += scale.y;
		guiBOTTOMLEFT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosBOTTOMLEFT , scale);
		guiBOTTOMLEFT.rows = 64;
		guiBOTTOMLEFT.textureAtlas = true;
		guiBOTTOMLEFT.offset = new Vector2f(0, 2);
		
		Vector2f tempPosTOPRIGHT= new Vector2f(position.x, position.y);
		tempPosTOPRIGHT.x -= scale.x;
		tempPosTOPRIGHT.y -= scale.y;
		guiTOPRIGHT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosTOPRIGHT , scale);
		guiTOPRIGHT.rows = 64;
		guiTOPRIGHT.textureAtlas = true;
		guiTOPRIGHT.offset = new Vector2f(2, 0);
	
		Vector2f tempPosBOTTOMRIGHT= new Vector2f(position.x, position.y);
		tempPosBOTTOMRIGHT.x -= scale.x;
		tempPosBOTTOMRIGHT.y += scale.y;
		guiBOTTOMRIGHT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosBOTTOMRIGHT , scale);
		guiBOTTOMRIGHT.rows = 64;
		guiBOTTOMRIGHT.textureAtlas = true;
		guiBOTTOMRIGHT.offset = new Vector2f(2, 2);
	}
	
	public List<GuiTexture> getAllButtons(){
		List<GuiTexture> buttons = new ArrayList<>();
		buttons.add(guiBG);
		buttons.add(guiTOP);
		buttons.add(guiBOTTOM);
		buttons.add(guiLEFT);
		buttons.add(guiRIGHT);
		buttons.add(guiTOPLEFT);
		buttons.add(guiBOTTOMLEFT);
		buttons.add(guiTOPRIGHT);
		buttons.add(guiBOTTOMRIGHT);
		return buttons;
	}
}
