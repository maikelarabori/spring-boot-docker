#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d "$POSTGRES_DB" <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE "$POSTGRES_DB" TO "$POSTGRES_USER";

CREATE TABLE if not exists public.tokens
(
    id uuid NOT NULL,
    user_id uuid,
    type integer,
    used boolean NOT NULL DEFAULT false,
    active boolean NOT NULL DEFAULT false,
    inserted_at timestamp(0) without time zone NOT NULL,
    updated_at timestamp(0) without time zone NOT NULL,
    CONSTRAINT tokens_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tokens
    OWNER to "$POSTGRES_USER";

CREATE TABLE public.users
(
    id uuid NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    year_of_birth integer NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    persistent boolean NOT NULL DEFAULT false,
    last_token uuid NOT NULL,
    status integer NOT NULL,
    deleted boolean NOT NULL DEFAULT false,
    inserted_at timestamp(0) without time zone NOT NULL,
    updated_at timestamp(0) without time zone NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_username_key UNIQUE (username)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to "$POSTGRES_USER";

-- Index: users_email_index

-- DROP INDEX public.users_email_index;

CREATE UNIQUE INDEX users_email_index
    ON public.users USING btree
    (email COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: users_username_index

-- DROP INDEX public.users_username_index;

CREATE UNIQUE INDEX users_username_index
    ON public.users USING btree
    (username COLLATE pg_catalog."default")
    TABLESPACE pg_default;
EOSQL
