package android.bde_forum;

import java.io.*;

public class connection implements Serializable {

	private String pass = null;
	private String name = null;
	private boolean enregistrer = false;

	public connection() {
		pass = null;
		name = null;
	}

	public connection(String pseudo, String mdp, boolean save) {
		setName(pseudo);
		setPass(mdp);
		setEnregistrer(save);
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass2) {
		this.pass = pass2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name2) {
		this.name = name2;
	}

	public boolean getEnregistrer() {
		return enregistrer;
	}

	public void setEnregistrer(boolean enregistrer) {
		this.enregistrer = enregistrer;
	}

	public void serialisation() {
		/* enregistrer dans un fichier le mot de passe (crypté) et le pseudo */
	}

	public void effacerCompte() {
		/* supprimer le fichier */
	}

}
