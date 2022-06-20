package models;

import java.util.ArrayList;

public class Software {
	private String name;
	private ArrayList<String> brushes; 
	private int[] brushesSelectedIndices;
	private String fileFormat;
	private String animationTools;
	private ArrayList<String> renders; 
	private int[] rendersSelectedIndices;

	
	public Software(String name, ArrayList<String> brushes, int[] brushesSelectedIndices, String fileFormat, String animationTools,  ArrayList<String> renders, int[] rendersSelectedIndices) {
		this.name = name;
		this.brushes = brushes;
		this.brushesSelectedIndices = brushesSelectedIndices;
		this.fileFormat = fileFormat;
		this.animationTools = animationTools;
		this.renders = renders;
		this.rendersSelectedIndices = rendersSelectedIndices;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<String> getBrushes() {
		return this.brushes;
	}
	
	public int[] getBrushesSelectedIndices() {
		return this.brushesSelectedIndices;
	}
	
	public String getFileFormat() {
		return this.fileFormat;
	}
	
	public String getAnimationTools() {
		return this.animationTools;
	}
	
	public ArrayList<String> getRender() {
		return this.renders;
	}
	
	public int[] getRendersSelectedIndices() {
		return this.rendersSelectedIndices;
	}
}

