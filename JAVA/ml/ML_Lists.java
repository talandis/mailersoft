package ml;

import java.util.Map;

import org.json.JSONObject;

import base.ML_Rest;

/**
 * Managing Lists Class for MailerSoft.
 * 
 * @author Vaidotas Strazdas
 *
 */
public class ML_Lists extends ML_Rest {

	/**
	 * Constructor.
	 * 
	 * @param apiKey API Key
	 */
    public ML_Lists(String apiKey) {
		super(apiKey);
		this.name = "lists";
		this.setPath(null);
    }
	
    /**
     * setId() overloaded, so the programmer won't need any casting.
     */
	public ML_Lists setId(long id) {
		super.setId(id);
		return this;
	}

	/**
	 * Get active on the list.
	 * 
	 * @param data Specific data about the list.
	 * @return Active users on the list.
	 * @throws Exception
	 * @see JSONObject
	 */
    public JSONObject getActive(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "active");
        return result;
    }

    /**
     * Overloaded getActive() method.
     * 
     * @return Active users on the list.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getActive() throws Exception {
    	JSONObject result = this.getActive(null);
        return result;
    }

    /**
     * Get unsubscribed users.
     * 
     * @param data Specific data about the list.
     * @return Unsubscribed users.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getUnsubscribed(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "unsubscribed");
        return result;
    }

    /**
     * Overloaded getUnsubscribed() method.
     * 
     * @return Unsubscribed users.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getUnsubscribed() throws Exception {
    	JSONObject result = this.getUnsubscribed(null);
        return result;
    }

    /**
     * Get bounced users.
     * 
     * @param data Specific data about the list.
     * @return Bounced users.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getBounced(Map<String, Object> data) throws Exception {
    	JSONObject result = this.get(data, "bounced");
        return result;
    }

    /**
     * Overloaded getBounced() method.
     * 
     * @return Bounced users.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject getBounced() throws Exception {
    	JSONObject result = this.getBounced(null);
        return result;
    }
	
}
