package android.bde_forum;

import java.io.*;
import java.net.URL;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Environment;
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

	public void serialisation() throws IOException {

		String passCrypte = null;
		try {
			passCrypte = this.getHash(this.pass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String objet = "\n" + name + "\n" + passCrypte + "\n" + enregistrer;
		/* enregistrer dans un fichier le mot de passe (crypté) et le pseudo */
		File f1 = new File(Environment.getExternalStorageDirectory().getPath() + "/android/data/bde-forum_" + name);
		FileOutputStream f = new FileOutputStream(f1);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(objet);
		o.close();
		f.close();
	}

	public void effacerCompte() {
		File f1 = new File(Environment.getExternalStorageDirectory().getPath() + "/android/data/bde-forum_" + name);
		f1.delete();
	}

	public void lancerConnection() {

		URL connectURL;
		InputStream in = null;
		DataInputStream dis;
		try {
			connectURL = new URL(
					"http://server-android.no-ip.org:8000/connect?login="
							+ name + "&password=" + pass);

			in = connectURL.openStream();
			dis = new DataInputStream(new BufferedInputStream(in));
			String inputLine;
			String result = "";
			while ((inputLine = dis.readLine()) != null)
				result += inputLine;
			in.close();

			if (result.equals("true")) {
				connected = true;
			} else {
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

	
	
	// fonction de cryptage
		public String getHash(String password) throws NoSuchAlgorithmException {
			byte[] input = password.getBytes();
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(input, 0, input.length);
			int hashLength = 20; // SHA-1 donne un hash de longueur 20
			byte[] hash = new byte[hashLength];
			try {
				digest.digest(hash, 0, hashLength);
			} catch (DigestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String hash2 = hashToString(hash);
			return hash2;
		}

		//affiche le hash en string<
		public String hashToString(byte[] hash) {  
		    StringBuilder sb = new StringBuilder(); 
		    for (int i = 0; i < hash.length; i++) {  
		        int v = hash[i] & 0xFF; 
		        if(v < 16) {
		            sb.append("0"); 
		        }
		        sb.append(Integer.toString(v, 16)); 
		    }  
		    return sb.toString(); 
		}

}
