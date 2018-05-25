package site.root3287.sudo2.api;

import java.io.DataOutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class API {
	public static final String HOST = "https://api.root3287.site";
	public static int TIMEOUT = 10000;
	
	public static HttpsURLConnection request(ConnectionMethod method, String path) {
		return request(method, path, null);
	}
	
	public static HttpsURLConnection request(ConnectionMethod method, String path, HashMap<String, String> data) {
		HttpsURLConnection connection = null;
		try {
			int dataLength = 0;
			StringBuilder strData = new StringBuilder();
			if(data != null) {
				int i = 0;
				for(String key : data.keySet()) {
					if(i > 0) {
						strData.append("&");
					}
					strData.append(key+"="+data.get(key).replaceAll(" ", "\\ "));
					i++;
				}
				
				dataLength = strData.toString().getBytes(StandardCharsets.UTF_8).length;
			}
			
			URL url = null;
			if(method == ConnectionMethod.GET) {
				String outData = (strData.length()>0)?"?"+strData.toString():"";
				url = new URL(HOST+path+outData);
			}else {
				url = new URL(HOST+path);
			}
		
			String strMethod = "";
			switch(method) {
			case GET:
				strMethod = "GET";
				break;
			case POST:
				strMethod = "POST";
				break;
			default:
				strMethod = "GET";
				break;
			}
			
			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setInstanceFollowRedirects( false );
			connection.setRequestMethod( strMethod );
			connection.setConnectTimeout(TIMEOUT);
			connection.setRequestProperty( "Content-Type", "application/json"); 
			connection.setRequestProperty( "charset", "utf-8");
			connection.setRequestProperty( "Content-Length", Integer.toString( dataLength ));
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setUseCaches( false );
			
			if(method == ConnectionMethod.POST) {
				DataOutputStream out = new DataOutputStream(connection.getOutputStream());
				out.write(strData.toString().getBytes(StandardCharsets.UTF_8));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
}
