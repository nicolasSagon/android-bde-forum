package android.bde_forum;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

	
	//on génère des messages
	private MessagePrive messageArray[] = {
			new MessagePrive("melvin", "Salut comment vas tu ?"),
			new MessagePrive("nicolas", "Tu peux me passer le tp de reseau ?"),
			new MessagePrive("benjamin", "Je vais m'acheter une moto !!! vroom"),
			new MessagePrive("bastien", "De toute façon l'iut ça sert à rien je rage quit !") };

	
	// Intitulé de l'extra qui contient la direction du défilé
	private final static String EXTRA_DIRECTION = "extraDirection";

	
	// Intitulé de l'extra qui contient la direction du défilé
	private final static String EXTRA_PREVIOUS = "previous";

	
	// La valeur pour défiler vers la droite
	private final static String EXTRA_NEXT = "next";

	
	// Intitulé de l'extra qui contient l'indice actuel dans le tableau des messages
	private final static String EXTRA_INDICE = "extraIndice";

	
	// Indice actuel dans le tableau des messages
	private int indice = 0;

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		// Petite astuce : permet de garder la longueur du tableau sans accéder plusieurs fois à l'objet, d'où optimisation
		final int length = appWidgetIds.length;
		for (int i = 0; i < length; i++) {

			
			// On récupère le RemoteViews qui correspond à l'AppWidget
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);

			// On met le bon texte dans le widget
			views.setTextViewText(R.id.mpWidget, messageArray[indice].getMessageComplet());

			// La prochaine section est destinée au bouton qui permet de passer au message suivant
			Intent nextIntent = new Intent(context, AppWidget.class);

			// On veut que l'intent lance la mise à jour
			nextIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

			nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			// On rajoute la direction
			nextIntent.putExtra(EXTRA_DIRECTION, EXTRA_NEXT);
			// Ainsi que l'indice
			nextIntent.putExtra(EXTRA_INDICE, indice);

			Uri data = Uri.withAppendedPath(Uri.parse("WIDGET://widget/id/"),
					String.valueOf(R.id.suivantW));

			nextIntent.setData(data);

			// On insère l'intent dans un PendingIntent
			PendingIntent nextPending = PendingIntent.getBroadcast(context, 0,
					nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			// Et on l'associe à l'activation du bouton
			views.setOnClickPendingIntent(R.id.suivantW, nextPending);

			Intent previousIntent = new Intent(context, AppWidget.class);

			previousIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

			previousIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
					appWidgetIds);

			previousIntent.putExtra(EXTRA_DIRECTION, EXTRA_PREVIOUS);

			previousIntent.putExtra(EXTRA_INDICE, indice);

			data = Uri.withAppendedPath(Uri.parse("WIDGET://widget/id/"),
					String.valueOf(R.id.precedentW));

			previousIntent.setData(data);

			PendingIntent previousPending = PendingIntent.getBroadcast(context,
					1, previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			views.setOnClickPendingIntent(R.id.precedentW, previousPending);

			
			// Et il faut mettre à jour toutes les vues
			appWidgetManager.updateAppWidget(appWidgetIds[i], views);

		}
	}

	public void onReceive(Context context, Intent intent) {

		int tmp = intent.getIntExtra(EXTRA_INDICE, -1);

		if (tmp != -1) {
			// On récupère la direction
			String extra = intent.getStringExtra(EXTRA_DIRECTION);
			// Et on calcule l'indice voulu par l'utilisateur
			if (extra.equals(EXTRA_PREVIOUS)) {
				indice = (tmp - 1) % messageArray.length;
				if (indice < 0)
					indice += messageArray.length;
			} else if (extra.equals(EXTRA_NEXT))
				indice = (tmp + 1) % messageArray.length;
		}

		super.onReceive(context, intent);
	}

}
