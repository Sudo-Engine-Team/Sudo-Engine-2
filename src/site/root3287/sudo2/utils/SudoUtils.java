package site.root3287.sudo2.utils;

import java.util.List;

public class SudoUtils {
	public static float[] toFloatArray(List<Float> f){
		float[] temp = new float[f.size()];
		int i = 0;
		for(float fo : f){
			temp[i++] = fo;
		}
		return temp;
	}
	public static int[] toIntegerArray(List<Integer> f){
		int[] temp = new int[f.size()];
		int i = 0;
		for(int fo : f){
			temp[i++] = fo;
		}
		return temp;
	}
	
}
