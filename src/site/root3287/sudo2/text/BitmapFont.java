package site.root3287.sudo2.text;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.VBO;
import site.root3287.sudo2.engine.texture.Texture;

public class BitmapFont {
	private Texture texture;
	private VAO model;
	private BitmapFontFile bmFile;
	private String text;
	private Vector2f positon = new Vector2f(0,0), scale= new Vector2f(2f, 2f);
	private Vector4f colour;
	private boolean isDistanceField;
	
	public BitmapFont(String text, String bmText, String bmImage){
		bmFile = new BitmapFontFile(bmText, bmImage);
		if(bmFile.getFileInfo().containsKey("distanceField")) {
			this.isDistanceField = Boolean.parseBoolean(bmFile.getFileInfo().get("distanceField"));
		}else {
			this.isDistanceField = false;
		}
		setTexture(new Texture(Loader.getInstance().loadTexture(bmImage)));
		this.text = (text);
		colour = new Vector4f(1,1,1,1f);
		generateText(text);
	}
	
	public BitmapFont(String text, BitmapFontFile file){
		bmFile = file;
		if(bmFile.getFileInfo().containsKey("distanceField")) {
			this.isDistanceField = Boolean.parseBoolean(bmFile.getFileInfo().get("distanceField"));
		}else {
			this.isDistanceField = false;
		}
		setTexture(new Texture(Loader.getInstance().loadTexture(file.getImage())));
		this.text = (text);
		colour = new Vector4f(1,1,1,1f);
		generateText(text);
	}
	
	public void setColour(Vector4f colour) {
		this.colour = colour;
	}

	public void generateText(String text){
		float xLine = 0; float yLine = -Float.parseFloat(bmFile.getFileInfo().get("lineHeight"));;
		int i = 0;
		List<Float> pos = new ArrayList<>(), tex = new ArrayList<>();
		List<Integer> ind = new ArrayList<>();
		for(char c : text.toCharArray()){
			if((int) c == 10 || (int)c == 32 || (int) c == 9){
				if((int)c == 9) {
					xLine+= bmFile.getGlyphs().get((char) 32).xAdvance*3;
					continue;
				}
				xLine += bmFile.getGlyphs().get(c).xAdvance;
				if((int)c == 10) {
					yLine += Float.parseFloat(bmFile.getFileInfo().get("lineHeight"));
					xLine = 0;
				}
				
				continue;
			}
			float kerning = getKerningAt(i);
			BMQuad q = generateQuad(bmFile.getGlyphs().get(c), xLine, yLine, kerning, i);
			pos.addAll(q.pos);
			ind.addAll(q.ind);
			tex.addAll(q.tex);
			xLine+=bmFile.getGlyphs().get(c).xAdvance;
			i++;
		}
		//this.model = Loader.getInstance().loadToVAO(BMQuad.toFloatArray(pos), BMQuad.toFloatArray(tex), BMQuad.toIntegerArray(ind));
		
		this.model = new VAO();
		VBO posVBO = new VBO();
		VBO indVBO = new VBO(true);
		VBO tcVBO = new VBO();
		
		indVBO.setData(BMQuad.toIntegerArray(ind));
		tcVBO.setData(BMQuad.toFloatArray(tex));
		posVBO.setData(BMQuad.toFloatTranslatedArray(pos, xLine/Float.parseFloat(bmFile.getFileInfo().get("scaleW")), yLine/Float.parseFloat(bmFile.getFileInfo().get("scaleH"))));
		
		model.addVBO(indVBO);
		model.addVBO(0, 3, posVBO);
		model.addVBO(1, 2, tcVBO);
	}
	
	private BMQuad generateQuad(BitmapGlyph glyph, float xLine, float yLine, float kerning, int i){
		float xx = xLine + kerning + glyph.xOffset , yy = yLine + glyph.yOffset;
		//System.out.println(xx);
		BMQuad quad = new BMQuad();
		float x1 = (xx/glyph.imgWidth);
		float x2 = ((xx+glyph.width)/glyph.imgWidth);
		float y1 = (yy/glyph.imgHeight);
		float y2 = ((yy+glyph.height)/glyph.imgHeight);
		
		quad.pos.add(x1); quad.pos.add(-y1); quad.pos.add(0f);	//TOP LEFT
		quad.pos.add(x1); quad.pos.add(-y2); quad.pos.add(0f);	//BOTTOM LEFT
		quad.pos.add(x2); quad.pos.add(-y1); quad.pos.add(0f);	//TOP RIGHT
		quad.pos.add(x2); quad.pos.add(-y2); quad.pos.add(0f); 	// BOTTOM RIGHT
		quad.ind.add(i*4);
		quad.ind.add(i*4+1);
		quad.ind.add(i*4+2);
		quad.ind.add(i*4+2);
		quad.ind.add(i*4+1);
		quad.ind.add(i*4+3);
		quad.tex.add((float) (glyph.x)/glyph.imgWidth); quad.tex.add((float) (glyph.y)/glyph.imgHeight);
		quad.tex.add((float) (glyph.x)/glyph.imgWidth); quad.tex.add((float) (glyph.y+glyph.height)/glyph.imgHeight);
		quad.tex.add((float) (glyph.x+glyph.width)/glyph.imgWidth); quad.tex.add((float) (glyph.y)/glyph.imgHeight);
		quad.tex.add((float) (glyph.x+glyph.width)/glyph.imgWidth); quad.tex.add((float) (glyph.y+glyph.height)/glyph.imgHeight);
		return quad;
	}

