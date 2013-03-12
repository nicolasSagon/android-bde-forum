package android.bde_forum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Categorie extends Activity {
	private TextView textCat;
	private TextView textTopic1;
	private TextView textTopic2;

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
		// on r�cup�re le statut de retour de l'activit� 2 c'est � dire
		// l'activit� num�ro 3
		if (requestCode == 3 || requestCode == 5) {
			// si le code de retour est �gal � 1 on stoppe l'activit� 1
			if (resultCode == 1) {
				finish();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	// M�thode qui se d�clenchera lorsque vous appuierez sur le bouton menu du
	// t�l�phone
	public boolean onCreateOptionsMenu(Menu menu) {

		// Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML sp�cifier en un objet Menu
		inflater.inflate(R.layout.menu, menu);

		return true;
	}

	// M�thode qui se d�clenchera au clic sur un item
	public boolean onOptionsItemSelected(MenuItem item) {
		// On regarde quel item a �t� cliqu� gr�ce � son id et on d�clenche une
		// action
		switch (item.getItemId()) {

		case R.id.option:

			Intent intent = new Intent(this, Pref.class);
			startActivityForResult(intent, 5);

			return true;

		case R.id.about:

			LayoutInflater factory = LayoutInflater.from(this);
			final View alertDialogView = factory.inflate(R.layout.about, null);

			// Cr�ation de l'AlertDialog
			AlertDialog.Builder adb = new AlertDialog.Builder(this);

			// On affecte la vue personnalis� que l'on a cr�e � notre
			// AlertDialog
			adb.setView(alertDialogView);

			// On donne un titre � l'AlertDialog
			adb.setTitle("A propos de BDE FORUM");

			adb.show();
			return true;

		case R.id.deconnexion:
				finish();
			return true;
		}
		return false;
	}

}