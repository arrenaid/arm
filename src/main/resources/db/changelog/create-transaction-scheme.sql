--DROP TABLE transact;

CREATE TABLE transact (
    transaction_id      INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    amount              FLOAT                        NOT NULL,
    debit_account       INT[]                       NOT NULL,
    credit_account      INT[]                       NOT NULL,
    transaction_time    DATE                        NOT NULL
);