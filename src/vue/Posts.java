package vue;

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
import android.widget.Toast;

public class Posts extends Activity {

	final Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.posts);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 5 || requestCode == 7) {

			if (resultCode == 1) {
				setResult(1);
				finish();
			}
			if (requestCode == 5) {

				Toast.makeText(this, "Modifications termin�es",
						Toast.LENGTH_SHORT).show();

			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.layout.menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

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
			finish();

			return true;
		}
		return false;
	}

}
