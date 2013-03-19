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
import android.widget.Button;

public class MonCompte extends Activity {
	
	private Context context;
	private Button modify;
	
		
	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perso);
		
		modify = (Button) findViewById(R.id.modify);
		
		
		modify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				modify.setText("Valider");
				
			}
		});
				
			
	}
	
	
	
		
	
	//gestion touche menu	
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
	//lors de la sélection d'un item du menu
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
