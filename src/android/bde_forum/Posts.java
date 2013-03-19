package android.bde_forum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Posts extends Activity {
	
	final Context context = this;

	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.posts);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// on r�cup�re le statut de retour de l'activit� 3 c'est � dire
		// l'activit� num�ro 4
		if (requestCode == 5) {
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
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("D�velopp�e par Bastien Gounon, Melvin Masdieu, Nicolas Sagon et Benjamin Grenier \n\nVersion 1.0")
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
