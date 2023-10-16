CREATE TABLE mushroom_location (
                                    id SERIAL PRIMARY KEY,
                                    coordinates GEOMETRY(Point, 4326),
                                    description TEXT
);