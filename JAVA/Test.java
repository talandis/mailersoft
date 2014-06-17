import java.util.ArrayList;
import java.util.HashMap;

import ml.ML_Campaigns;
import ml.ML_Lists;
import ml.ML_Messages;
import ml.ML_Subscribers;
import ml.ML_Recipient;
import ml.ML_Subscriber;

import org.json.JSONObject;

import base.ML_Rest;
import base.ML_Rest_Base;


public class Test {

	public static void main(String[] args) throws Exception {

		ArrayList<ML_Recipient> recipients = new ArrayList<ML_Recipient>();
		ML_Recipient recipient;
		ML_Messages mlMessages = new ML_Messages("w7k1h3q4l6c3o3e7l4v6a1l4t8q6c6d9");
		
		recipient = new ML_Recipient("first@example.com", "First Name 1");
		recipient.setField("item1", "value 1");
		recipient.setField("item2", "value 2");
		recipients.add(recipient);
		
		recipient = new ML_Recipient("second@example.com", "First Name 2");
		recipient.setField("item1", "value 1");
		recipient.setField("item2", "value 2");
		recipients.add(recipient);
		
		recipient = new ML_Recipient("third@example.com", "First Name 3");
		recipient.setField("item1", "value 1");
		recipient.setField("item2", "value 2");
		recipients.add(recipient);
		System.out.println(mlMessages.setId(30007244).addRecipients(recipients).send());

	}
	
}
