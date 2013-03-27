package vue;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

public class CategorieTextView extends TextView {

	private List<TopicTextView> textViewTopic= new ArrayList();
	private int index = 0;
	
	public CategorieTextView(Context context) {
	      super(context);
	}
	
	
	protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        
       
    }

	public void insererTextView(TopicTextView t)
	{
		textViewTopic.add(t);
	}
	
	public TopicTextView getTextView(int i)
	{
		return textViewTopic.get(i);
	}
	
	public int sizeTextViewTopic()
	{
		return textViewTopic.size();
	}
	
	public void setTextViewTopicVisible()
	{
		for (int i = 0; i < textViewTopic.size(); i++)
		{
			textViewTopic.get(i).setVisibility(View.VISIBLE);
		}
	}
	
	public boolean isTextViewTopicVisible()
	{
		return (textViewTopic.get(0).getVisibility() == View.VISIBLE);
	}
	
	public void setTextViewTopicGone()
	{
		for (int i = 0; i < textViewTopic.size(); i++)
		{
			textViewTopic.get(i).setVisibility(View.GONE);
		}
	}

	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}
	
	
	
	
	
}
