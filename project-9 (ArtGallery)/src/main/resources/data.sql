INSERT INTO ARTIST (id, name, genre) VALUES
(1, 'Leonardo da Vinci', 'Renaissance'),
(2, 'Vincent van Gogh', 'Post-Impressionism'),
(3, 'Pablo Picass', 'Cubism'),
(4, 'Edward Hopper', 'American Modernism');

INSERT INTO ART (id, title, theme, artistId) VALUES
(1, 'The Flight Study', 'Studies of Bird Wings', 1),
(2, 'Mona Lisa 2.0', 'Renaissance Portrait', 1),
(3, 'Starry Countryside', 'Night Landscape', 2),
(4, 'Sunflower Impressions', 'Floral', 2),
(5, 'Cubist Self-Portrait', 'Abstract Portrait', 3),
(6, 'Barcelona Abstracted', 'City Landscape', 3),
(7, 'Downtown Solitude', 'Urban Scene', 4),
(8, 'Night Cafe Redux', 'Modernist Interior', 4);

INSERT INTO GALLERY (id, name, location) VALUES
(1, 'Louvre Museum', 'Paris'),
(2, 'Van Gogh Museum', 'Amsterdam'),
(3, 'Museo Picasso', 'Barcelona'),
(4, 'Whitney Museum of American Art', 'New York');

INSERT INTO ARTIST_GALLERY (artistId, galleryId) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(3, 4),
(4, 4);