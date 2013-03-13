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

public class Pref extends Activity {

	private TextView textBR;
	private TextView textSearch;
	private TextView textFav;
	private TextView textCompte;

	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);

		textBR = (TextView) findViewById(R.id.message);
		textSearch = (TextView) findViewById(R.id.search);
		textFav = (TextView) findViewById(R.id.favoris);
		textCompte = (TextView) findViewById(R.id.account);

		// listener pour le textView des recherches
		textSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			
				Intent intent = new Intent(Pref.this, Search.class);
				startActivityForResult(intent, 6);
			}
		});

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// on r�cup�re le statut de retour de l'activit� 3 c'est � dire
		// l'activit� num�ro 4
		if (requestCode == 6) {
			// si le code de retry est �gal � 1 on stoppe l'activit� 2
			if (resultCode == 1) {
				setResult(1);
				finish();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		// Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML sp�cifier en un objet Menu
		inflater.inflate(R.layout.menu, menu);

		// Il n'est pas possible de modifier l'ic�ne d'ent�te du sous-menu via
		// le fichier XML on le fait donc en JAVA
		// menu.getItem(0).getSubMenu().setHeaderIcon(R.drawable.option);

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
			setResult(1);
			finish();

			return true;
		}
		return false;
	}

}
