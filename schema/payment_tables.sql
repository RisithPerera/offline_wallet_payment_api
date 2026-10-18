CREATE TABLE "client" (
    id            uuid        PRIMARY KEY,
    created_at    timestamptz NOT NULL DEFAULT now(),
    username      text        NOT NULL UNIQUE,
    password_hash text        NOT NULL,
    password_salt text        NOT NULL,
    public_key    bytea       NOT NULL,
    key_status    text        NOT NULL,
    status        text        NOT NULL
);

CREATE TABLE "merchant" (
    id           uuid        PRIMARY KEY,
    created_at   timestamptz NOT NULL DEFAULT now(),
    name         text        NOT NULL,
    category     text,
    address      text,
    payout_iban  text,
    status       text        NOT NULL
);

CREATE TABLE "wallet" (
    id           uuid          PRIMARY KEY,
    created_at   timestamptz   NOT NULL DEFAULT now(),
    owner_type   text          NOT NULL,
    owner_id     uuid,
    wallet_type  text          NOT NULL,
    name         text          NOT NULL,
    currency     text          NOT NULL DEFAULT 'EUR',
    balance      numeric(10,2) NOT NULL DEFAULT 0,
    status       text          NOT NULL
);

CREATE TABLE "transaction" (
    id              uuid          PRIMARY KEY,
    created_at      timestamptz   NOT NULL DEFAULT now(),
    updated_at      timestamptz   NOT NULL DEFAULT now(),
    type            text          NOT NULL,
    status          text          NOT NULL DEFAULT 'pending',
    user_id         uuid          REFERENCES client (id),
    from_wallet_id  uuid          REFERENCES wallet (id),
    to_wallet_id    uuid          REFERENCES wallet (id),
    amount          numeric(10,2) NOT NULL,
    vat_rate        numeric(4,2)  NOT NULL DEFAULT 0,
    currency        text          NOT NULL DEFAULT 'EUR',
    failure_reason  text
);

CREATE TABLE "ledger" (
    id          uuid          PRIMARY KEY,
    created_at  timestamptz   NOT NULL DEFAULT now(),
    trans_id    uuid          NOT NULL REFERENCES transaction (id),
    ref_id      uuid          REFERENCES transaction (id),
    wallet_id   uuid          NOT NULL REFERENCES wallet (id),
    amount      numeric(10,2) NOT NULL,
    vat         numeric(10,2) NOT NULL DEFAULT 0,
    vat_rate    numeric(4,2)  NOT NULL DEFAULT 0,
    currency    text          NOT NULL DEFAULT 'EUR',
    type        text          NOT NULL
);