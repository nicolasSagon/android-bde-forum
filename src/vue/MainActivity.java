package vue;

import java.io.IOException;
import java.io.StreamCorruptedException;


import donnees.Connection;

import android.app.Activity;
import android.app.AlertDialog;
import android.bde_forum.R;
import android.bde_forum.R.id;
import android.bde_forum.R.layout;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button connect = null;
	private EditText pseudo = null;
	private EditText pass = null;
	private CheckBox staycon = null;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);

		Connection conn1 = new Connection();

		// on deserialise le fichier
		try {
			conn1.deserialisation();
			
			if (conn1.getEnregistrer()) {

				Log.e("Connexion", "lancement");

				// on lance la connexion
				conn1.lancerConnection();

				if (conn1.isConnected()) {

					Intent intent = new Intent(MainActivity.this,
							CategorieAuto.class);
					startActivityForResult(intent, 10);

				} else {
					
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setMessage("Mauvais login ou password").setTitle(
							"Erreur");
					builder.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
									pseudo = (EditText) findViewById(R.id.Login);
									pseudo.setText("");
									pass = (EditText) findViewById(R.id.password);
									pass.setText("");
									staycon.setSelected(false);
								}
							});

					AlertDialog dialog = builder.create();
					dialog.show();
				}
			} else {
				pseudo = (EditText) findViewById(R.id.Login);
				pass = (EditText) findViewById(R.id.password);
				pseudo.setText(conn1.getName());
				
			}
		} catch (StreamCorruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e) {

		}

		

		connect = (Button) findViewById(R.id.connection);

		connect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				pseudo = (EditText) findViewById(R.id.Login);
				String ps = pseudo.getText().toString();
				
				pass = (EditText) findViewById(R.id.password);
				String pa = pass.getText().toString();

				staycon = (CheckBox) findViewById(R.id.stayConnected);
				boolean sc = staycon.isChecked();

				Connection conn = new Connection(ps, pa, sc);
				Log.e("Connexion", "lancement");
				conn.lancerConnection();

				if (conn.isConnected()) {
					try {
						conn.serialisation();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Intent intent = new Intent(MainActivity.this,
							CategorieAuto.class);
					startActivity(intent);

				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setMessage("Mauvais login ou password").setTitle(
							"Erreur");
					builder.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
									pseudo.setText("");
									pass.setText("");
									staycon.setSelected(false);
								}
							});

					AlertDialog dialog = builder.create();
					dialog.show();
				}

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.layout.menumain, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.option:
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Veuillez vous connecter pour accéder aux options");
			builder.setTitle("Problème de connexion");
			AlertDialog dialog = builder.create();
			dialog.show();
			return true;
		case R.id.about:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
			builder2.setMessage(
					"Développée par Bastien Gounon, Melvin Masdieu, Nicolas Sagon et Benjamin Grenier \n\nVersion 1.0")
					.setTitle("BDE Forum");
			AlertDialog dialog2 = builder2.create();
			dialog2.show();
			return true;

		case R.id.deconnexion:
			finish();
			return true;
		}
		return false;
	}
}
