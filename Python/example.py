from mailersoft.ML_Messages import ML_Messages

ml_messages = ML_Messages('API_KEY')

subscribers = [
    {
        'recipientEmail': 'first@example.com',
        'recipientName': 'First name',
        'variables': {'item1': 'value 1', 'item2': 'value2'}
    },
    {
        'recipientEmail': 'second@example.com',
        'recipientName': 'First name',
        'variables': {'item1': 'value 1', 'item2': 'value2'}
    }
]

print ml_messages.set_id(MAIL_ID).add_recipients(subscribers).send()
