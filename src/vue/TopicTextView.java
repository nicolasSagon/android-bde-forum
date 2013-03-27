package vue;

import android.content.Context;
import android.widget.TextView;

public class TopicTextView extends TextView {

	private int idForum;
	
	public TopicTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public TopicTextView(Context context, int idForum)
	{
		super(context);
		this.idForum = idForum;
	}

	public int getIdForum() {
		return idForum;
	}

	public void setIdForum(int idForum) {
		this.idForum = idForum;
	}

	
	
}
