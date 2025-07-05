CREATE TABLE IF NOT EXISTS COUNTRY(
    countryId INT PRIMARY KEY AUTO_INCREMENT,
    countryName TEXT,
    currency TEXT,
    population INT,
    latitude TEXT,
    longitude TEXT
);

CREATE TABLE IF NOT EXISTS CITY(
    cityId INT PRIMARY KEY AUTO_INCREMENT,
    cityName TEXT,
    population INT,
    latitude TEXT,
    longitude TEXT,
    countryId INT,
    FOREIGN KEY(countryId) REFERENCES COUNTRY(countryId) ON DELETE CASCADE
);