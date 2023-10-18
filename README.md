This is app for maintaining mushroom locations. You can add, delete, modify and query mushroom location.  A location is characterized by name, location coordinates and description. The aim is to share inforamtion in free text what mushrooms can be found and  in that place. Aplication uses GeoJSON format as input-output for its services.
Adding, deleting, modifying are done one object at a time. 
Data is read and saved from the PostgreSQL database. The services are free for everyone to use, authentication is not implemented.

Development plans for phase 2 is to create a web application where it is possible to use in the previous stage created services to display existing mushroom locations on a map and add new locations. 
The map will be interactive - zoomable in and out, movable and limited to Estonia and its surroundings. The map will have a publicly available base map (Land Board, OpenStreetMap, etc.) as a background.

Initial view displays all database locations on the map. 
Adding a new location is done by clicking on the desired place on the map with the mouse.