package android.bde_forum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Threads extends Activity {

	private TextView textThread;

	public void onCreate(Bundle savedInstanceState) // A la creation de la vue
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threads);

		textThread = (TextView) findViewById(R.id.thread1);

		textThread.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Threads.this, Posts.class);
				startActivity(intent);
			}
		});

	}

}
