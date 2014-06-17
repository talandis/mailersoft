package ml;

import java.util.Map;

import org.json.JSONObject;

import base.ML_Rest;

/**
 * Managing Campaigns class for MailerSoft.
 * 
 * @author Vaidotas Strazdas
 *
 */
public class ML_Campaigns extends ML_Rest {

	/**
	 * Constructor.
	 * 
	 * @param apiKey API Key
	 */
	public ML_Campaigns(String apiKey) {
		super(apiKey);
		this.name = "campaigns";
		this.setPath(null);
	}
	
	/**
	 * Overloaded ML_Rest setId() method, so programmer
	 * would not need any casting.
	 */
	public ML_Campaigns setId(long id) {
		super.setId(id);
		return this;
	}
	
	/**
	 * Get recipients.
	 * 
	 * @param data Specific data about recipients.
	 * @return Recipients list.
	 * @throws Exception
	 * @see JSONObject
	 */
    public JSONObject getRecipients(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "recipients");
    	return result;
    }
	
    /**
     * Overloaded getRecipients() method.
     * 
     * @return Recipients list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getRecipients() throws Exception {
    	JSONObject result = this.getRecipients(null);
    	return result;
    }
    
    /**
     * Get opens.
     * 
     * @param data Specific data about opens.
     * @return Opens list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getOpens(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "opens");
    	return result;
    }

    /**
     * Overloaded getOpens() method.
     * 
     * @return Opens list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getOpens() throws Exception {
    	JSONObject result = this.getOpens(null);
    	return result;
    }

    /**
     * Get clicks.
     * 
     * @param data Specific data about clicks.
     * @return Clicks list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getClicks(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "clicks");
    	return result;
    }

    /**
     * Overloaded getClicks() method.
     * 
     * @return Clicks list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getClicks() throws Exception {
    	JSONObject result = this.getClicks(null);
    	return result;
    }
    
    /**
     * Get unsubscribed users.
     * 
     * @param data Specific data about unsubscribed users list.
     * @return Unsubscribed users list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getUnsubscribes(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "unsubscribes");
    	return result;
    }

    /**
     * Overloaded getUnsubscribes() method.
     * 
     * @return Unsubscribed users list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getUnsubscribes() throws Exception {
    	JSONObject result = this.getUnsubscribes(null);
    	return result;
    }
    
    /**
     * Get bounces.
     * 
     * @param data Specific data about bounces.
     * @return Bounces list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getBounces(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "bounces");
    	return result;
    }

    /**
     * Overloaded getBounces() method.
     * 
     * @return Bounces list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getBounces() throws Exception {
    	JSONObject result = this.getBounces(null);
    	return result;
    }
    
    /**
     * Get users who complained about junk.
     * 
     * @param data Specific data about users.
     * @return Users complaining about junk list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getJunk(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "junk");
    	return result;
    }

    /**
     * Overloaded getJunk() method.
     * 
     * @return Users complaining about junk list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getJunk() throws Exception {
    	JSONObject result = this.getJunk(null);
    	return result;
    }
    
}
