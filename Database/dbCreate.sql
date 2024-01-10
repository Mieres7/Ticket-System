BEGIN;

-- Creación de las tablas

CREATE TABLE IF NOT EXISTS public.unit(
    id_unit SERIAL PRIMARY KEY,
    name_unit TEXT COLLATE pg_catalog."default",
    description_unit TEXT COLLATE pg_catalog."default");

CREATE TABLE IF NOT EXISTS public.category(
    id_category SERIAL PRIMARY KEY,
    faculty TEXT COLLATE pg_catalog."default",
    department TEXT COLLATE pg_catalog."default",
    topic TEXT COLLATE pg_catalog."default");

CREATE TABLE IF NOT EXISTS public.role(
    id_role SERIAL PRIMARY KEY,
    name_role TEXT COLLATE pg_catalog."default");

CREATE TABLE IF NOT EXISTS public.status(
    id_status SERIAL PRIMARY KEY,
    status INTEGER);

CREATE TABLE IF NOT EXISTS public.user(
    id_user SERIAL PRIMARY KEY,
    username TEXT UNIQUE COLLATE pg_catalog."default",
    password TEXT COLLATE pg_catalog."default",
    id_profile INTEGER,
    id_unit INTEGER,
    id_role INTEGER);

CREATE TABLE IF NOT EXISTS public.profile(
    id_profile SERIAL PRIMARY KEY,
    firstname TEXT COLLATE pg_catalog."default",
    middlename TEXT COLLATE pg_catalog."default",
    lastname TEXT COLLATE pg_catalog."default",
    email TEXT UNIQUE COLLATE pg_catalog."default",
    description_profile TEXT COLLATE pg_catalog."default",
    gender TEXT COLLATE pg_catalog."default",
    birthday DATE,
    picture_profile TEXT COLLATE pg_catalog."default");

CREATE TABLE IF NOT EXISTS public.ticket(
    id_ticket SERIAL PRIMARY KEY,
    subject TEXT COLLATE pg_catalog."default",
    description_ticket TEXT COLLATE pg_catalog."default",
    annotation TEXT COLLATE pg_catalog."default",
    creation_date DATE,
    last_update DATE,
    id_creator INTEGER,
    id_headship INTEGER,
    id_analyst INTEGER,
    id_category INTEGER,
    id_status INTEGER,
    id_unit INTEGER);

-- Creación de las relaciones

ALTER TABLE IF EXISTS public.user
    ADD CONSTRAINT "idProfileFK" FOREIGN KEY (id_profile)
    REFERENCES public.profile (id_profile) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.user
    ADD CONSTRAINT "idUnitFK" FOREIGN KEY (id_unit)
    REFERENCES public.unit (id_unit) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.user
    ADD CONSTRAINT "idRoleFK" FOREIGN KEY (id_role)
    REFERENCES public.role (id_role) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT "idCreatorFK" FOREIGN KEY (id_creator)
    REFERENCES public.user (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT "idHeadshipFK" FOREIGN KEY (id_headship)
    REFERENCES public.user (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT "idAnalystFK" FOREIGN KEY (id_analyst)
    REFERENCES public.user (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT "idCategoryFK" FOREIGN KEY (id_category)
    REFERENCES public.category (id_category) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT "idStatusFK" FOREIGN KEY (id_status)
    REFERENCES public.status (id_status) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT "idUnitFK" FOREIGN KEY (id_unit)
    REFERENCES public.unit (id_unit) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;

COMMIT;