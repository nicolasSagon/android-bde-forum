package metier;

public class ForumThread {

	private int threadId;
	private String title;
	private int nb_post;
	
	public ForumThread(int threadId, String title, int nb_post) {
		super();
		this.threadId = threadId;
		this.title = title;
		this.nb_post = nb_post;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNb_post() {
		return nb_post;
	}

	public void setNb_post(int nb_post) {
		this.nb_post = nb_post;
	}
	
	
	
}
