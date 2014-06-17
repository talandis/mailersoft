package ml;

public class ML_Subscriber extends ML_Person {

	/**
	 * Constructor of ML_Subscriber.
	 * Just description of specific fields to be sent to the MailerSoft API.
	 * 
	 * @param email Email of the person.
	 * @param name Name of the person.
	 */
	public ML_Subscriber(String email, String name) {
		super(email, name);
		this.emailFieldName = "email";
		this.nameFieldName = "name";
		this.fieldsFieldName = "fields";
	}
	
}
