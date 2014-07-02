from ML_Rest import ML_Rest

class ML_Messages(ML_Rest):
    """
    ML_Messages class is intended to be used for planning, queueing the newsletters for subscribers.
    """
    
    def __init__(self, api_key):
        """
        Constructor for the ML_Messages class.
        
        :param api_key: Your API Key for MailerSoft.
        :type api_key: str
        """
        self._mc_parent = ML_Rest
        self._mc_parent.__init__(self, api_key)
        self._name = "messages"
        
        self._variables = {}
        self._batch_recipients = []
        self._attachments = []
        self._recipient_email = ''
        self._recipient_name = ''
        self._from_email = ''
        self._from_name = ''
        self._type = ''
        self._language = ''
        self._reply_to_email = ''
        self._reply_to_name = ''
        
        self._set_path()
    
    def set_recipient(self, email, name=''):
        """
        Set email and name of the recipient.
        
        :param email: Email address of the recipient
        :type email: str
        :param name: name of the recipient
        :type name: str
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._recipient_email = email
        self._recipient_name = name
        return self
    
    def set_from(self, email, name=''):
        """
        Set email address and name which will be seen as sent from.
        
        :param email: Email address of the FROM
        :type email: str
        :param name: name of the FROM
        :type name: str
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._from_email = email
        self._from_name = name
        return self
    
    def set_variables(self, variables={}):
        """
        Set variables which will be attached to the newsletter.
        
        :param variables: Variables to be attached to the newsletter.
        :type variables: dict
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        for key in variables.keys():
            self.set_variable(key, variables[key])
        return self
    
    def set_variable(self, key, value):
        """
        Set variable which will be attached to the newsletter.
        
        :param key: Name of the variable (key).
        :type key: str
        :param value: Value of the variable.
        :type value: str, list or dict
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._variables[key] = value
        return self
    
    def set_type(self, type):
        """
        Set type of the newsletter.
        
        :param type: Type of the newsletter.
        :type type: str
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._type = type
        return self
    
    def set_language(self, language):
        """
        Set language of the newsletter.
        
        :param language: Language of the newsletter.
        :type language: str
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._language = language
        return self
    
    def add_recipient(self, recipient):
        """
        Add the recipient to the newsletter which will be sent.
        
        :param recipient:
            Recipient information.
            Recipient is a dictionary with such keys: **recipientEmail**, **recipientName**, and **variables**.
            The latter key for dictionary has value which has dictionary type, too.
        :type recipient: dict
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._batch_recipients.append(recipient)
        return self
    
    def add_recipients(self, recipients):
        """
        Add recipients to the newsletter.
        
        :param recipients: List of recipients to be added.
        :type recipients: list
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._batch_recipients += recipients
        return self
    
    def set_reply_to(self, email, name=''):
        """
        Set reply to email and name.
        
        :param email: Email address to which reply should be send.
        :type email: str
        :param name: name to which reply should be send.
        :type name: str
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        self._reply_to_email = email
        self._reply_to_name = name
        return self
    
    def add_attachment(self, filename, content):
        """
        Add attachment to the newsletter.
        
        :param filename: Filename of the attachment.
        :type filename: str
        :param content: Contents of the attachment.
        :type content: str
        
        :returns: ML_Messages instance.
        :rtype: ML_Messages
        """
        attachment = {
            'filename': filename,
            'contents': content
        }
        self._attachments.append(attachment)
        return self
    
    def send(self):
        """
        Send the newsletter.
        
        :returns: JSONObject of the newsletter sent. If everything is ok, the message should be queued.
        :rtype: json
        """
        data = {
            'mailId': self._id,
            'language': self._language,
            'fromName': self._from_name,
            'fromEmail': self._from_email
        }
        
        if len(self._batch_recipients) > 0:
            data['batch'] = self._batch_recipients
        else:
            data['recipientName'] = self._recipient_name
            data['recipientEmail'] = self._recipient_email
            data['variables'] = self._variables
            data['attachments'] = self._attachments
        
        return self._mc_parent.post(self, data)