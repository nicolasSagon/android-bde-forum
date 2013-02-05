package android.bde_forum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
public class Categorie extends Activity
{
	private TextView textCat;
	private TextView textTopic1;
	private TextView textTopic2;
	
    public void onCreate(Bundle savedInstanceState) //A la   creation de la vue
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorie);
        
        textCat = (TextView)findViewById(R.id.categorie5);
        textTopic1 = (TextView)findViewById(R.id.topic1);
        textTopic2 = (TextView)findViewById(R.id.topic2);
        
        
        textCat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (textTopic1.getVisibility() == View.GONE)
				{
					textTopic1.setVisibility(View.VISIBLE);
					textTopic2.setVisibility(View.VISIBLE);
				}
				else 
				{
					textTopic1.setVisibility(View.GONE);
					textTopic2.setVisibility(View.GONE);
				}
			}
        });
        
        textTopic1.setOnClickListener (new View.OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		Intent intent = new Intent(Categorie.this, Threads.class);  
                startActivity(intent);
        	}    
    	});
        
        
     }
}