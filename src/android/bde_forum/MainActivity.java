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

	// M�thode qui se d�clenchera lorsque vous appuierez sur le bouton menu du
	// t�l�phone
	public boolean onCreateOptionsMenu(Menu menu) {

		// Cr�ation d'un MenuInflater qui va permettre d'instancier un Menu XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML sp�cifier en un objet Menu
		inflater.inflate(R.layout.menumain, menu);

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
			finish();
			return true;
		}
		return false;
	}
}
