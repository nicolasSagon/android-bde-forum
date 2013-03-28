package vue;

import java.util.ArrayList;
import java.util.List;

import donnees.DonneesServeur;
import android.app.Activity;
import android.app.AlertDialog;
import android.bde_forum.R;
import android.bde_forum.R.id;
import android.bde_forum.R.layout;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import metier.ForumThread;

public class ThreadAuto extends Activity {

	private int forumId;
	private List<ForumThread> dbThread = new ArrayList();
	final Context context = this;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.threads);

		Bundle objetbunble = this.getIntent().getExtras();

		if (objetbunble != null && objetbunble.containsKey("forumId")) {
			forumId = Integer.parseInt(this.getIntent().getStringExtra(
					"forumId"));
		} else {
			forumId = 0;

		}

		dbThread = DonneesServeur.getListThread(forumId);

		for (int i = 0; i < dbThread.size() && i < 7; i++) {
			TextView t = (TextView) findViewById(R.id.thread1 + i);
			t.setText(dbThread.get(i).getTitle());
			t.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					Intent intent = new Intent(ThreadAuto.this, Posts.class);
					startActivityForResult(intent, 4);
				}
			});

		}

	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 4 || requestCode == 5 || requestCode == 7) {
			if (resultCode == 1) {
				setResult(1);
				finish();
			}
		}

		if (requestCode == 5) {

			Toast.makeText(this, "Modifications termin�es", Toast.LENGTH_SHORT)
					.show();

		}
		super.onActivityResult(requestCode, resultCode, data);

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		// Création d'un MenuInflater qui va permettre d'instancier un Menu XML
		// en un objet Menu
		MenuInflater inflater = getMenuInflater();
		// Instanciation du menu XML spécifier en un objet Menu
		inflater.inflate(R.layout.menu, menu);

		// Il n'est pas possible de modifier l'icône d'entète du sous-menu via
		// le fichier XML on le fait donc en JAVA
		// menu.getItem(0).getSubMenu().setHeaderIcon(R.drawable.option);

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
		
		case R.id.chatbox:
			Intent intent2 = new Intent(this, Chatbox.class);
			startActivityForResult(intent2, 7);
			return true;

		case R.id.about:
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					"D�velopp�e par Bastien Gounon, Melvin Masdieu, Nicolas Sagon et Benjamin Grenier \n\nVersion 1.0")
					.setTitle("BDE Forum");
			AlertDialog dialog = builder.create();
			dialog.show();
			return true;

		case R.id.deconnexion:
			setResult(1);
			// et on ferme cette activité
			finish();

			return true;
		}
		return false;
	}
	
}
