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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button connect = null;
	private TextView text = null;
	private EditText pseudo = null;
	private EditText pass = null;

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
				// Si la connexion est bonne
				Intent intent = new Intent(MainActivity.this, Categorie.class);
				startActivity(intent);

			}

		});
	}

	// Méthode qui se déclenchera lorsque vous appuierez sur le bouton menu du
	// téléphone
	public boolean onCreateOptionsMenu(Menu menu) {

		// Création d'un MenuInflater qui va permettre d'instancier un Menu XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML spécifier en un objet Menu
		inflater.inflate(R.layout.menumain, menu);

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
			LayoutInflater factory = LayoutInflater.from(this);
			final View alertDialogView = factory.inflate(R.layout.about, null);

			// Création de l'AlertDialog
			AlertDialog.Builder adb = new AlertDialog.Builder(this);

			// On affecte la vue personnalisé que l'on a crée à notre
			// AlertDialog

			adb.setView(alertDialogView);

			// On donne un titre à l'AlertDialog
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
