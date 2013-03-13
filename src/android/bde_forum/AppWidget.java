package android.bde_forum;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

	private MessagePrive messageArray[] = {
			new MessagePrive("melvin", "Salut comment vas tu ?"),
			new MessagePrive("nicolas", "Tu peux me passer le tp de reseau ?"),
			new MessagePrive("benjamin", "Je vais m'acheter une moto !!! vroom"),
			new MessagePrive("bastien", "De toute façon l'iut ça sert à rien je rage quit !")
	};
	
	private final static String EXTRA_DIRECTION = "extraDirection";
	
	private final static String EXTRA_PREVIOUS = "previous";
	
	private final static String EXTRA_NEXT = "next";
	
	private final static String EXTRA_INDICE = "extraIndice";
	
	private int indice = 0;

	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
			super.onUpdate(context, appWidgetManager, appWidgetIds);
			
			final int length = appWidgetIds.length;
			
			for (int i = 0 ; i < length ; i++) {
				
				RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.appwidget);
				
				views.setTextViewText(R.id.mpWidget, messageArray[indice].getMessageComplet());
				

				Intent nextIntent = new Intent(context, AppWidget.class);
				
				nextIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
				
				nextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
				
				nextIntent.putExtra(EXTRA_DIRECTION, EXTRA_NEXT);
				
				nextIntent.putExtra(EXTRA_INDICE, indice);
				
				Uri data = Uri.withAppendedPath(Uri.parse("WIDGET://widget/id/"), String.valueOf(R.id.suivantW));
				
				nextIntent.setData(data);
				
				PendingIntent nextPending = PendingIntent.getBroadcast(context, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				
				views.setOnClickPendingIntent(R.id.suivantW, nextPending);
				
				Intent previousIntent = new Intent(context, AppWidget.class);
				
				previousIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
				
				previousIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
				
				previousIntent.putExtra(EXTRA_DIRECTION, EXTRA_PREVIOUS);
				
				previousIntent.putExtra(EXTRA_INDICE, indice);
				
				data = Uri.withAppendedPath(Uri.parse("WIDGET://widget/id/"), String.valueOf(R.id.precedentW));
				
				previousIntent.setData(data);
				
				PendingIntent previousPending = PendingIntent.getBroadcast(context, 1, previousIntent, PendingIntent.FLAG_UPDATE_CURRENT);
				views.setOnClickPendingIntent(R.id.precedentW, previousPending);
				
				appWidgetManager.updateAppWidget(appWidgetIds[i], views);
				
			}
	}
	
	
}
