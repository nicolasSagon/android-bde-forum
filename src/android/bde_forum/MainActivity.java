package android.bde_forum;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button connect = null;
	private TextView text = null;
	private EditText pseudo = null;
	private EditText pass = null;
	
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			
				setContentView(R.layout.login);
		
				connect = (Button)findViewById(R.id.connection);
				connect.setOnClickListener(new View.OnClickListener() {
				
					@Override
					public void onClick(View v) {
					
						pseudo = (EditText)findViewById(R.id.Login);
						String ps = pseudo.getText().toString();
						pass = (EditText)findViewById(R.id.password);
						String pa = pass.getText().toString();
						//Si la connexion est bonne
						setContentView(R.layout.threads);
				
					}
				
				});
				
				try
				{
				text = (TextView)findViewById(R.id.texte);
				text.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						setContentView(R.layout.posts);
						
					}
				
				}); 
				}
				catch (NullPointerException e) {
					System.out.println("Oups ! Une erreur est survenue : " + e);
				}
			
		}

}
	


