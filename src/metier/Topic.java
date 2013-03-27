package metier;

public class Topic {

	private int forum_id;
	private String forum_name;
	private String forum_desc;
	private int nb_thread;
	private int nb_post;
	
	
	public Topic(int forum_id, String forum_name, String forum_desc,
			int nb_thread, int nb_post) {
		this.forum_id = forum_id;
		this.forum_name = forum_name;
		this.forum_desc = forum_desc;
		this.nb_thread = nb_thread;
		this.nb_post = nb_post;
	}
	
	public int getForum_id() {
		return forum_id;
	}
	public void setForum_id(int forum_id) {
		this.forum_id = forum_id;
	}
	public String getForum_name() {
		return forum_name;
	}
	public void setForum_name(String forum_name) {
		this.forum_name = forum_name;
	}
	public String getForum_desc() {
		return forum_desc;
	}
	public void setForum_desc(String forum_desc) {
		this.forum_desc = forum_desc;
	}
	public int getNb_thread() {
		return nb_thread;
	}
	public void setNb_thread(int nb_thread) {
		this.nb_thread = nb_thread;
	}
	public int getNb_post() {
		return nb_post;
	}
	public void setNb_post(int nb_post) {
		this.nb_post = nb_post;
	}
	
	
	
}
