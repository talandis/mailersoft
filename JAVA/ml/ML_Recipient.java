package ml;

/**
 * Class to be used for describing recipients.
 * 
 * 
 * @author Vaidotas Strazdas
 *
 */
public class ML_Recipient extends ML_Person {

	/**
	 * Constructor of ML_Recipient.
	 * Just description of specific fields to be sent to the MailerSoft API.
	 * 
	 * @param email Email of the person.
	 * @param name Name of the person.
	 */
	public ML_Recipient(String email, String name) {
		super(email, name);
		this.emailFieldName = "recipientEmail";
		this.nameFieldName = "recipientName";
		this.fieldsFieldName = "variables";
	}

}
