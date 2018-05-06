package site.root3287.sudo2.api;

import java.net.URL;

public class API {
	private static final String HOST = "https://api.root3287.site/";
	private String publicKey;
	private URL host;
	
	public API(String appKey) {
		
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public URL getHost() {
		return host;
	}

	public void setHost(URL host) {
		this.host = host;
	}

}
