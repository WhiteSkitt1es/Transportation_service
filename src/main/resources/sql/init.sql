DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(128),
    username VARCHAR(64)  NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    role     VARCHAR(64) DEFAULT 'USER'
);

DROP TABLE IF EXISTS carriers;
CREATE TABLE IF NOT EXISTS carriers
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(64) NOT NULL,
    phone_number VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS routes;
CREATE TABLE IF NOT EXISTS routes
(
    id                BIGSERIAL PRIMARY KEY,
    departure_point   VARCHAR(64) NOT NULL,
    destination_point VARCHAR(64) NOT NULL,
    id_carrier        BIGINT      NOT NULL REFERENCES carriers (id)
);

DROP TABLE IF EXISTS tickets;
CREATE TABLE IF NOT EXISTS tickets
(
    id             BIGSERIAL PRIMARY KEY,
    seat_number    INT       NOT NULL,
    time           TIMESTAMP NOT NULL,
    price          DECIMAL   NOT NULL,
    passenger_name VARCHAR(128) DEFAULT NULL,
    routes_id      BIGINT    NOT NULL REFERENCES routes (id)
);

