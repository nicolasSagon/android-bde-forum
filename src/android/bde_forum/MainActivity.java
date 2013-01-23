package android.bde_forum;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

	private Button connect = null;
	private EditText pseudo = null;
	private EditText pass = null;
	private TextView res = null;
	
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.login);
		
				connect = (Button)findViewById(R.id.connection);
				connect.setOnClickListener(envoyerListener);
			

		}
	
		private OnClickListener envoyerListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			
					pseudo = (EditText)findViewById(R.id.Login);
					String ps = pseudo.getText().toString();
					pass = (EditText)findViewById(R.id.password);
					String pa = pass.getText().toString();
					res = (TextView)findViewById(R.id.result);
					res.setText("nom : " + ps + " passe : " + pa);
				
				
			}
		};
}

	


