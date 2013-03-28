package donnees;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.URL;

import org.jasypt.util.text.BasicTextEncryptor;

import android.os.Environment;
import android.util.Log;

public class Connection implements Serializable {

	private String pass = null;
	private String name = null;
	private boolean enregistrer = false;
	private boolean connected = false;
	private BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
	

	public Connection() {
		// création d'un Encryptor avec une clé de cryptage
		textEncryptor.setPassword("123Abc");
	}

	public Connection(String pseudo, String mdp, boolean save) {
		textEncryptor.setPassword("123Abc");
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

		
		String password = pass;

		String passCrypte = cryptage(password);

		String objet = "\n" + name + "\n" + passCrypte + "\n" + enregistrer;
		/* enregistrer dans un fichier le mot de passe (crypté) et le pseudo */
		File f1 = new File(Environment.getExternalStorageDirectory().getPath()
				+ "/android/data/bde-forum");
		FileOutputStream f = new FileOutputStream(f1);
		ObjectOutputStream o = new ObjectOutputStream(f);
		o.writeObject(objet);
		o.close();
		f.close();
	}

	public void deserialisation() throws StreamCorruptedException, IOException,
			ClassNotFoundException {
		FileInputStream fichier = new FileInputStream(Environment
				.getExternalStorageDirectory().getPath()
				+ "/android/data/bde-forum");
		ObjectInputStream ois = new ObjectInputStream(fichier);
		String objet = (String) ois.readObject();

		String[] details = objet.split("\n");
		// details[1] = name
		// details[2] = pass crypte
		// details[3] = rester connecte
		String mdp = decryptage(details[2]);
		pass = mdp;
		name = details[1];
		enregistrer = Boolean.parseBoolean(details[3]);
		ois.close();

	}

	public void effacerCompte() {
		File f1 = new File(Environment.getExternalStorageDirectory().getPath()
				+ "/android/data/bde-forum");
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
	public String cryptage(String password) {

		
		String myEncryptedPassword = textEncryptor.encrypt(password);

		return myEncryptedPassword;

	}

	// fonction de decryptage
	public String decryptage(String myEncryptedPassword) {

		String plainText = textEncryptor.decrypt(myEncryptedPassword);

		return plainText;
	}

}
