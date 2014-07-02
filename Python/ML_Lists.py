from ML_Rest import ML_Rest

class ML_Lists(ML_Rest):
    """
    ML_Lists class is intended to be used for getting information about the lists of recipients.
    """
    
    def __init__(self, api_key):
        """
        Constructor for the ML_Lists class.
        
        :param api_key: Your API Key for MailerSoft.
        :type api_key: str
        """
        self._mc_parent = ML_Rest
        self._mc_parent.__init__(self, api_key)
        self._name = "lists"
        self._set_path()
    
    def get_active(self, data={}):
        """
        Get all active subscribers in given list.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with subscribers in given list.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, "active")
    
    def get_unsubscribed(self, data={}):
        """
        Get all unsubscribed subscribers in given list.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with unsubscribed subscribers in given list.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, "unsubscribed")
    
    def get_bounced(self, data={}):
        """
        Get all bounced subscribers in given list.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with bounced subscribers in given list.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, "bounced")