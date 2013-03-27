package metier;

import java.util.List;

public class Categorie {

	private String cat_name;
	private List<Topic> donnesTopic;
	
	public Categorie(String cat_name, List<Topic> donnesTopic) {
		super();
		this.cat_name = cat_name;
		this.donnesTopic = donnesTopic;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public List<Topic> getDonnesTopic() {
		return donnesTopic;
	}

	public void setDonnesTopic(List<Topic> donnesTopic) {
		this.donnesTopic = donnesTopic;
	}
	
	




}
