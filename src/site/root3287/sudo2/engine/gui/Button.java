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
		scale = new Vector2f(400, 50);
		
		guiBG = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), position, scale);
		guiBG.rows = 64;
		guiBG.textureAtlas = true;
		guiBG.offset = new Vector2f(1, 1);
		
		Vector2f tempPosTOP = new Vector2f(position.x, position.y);
		tempPosTOP.y -= scale.y;
		Vector2f tempScaleTOP = new Vector2f(16,16);
		tempScaleTOP.x = scale.x;
		guiTOP = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosTOP, tempScaleTOP);
		guiTOP.rows = 64;
		guiTOP.textureAtlas = true;
		guiTOP.offset = new Vector2f(1, 0);
		
		Vector2f tempPosBOTTOM = new Vector2f(position.x, position.y);
		tempPosBOTTOM.y += scale.y;
		Vector2f tempScaleBOTTOM = new Vector2f(16,16);
		tempScaleBOTTOM.x = scale.x;
		guiBOTTOM = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosBOTTOM , tempScaleBOTTOM);
		guiBOTTOM.rows = 64;
		guiBOTTOM.textureAtlas = true;
		guiBOTTOM.offset = new Vector2f(1, 2);
		
		Vector2f tempPosLEFT = new Vector2f(position.x, position.y);
		tempPosLEFT.x += scale.x;
		Vector2f tempScaleLEFT = new Vector2f(16,16);
		tempScaleLEFT.y = scale.y;
		guiLEFT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosLEFT , tempScaleLEFT);
		guiLEFT.rows = 64;
		guiLEFT.textureAtlas = true;
		guiLEFT.offset = new Vector2f(0, 1);
		
		Vector2f tempPosRIGHT = new Vector2f(position.x, position.y);
		tempPosRIGHT.x -= scale.x;
		Vector2f tempScaleRIGHT = new Vector2f(16,16);
		tempScaleRIGHT.y = scale.y;
		guiRIGHT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosRIGHT , tempScaleRIGHT);
		guiRIGHT.rows = 64;
		guiRIGHT.textureAtlas = true;
		guiRIGHT.offset = new Vector2f(2, 1);
		
		Vector2f tempPosTOPLEFT= new Vector2f(position.x, position.y);
		tempPosTOPLEFT.x += scale.x;
		tempPosTOPLEFT.y -= scale.y;
		Vector2f tempScaleTOPLEFT = new Vector2f(16,16);
		guiTOPLEFT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosTOPLEFT , tempScaleTOPLEFT);
		guiTOPLEFT.rows = 64;
		guiTOPLEFT.textureAtlas = true;
		guiTOPLEFT.offset = new Vector2f(0, 0);
	
		Vector2f tempPosBOTTOMLEFT= new Vector2f(position.x, position.y);
		tempPosBOTTOMLEFT.x += scale.x;
		tempPosBOTTOMLEFT.y += scale.y;
		Vector2f tempScaleBOTTOMLEFT = new Vector2f(16,16);
		guiBOTTOMLEFT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosBOTTOMLEFT , tempScaleBOTTOMLEFT);
		guiBOTTOMLEFT.rows = 64;
		guiBOTTOMLEFT.textureAtlas = true;
		guiBOTTOMLEFT.offset = new Vector2f(0, 2);
		

		Vector2f tempPosTOPRIGHT= new Vector2f(position.x, position.y);
		tempPosTOPRIGHT.x -= scale.x;
		tempPosTOPRIGHT.y -= scale.y;
		Vector2f tempScaleTOPRIGHT = new Vector2f(16,16);
		guiTOPRIGHT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosTOPRIGHT , tempScaleTOPRIGHT);
		guiTOPRIGHT.rows = 64;
		guiTOPRIGHT.textureAtlas = true;
		guiTOPRIGHT.offset = new Vector2f(2, 0);
	
		Vector2f tempPosBOTTOMRIGHT= new Vector2f(position.x, position.y);
		tempPosBOTTOMRIGHT.x -= scale.x;
		tempPosBOTTOMRIGHT.y += scale.y;
		Vector2f tempScaleBOTTOMRIGHT = new Vector2f(16,16);
		guiBOTTOMRIGHT = new GuiTexture(Loader.getInstance().loadTexture("res/image/GUIAtlas.png"), tempPosBOTTOMRIGHT , tempScaleBOTTOMRIGHT);
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
