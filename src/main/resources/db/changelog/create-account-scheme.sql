--DROP TABLE account;

CREATE TABLE account (
    account_id      INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    number_account  VARCHAR(25)                 NOT NULL,
    opening_date    DATE                        NOT NULL,
    balance         FLOAT                       NOT NULL,
    owner           INT                         NOT NULL
);