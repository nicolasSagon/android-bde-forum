package android.bde_forum;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

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
	
	public void lancerConnection() {
		
		URL connectURL;
		InputStream in = null;
		DataInputStream dis;
		try {
			connectURL = new URL("http://server-android.no-ip.org:8000/connect?login="+ name +"&password=" +pass);

				in = connectURL.openStream();
	        	dis = new DataInputStream(new BufferedInputStream(in));
	            String inputLine;
	            String result = "";
	            while ((inputLine = dis.readLine()) != null)
				    result += inputLine;
				in.close();
				
				if (result.equals("true"))
				{
						connected = true;
				}
				else 
				{
					connected = false;
				}			
            
		} catch (Exception e) {
			Log.e("Connection.java", e.getMessage());
		}
        
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	

}
