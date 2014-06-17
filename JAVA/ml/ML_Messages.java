package ml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import base.ML_Rest;

/**
 * Class to send messages to subscribers.
 * 
 * @author Vaidotas Strazdas
 *
 */
public class ML_Messages extends ML_Rest {
	
	private String fromName;
	private String fromEmail;
	private HashMap<String, Object> variables;
	private long id;
	private String recipientEmail;
	private String recipientName;
	private String type;
	private String language;
	private ArrayList<Map<String, Object>> batchRecipients;
	private ArrayList<Map<String, Object>> attachments;
	private String replyToEmail;
	private String replyToName;
	private boolean isTest = false;

	/**
	 * Constructor.
	 * 
	 * @param apiKey API Key
	 */
	public ML_Messages(String apiKey) {
		super(apiKey);
		this.name = "messages";
		
		this.variables = new HashMap<String, Object>();
		this.batchRecipients = new ArrayList<Map<String, Object>>();
		this.attachments = new ArrayList<Map<String, Object>>();
		
		this.setPath(null);
	}
	
	/**
	 * setId() overloaded, so the programmer won't need any casting.
	 */
	public ML_Messages setId(long id) {
		super.setId(id);
		return this;
	}

	/**
	 * Set single recipient.
	 * 
	 * @param email The email of the subscriber.
	 * @param name Name of the subscriber.
	 * @return ML_Messages instance.
	 */
    public ML_Messages setRecipient(String email, String name) {
        this.recipientEmail = email;
        this.recipientName = name;
        return this;
    }

    /**
     * Overloaded setRecipient() method without name provided.
     * 
     * @param email The email of the subscriber.
     * @return ML_Messages instance.
     */
    public ML_Messages setRecipient(String email) {
        return this.setRecipient(email, "");
    }

    /**
     * Set name from which message was sent.
     * 
     * @param name Name of the sender.
     * @return ML_Messages instance.
     */
    public ML_Messages setFromName(String name) {
        this.fromName = name;
        return this;
    }

    /**
     * Set variables to attach for email.
     * 
     * @param variables Variables to attach.
     * @return ML_Messages instance.
     */
    public ML_Messages setVariables(HashMap<String, Object> variables) {

    	for (Map.Entry<String, Object> entry: variables.entrySet())
    		this.setVariable(entry.getKey(), entry.getValue());

        return this;
    }

    /**
     * Set single variable.
     * 
     * @param name Name of the variable.
     * @param value Value of the variable.
     * @return ML_Messages instance.
     */
    public ML_Messages setVariable(String name, Object value) {
    	this.variables.put(name, value);
        return this;
    }

    /**
     * Set some type or whatever it is...
     * 
     * @param type Again, some type.
     * @return ML_Messages instance.
     */
	public ML_Messages setType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Set language.
	 * 
	 * @param language Language.
	 * @return ML_Messages instance.
	 */
	public ML_Messages setLanguage(String language) {
		this.language = language;
		return this;
	}

	/**
	 * Add recipient.
	 * 
	 * @param recipient ML_Recipient instance.
	 * @return ML_Messages instance.
	 */
    public ML_Messages addRecipient(ML_Recipient recipient) {
    	this.batchRecipients.add(recipient.get());
    	return this;
	}
    
    /**
     * Add multiple recipients.
     * 
     * @param recipients List of ML_Recipient instances.
     * @return ML_Messages instance.
     */
    public ML_Messages addRecipients(ArrayList<ML_Recipient> recipients) {
    	for(ML_Recipient recipient : recipients)
    		this.addRecipient(recipient);
    	return this;
    }
    
    /**
     * Set email and name to which subscriber may reply.
     * 
     * @param email Email to reply to.
     * @param name Name to reply to.
     * @return ML_Messages instance.
     */
	public ML_Messages setReplyTo(String email, String name) {
		this.replyToEmail = email;
		this.replyToName = name;
		return this;
	}
    
	/**
	 * Overloaded setReplyTo() method without a name provided.
	 * 
	 * @param email Email to reply to.
	 * @return ML_Messages instance.
	 */
	public ML_Messages setReplyTo(String email) {
		return this.setReplyTo(email, "");
	}

	/**
	 * Set attachment to add.
	 * 
	 * @param filename Attachment filename.
	 * @param content Attachment contents.
	 * @return ML_Messages instance.
	 */
	public ML_Messages addAttachment(String filename, Object content) {
		HashMap<String, Object> attachment = new HashMap<String, Object>();
		attachment.put("filename", filename);
		attachment.put("content", content);
		this.attachments.add(attachment);
		return this;
	}

	/**
	 * Send email to subscribers.
	 * 
	 * @return Result of sending an email.
	 * @throws Exception
	 * @see JSONObject
	 */
	public JSONObject send() throws Exception {
		HashMap<String, Object> data;
		JSONObject result;
		
		data = new HashMap<String, Object>();
		data.put("mailId", this.id);
		data.put("language", this.language);
		data.put("fromName", this.fromName);
		data.put("fromEmail", this.fromEmail);
		
		if (this.batchRecipients.size() > 0)
			data.put("batch", this.batchRecipients);
		else {
			data.put("recipientName", this.recipientName);
			data.put("recipientEmail", this.recipientEmail);
			data.put("variables", this.variables);
			data.put("attachments", this.attachments);
		}
		
		result = this.post(data, null);
		
		return result;
    }
	
}
