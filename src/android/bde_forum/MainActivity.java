package android.bde_forum;

import java.io.IOException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
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
					Intent intent = new Intent(MainActivity.this,
							CategorieAuto.class);
					startActivity(intent);

					
			} 
					else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setMessage("Mauvais login ou password").setTitle(
							"Erreur");
					builder.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
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

	// Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu
	// du
	// téléphone
	public boolean onCreateOptionsMenu(Menu menu) {

		// Création d'un MenuInflater qui va permettre d'instancier un Menu
		// XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML spécifier en un objet Menu
		inflater.inflate(R.layout.menumain, menu);

		// Il n'est pas possible de modifier l'ic�ne d'ent�te du sous-menu
		// via
		// le fichier XML on le fait donc en JAVA
		// menu.getItem(0).getSubMenu().setHeaderIcon(R.drawable.option);

		return true;
	}

	// Méthode qui se déclenchera au clic sur un item
	public boolean onOptionsItemSelected(MenuItem item) {
		// On regarde quel item a été cliqué grâce à son id et on
		// déclenche une
		// action
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
