package models;

public class Render {
	private String materials;
	private String cameras;
	private String objects;
	private String name;
	
	public Render(String name, String materials, String cameras, String objects) {
		this.name = name;
		this.materials = materials;
		this.objects = objects;
		this.cameras = cameras;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getMaterials() {
		return this.materials;
	}
	
	public String getCameras() {
		return this.cameras;
	}
	
	public String getObjects() {
		return this.objects;
	}

}