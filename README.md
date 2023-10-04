# Fourthingsplus DB

Dette repo indeholder et forslag til løsning på opgaven:

- [Fourthingsplus](https://github.com/dat2Cph/content/blob/main/webstack/backend/fourthingsplus.md)

I denne udgave implementeres løsningen lidt ad gangen.

Databasen er oprettet med to tabeller i Postgres:

```sql
-- Database: fourthingsplus

-- DROP DATABASE IF EXISTS fourthingsplus;

CREATE DATABASE fourthingsplus
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
```
```sql
-- Table: public.user

-- DROP TABLE IF EXISTS public."user";

CREATE TABLE IF NOT EXISTS public."user"
(
    id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."user"
    OWNER to postgres;
```
```sql
-- Table: public.task

-- DROP TABLE IF EXISTS public.task;

CREATE TABLE IF NOT EXISTS public.task
(
    id integer NOT NULL DEFAULT nextval('task_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    done boolean NOT NULL DEFAULT false,
    user_id integer NOT NULL,
    CONSTRAINT task_pkey PRIMARY KEY (id),
    CONSTRAINT fk_task_user FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.task
    OWNER to postgres;
```

