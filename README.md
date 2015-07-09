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
	
##Artist

#Get all artists
	Method: GET
	Endpoint: /player-server/artist/get
	Response: 200 OK
	Response body:
	{
		artists:
		[
			{
				"id":1,
				"name":"Nirvana"
			},
			{
				"id":6,
				"name":"Pink Floyd"
			},
			{
				"id":5,
				"name":"Arctic Monkeys"
			}
		]
	}
	
#Get artist by id
	Method: GET
	Endpoint: /player-server/artist/get/1
	Response: 200 OK
	Response body:
	{
		"id":1,
		"name":"Nirvana"
	}
	
#Add artist
	Method: POST
	Endpoint: /player-server/artist/add
	Request body:
	{
		"name":"Greenskeeper"
	}
	Response: 201 Created
	Response body:
	{
		"id":7
		"name":"Greenskeeper"
	}
	
#Update artist
	Method: PUT
	Endpoint: /player-server/artist/update/7
	Request body:
	{
		"name":"Greenskeepers",
	}
	Response: 204 No Content
	
	Delete artist
	Method: DELETE
	Endpoint: /player-server/artist/delete/7
	Response: 204 No Content
	
##Genre

#Get all genres
	Method: GET
	Endpoint: /player-server/genre/get
	Response: 200 OK
	Response body:
	{
		artists:
		[
			{
				"id":1,
				"name":"Grunge"
			},
			{
				"id":7,
				"name":"Alternative"
			},
			{
				"id":8,
				"name":"Indie"
			}
		]
	}
	
#Get genre by id
	Method: GET
	Endpoint: /player-server/genre/get/1
	Response: 200 OK
	Response body:
	{
		"id":1,
		"name":"Grunge"
	}
	
#Add genre
	Method: POST
	Endpoint: /player-server/genre/add
	Request body:
	{
		"name":"Blues Rock"
	}
	Response: 201 Created
	Response body:
	{
		"id":17
		"name":"Blues Rock"
	}
	
#Update genre
	Method: PUT
	Endpoint: /player-server/artist/update/17
	Request body:
	{
		"name":"Blues Rock1",
	}
	Response: 204 No Content
	
#Delete genre
	Method: DELETE
	Endpoint: /player-server/genre/delete/17
	Response: 204 No Content
	
##Song
    
#Get all songs
    Method: GET
    Endpoint: /player-server/song/get
    Response: 200 OK
    Response body:
    {
    	"songs":
    	[
			{
				"id":1,
				"name":"Do I Wanna Know?",
				"year":2014,
				"genres":["Indie"],
				"artist_name":"Arctic Monkeys",
				"album_name":"AM",
				"track_number":1
			},
			{
				"id":2,
				"name":"R U Mine?",
				"year":2014,
				"genres":["Indie"],
				"artist_name":"Arctic Monkeys",
				"album_name":"AM",
				"track_number":2
			}
    	]
    }
    	
#Get song by id
    Method: GET
    Endpoint: /player-server/song/get/1
    Response: 200 OK
    Response body:
    {
		"id":1,
		"name":"Do I Wanna Know?",
		"year":2014,
		"genres":["Indie"],
		"artist_name":"Arctic Monkeys",
		"album_name":"AM",
		"track_number":1
    }
    	
#Add song
    Method: POST
    Endpoint: /player-server/song/add
    Request body: 
    song file
    Response: 201 Created
    Response body:
    {
		"id":3,
		"name":"Invaders Must Die",
		"year":2009,
		"genres":[" "],
		"artist_name":"The Prodigy",
		"album_name":"Invaders Must Die",
		"track_number":1
    }
    	
#Stream song
	Method: GET
    Endpoint: /player-server/song/stream/1
    Request body: 
    song file
    Response: 200 OK
    Produce: application/octet-stream
   