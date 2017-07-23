package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.font.FontType;
import site.root3287.sudo2.engine.font.GUIText;
import site.root3287.sudo2.engine.font.TextMeshData;
import site.root3287.sudo2.engine.shader.programs.FontShader;
import site.root3287.sudo2.logger.LogLevel;
import site.root3287.sudo2.logger.Logger;
import site.root3287.sudo2.utils.SudoMaths;

public class FontRender{
	private FontShader shader;
	private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
    private static List<GUIText> allText = new ArrayList<>();
    private Matrix4f projection;
    public FontRender() {
        this.shader = new FontShader();
    }
     
    public void render(){
    	//System.out.println("Edge: "+edge+"\nWidth: "+width+"\n");
    	GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        shader.start();
        shader.loadProjection(projection);
        for(FontType font : texts.keySet()){
        	Logger.log(LogLevel.DEBUG_RENDER, "Preparing to render some text");
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, font.getTextureAtlas());
            for(GUIText text : texts.get(font)){
            	Logger.log(LogLevel.DEBUG_RENDER, "Rendering Text");
            	 GL30.glBindVertexArray(text.getMesh());
                 GL20.glEnableVertexAttribArray(0);
                 GL20.glEnableVertexAttribArray(1);
                 ((FontShader) shader).loadColour(text.getColour());
                 // Larger text, less edge higher width (0.51, 0.2)
                 // small text, MOre edge less width (0.46, 0.19)
                 ((FontShader) shader).isDistanceField(text.getFont().isDistanceField());
                 ((FontShader) shader).loadDistanceFields(text.getFontSize()*(0.01f/9f)+0.5f, text.getFontSize()*(0.02f/9f)+0.13f);
                 Matrix4f transformationMatrix = SudoMaths.createTransformationMatrix(text.getPosition(), text.getRotation(), new Vector2f(text.getScale(), text.getScale()));
                 ((FontShader) shader).loadTranslation(transformationMatrix);
                 GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertexCount());
                 GL20.glDisableVertexAttribArray(0);
                 GL20.glDisableVertexAttribArray(1);
                 GL30.glBindVertexArray(0);
            }
        }
        shader.stop();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
    
    public static int loadText(GUIText text){
        FontType font = text.getFont();
        TextMeshData data = font.loadText(text);
        int vao = Loader.getInstance().loadText(data.getVertexPositions(), data.getTextureCoords());
        text.setMeshInfo(vao, data.getVertexCount());
        List<GUIText> textBatch = texts.get(font);
        if(textBatch == null){
            textBatch = new ArrayList<GUIText>();
            texts.put(font, textBatch);
        }
        textBatch.add(text);
        allText.add(text);
        return allText.size();
    }
    
    public static void removeText(int index){
    	GUIText text = allText.get(index);
        List<GUIText> textBatch = texts.get(text.getFont());
        textBatch.remove(text);
        Loader.getInstance().removeTextFromMemory(text.getMesh());
        if(textBatch.isEmpty()){
            texts.remove(texts.get(text.getFont()));
        }
        allText.remove(index);
    }
    
    public static void updateText(int index){
    	 FontType font = allText.get(index).getFont();
         TextMeshData data = font.loadText(allText.get(index));
         allText.get(index).setMeshInfo(allText.get(index).getMesh(), data.getVertexCount());
    }
    
    public List<GUIText> getAllText(){
    	return allText;
    }
    
	public void dispose(){
		shader.dispose();
	}
	
	public void setProjeciton(Matrix4f projection){
		this.projection = projection;
	}
}
