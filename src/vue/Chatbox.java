package vue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


import android.app.Activity;
import android.app.AlertDialog;
import android.bde_forum.R;
import android.bde_forum.R.id;
import android.bde_forum.R.layout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Chatbox extends Activity {

	// infos de connexion
	private String ip = "78.206.144.7";
	private int port = 48555;

	// composants graphiques
	private Button envoi = null;
	private EditText newMess = null;
	private TextView discussion = null;

	// composants de connexion
	private Socket socket;
	private DataOutputStream dout;
	private DataInputStream din;

	final Context context = this;

	//Au lancement de l'activit�
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//permet de r�cup�rer le pseudo dans les pr�f�rences
		final SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		// cr�ation du socket � partir des infos de connexion
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		super.onCreate(savedInstanceState);

		// si la connexion s'effectue (<=> serveur ON)
		if (socket != null) {

			setContentView(R.layout.chatbox);

			newMess = (EditText) findViewById(R.id.chatmessage);
			discussion = (TextView) findViewById(R.id.discussion);
			discussion.setMovementMethod(new ScrollingMovementMethod());
			envoi = (Button) findViewById(R.id.envoimessage);

			// listener sur le bouton Envoyer
			envoi.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					// on envoie le message au serveur (encapsul� dans le code HTML pour la mise en forme, et pr�c�d� du pseudo)
					processMessage(
							"<font color=\"blue\">"
							+ prefs.getString("pseudo", "") //r�cup�re le pseudo enregistr� dans les pr�f�rences
							+ " : "
							+ "</font>"
							+ newMess.getText().toString()
							);
				}
				
				

			});

			// on attribue les flux d'entr�e/de sortie sur le socket aux streams
			// correspondants
			try {
				din = new DataInputStream(socket.getInputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				dout = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// on d�clare le thread d'�coute et on lui affecte une m�thode � run
			Thread t = new Thread() {

				@Override
				public void run() {

					// boucle d'�coute infinie
					while (true) {

						// on obtient le nouveau message pr�-mis en forme
						String message = null;
						try {
							message = din.readUTF();
						} catch (IOException e) {
							e.printStackTrace();
						}

						// on l'encapsule dans un message lisible par le handler
						Message msg = new Message();
						Bundle b = new Bundle();
						b.putString("cle", message);
						msg.setData(b);
						// on envoie cette capsule au handler
						handler.sendMessage(msg);
					}
				}
			};

			// on lance le thread d'�coute
			t.start();
		}

		// si la connexion ne s'effectue pas (<=> server OFF)
		else {
			setContentView(R.layout.errorserver);
		}
	}

	// m�thode d'envoi du message vers le serveur
	private void processMessage(String message) {
		try {
			// on l'inscrit dans le stream de sortie
			dout.writeUTF(message);

			// on clear l'EditText du message
			newMess.setText("");
		} catch (IOException ie) {
			System.out.println(ie);
		}
	}

	// le handler appel� lors de la r�ception d'un message
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {

			// on extrait la String contenue dans la capsule
			Bundle b = msg.getData();
			String txt = b.getString("cle");

			// on la transforme en Spanned pour que le code HTML de mise en forme soit interpr�t�
			Spanned styledText = Html.fromHtml(txt);

			// on la met � la suite de notre discussion
			discussion.append(styledText);
			discussion.append("\n");
		}

	};

	// gestion des pref
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 5) {

			Toast.makeText(this, "Modifications termin�es", Toast.LENGTH_SHORT)
					.show();

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.layout.menuchatbox, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.pref:
			Intent intent = new Intent(this, MyPreferences.class);
			startActivityForResult(intent, 5);

			return true;

		case R.id.about:
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					"D�velopp�e par Bastien Gounon, Melvin Masdieu, Nicolas Sagon et Benjamin Grenier \n\nVersion 1.0")
					.setTitle("BDE Forum");
			AlertDialog dialog = builder.create();
			dialog.show();
			return true;

		case R.id.deconnexion:
			setResult(1);
			finish();

			return true;
		}
		return false;
	}

}