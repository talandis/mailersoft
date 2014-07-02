from ML_Rest_Base import ML_Rest_Base

class ML_Rest(ML_Rest_Base):
    """
    ML_Rest class is used to make use of ML_Rest_Base, i.e. other classes will inherit ML_Rest which is easier to use than ML_Rest_Base.
    """
    
    def __init__(self, api_key, url='', verb=''):
        """
        Constructor for the ML_Rest class.
        
        :param api_key: Your API Key for MailerSoft.
        :type api_key: str
        :param url: URL of the MailerSoft API.
        :type url: str
        :param verb: GET/POST/PUT/DELETE
        :type verb: str

        """
        self._parent = ML_Rest_Base
        self._parent.__init__(self, url, verb)
        self._id = ''
        self._name = ''
        self._api_key = api_key
    
    def set_id(self, id=''):
        """
        Set specific id for specific element.
        
        :param id: ID of the specific element.
        :type id: int
        
        :returns: Instance of ML_Rest or the instance of inherited class.
        :rtype: ML_Rest or the class which inherits ML_Rest
        
        """
        self._id = id
        self._set_path(id)
        return self
    
    def get_all(self, data={}):
        """
        Get all elements of something.
        
        :param data: Mostly this will be configuration of page parameters.
        :type data: dict
        
        :returns: All elements of something.
        :rtype: json
        
        """
        self._set_path()
        return self._parent.execute(self, 'GET', data)
    
    def get(self, data={}, name=''):
        """
        Get specific element by specific query.
        Specific element is defined by id or name.
        
        :param data: Mostly this will be configuration of what element needs to be get.
        :type data: dict
        :param name: What element to get.
        :type name: str
        
        :returns: Details of something.
        :rtype: json
        
        """
        return self._parent.execute(self, 'GET', data, name)
    
    def post(self, data={}, name=''):
        """
        Post something by specific query.
        
        :param data: What to post.
        :type data: dict
        :param name: Where to post.
        :type name: str
        
        :returns: Details of posting something.
        :rtype: json
        
        """
        return self._parent.execute(self, 'POST', data, name)
    
    def add(self, data={}):
        """
        Add something.
        
        :param data: What to add.
        :type data: dict
        
        :returns: Details of adding something.
        :rtype: json
        
        """
        return self._parent.execute(self, 'POST', data)
    
    def put(self, data={}):
        """
        Put something.
        
        :param data: What to put.
        :type data: dict
        
        :returns: Details of puting something.
        :rtype: json
        
        """
        return self._parent.execute(self, 'PUT', data)
    
    def delete(self, data={}):
        """
        Delete something.
        
        :param data: What to delete.
        :type data: dict
        
        :returns: Details of deleting something.
        :rtype: json
        
        """
        return self._parent.execute(self, 'DELETE', data)
    
    def _set_path(self, id=''):
        """
        Set path of querying.
        
        :param id: ID of the specific element
        :type id: int
        
        """
        self._path = self._url
        if self._name != '':
            self._path += self._name + '/'
        
        if id != '':
            self._path += str(id) + '/'
    