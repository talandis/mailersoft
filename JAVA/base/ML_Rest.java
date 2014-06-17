package base;

import java.util.Map;

import org.json.JSONObject;

/**
 * ML_Rest class to extend ML_Rest_Base functionality
 * in order to use it more simply in other classes.
 * 
 * @author Vaidotas Strazdas
 *
 */
public class ML_Rest extends ML_Rest_Base {

	protected String name = "";
	private long id = -1;
	
	/**
	 * ML_Rest constructor.
	 * 
	 * @param apiKey API Key
	 */
	public ML_Rest(String apiKey) {
		super();
		this.apiKey = apiKey;
		this.setPath(null);
	}

	/**
	 * Set element in MailerSoft Database ID
	 * 
	 * @param id Element in MailerSoft DB ID
	 * @return ML_Rest object
	 */
	public ML_Rest setId(long id) {
		this.id = id;
		
		this.setPath(this.id);
		
		return this;
	}

	/**
	 * Get all elements.
	 * 
	 * @param data Specific query to send to the server.
	 * @return Elements by specific query.
	 * @throws Exception
	 * @see JSONObject
	 */
	public JSONObject getAll(Map<String, Object> data) throws Exception {
		JSONObject result;
		
		this.setPath(null);
		
		result = this.execute(Method.GET, data);
		
		return result;
	}

	/**
	 * Overloaded getAll() method without any specific query.
	 * 
	 * @return Elements without any specific query.
	 * @throws Exception
	 * @see JSONObject
	 */
	public JSONObject getAll() throws Exception {
		JSONObject result = this.getAll(null);
		return result;
	}

	/**
	 * Retrieve some element by specific ID.
	 * 
	 * @param data Specific query.
	 * @param name Name of the element to retrieve.
	 * @return Result of retrieval.
	 * @throws Exception
	 * @see JSONObject
	 */
    public JSONObject get(Map<String, Object> data, String name) throws Exception {
		JSONObject result;
		
		if (this.id == -1)
			throw new IllegalArgumentException("ID is not set.");
		
    	result = this.execute(Method.GET, data, name);
    	
    	return result;
    }

	/**
	 * Overloaded get() method.
	 * 
	 * @param data Specific query.
	 * @return Result of retrieval.
	 * @throws Exception
	 * @see JSONObject
	 */
	public JSONObject get(Map<String, Object> data) throws Exception {
		JSONObject result = this.get(data, null);
		return result;
	}

	/**
	 * Overloaded get() method without specific query.
	 * 
	 * @return Specific element.
	 * @throws Exception
	 * @see JSONObject
	 */
	public JSONObject get() throws Exception {
		JSONObject result = this.get(null);
		return result;
	}
    
    /**
     * Post data by specific name to the MailerSoft server.
     * 
     * @param data Specific query.
     * @param name Name of the element to post to.
     * @return Result of posting.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject post(Map<String, Object> data, String name) throws Exception {
    	JSONObject result = this.execute(Method.POST, data, name);
    	return result;
    }

	/**
	 * Add some element to the server.
	 * 
	 * @param data Query to send to the server.
	 * @return Result of addition to the server.
	 * @throws Exception
	 * @see JSONObject
	 */
    public JSONObject add(Map<String, Object> data) throws Exception {
    	JSONObject result = this.execute(Method.POST, data);
    	return result;
    }

    /**
     * Overloaded add() method without any specific query.
     * 
     * @return Result of addition.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject add() throws Exception {
    	JSONObject result = this.add(null);
    	return result;
    }
   
    /**
     * Put some data to the server.
     * 
     * @param data Specific query.
     * @return Result of addition.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject put(Map<String, Object> data) throws Exception {
    	JSONObject result = this.execute(Method.PUT, data);
    	return result;
    }

    /**
     * Overloaded put() method without any specific query.
     * 
     * @return Result of addition.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject put() throws Exception {
    	JSONObject result = this.put(null);
    	return result;
    }

    /**
     * Delete some data from the server. If deletion is successful, the
     * server will not return correctly formatted JSONObject, so this API
     * will return null value.
     * 
     * @param data Specific query.
     * @return null on success and JSONObject on failure.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject delete(Map<String, Object> data) throws Exception {
    	JSONObject result = this.execute(Method.DELETE, data);
    	return result;
    }


    /**
     * Overloaded delete() method without any specific query.
     * 
     * @param data Specific query.
     * @return Result of deletion.
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject delete() throws Exception {
    	JSONObject result = this.delete(null);
    	return result;
    }
	
    /**
     * Set path.
     * 
     * @param id ID of element in the MailerSoft database.
     */
	protected void setPath(Long id) {

		if (this.name.equals(""))
			this.path = this.url;
		else
			this.path = this.url + this.name + "/";
		
		if (id != null)
			this.path = this.path + Long.toString(id) + "/";
	}
	
}
