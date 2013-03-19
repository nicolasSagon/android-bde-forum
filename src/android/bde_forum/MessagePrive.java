package android.bde_forum;

public class MessagePrive {

	private String auteur;
	private String message;
	private static int numeroM = 0;
	
	public MessagePrive(String auteur, String message) {
		this.auteur = auteur;
		this.message = message;
		numeroM += numeroM;
	}
	
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static int getNumeroM() {
		return numeroM;
	}
	
	public String getMessageComplet(){
		return ("\n\t" + auteur + "\n\n\t\t\t" + message);
	}
	
}
