package site.root3287.sudo2.text;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.texture.Texture;

public class BitmapFont {
	private Texture texture;
	private Model model;
	private BitmapFontFile bmFile;
	private String text;
	private Vector2f positon = new Vector2f(-0.5f,0), scale= new Vector2f(1f, 1f);
	public BitmapFont(String text, String bmText, String bmImage){
		bmFile = new BitmapFontFile(bmText);
		setTexture(new Texture(Loader.getInstance().loadTexture(bmImage)));
		this.setText(text);
		generateText(text);
	}
	
	public void generateText(String text){
		float xLine = 0; float yLine = 0;
		int i = 0;
		List<Float> pos = new ArrayList<>(), tex = new ArrayList<>();
		List<Integer> ind = new ArrayList<>();
		for(char c : text.toCharArray()){
			if((int) c == 10 || c == 32){
				xLine += bmFile.getGlyphs().get(c).xAdvance;
				continue;
			}
			BMQuad q = generateQuad(bmFile.getGlyphs().get(c), xLine, yLine, i);
			pos.addAll(q.pos);
			ind.addAll(q.ind);
			tex.addAll(q.tex);
			System.out.println(bmFile.getGlyphs().get(c).xAdvance);
			xLine+=bmFile.getGlyphs().get(c).xAdvance;
			i++;
		}
		System.out.println(tex);
		this.model = Loader.getInstance().loadToVAO(BMQuad.toFloatArray(pos), BMQuad.toFloatArray(tex), BMQuad.toIntegerArray(ind));
	}
	
	private BMQuad generateQuad(BitmapGlyph glyph, float xLine, float yLine, int i){
		float xx = xLine + glyph.xOffset, yy = yLine + glyph.yOffset;
		BMQuad quad = new BMQuad();
		quad.pos.add((xx-glyph.width)/glyph.imgWidth); quad.pos.add((yy+glyph.height)/glyph.imgHeight); quad.pos.add(0f); 	// BOTTOM RIGHT
		quad.pos.add(xx/glyph.imgWidth); quad.pos.add((yy+glyph.height)/glyph.imgHeight); quad.pos.add(0f);				//BOTTOM LEFT
		quad.pos.add((xx-glyph.width)/glyph.imgWidth); quad.pos.add(yy/glyph.imgHeight); quad.pos.add(0f);				//TOP RIGHT
		quad.pos.add(xx/glyph.imgWidth); quad.pos.add(yy/glyph.imgHeight); quad.pos.add(0f);							//TOP LEFT
		quad.ind.add(i*4);
		quad.ind.add(i*4+1);
		quad.ind.add(i*4+2);
		quad.ind.add(i*4+2);
		quad.ind.add(i*4+1);
		quad.ind.add(i*4+3);
		quad.tex.add((float) (glyph.x)/glyph.imgWidth); quad.tex.add((float) (glyph.y)/glyph.imgHeight);
		quad.tex.add((float) (glyph.x+glyph.width)/glyph.imgWidth); quad.tex.add((float) (glyph.y)/glyph.imgHeight);
		quad.tex.add((float) (glyph.x)/glyph.imgWidth); quad.tex.add((float) (glyph.y+glyph.height)/glyph.imgHeight);
		quad.tex.add((float) (glyph.x+glyph.width)/glyph.imgWidth); quad.tex.add((float) (glyph.y+glyph.height)/glyph.imgHeight);
		return quad;
	}

	public Model getModel() {
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
		public static int[] toIntegerArray(List<Integer> toString){
			int[] ret = new int[toString.size()];
			for(int i = 0; i < toString.size(); i++){
				ret[i] = toString.get(i);
			}
			return ret;
		}
	}
}
