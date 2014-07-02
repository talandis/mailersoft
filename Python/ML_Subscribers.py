from ML_Rest import ML_Rest

class ML_Subscribers(ML_Rest):
    """
    ML_Subscribers class is intended to be used for managing Subscribers in given lists.
    """
    
    def __init__(self, api_key):
        """
        Constructor for the ML_Subscribers class.
        
        :param api_key: Your API Key for MailerSoft.
        :type api_key: str
        """
        self._mc_parent = ML_Rest
        self._mc_parent.__init__(self, api_key)
        self._name = "subscribers"
        self._set_path()
    
    def add(self, subscriber, resubscribe=False):
        """
        Add a subscriber to an existing subscriber list, including custom field data if supplied.
        If the subscriber (email address) already exists, their name and any custom field values are updated with whatever is passed in.
        
        :param subscriber:
            Info about subscriber.
            Subscriber should have such keys: **email**, **name**, **fields**, where **fields** is a list of fields.
            E.g. Subscriber can be defined as this:
            subscriber = {'email': 'first@example.com', 'name': 'First Name', 'fields': [{'name': 'custom_field_1', 'value': 'field value 1'}, {'name': 'custom_field_2', 'value': 'field value 2'}]}
        :type subscriber: dict
        :param resubscribe: Set it to True, if you want to reactivate subscriber (default False)
        :type resubscribe: Boolean
        
        :returns: JSONObject with status of adding a new subscriber.
        :rtype: json
        
        """
        subscriber['resubscribe'] = '1' if resubscribe else '0'
        return self._mc_parent.post(self, subscriber)
    
    def add_all(self, subscribers, resubscribe=False):
        """
        Add many subscribers to a subscriber list in one API request, including custom field data if supplied.
        If a subscriber (email address) already exists, their name and any custom field values are updated with whatever is passed in.
        
        :param subscribers:
            List of subscribers.
            See add() method above to see the information needed to pass about the subscriber.
            E.g. Subscribers would be defined as this: subscribers = [subscriber1, subscriber2, ..., subscriberN].
        :type subscribers: list
        :param resubscribe: Set it to True, if you want to reactivate subscriber (default False)
        :type resubscribe: Boolean
        
        :returns: JSONObject with status of adding multiple subscribers. For bad emails you would get errors.
        :rtype: json
        
        """
        data = {
            'resubscribe': '1' if resubscribe else '0',
            'subscribers': subscribers
        }
        return self._mc_parent.post(self, data, 'import')
    
    def get(self, email, history=False):
        """
        Retrieve a subscriber's details including their email address, name, active/inactive state, and any custom field data.
        
        :param email: The email address of the subscriber whose details should be retrieved.
        :type email: str
        :param history: Set to True if you want to get historical records of campaigns and autoresponder emails received by a subscriber (default - False).
        :type history: Boolean
        
        :returns: JSONObject with subscriber's details.
        :rtype: json
        
        """
        data = {
            'email': email,
            'history': '1' if history else '0'
        }
        return self._mc_parent.get(self, data)
    
    def remove(self, email):
        """
        Remove subscriber from the group. He will no longer receive campaigns sent to this group.
        
        :param email: The email of the subscriber.
        :type email: str
        
        :returns: JSONObject with result of deletion.
        :rtype: json
        
        """
        return self._mc_parent.delete(self, {'email': email})
    
    def unsubscribe(self, email):
        """
        Mark subscriber as unsubscribed. He will no longer receive any campaigns.
        
        :param email: The email of the subscriber.
        :type email: str
        
        :returns: JSONObject with result of unsubscribing.
        :rtype: json
        
        """
        return self._mc_parent.post(self, {'email': email}, 'unsubscribe')