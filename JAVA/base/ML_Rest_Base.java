package base;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/**
 * ML_Rest_Base is an abstract class that is intended to be inherited by
 * ML_Rest or other more difficult 'ml' package class.
 * 
 * @author Vaidotas Strazdas
 *
 */

public abstract class ML_Rest_Base {
	
    protected String url;
    protected String path = "";
    protected String host;
    protected String requestBody;
    protected String apiKey = "";
    protected String username;
    protected String password;
    protected String acceptType;
    protected String responseBody;
    protected Method verb;
    protected long requestLength;
    protected int responseCode = 0;
    protected Map<String, List<String>> responseInfo;
    
    private String request_url;
    private String action;
    private HttpsURLConnection connection;
    
    /**
     * ML_Rest_Base constructor
     * 
     * @param url URL to use for MailerSoft API
     * @param verb Which method to use
     */
    public ML_Rest_Base(String url, Method verb) {
        this.url = url;
        this.verb = verb;
        this.requestLength = 0;
        this.username = null;
        this.password = null;
        this.acceptType	= "application/json";
        this.responseBody = null;
        this.responseInfo = null;
        this.setHost();
    }

    /**
     * ML_Rest_Base overloaded constructor, which makes
     * GET HTTP/1.1 method as default.
     * 
     * @param url
     */
	public ML_Rest_Base(String url) {
    	this(url, Method.GET);
    }
    
	/**
	 * ML_Rest_Base overloaded constructor with default
	 * url and GET method.
	 */
    public ML_Rest_Base() {
    	this("https://api.mailersoft.com/api/v1/", Method.GET);
    }

    /**
     * Reset query.
     */
    public void flush() {
        this.requestBody = null;
        this.requestLength = 0;
        this.verb = Method.GET;
        this.responseBody = null;
        this.responseInfo = null;
        this.responseCode = 0;
    }
    
    /**
     * Method to send some query to MailerSoft and parse the
     * answer MaileSoft server provides.
     * 
     * @param method Which method to use
     * @param data Data stream to send to MailerSoft
     * @param action What action to do with MailerSoft
     * @return Result of the execution
     * @throws Exception
     * @see JSONObject
     */
    public JSONObject execute(Method method, Map<String, Object> data, String action) throws Exception {

    	JSONObject result;
    	
    	if (method != null)
    		this.verb = method;
    	
    	this.buildPostBody(data);
    	
    	if (action != null)
    		action += '/';
    	else
    		action = "/";
    	
    	this.action = action;
    	
    	try {
    		switch (this.verb) {
	    		case GET:
	    			this.executeGet();
	    			break;
	    		case POST:
	    			this.executePost();
	    			break;
	    		case PUT:
	    			this.executePut();
	    			break;
	    		case DELETE:
	    			this.executeDelete();
	    			break;
    		}
    	} catch (IllegalArgumentException e) {
    		throw e;
    	} catch (Exception e) {
    		throw e;
    	}
    	
    	/*
    	 * MailerSoft does not always give pure JSON Array, but
    	 * some responses are correct even if there is not JSON Array response,
    	 * so we can't simply throw an exception.
    	 */
    	try {
    		result = new JSONObject(this.responseBody);
    		return result;
    	} catch(Exception e) {
    		return null;
    	}
    }
    
    /**
     * Overloaded execute method without action specified.
     * 
     * @param method
     * @param data
     * @return
     * @throws Exception
     */
    public JSONObject execute(Method method, Map<String, Object> data) throws Exception {
    	JSONObject result = this.execute(method, data, null);
    	return result;
    }
    
    /**
     * Method to build data stream to send to MailerSoft.
     * 
     * @param data Data fields for MailerSoft
     */
    public void buildPostBody(Map<String, Object> data) {

        if (data == null)
        	data = new HashMap<String, Object>();
        
        // Maybe there is apiKey provided already, maybe there isn't...
        data.put("apiKey", this.apiKey);

        this.requestBody = URLBuilder.httpBuildQuery(data, "UTF-8");
        
    }

    /**
     * Set accept type.
     * 
     * @param acceptType Response type we accept.
     */
    public void setAcceptType(String acceptType) {
        this.acceptType = acceptType;
    }
    
    /**
     * Get accept type.
     * 
     * @return Which response type do we accept.
     */
    public String getAcceptType() {
        return this.acceptType;
    }

    /**
     * Set user name.
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get user name.
     * 
     * @return user name
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set password.
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get password.
     * 
     * @return Password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get response stream from the server.
     * 
     * @return Response stream.
     */
    public String getResponseBody() {
        return this.responseBody;
    }

