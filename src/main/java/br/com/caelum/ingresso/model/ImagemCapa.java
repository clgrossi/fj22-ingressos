package br.com.caelum.ingresso.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImagemCapa {

	@JsonProperty("Poster")
	private String url;	// pra criar os sets e gets, pode ser GGAS no quick access ou usar get(letra)/set(letra)

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
		
		
	
}
