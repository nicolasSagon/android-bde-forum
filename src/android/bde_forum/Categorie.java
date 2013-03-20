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
import android.widget.Toast;

public class Categorie extends Activity {
	private TextView textCat;
	private TextView textTopic1;
	private TextView textTopic2;
	final Context context = this;

	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categorie);

		textCat = (TextView) findViewById(R.id.categorie5);
		textTopic1 = (TextView) findViewById(R.id.topic1);
		textTopic2 = (TextView) findViewById(R.id.topic2);

		textCat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (textTopic1.getVisibility() == View.GONE) {
					textTopic1.setVisibility(View.VISIBLE);
					textTopic2.setVisibility(View.VISIBLE);
				} else {
					textTopic1.setVisibility(View.GONE);
					textTopic2.setVisibility(View.GONE);
				}
			}
		});

		textTopic1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Categorie.this, Threads.class);
				startActivityForResult(intent, 3);
			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// on récupère le statut de retour de l'activité 2 c'est à dire
		// l'activité numéro 3
		if (requestCode == 3 || requestCode == 5) {
			// si le code de retour est égal à 1 on stoppe l'activité 1
			if (resultCode == 1) {
				finish();
			}
			if (requestCode == 5) {

				Toast.makeText(this, "Modifications terminées",
						Toast.LENGTH_SHORT).show();

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	// Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du
	// téléphone
	public boolean onCreateOptionsMenu(Menu menu) {

		// Création d'un MenuInflater qui va permettre d'instancier un Menu XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML spécifier en un objet Menu
		inflater.inflate(R.layout.menu, menu);

		return true;
	}

	// Méthode qui se déclenchera au clic sur un item
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
			finish();
			return true;
		}
		return false;
	}

}