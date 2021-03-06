from gcm import *

gcm = GCM(API_KEY)
data = {'param1': 'value1', 'param2': 'value2'}

# Plaintext request
reg_id = '12'
gcm.plaintext_request(registration_id=reg_id, data=data)

# JSON request
reg_ids = ['12', '34', '69']
response = gcm.json_request(registration_ids=reg_ids, data=data)

# Extra arguments
res = gcm.json_request(
        registration_ids=reg_ids, data=data,
            collapse_key='uptoyou', delay_while_idle=True, time_to_live=3600
    )


try:
    canonical_id = gcm.plaintext_request(registration_id=reg_id, data=data)
    if canonical_id:
        # Repace reg_id with canonical_id in  database
        entry = entity.filter(registration_id=reg_id)
        entry.registration_id = canonical_id
        entry.save()
except GCMNotRegisteredException:
    # Remove this reg_id from database
    entity.filter(registration_id=reg_id).delete()
except GCMUnavailableException:
    print "asdf"
    # Resent the message

# JSON request
reg_ids = ['12', '34', '69']
response = gcm.json_request(registration_ids=reg_ids, data=data)

# Handling errors
if 'errors' in response:
    for error, reg_ids in response['errors'].items():
        # Check for errors and act accordingly
        if error in ['NotRegistered', 'InvalidRegistration']:
            # Remove reg_ids from database
            for reg_id in reg_ids:
                entity.filter(registration_id=reg_id).delete()
if 'canonical' in response:
    for reg_id, canonical_id in response['canonical'].items():
        # Repace reg_id with canonical_id in your database
        entry = entity.filter(registration_id=reg_id)
        entry.registration_id = canonical_id
        entry.save()
