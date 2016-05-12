--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: arlen
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    stylistid integer
);


ALTER TABLE clients OWNER TO arlen;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: arlen
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO arlen;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: arlen
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: arlen
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE stylists OWNER TO arlen;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: arlen
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO arlen;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: arlen
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: arlen
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: arlen
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: arlen
--

COPY clients (id, name, stylistid) FROM stdin;
52	Arlen	\N
53	Arlen	\N
54	Arlen	\N
55	Arlen	\N
56	Arlen	\N
57	Arlen	\N
58	Arlen	\N
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: arlen
--

SELECT pg_catalog.setval('clients_id_seq', 58, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: arlen
--

COPY stylists (id, name) FROM stdin;
11	Rick
12	Morty
13	Jerry
14	Beth
15	Summer
16	BirdPerson
17	Gearhead
18	Mr.PoopyButthole
19	Rick
20	Morty
21	Jerry
22	Beth
23	Summer
24	BirdPerson
25	Gearhead
26	Mr.PoopyButthole
27	Rick
28	Morty
29	Jerry
30	Beth
31	Summer
32	BirdPerson
33	Gearhead
34	Mr.PoopyButthole
35	Rick
36	Morty
37	Jerry
38	Beth
39	Summer
40	BirdPerson
41	Gearhead
42	Mr.PoopyButthole
43	Rick
44	Morty
45	Jerry
46	Beth
47	Summer
48	BirdPerson
49	Gearhead
50	Mr.PoopyButthole
51	Rick
52	Morty
53	Jerry
54	Beth
55	Summer
56	BirdPerson
57	Gearhead
58	Mr.PoopyButthole
59	Rick
60	Morty
61	Jerry
62	Beth
63	Summer
64	BirdPerson
65	Gearhead
66	Mr.PoopyButthole
67	Rick
68	Morty
69	Jerry
70	Beth
71	Summer
72	BirdPerson
73	Gearhead
74	Mr.PoopyButthole
75	Rick
76	Morty
77	Jerry
78	Beth
79	Summer
80	BirdPerson
81	Gearhead
82	Mr.PoopyButthole
83	Rick
84	Morty
85	Jerry
86	Beth
87	Summer
88	BirdPerson
89	Gearhead
90	Mr.PoopyButthole
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: arlen
--

SELECT pg_catalog.setval('stylists_id_seq', 90, true);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: arlen
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: arlen
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: arlen
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM arlen;
GRANT ALL ON SCHEMA public TO arlen;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

