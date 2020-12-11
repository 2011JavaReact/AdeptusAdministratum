# Adeptus Administratum
The Adeptus Administratum is the administrative and bureaucratic heart of the Imperium of Man, managing thousands of worlds, billions of soldiers, and trillions of citizens. "How does it do this?" you may ask. The answer: Poorly.

Redudancies, religious zealotry, petty politics. Anything that could get in the way of running a government well is getting in the way of running the Imperium, on a galactic scale. It is only through sheer size and inertia, and perhaps the will of the Emperor, that governmental duties are carried out at all.

This project seeks not to remedy these bureaucratic nightmares, but to add to them. Records of planets, inhabitants, populations, and territorial boundaries can be found every where, and that's just the problem: there is no singular, trustworthy source for this information. You could ask the Adeptus Astronomica for information about planets, but they're more focused on how to navigate to those planets You could ask the Inquisition about xenos planets, but asking too many questions around them is quick way to gain their unwanted attention. Or maybe just ask the Astra Militarum, but they are most certainly soliders before they are census takers.

Or, as I've suggested, a new database is formed solely to fill this purpose!

# Endpoints

## GET /planets
This will return an array of all planets.

## GET /garrisons
This will return an array of all garrisons.

## GET /users
This will return an array of all users.

## GET /planets/#
This will return a single planet specified by its ID.
```
.../AdeptusAdministratum/planets/12
```

## GET /garrisons/#
This will return a single garrison specified by its ID.
```
.../AdeptusAdministratum/garrisons/12
```

## GET /users/#
This will return a single user specified by its ID.
```
.../AdeptusAdministratum/users/2
```

## POST /planets
This will create a new planet in the database. Send new the planet as a JSON. The response will contain the new planet along with its ID in the database.
```
{
    "name": "Terra",
    "inhabitants": "Human",
    "population": 10000,
    "garrison_id": 1
}
```

## POST /garrisons
This will create a new garrison in the database. Send the new garrison as a JSON. The response will contain the new garrison along with its ID in the database.
```
{
    "chapter": "Astra Militarum",
    "size": 1000
}
```

## POST /users
This will create a new user in the database. Send the new user as a JSON. The response will contain the new user along with its ID in the database.
```
{
    "username": "test123",
    "password": "password123",
    "admin": false,
    "loggedIn": false
}
```

## PUT /planets/#
This will update a single planet specified by its ID. Send the updated planet information as a JSON. The response will contain the update planet.
```
.../AdeptusAdministratum/planets/12
```
```
{
    "name": "/mars",
    "inhabitants": "Martians",
    "population": 5000,
    "garrison_id": 8
}
```

## PUT /garrisons/#
This will update a single garrison specified by its ID. Send the updated garrison information as a JSON. The response will contain the update garrison.
```
.../AdeptusAdministratum/garrisons/12
```
```
{
    "chapter": "Space Wolves",
    "size": 500
}
```

## PUT /users/#
This will update a single user specified by its ID. Send the updated user information as a JSON. The response will contain the update user.
```
.../AdeptusAdministratum/users/2
```
```
{
    "username": "test123",
    "password": "password123",
    "admin": true,
    "loggedIn": true
}
```

## DELETE /planets/#
This will remove a single planet specified by its ID. At present, nothing is returned, and a console message is printed.
```
.../AdeptusAdministratum/planets/12
```

## DELETE /garrisons/#
This will remove a single garrison specified by its ID. At present, nothing is returned, and a console message is printed.
```
.../AdeptusAdministratum/garrisons/12
```

## DELETE /users/#
This will remove a single user specified by its ID. At present, nothing is returned, and a console message is printed.
```
.../AdeptusAdministratum/users/2
```

## POST /login
Receives a username and password in a JSON format, authenticates the credentials, and returns the logged in user.
```
{
    "username": "admin",
    "password": "admin"
}
```

Returns the following:
```
{
    "id": 1,
    "username": "admin",
    "password": "admin",
    "loggedIn": true,
    "admin": true
}
```

## POST /logout
Receives a username and password in a JSON format, and authenticates the credentials. At present, this endpoint is extremely superficial. Under the hood, it is checking the username and password, but all this does in the database is toggle a boolean. And yes, you do need you credentials to logout!
```
{
    "username": "admin",
    "password": "admin"
}
```
