package android.bde_forum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Pref extends Activity {

	private TextView textBR;
	private TextView textCompte;
	private TextView textChatbox;
	final Context context = this;

	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
	

		textBR = (TextView) findViewById(R.id.message);
		textCompte = (TextView) findViewById(R.id.account);
		textChatbox = (TextView) findViewById(R.id.chatbox);

		// listener pour chatbox

		textChatbox.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Pref.this, Chatbox.class);
				startActivityForResult(intent, 8);
			}
		});

		// listener pour boite recep

		textBR.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Pref.this, BoiteRecep.class);
				startActivityForResult(intent, 6);
			}
		});

		// listener pour mon compte
		textCompte.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Pref.this, MonCompte.class);
				startActivityForResult(intent, 7);
			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// on récupère le statut de retour de l'activité 3 c'est à dire
		// l'activité numéro 4
		if (requestCode == 6 || requestCode == 7 || requestCode == 8) {
			// si le code de retry est égal à 1 on stoppe l'activité 2
			if (resultCode == 1) {
				setResult(1);
				finish();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		// Création d'un MenuInflater qui va permettre d'instancier un Menu XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML spécifier en un objet Menu
		inflater.inflate(R.layout.menu, menu);

		// Il n'est pas possible de modifier l'icône d'entête du sous-menu via
		// le fichier XML on le fait donc en JAVA
		// menu.getItem(0).getSubMenu().setHeaderIcon(R.drawable.option);

		return true;
	}

	// Méthode qui se déclenchera au clic sur un item
	public boolean onOptionsItemSelected(MenuItem item) {
		// On regarde quel item a été cliqué grâce à son id et on déclenche une
		// action
		switch (item.getItemId()) {

		case R.id.option:
			Intent intent = new Intent(this, Pref.class);
			startActivityForResult(intent, 5);
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
