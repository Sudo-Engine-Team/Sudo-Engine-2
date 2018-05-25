package site.root3287.sudo2.test.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import site.root3287.sudo2.api.API;
import site.root3287.sudo2.api.ConnectionMethod;

public class APITest {

	public static void main(String[] args) throws IOException {
		HashMap<String, String> data = new HashMap<>();
		data.put("uid", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		data.put("version", "1.4.2");
		HttpsURLConnection connection = API.request(ConnectionMethod.GET, "/sudo-engine/", data);
		InputStreamReader in = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
		
		StringBuilder out = new StringBuilder();
		int cha = in.read();
		while(cha != -1) {
			out.append((char)cha);
			cha = in.read();
		}
		System.out.println(out.toString().replace('\n', ' '));
	}

}
