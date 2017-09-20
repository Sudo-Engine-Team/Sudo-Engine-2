package site.root3287.sudo2.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SudoFile {
	public BufferedInputStream getInternal(String file) {
		return new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream(file));
	}
	public BufferedInputStream getExturnal(String file) {
		try {
			return new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
