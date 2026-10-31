CREATE TABLE "terminals" (
    id                  uuid        PRIMARY KEY DEFAULT gen_random_uuid(),
    merchant_id         uuid        NOT NULL REFERENCES merchants (id),
    name                text        NOT NULL,
    cert_serial_number  text        NOT NULL UNIQUE,
    status              text        NOT NULL,
    created_at          timestamptz NOT NULL DEFAULT now(),
    updated_at          timestamptz NOT NULL DEFAULT now()
);