	public VAO getModel() {
		return model;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector2f getPosition() {
		return positon;
	}

	public Vector2f getScale() {
		return scale;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		this.model.dispose();
		generateText(text);
	}

	private static class BMQuad{
		List<Float> pos = new ArrayList<>();
		List<Integer> ind = new ArrayList<>();
		List<Float> tex= new ArrayList<>();
		
		public static float[] toFloatArray(List<Float> toString){
			float[] ret = new float[toString.size()];
			for(int i = 0; i < toString.size(); i++){
				ret[i] = toString.get(i);
			}
			return ret;
		}
		public static float[] toFloatTranslatedArray(List<Float> toString, float width, float height){
			float[] temp = new float[toString.size()];
			if(toString.size() %3 == 0){ // make sure that we have 3 vertices
				for(int i =0; i < toString.size(); i+=3){
					Vector4f vec = new Vector4f(toString.get(i), toString.get(i+1), toString.get(i+2), 1);
					Matrix4f mat = new Matrix4f();
					mat.setIdentity();
					mat.translate(new Vector3f(-width/2, height/2, 0));
					Matrix4f.transform(mat, vec, vec);
					
					temp[i] = vec.x;
					temp[i+1] = vec.y;
					temp[i+2] = vec.z;
				}
			}
			return temp;
		}
		public static int[] toIntegerArray(List<Integer> toString){
			int[] ret = new int[toString.size()];
			for(int i = 0; i < toString.size(); i++){
				ret[i] = toString.get(i);
			}
			return ret;
		}
	}

	public Vector4f getColour() {
		return colour;
	}
	
	public BitmapFontFile getFile(){
		return bmFile;
	}
	private float getKerningAt(int index) {
		float res = 0;
		if(index+1 > text.length()-1) {
			return res;
		}
		char letter = text.charAt(index);
		char letterAhead = text.charAt(index+1);
		if(!bmFile.getKernings().containsKey(letter)) {
			return res;
		}
		if(!bmFile.getKernings().get(letter).containsKey(letterAhead)) {
			return res;
		}
		res = bmFile.getKernings().get(letter).get(letterAhead);
		return res;
	}

	public void setPosition(Vector2f pos) {
		this.positon = pos;
	}
	public void setScale(float x, float y) {
		this.scale = new Vector2f(x, y);
	}
	public void setScale(Vector2f s) {
		this.scale = s;
	}

	public boolean isDistanceField() {
		return isDistanceField;
	}

	public void setDistanceField(boolean isDistanceField) {
		this.isDistanceField = isDistanceField;
	}
	
	public static class DistanceFieldAttribs{
		private boolean isDistanceField;
		private Vector4f outlineColour;
		private float outlineDistance;
		private Vector4f dropShadowColour;
		private Vector2f dropShadowOffset;
		private float dropShadowSmoothing;
		public boolean isDistanceField() {
			return isDistanceField;
		}
		public void setDistanceField(boolean isDistanceField) {
			this.isDistanceField = isDistanceField;
		}
		public Vector4f getOutlineColour() {
			return outlineColour;
		}
		public void setOutlineColour(Vector4f outlineColour) {
			this.outlineColour = outlineColour;
		}
		public Vector4f getDropShadowColour() {
			return dropShadowColour;
		}
		public void setDropShadowColour(Vector4f dropShadowColour) {
			this.dropShadowColour = dropShadowColour;
		}
		public Vector2f getDropShadowOffset() {
			return dropShadowOffset;
		}
		public void setDropShadowOffset(Vector2f dropShadowOffset) {
			this.dropShadowOffset = dropShadowOffset;
		}
		public float getDropShadowSmoothing() {
			return dropShadowSmoothing;
		}
		public void setDropShadowSmoothing(float dropShadowSmoothing) {
			this.dropShadowSmoothing = dropShadowSmoothing;
		}
		public float getOutlineDistance() {
			return outlineDistance;
		}
		public void setOutlineDistance(float outlineDistance) {
			this.outlineDistance = outlineDistance;
		}
	}
}
