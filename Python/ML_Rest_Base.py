import re
import json
import urllib
import httplib
import php

class ML_Rest_Base:
    """
    ML_Rest_Base class is used to encapsulate basic querying to the MailerSoft server.
    """
    
    def __init__(self, url='', verb=''):
        """
        Constructor for the ML_Rest_Base class.
        
        :param api_key: Your API Key for MailerSoft.
        :type api_key: str
        :param url: URL of the MailerSoft API.
        :type url: str
        :param verb: GET/POST/PUT/DELETE
        :type verb: str

        """
        self._url = 'https://api.mailersoft.com/api/v1/' if url == '' else url
        self._path = ''
        self._set_host()
        self._action = ''
        self._request_body = ''
        self._api_key = ''
        self._response_body = ''
        self._verb = verb
        self._accept_type = 'application/json'
        self._request_length = 0
        self._response_code = 0
        self._response_info = []
        self._connection = None
    
    def flush(self):
        """
        Reset query.
        """
        self._request_body = ''
        self._request_length = 0
        self._verb = 'GET'
        self._response_body = ''
        self._response_info = ''
        self._response_code = 0
    
    def execute(self, method, data, action=''):
        """
        Execute some query to the MailerSoft Server.
        
        :param method: GET/POST/PUT/DELETE
        :type method: str
        :param data: Request information for the request body.
        :type data: dict
        :param action: Where query should be executed.
        :type action: str
        
        :returns: Execution result of the query.
        :rtype: json
        
        """
        self._resolve_method(method)
        
        self._build_post_body(data)
        
        if action != '':
            action += '/'
        else:
            action = '/'
        
        self._action = action
        
        self._connection = httplib.HTTPConnection(self._host)
        
        self._set_request_url()
        
        if self._verb == 'GET':
            self._execute_get()
        elif self._verb == 'POST':
            self._execute_post()
        elif self._verb == 'PUT':
            self._execute_put()
        elif self._verb == 'DELETE':
            self._execute_delete()
        
        response = self._connection.getresponse()
        self._response_code = response.status
        self._response_body = response.read()
        result = json.loads(self._response_body)
        
        return result

    def _set_host(self):
        """
        Set host by the given url. This will be needed to set connection to the server.
        """
        url = self._url.replace('https://', '')
        url = url.replace('http://', '')
        parts = re.split('/', url)
        self._host = parts[0]
    
    def _resolve_method(self, method):
        """
        Make sure that method is only GET/POST/PUT/DELETE.
        
        :param method: Anything that is of string type.
        :type method: str
        """
        method = method.upper()
        if method == 'GET' or method == 'POST' or method == 'PUT' or method == 'DELETE':
            self._verb = method
        else:
            self._verb = 'GET'
    
    def _build_post_body(self, data):
        """
        Build request body. Actually, this part is quite complicated, because urllib library is not enough for more complicated data streams to send.
        So, we need to use third-party http_build_query() for Python. Sometimes PHP functions are very valuable. More on that, for older servers it becomes quite important.
        
        :param data: Request information for the request body.
        :type data: dict
        
        """
        data['apiKey'] = self._api_key
        self._request_body = php.http_build_query(data)
    
    def _execute_get(self):
        """
        Execute GET query.
        """
        self._connection.request('GET', self._request_url, self._request_body)
    
    def _execute_post(self):
        """
        Execute POST query.
        """
        self._data_connection('POST')
    
    def _execute_put(self):
        """
        Execute PUT query.
        """
        self._data_connection('PUT')
    
    def _execute_delete(self):
        """
        Execute DELETE query.
        """
        self._connection.request('DELETE', self._request_url, self._request_body)

    def _data_connection(self, method):
        """
        Set PUT or POST connection.
        
        :param method: PUT/POST
        :type method: str
        """
        headers = {'Content-type': 'application/x-www-form-urlencoded', 'Accept': 'text/plain'}
        self._connection.request(method, self._request_url, self._request_body, headers)

    def _set_request_url(self):
        """
        Set GET or DELETE connection.
        
        :param method: GET/DELETE
        :type method: str
        """
        self._request_url = self._path + self._action
