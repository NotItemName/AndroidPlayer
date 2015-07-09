### AndroidPlayer
##Albums:
	#Get all albums 
	Method: GET
	Endpoint: /player-server/album/get
	Response: 200 OK
	Response body:
	{
		albums:
		[
			{
				"id":1,
				"name":"Nevermind",
				"year":1991,
				"artist":"Nirvana",
				"genres":["Grunge","Alternative"]
			},
			{
				"id":2,
				"name":"Bleach",
				"year":1990,
				"artist":"Nirvana",
				"genres":["Grunge","Alternative"]
			}
		]
	}
	
	#Get album by id
	Method: GET
	Endpoint: /player-server/album/get/1
	Response: 200 OK
	Response body:
	{
		"id":1,
		"name":"Nevermind",
		"year":1991,
		"artist":"Nirvana",
		"genres":["Grunge","Alternative"]
	}
	
	#Add album
	Method: POST
	Endpoint: /player-server/album/add
	Request body:
	{
		"name":"Nevermind1",
		"year":1992,
		"artist":"Nirvana",
		"genres":["Grunge","Alternative"]
	}
	Response: 201 Created
	Response body:
	{
		"id":9
		"name":"Nevermind1",
		"year":1992,
		"artist":"Nirvana",
		"genres":["Grunge","Alternative"]
	}
	
	#Update album
	Method: PUT
	Endpoint: /player-server/album/update/1
	Request body:
	{
		"name":"Nevermind",
		"year":1992,
		"artist":"Nirvana",
		"genres":["Grunge","Alternative"]
	}
	Response: 204 No Content
	
	Delete album
	Method: DELETE
	Endpoint: /player-server/album/delete/9
	Response: 204 No Content
