package com.ea.festivalservice.model;

import java.util.List;

public class MusicFestival {

	private String name = null;
	private List<Band> bands = null;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Band> getBands() {
		return bands;
	}
	public void setBands(List<Band> bands) {
		this.bands = bands;
	}

}
