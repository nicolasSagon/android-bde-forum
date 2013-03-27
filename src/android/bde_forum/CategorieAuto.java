package android.bde_forum;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import vue.CategorieTextView;
import vue.TopicTextView;

import donnees.AttributGetter;
import donnees.DonneesServeur;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import metier.Categorie;

@SuppressLint("ResourceAsColor")
public class CategorieAuto extends Activity {

	private TableLayout tLayout;
	private List<Categorie> donneesCat;
	private List<CategorieTextView> listTextView;
	final Context context = this;
	private int[] tableIndex = new int[15];
	
	
	@SuppressLint("ResourceAsColor")
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		donneesCat = new ArrayList();
		donneesCat = DonneesServeur.getListCat();
		listTextView = new ArrayList();
				
		setContentView(R.layout.categorietest);
		
		tLayout = (TableLayout) findViewById(R.id.tableLayoutCat);
		View separation = (View) findViewById(R.id.separationfirst);
		Drawable background = separation.getBackground();
				
		for (int i = 0; i < donneesCat.size(); i++) {
			if (i != 0)
			{
				View v = new View(this);
				v.setLayoutParams(new LayoutParams(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics())));
				v.setBackgroundDrawable(background);
				tLayout.addView(v);
				
			}
			listTextView.add(new CategorieTextView(this));
			listTextView.get(i).setPadding(15, 15, 15, 15);
			listTextView.get(i).setGravity(1);
			listTextView.get(i).setId(1000+i);
			listTextView.get(i).setBackgroundResource(R.color.threads);
			listTextView.get(i).setText(donneesCat.get(i).getCat_name());
			listTextView.get(i).setTextColor(Color.BLACK);
			listTextView.get(i).setTextSize(20);
			listTextView.get(i).setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					
					int index = v.getId()-1000;
					if (listTextView.get(index).isTextViewTopicVisible())
					{
						listTextView.get(index).setTextViewTopicGone();
					}
					else
					{
						listTextView.get(index).setTextViewTopicVisible();
					}
					

				}
			});
			tLayout.addView(listTextView.get(i));
			View v = new View(this);
			v.setLayoutParams(new LayoutParams(0,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics())));
			v.setBackgroundDrawable(background);
			tLayout.addView(v);
			for (int j = 0; j < donneesCat.get(i).getDonnesTopic().size(); j++)
			{
				listTextView.get(i).insererTextView(new TopicTextView(this, i));
				listTextView.get(i).getTextView(j).setBackgroundResource(R.color.threads);
				listTextView.get(i).getTextView(j).setTextColor(Color.BLACK);
				listTextView.get(i).getTextView(j).setId(3000+j);
				listTextView.get(i).getTextView(j).setGravity(1);
				listTextView.get(i).getTextView(j).setPadding(15, 15, 15, 15);
				listTextView.get(i).getTextView(j).setVisibility(View.GONE);
				listTextView.get(i).getTextView(j).setText(donneesCat.get(i).getDonnesTopic().get(j).getForum_name());
				listTextView.get(i).getTextView(j).setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						int indexCat = ((TopicTextView) v).getIdForum();
						int indexTopic = v.getId()-3000;
						Log.e("Index Topic", String.valueOf(indexTopic));
						Bundle objetbunble = new Bundle();
						objetbunble.putString("forumId" , String.valueOf(donneesCat.get(indexCat).getDonnesTopic().get(indexTopic).getForum_id()));
						Intent intent = new Intent(CategorieAuto.this,
								ThreadAuto.class);
						intent.putExtras(objetbunble);
						startActivity(intent);
					}
				});
				tLayout.addView(listTextView.get(i).getTextView(j));

			}
		}
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 3 || requestCode == 5) {

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


	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {
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
