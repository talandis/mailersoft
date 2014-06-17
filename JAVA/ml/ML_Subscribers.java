package ml;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import base.ML_Rest;
import base.Method;

/**
 * Managing Subscribers class for MailerSoft.
 * 
 * @author Vaidotas Strazdas
 *
 */
public class ML_Subscribers extends ML_Rest {
	
	/**
	 * Constructor.
	 * 
	 * @param apiKey API Key
	 */
    public ML_Subscribers(String apiKey) {
		super(apiKey);
		this.name = "subscribers";
		this.setPath(null);
    }
	
    /**
     * setId() overloaded, so the programmer won't need any casting.
     */
	public ML_Subscribers setId(long id) {
		super.setId(id);
		return this;
	}
	
	/**
	 * Add subscriber.
	 * 
	 * @param data Data about subscriber.
	 * @param resubscribe
	 * @return Result of adding subscriber.
	 * @throws Exception
	 * @see JSONObject
	 */
    public JSONObject add(ML_Subscriber subscriber, boolean resubscribe) throws Exception {
    	JSONObject result;
    	HashMap<String, Object> data;

    	data = subscriber.get();
    	data.put("resubscribe", resubscribe ? "1" : "0");
    	System.out.println(data.toString());
    	result = this.post(data, null);
    	
        return result;
    }
	
    /**
     * Overloaded add() method with resubscribe set to false.
     * 
     * @param data Data about subscriber.
     * @return Result of adding subscriber.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject add(ML_Subscriber subscriber) throws Exception {
    	JSONObject result = this.add(subscriber, false);
        return result;
    }
	
    /**
     * Add multiple list of subscribers.
     * 
     * @param subscribers Subscribers list.
     * @param resubscribe Do these subscribers resubscribe.
     * @return Result of adding subscribers.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject addAll(ArrayList<ML_Subscriber> subscribers, boolean resubscribe) throws Exception {
    	JSONObject result;
    	HashMap<String, Object> data;
    	ArrayList<HashMap<String, Object>> subscribersList;
    	
    	// Why no lambda expressions?
    	subscribersList = new ArrayList<HashMap<String, Object>>();
    	for(ML_Subscriber subscriber : subscribers)
    		subscribersList.add(subscriber.get());
    	
    	data = new HashMap<String, Object>();
    	data.put("resubscribe", resubscribe ? "1" : "0");
    	data.put("subscribers", subscribersList);
    	
    	result = this.post(data, "import");
    	
        return result;
    }

    /**
     * Overloaded addAll() method with resubscribe set to false.
     * 
     * @param data Subscribers list.
     * @return Result of adding subscribers.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject addAll(ArrayList<ML_Subscriber> subscribers) throws Exception {
    	JSONObject result = this.addAll(subscribers, false);
        return result;
    }
    
    /**
     * Get information about subscriber by his email.
     * 
     * @param email The email address of the subscriber whose details should be retrieved.
     * @param history Set to true if you want to get historical records of campaigns and autoresponder emails received by a subscriber (default - false)
     * @return Details of the subscriber.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject get(String email, boolean history) throws Exception {
    	HashMap<String, Object> data;
    	JSONObject result;
    	
    	this.setId(0);
        
        data = new HashMap<String, Object>();
        data.put("email", email);
        data.put("history", history ? "1" : "0");
        
        result = this.get(data);
        
        return result;
    }
    
    /**
     * Overloaded get() method with history parameter set to false.
     * 
     * @param email The email address of the subscriber whose details should be retrieved.
     * @return Details of the subscriber.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject get(String email) throws Exception {
    	JSONObject result = this.get(email, false);
        return result;
    }
 
    /**
     * Removes subscriber from the group. He will no longer receive campaigns sent to this group.
     * 
     * @param email The email of the subscriber.
     * @return null if email removed, errors otherwise.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject remove(String email) throws Exception {
        HashMap<String, Object> data;
        JSONObject result;
        
        data = new HashMap<String, Object>();
        data.put("email", email);
        
        result = this.execute(Method.DELETE, data);
        
        return result;
        
    }

    /**
     * Marks subscriber as unsubscribed. He will no longer receive any campaigns.
     * 
     * @param email The email of the subscriber.
     * @return null if email unsubscribed, errors otherwise.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject unsubscribe(String email) throws Exception {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("email", email);
        
    	JSONObject result = this.post(data, "unsubscribe");
    	
        return result;
    }
	
}
