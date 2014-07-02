from ML_Rest import ML_Rest

class ML_Campaigns(ML_Rest):
    """
    ML_Campaigns class used to manage Campaigns.
    """
    def __init__(self, api_key):
        """
        Constructor for the ML_Campaigns class.
        
        :param api_key: Your API Key for MailerSoft.
        :type api_key: str
        """
        self._mc_parent = ML_Rest
        self._mc_parent.__init__(self, api_key)
        self._name = "campaigns"
        self._set_path()
    
    def get_recipients(self, data={}):
        """
        Get recipients from MailerSoft for a given campaign.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with recipients.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, 'recipients')
    
    def get_opens(self, data={}):
        """
        Retrieve a paged result representing all the subscribers that opened a given campaign.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with recipients that opened a given campaign.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, 'opens')
    
    def get_clicks(self, data={}):
        """
        Retrieve a paged result representing all the subscribers that clicked on a given campaign.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with recipients that clicked a given campaign.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, 'clicks')
    
    def get_unsubscribes(self, data={}):
        """
        Retrieve a paged result representing all the subscribers that unsubscribed from a given campaign.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with recipients that unsubscribed from a given campaign.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, 'unsubscribes')
    
    def get_bounces(self, data={}):
        """
        Retrieve a paged result representing all the subscribers who bounced for a given campaign.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with recipients who bounced for a given campaign.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, 'bounces')
    
    def get_junk(self, data={}):
        """
        Retrieve a paged result representing all the subscribers who complained about a given campaign.
        You do not call this method before calling set_id().
        
        :param data:
            You can set limits of results in one page (default is 1000).
            You also can navigate through records by increasing 'page'.
            So, you may use data = {'results': 100, 'page': 1} to get 100 results in the 2nd (0 is the 1st) page.
        :type data: dict

        :returns: JSONObject with recipients who complained about a given campaign.
        :rtype: json
        
        """
        return self._mc_parent.get(self, data, 'junk')