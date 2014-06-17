package ml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for Subscribers, Recipients, etc.
 * 
 * @author Vaidotas Strazdas
 *
 */
public abstract class ML_Person {
	protected String emailFieldName;
	protected String nameFieldName;
	protected String fieldsFieldName;
	private String email;
	private String name;
	private ArrayList<Map<String, String>> fields;

	/**
	 * Constructor of ML_Person.
	 * 
	 * @param email Email of the person.
	 * @param name Name of the person.
	 */
	public ML_Person(String email, String name) {
		this.email = email;
		this.name = name;
		this.fields = new ArrayList<Map<String, String>>();
	}

	/**
	 * Set specific field with it's value. Like the city, country, etc.
	 * 
	 * @param name Name of the field.
	 * @param value Value of the field.
	 */
	public void setField(String name, String value) {
		HashMap<String, String> field = new HashMap<String, String>();
		field.put("name", name);
		field.put("value",  value);
		this.fields.add(field);
	}

	/**
	 * Get specific person for making a stream to MailerSoft API.
	 * 
	 * @return Hashmap of the person.
	 */
	public HashMap<String, Object> get() {
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put(this.emailFieldName, this.email);
		info.put(this.nameFieldName, this.name);
		info.put(this.fieldsFieldName, this.fields);
		return info;
	}
	
}
