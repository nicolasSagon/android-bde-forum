package android.bde_forum;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection implements Serializable {

	private String pass = null;
	private String name = null;
	private boolean enregistrer = false;
	private boolean connected = false;

	public Connection() {
		pass = null;
		name = null;
	}

	public Connection(String pseudo, String mdp, boolean save) {
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
	
	public void lancerConnection() throws IOException {
		
		URL connectURL = new URL("http://192.168.1.32:8000/connect?login="+ name +"&password=" +pass);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connectURL.openStream()));

        String inputLine;
        String result = "";
        while ((inputLine = in.readLine()) != null)
            result += inputLine;
        in.close();
        
        if (result.equals("true")) connected = true;
        else connected = false;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	

}
