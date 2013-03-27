package donnees;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

import metier.Categorie;
import metier.ForumThread;

public class DonneesServeur {

	public static List<Categorie> getListCat()
	{
		URL connectURL;
		InputStream in = null;
		DataInputStream dis;
		List<Categorie> la = new ArrayList();
		try {
			connectURL = new URL("http://server-android.no-ip.org:8000/forum/show.json");
				in = connectURL.openStream();
	        	dis = new DataInputStream(new BufferedInputStream(in));
	            String inputLine;
	            String result = "";
	            while ((inputLine = dis.readLine()) != null)
				    result += inputLine;
				in.close();
				
				Type dbCatType = new TypeToken<List<Categorie>>() {}.getType();
				Gson gson = new Gson();
				la = gson.fromJson(result, new TypeToken<List<Categorie>>(){}.getType());
				Log.e("DonneesCat", la.get(0).getCat_name());
		}catch (Exception e)
		{
			Log.e("donneesCat.java", e.getMessage());
		}
		return la;
	}
	
	public static List<ForumThread> getListThread(int forumId)
	{
		URL connectURL;
		InputStream in = null;
		DataInputStream dis;
		List<ForumThread> la = new ArrayList();
		try {
			connectURL = new URL("http://server-android.no-ip.org:8000/forum/topic/show.json?forumId="+ forumId);
				in = connectURL.openStream();
	        	dis = new DataInputStream(new BufferedInputStream(in));
	            String inputLine;
	            String result = "";
	            while ((inputLine = dis.readLine()) != null)
				    result += inputLine;
				in.close();
				Log.e("Result", result);
				Type dbCatType = new TypeToken<List<ForumThread>>() {}.getType();
				Gson gson = new Gson();
				la = gson.fromJson(result, new TypeToken<List<ForumThread>>(){}.getType());
		}catch (Exception e)
		{
			Log.e("donneesCat.java", e.getMessage());
		}
		return la;
	}
}
