package site.root3287.sudo2.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BitmapFontFile {
	private String file, image;
	private Map<String, String> fileInfo = new HashMap<>();
	private Map<Character, BitmapGlyph> charInfo = new HashMap<>();
	//First , Second, Ammount
	private Map<Character, Map<Character, Float>> kerningInfo = new HashMap<>();
	public BitmapFontFile(String file, String image) {
		this.file = file;
		this.image = image;
		loadFile();
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void loadFile(){
		 try {
	        	InputStream in = Class.class.getResourceAsStream(file);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	            String line;
				while((line = reader.readLine())!=null){
					String[] value = line.split(" ");
					if(value[0].matches("info") || value[0].matches("common") || value[0].matches("page") || value[0].matches("chars")){
						for(int i = 1; i < value.length; i++){
							String[] pairs = value[i].split("=");
							fileInfo.put(pairs[0], pairs[1]);
						}
					}
					if(value[0].matches("char")){
						String[][] allPairs = new String[16][2];
						int w = 0;
						for(int i = 1; i < value.length; i++){
							String[] pairs = value[i].split("=");
							if(pairs.length > 1){
								allPairs[w++] = pairs;
							}
						}
							BitmapGlyph glyph = new BitmapGlyph();
							glyph.id = Integer.parseInt(allPairs[0][1]);
							glyph.x = Integer.parseInt(allPairs[1][1]);
							glyph.y = Integer.parseInt(allPairs[2][1]);
							glyph.width = Integer.parseInt(allPairs[3][1]);
							glyph.height = Integer.parseInt(allPairs[4][1]);
							glyph.xOffset = Integer.parseInt(allPairs[5][1]);
							glyph.yOffset = Integer.parseInt(allPairs[6][1]);
							glyph.xAdvance = Integer.parseInt(allPairs[7][1]);
							glyph.u = (float) (glyph.x)/ Integer.parseInt(fileInfo.get("scaleW"));
							glyph.v = (float) (glyph.y)/ Integer.parseInt(fileInfo.get("scaleH"));
							glyph.u2 = (float) ((glyph.x+glyph.width))/ Integer.parseInt(fileInfo.get("scaleW"));
							glyph.v2 = (float) ((glyph.y+glyph.height))/ Integer.parseInt(fileInfo.get("scaleH"));
							glyph.imgWidth = Integer.parseInt(fileInfo.get("scaleW"));
							glyph.imgHeight = Integer.parseInt(fileInfo.get("scaleH"));
							charInfo.put((char)glyph.id, glyph);
					}
					if(value[0].matches("kerning")){
						String[][] allPairs = new String[16][2];
						int w = 0;
						for(int i = 1; i < value.length; i++){
							String[] pairs = value[i].split("=");
							if(pairs.length > 1){
								allPairs[w++] = pairs;
							}
						}
						Map<Character, Float> pairs = kerningInfo.get((char) Integer.parseInt(allPairs[0][1]));
						if(pairs != null || kerningInfo.containsKey((char) Integer.parseInt(allPairs[0][1]))){
							pairs.put((char)Integer.parseInt(allPairs[1][1]), Float.parseFloat(allPairs[2][1]));
						}else{
							pairs = new HashMap<>();
							pairs.put((char)Integer.parseInt(allPairs[1][1]), Float.parseFloat(allPairs[2][1]));
						}
						kerningInfo.put((char)Integer.parseInt(allPairs[0][1]), pairs);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// printKerning();
	}
	public Map<Character, BitmapGlyph> getGlyphs() {
		return charInfo;
	}
	public Map<String, String> getFileInfo() {
		return fileInfo;
	}
	
	public Map<Character, Map<Character, Float>> getKernings(){
		return kerningInfo;
	}
	
	@SuppressWarnings("unused")
	private void printKerning() {
		int size  = 0;
		for(Character x : kerningInfo.keySet()) {
			System.out.println(x);
			for(char y : kerningInfo.get(x).keySet()) {
				System.out.println("\t"+y);
				System.out.println("\t\t"+kerningInfo.get(x).get(y));
				size++;
			}
		}
		System.out.println("size: "+size);
	}
}
