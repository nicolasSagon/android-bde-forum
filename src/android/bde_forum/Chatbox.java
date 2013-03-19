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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Chatbox extends Activity {

	private Button envoi = null;
	private EditText newMess = null;
	private TextView discussion = null;

	private Socket socket;

	private DataOutputStream dout;
	private DataInputStream din;

	final Context context = this;

	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{
		super.onCreate(savedInstanceState);
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
				processMessage(newMess.getText().toString());
			}

		});

		// bloc de connexion

		// infos de connexion
		try {
			socket = new Socket("78.206.144.7", 48555);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// confirmation
		System.out.println("connected to " + socket);

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

		// on lance le thread d'écoute
		Thread t = new Thread() {
			public void run() {
				Log.i("test", "<DEBUG> loop du thread lancee");
				// boucle d'écoute infinie
				while (true) {

					// on obtient le nouveau message
					String message = null;
					try {
						message = din.readUTF();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// on le met à la suite de notre discussion
					Message msg = new Message();
					Bundle b = new Bundle();
					b.putString("cle", message);
					msg.setData(b);
					// send message to the handler with the current message
					// handler
					handler.sendMessage(msg);

					// discussion.setText(message + "\n");

				}
			}
		};
		t.start();
		Log.i("test", "<DEBUG> thread lance");

	}

	private void processMessage(String message) // méthode d'envoi du message
												// vers le serveur
	{
		try {

			// on l'inscrit dans le stream de sortie
			dout.writeUTF(message);

			// on clear l'EditText du message
			newMess.setText("");

		} catch (IOException ie) {
			System.out.println(ie);
		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {

			// get the bundle and extract data by key
			Bundle b = msg.getData();
			String txt = b.getString("cle");
			discussion.append(txt + "\n");
		}

	};

	// gestion des pref
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// on récupère le statut de retour de l'activité 2 c'est à dire
		// l'activité numéro 3
		if (requestCode == 5) {

			Toast.makeText(this, "Modifications terminées", Toast.LENGTH_SHORT)
					.show();

		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	// gestion du menu
	public boolean onOptionsItemSelected(MenuItem item) {
		// On regarde quel item a été cliqué grâce à son id et on déclenche une
		// action
		switch (item.getItemId()) {

		case R.id.pref:
			Intent intent = new Intent(this, MyPreferences.class);
			startActivityForResult(intent, 5);

			return true;
		case R.id.message:
			Intent intent3 = new Intent(this, BoiteRecep.class);
			startActivityForResult(intent3, 6);
			return true;

		case R.id.chatbox:
			Intent intent2 = new Intent(this, Chatbox.class);
			startActivityForResult(intent2, 7);
			return true;

		case R.id.about:
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					"Développée par Bastien Gounon, Melvin Masdieu, Nicolas Sagon et Benjamin Grenier \n\nVersion 1.0")
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