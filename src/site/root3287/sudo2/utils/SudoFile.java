package site.root3287.sudo2.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SudoFile {
	public static InputStream getInternal(String file) {
		return Class.class.getResourceAsStream(file);
	}
	public static InputStream getExturnal(String file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