    /**
     * Get header fields that server gave us after response.
     * 
     * @return Header fields.
     */
    public Map<String, List<String>> getResponseInfo() {
        return this.responseInfo;
    }

    /**
     * Set URL.
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get URL.
     * 
     * @return URL
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Set method we use to send data.
     * 
     * @param verb
     * @see Method
     */
    public void setVerb(Method verb) {
        this.verb = verb;
    }

    /**
     * Get method we use to send data.
     * 
     * @return method.
     * @see Method
     */
    public Method getVerb() {
        return this.verb;
    }

    /**
     * Execute GET method
     * 
     * @throws Exception
     */
    protected void executeGet() throws Exception {
        this.requestConnection("GET");
    }

    /**
     * Execute POST method
     * 
     * @throws Exception
     */
    protected void executePost() throws Exception {
    	this.dataConnection("POST");
    }

    /**
     * Execute PUT method
     * 
     * @throws Exception
     */
    protected void executePut() throws Exception {
    	this.dataConnection("PUT");
    }

    /**
     * Execute DELETE method
     * 
     * @throws Exception
     */
    protected void executeDelete() throws Exception {
    	this.requestConnection("DELETE");
    }
 
    /**
     * Send data to MailerSoft and retrieve response
     * 
     * @param type
     * @throws Exception
     */
    protected void doExecute(String type) throws Exception {
    	
    	DataOutputStream wr;
    	BufferedReader in;
    	String inputLine;
    	StringBuffer response;
    	boolean isOk; // Is response 2XX
    	
    	// Set Host, Accept and Connection HTTP/1.1 stream parameters
    	this.connection.setRequestProperty("Host", this.host);
    	this.connection.setRequestProperty("Accept", this.acceptType);
    	this.connection.setRequestProperty("Connection", "Close");
    	
    	// If POST or PUT, then we write additional stream to the server
    	if (type.equals("POST") || type.equals("PUT")) {
    		wr = new DataOutputStream(this.connection.getOutputStream());
    		wr.writeBytes(this.requestBody);
    		wr.flush();
    		wr.close();
    	}
    	
    	this.responseCode = this.connection.getResponseCode();

    	isOk = this.responseCode / 100 == 2;
    	in = new BufferedReader(new InputStreamReader(!isOk ? this.connection.getErrorStream() : this.connection.getInputStream()));
    	response = new StringBuffer();
    
    	while ((inputLine = in.readLine()) != null)
    		response.append(inputLine);
    	in.close();
    	
    	this.responseBody = response.toString();
    	this.responseInfo = this.connection.getHeaderFields();
    	
    }

    protected void setAuth() {
        /*
         * If the username and password would be set,
         * then we would need to set DIGEST session. However, there is no
         * indication that there is needed something more than just an
         * apiKey for MailerSoft.
         */
    }

    /**
     * GET or DELETE connection configuration method.
     * 
     * @param type Request method (GET/DELETE)
     * @throws Exception
     */
    private void requestConnection(String type) throws Exception {
    	// Set Request URL
        this.request_url = this.path + this.action;
        this.request_url += this.path.contains("?") ? '&' : '?';
        this.request_url += this.requestBody;
        
        this.setConnection();
        
        this.connection.setRequestMethod(type);
        
        this.doExecute(type);
    }

    /**
     * POST or PUT connection configuration method.
     * 
     * @param type Request method (POST/PUT)
     * @throws Exception
     */
    private void dataConnection(String type) throws Exception {
    	// Set Request URL
    	this.request_url = this.path + this.action;
    	
    	this.setConnection();
    	
    	// Configure connection according to POST or PUT specification
    	this.connection.setRequestMethod(type);
    	this.connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    	this.connection.setRequestProperty("Content-Length", Integer.toString(this.requestBody.length()));
    	this.connection.setDoOutput(true);
    	this.connection.setDoInput(true);
    	
    	this.doExecute(type);
    }

    /**
     * Create connection to MailerSoft descriptor.
     * 
     * @throws IOException
     */
    private void setConnection() throws IOException {
        try {
			URL obj = new URL(this.request_url);
			HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
			this.connection = conn;
		} catch (MalformedURLException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
    }
    
    /**
     * Parses host for Host parameter for HTTP/1.1
     * request from url provided for ML_Rest_Base class
     * constructor.
     */
    private void setHost() {
    	String url = this.url;
    	url = url.replace("https://", "");
    	url = url.replace("http://", "");
    	String[] parts = url.split("/");
    	this.host = parts[0];
    }
    
}
