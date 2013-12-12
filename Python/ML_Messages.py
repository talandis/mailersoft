import urllib, urllib2, json

class ML_Messages:
    MAILER_SERVER_URI = 'https://api.mailersoft.com/api/v1/messages'
    api_key = ''
    from_name = ''
    from_email = ''
    variables = dict()
    id = 0
    recipient_email = ''
    recipient_name = ''
    batchRecipients = []

    def __init__(self, apiKey):
        self.set_api_key(apiKey)

    def set_recipient(self, email, name=None):
        self.recipient_email = email
        if name:
            self.recipient_name = name
        return self

    def set_api_key(self, api_key):
        self.api_key = api_key
        return self

    def set_from_name(self, name):
        self.from_name = name
        return self

    def set_from_email(self, email):
        self.from_email = email
        return self

    def set_variables(self, variables):
        if isinstance(variables, dict):
            for name, value in variables.items():
                self.set_variable(name, value)
        return self

    def set_variable(self, name, value):
        self.variables[name] = value
        return self

    def set_id(self, id):
        self.id = id
        return self
        
    def add_recipient(self, recipient):
        self.batchRecipients.append(recipient)
        return self
        
    def add_recipients(self, recipients):
        for item in recipients:
        	self.add_recipient(item)
        return self

    def send(self):
        data = {
            'apiKey': self.api_key,
            'id': self.id,
            'recipientName': self.recipient_name,
            'recipientEmail': self.recipient_email,
            'variables': self.variables
        }
        
        data = {
        	'apiKey': self.api_key,
            'id': self.id,
            'batch': self.batchRecipients
        }
        return urllib2.urlopen(self.MAILER_SERVER_URI, data=urllib.urlencode(data)).read()

