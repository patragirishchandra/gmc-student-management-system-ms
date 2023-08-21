CREATE SCHEMA gmc_studentmanagement;

-- Table: gmc_studentmanagement.student

CREATE TABLE IF NOT EXISTS gmc_studentmanagement.student
(
    stud_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    average bigint,
    dob date,
    first_nm character varying(255) COLLATE pg_catalog."default",
    gender character varying(255) COLLATE pg_catalog."default",
    last_nm character varying(255) COLLATE pg_catalog."default",
    marks1 bigint,
    marks2 bigint,
    marks3 bigint,
    result character varying(255) COLLATE pg_catalog."default",
    sec character varying(255) COLLATE pg_catalog."default",
    total_mrk bigint,
    CONSTRAINT student_pkey PRIMARY KEY (stud_id)
);