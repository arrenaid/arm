--DROP TABLE client;

CREATE TABLE client (
    client_id       INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    last_name       VARCHAR(100)                 NOT NULL,
    first_name      VARCHAR(100)                 NOT NULL,
    middle_name     VARCHAR(100)                 NOT NULL,
    date_of_birth   DATE                         NOT NULL
);