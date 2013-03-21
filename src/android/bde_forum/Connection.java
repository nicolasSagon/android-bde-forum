package android.bde_forum;

import java.io.*;
import java.net.URL;
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

		// fonction de cryptage
		double p = 433;
		double q = 719;
		double n = p * q;
		double phiden = (p - 1) * (q - 1);
		int compteur = 0;
		double PGCD1 = 0;
		double e = 0;

		while (PGCD1 != 1) {
			// Tant que compteur=0
			while (compteur == 0) {
				// Si p inférieur à e et si q inférieur à e et si e inférieur à
				// n
				if ((p < e) && (q < e) && (e < phiden)) {
					// La boucle se coupe (on peut aussi mettre le mot-clé :
					// break
					compteur = 1;
				}
				// Tant que rien n'est trouvé, e s'incrémente
				e = e + 1;
				// On récupère le résultat du pgcd
				PGCD1 = pgcd(e, phiden);
			}
		}

		String passCrypte = pass;
		int taille_du_mot = passCrypte.length();
		String passCrypte2 = null;
		double ascii = 0;
		double lettre_crypt = 0;
		char c;

		int i = 0;

		// Tant que i inférieur aux nombres de caractères
		while (i < taille_du_mot) {

			// Comme i s'incrémente jusqu'à egalité avec la taille du mot, à
			// chaque passage dans la fonction chaque lettre sera converti.
			c = passCrypte.charAt(i);
			double a = c;
			ascii = a;

			// ascii = Integer.parseInt(String.valueOf(passCrypte.charAt(i)));

			// On crypte la lettre ou plutot son code ASCII
			lettre_crypt = Math.pow(ascii, e) % n;

			passCrypte2 = passCrypte2 + String.valueOf(lettre_crypt);
			// On incrémente i
			i = i + 1;
		}

		String objet = name + "\n" + passCrypte2 + "\n" + enregistrer;
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

	private double pgcd(double a, double b) {
		while (a != b) {
			if (a > b) {
				a = a - b;
			} else {
				b = b - a;
			}
		}
		return a;

	}


}
