--DROP TABLE account;

CREATE TABLE account (
    account_id      INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    namber          INT[]                       NOT NULL,
    opening_date    DATE                        NOT NULL,
    balance         FLOAT                        NOT NULL
);