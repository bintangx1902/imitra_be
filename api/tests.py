import json

import requests

# Define the API endpoint
url = "http://localhost:8000/api/staff/create-pks"  # Adjust the URL as needed

# Sample dummy data for creating a new Mou_Nda instance
dummy_data = {
    "base": "m",
    "title": "Dummy Partnership Agreement",
    "partnership_type": "JO",
    "partnership_method": 't1',
    "material_type": "ms",
    "background": "This is a dummy agreement outlining the terms of the partnership.",
    "budget_type": 'oc',
    "budget_number": "11102-222",
    "note": "This note is for internal use only.",
    "partnership_candidate": "PLN Comnets",
    "user": 1,  # Replace with a valid user ID
    "status": "p",  # Assuming 'p' is a valid status
    "scopes": [{"scope_name": "Dummy Scope 1"}, {"scope_name": "Dummy Scope 2"}],
    "rabs": [{
        'customer': 'cust-1',
        'product': "prod-1",
        'is_pln': True,
        'revenue': 10_000_000,
        "cost": 8_000_000,
        "cost_desc": "deskripsinya",
    }, {
        'customer': 'cust-1',
        'product': "prod-1",
        'is_pln': True,
        'revenue': 10_000_000,
        "cost": 8_000_000,
        "cost_desc": "deskripsinya",
    }]
}

# Prepare files for the request
files = [
    ('attachments', open('/mnt/c/Users/Bintang/Downloads/labels.txt', 'rb')),  # Replace with actual file path
    ('attachments', open('/mnt/c/Users/Bintang/Downloads/labels.txt', 'rb'))  # Another file
]

data = {
    **dummy_data,
    'scopes': json.dumps(dummy_data['scopes']),
    'rabs': json.dumps(dummy_data['rabs'])
}

# Send the POST request
response = requests.post(url, data=data, files=files)

# Print the response
if response.status_code == 201:
    print("Successfully created Mou_Nda:")
    print(response.json())
else:
    print("Failed to create Mou_Nda:")
    print(f"Status Code: {response.status_code}")
    print(f"Response: {response.json()}")
