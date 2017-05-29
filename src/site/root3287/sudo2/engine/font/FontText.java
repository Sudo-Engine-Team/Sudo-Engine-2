package site.root3287.sudo2.engine.font;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.render.FontRender;
import site.root3287.sudo2.engine.shader.programs.FontShader;

public class FontText {
    private static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
    private static List<GUIText> allText = new ArrayList<>();
    private FontShader fontShader = new FontShader();
    private FontRender render;
     
    public FontText(){
        render = new FontRender(fontShader);
    }
     
    public void render(){
        render.render(texts);
    }
    
    /**
     * Load the text to the rendering system...
     * @param text The loaded text
     * @return int Text id;
     */
    public int loadText(GUIText text){
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
     
    public void removeText(int index){
    	GUIText text = allText.get(index);
        List<GUIText> textBatch = texts.get(text.getFont());
        textBatch.remove(text);
        Loader.getInstance().removeTextFromMemory(text.getMesh());
        if(textBatch.isEmpty()){
            texts.remove(texts.get(text.getFont()));
        }
        allText.remove(index);
    }
    
    public void updateText(int index){
    	 FontType font = allText.get(index).getFont();
         TextMeshData data = font.loadText(allText.get(index));
         int vao = Loader.getInstance().loadText(data.getVertexPositions(), data.getTextureCoords());
         allText.get(index).setMeshInfo(vao, data.getVertexCount());
    }
    
    public List<GUIText> getAllText(){
    	return allText;
    }
    
	public void dispose(){
		render.dispose();
	}
}
