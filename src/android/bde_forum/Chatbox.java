package android.bde_forum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
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

	// A la creation de la vue
	@Override
	public void onCreate(Bundle savedInstanceState) {
		final SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		// création du socket à partir des infos de connexion
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

					// on envoie le message au serveur
					processMessage("<font color=\"blue\">"
							+ prefs.getString("pseudo", "") + " : " + "</font>"
							+ newMess.getText().toString());
				}
				
				

			});

			// on attribue les flux d'entrée/de sortie sur le socket aux streams
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

			// on déclare le thread d'écoute et on lui affecte une méthode à run
			Thread t = new Thread() {

				@Override
				public void run() {

					// boucle d'écoute infinie
					while (true) {

						// on obtient le nouveau message
						String message = null;
						try {
							message = din.readUTF();
						} catch (IOException e) {
							e.printStackTrace();
						}

						// on l'encapsule dans un message lisible par le
						// handler, en le précédant du pseudo
						Message msg = new Message();
						Bundle b = new Bundle();
						b.putString("cle", message);
						msg.setData(b);
						// on envoie cette capsule au handler
						handler.sendMessage(msg);
					}
				}
			};

			// on lance le thread d'écoute
			t.start();
		}

		// si la connexion ne s'effectue pas (<=> server OFF)
		else {
			setContentView(R.layout.errorserver);
		}
	}

	// méthode d'envoi du message vers le serveur
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

	// le handler appelé lors de la réception d'un message
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {

			// on extrait la String contenue dans la capsule
			Bundle b = msg.getData();
			String txt = b.getString("cle");

			// on applique la mise en forme HTML pour avoir l'auteur en bleu
			Spanned styledText = Html.fromHtml(txt);

			// on la met à la suite de notre discussion
			discussion.append(styledText);
			discussion.append("\n");
		}

	};

	// gestion des pref
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 5) {

			Toast.makeText(this, "Modifications terminées", Toast.LENGTH_SHORT)
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
		case R.id.message:
			Intent intent3 = new Intent(this, BoiteRecep.class);
			startActivityForResult(intent3, 6);
			return true;

		case R.id.about:
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					"DÃ©veloppée par Bastien Gounon, Melvin Masdieu, Nicolas Sagon et Benjamin Grenier \n\nVersion 1.0")
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