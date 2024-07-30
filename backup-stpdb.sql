--
-- PostgreSQL database dump
--

-- Dumped from database version 13.15 (Debian 13.15-1.pgdg120+1)
-- Dumped by pg_dump version 13.15 (Debian 13.15-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: documento_transferencia; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.documento_transferencia (
    id bigint NOT NULL,
    procedimentos_acondicionamento character varying(255)[],
    procedimentos_recebimento character varying(255)[]
);


ALTER TABLE public.documento_transferencia OWNER TO admin;

--
-- Name: documento_transferencia_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.documento_transferencia ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.documento_transferencia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: documento_transferencia_medicamento; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.documento_transferencia_medicamento (
    documento_transferencia_id bigint NOT NULL,
    medicamento_id bigint NOT NULL
);


ALTER TABLE public.documento_transferencia_medicamento OWNER TO admin;

--
-- Name: endereco; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    bairro character varying(50) NOT NULL,
    cep character(8) NOT NULL,
    cidade character varying(50) NOT NULL,
    complemento character varying(100),
    estado character(8) NOT NULL,
    rua character varying(100) NOT NULL
);


ALTER TABLE public.endereco OWNER TO admin;

--
-- Name: endereco_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.endereco ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.endereco_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: especialidade; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.especialidade (
    id bigint NOT NULL,
    descricao character varying(255),
    nome character varying(255) NOT NULL
);


ALTER TABLE public.especialidade OWNER TO admin;

--
-- Name: especialidade_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.especialidade ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.especialidade_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: medicamento; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.medicamento (
    id bigint NOT NULL,
    descricao character varying(255),
    nome character varying(255) NOT NULL,
    principio_ativo character varying(255)
);


ALTER TABLE public.medicamento OWNER TO admin;

--
-- Name: medicamento_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.medicamento ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.medicamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: medicamento_prescrito; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.medicamento_prescrito (
    id bigint NOT NULL,
    dosagem double precision,
    posologia character varying(255),
    unidade_dosagem character varying(255),
    via_administracao character varying(255),
    medicamento_id bigint NOT NULL,
    CONSTRAINT medicamento_prescrito_unidade_dosagem_check CHECK (((unidade_dosagem)::text = ANY ((ARRAY['mg'::character varying, 'µg'::character varying, 'mL'::character varying, 'cp'::character varying, 'UI'::character varying])::text[])))
);


ALTER TABLE public.medicamento_prescrito OWNER TO admin;

--
-- Name: medicamento_prescrito_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.medicamento_prescrito ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.medicamento_prescrito_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: medico; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.medico (
    id bigint NOT NULL,
    crm character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    papel smallint NOT NULL,
    telefone character varying(255) NOT NULL,
    especialidade_id bigint NOT NULL,
    unidade_hospitalar_id bigint NOT NULL,
    CONSTRAINT medico_papel_check CHECK (((papel >= 0) AND (papel <= 1)))
);


ALTER TABLE public.medico OWNER TO admin;

--
-- Name: medico_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.medico ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.medico_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: paciente; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.paciente (
    id bigint NOT NULL,
    cpf character varying(255) NOT NULL,
    email character varying(255),
    name character varying(255) NOT NULL,
    telefone character varying(255),
    tipo_sanguineo smallint NOT NULL,
    endereco_id bigint,
    prontuario_id bigint NOT NULL,
    CONSTRAINT paciente_tipo_sanguineo_check CHECK (((tipo_sanguineo >= 0) AND (tipo_sanguineo <= 7)))
);


ALTER TABLE public.paciente OWNER TO admin;

--
-- Name: paciente_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.paciente ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.paciente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: prontuario; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.prontuario (
    id bigint NOT NULL,
    classificacao character varying(255),
    CONSTRAINT prontuario_classificacao_check CHECK (((classificacao)::text = ANY ((ARRAY['PRIMARIA'::character varying, 'SECUNDARIA'::character varying, 'TERCIARIA'::character varying])::text[])))
);


ALTER TABLE public.prontuario OWNER TO admin;

--
-- Name: prontuario_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.prontuario ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.prontuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: prontuario_medicamento_prescrito; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.prontuario_medicamento_prescrito (
    prontuario_id bigint NOT NULL,
    medicamento_prescrito_id bigint NOT NULL
);


ALTER TABLE public.prontuario_medicamento_prescrito OWNER TO admin;

--
-- Name: role; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO admin;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.role ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: solicitacao; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.solicitacao (
    id bigint NOT NULL,
    horario_solicitacao timestamp(6) without time zone NOT NULL,
    motivo character varying(255),
    documento_id bigint NOT NULL,
    medico_id bigint NOT NULL,
    paciente_id bigint NOT NULL
);


ALTER TABLE public.solicitacao OWNER TO admin;

--
-- Name: solicitacao_especialidade; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.solicitacao_especialidade (
    solicitacao_id bigint NOT NULL,
    especialidade_id bigint NOT NULL
);


ALTER TABLE public.solicitacao_especialidade OWNER TO admin;

--
-- Name: solicitacao_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.solicitacao ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.solicitacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: transferencia; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.transferencia (
    id bigint NOT NULL,
    distancia double precision,
    horario_previsto_chegada timestamp(6) without time zone,
    horario_saida timestamp(6) without time zone,
    meio_transporte character varying(20),
    destino_id bigint NOT NULL,
    documento_transferencia_id bigint NOT NULL,
    medico_destino_id bigint,
    medico_origem_id bigint NOT NULL,
    medico_regulador_id bigint NOT NULL,
    origem_id bigint NOT NULL,
    paciente_id bigint NOT NULL,
    solicitacao_id bigint NOT NULL,
    CONSTRAINT transferencia_meio_transporte_check CHECK (((meio_transporte)::text = ANY ((ARRAY['AMBULANCIA'::character varying, 'HELICOPTERO'::character varying, 'AVIAO'::character varying, 'EVTOL'::character varying])::text[])))
);


ALTER TABLE public.transferencia OWNER TO admin;

--
-- Name: transferencia_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.transferencia ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.transferencia_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: unidade_hospitalar; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.unidade_hospitalar (
    id bigint NOT NULL,
    dados_pessoal character varying(255) NOT NULL,
    disponibilidade_leitos integer NOT NULL,
    email character varying(255) NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    telefone character varying(255) NOT NULL,
    temuti boolean NOT NULL,
    endereco_id bigint NOT NULL
);


ALTER TABLE public.unidade_hospitalar OWNER TO admin;

--
-- Name: unidade_hospitalar_especialidades; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.unidade_hospitalar_especialidades (
    unidade_hospitalar_id bigint NOT NULL,
    especialidade_id bigint NOT NULL
);


ALTER TABLE public.unidade_hospitalar_especialidades OWNER TO admin;

--
-- Name: unidade_hospitalar_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.unidade_hospitalar ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.unidade_hospitalar_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO admin;

--
-- Name: users; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO admin;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: documento_transferencia; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.documento_transferencia (id, procedimentos_acondicionamento, procedimentos_recebimento) FROM stdin;
\.


--
-- Data for Name: documento_transferencia_medicamento; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.documento_transferencia_medicamento (documento_transferencia_id, medicamento_id) FROM stdin;
\.


--
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.endereco (id, bairro, cep, cidade, complemento, estado, rua) FROM stdin;
1	Centro	30112233	Belo Horizonte	Sala 502	MG      	Avenida Afonso Pena
3	Centro	12345678	São Paulo	Apto 101	SP      	Rua das Flores
4	Jardim Olímpico	87654321	Rio de Janeiro	Sala 202	RJ      	Avenida dos Esportes
5	Jardim Esperança	54321098	Belo Horizonte	Bloco C	MG      	Rua dos Bebês
6	Parque Médico	13579246	Porto Alegre	Torre 2, 5º andar	RS      	Avenida da Saúde
7	Copacabana	22334455	Rio de Janeiro	Casa 2	RJ      	Rua Copacabana
8	Bela Vista	04567890	São Paulo	Apto 1001	SP      	Avenida Paulista
\.


--
-- Data for Name: especialidade; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.especialidade (id, descricao, nome) FROM stdin;
1	Especialidade médica que trata doenças do coração.	Cardiologia
2	Especialidade médica que trata de lesões e doenças do sistema musculoesquelético.	Ortopedia
3	Especialidade médica dedicada ao cuidado da mulher durante a gravidez, parto e pós-parto.	Obstetrícia
4	Especialidade médica que estuda e trata o câncer em suas diversas formas.	Oncologia
5	Especialidade médica que estuda e trata Crianças.	Pediatria
\.


--
-- Data for Name: medicamento; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.medicamento (id, descricao, nome, principio_ativo) FROM stdin;
1	Hormônio para controle de glicemia	Insulina	Insulina humana
2	Inibidor da ECA	Enalapril	Maleato de enalapril
3	Estatina para controle de colesterol	Sinvastatina	Sinvastatina
4	Anti-hipertensivo	Losartana	Losartana potássica
5	Antidiabético	Metformina	Cloridrato de metformina
6	Inibidor da bomba de prótons	Omeprazol	Omeprazol
\.


--
-- Data for Name: medicamento_prescrito; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.medicamento_prescrito (id, dosagem, posologia, unidade_dosagem, via_administracao, medicamento_id) FROM stdin;
1	10	3 vezes ao dia	UI	subcutânea	1
2	10	2 vezes ao dia	mg	oral	2
3	20	1 vez ao dia	mg	oral	3
4	50	2 vezes ao dia	mg	oral	4
5	500	3 vezes ao dia	mg	oral	5
6	20	1 vez ao dia	mg	oral	6
\.


--
-- Data for Name: medico; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.medico (id, crm, nome, papel, telefone, especialidade_id, unidade_hospitalar_id) FROM stdin;
\.


--
-- Data for Name: paciente; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.paciente (id, cpf, email, name, telefone, tipo_sanguineo, endereco_id, prontuario_id) FROM stdin;
1	04753549070	ana.oliveira@email.com	Ana Oliveira	31912345678	3	1	1
2	43705563173	joao.santos@email.com	João Santos	21976543210	1	7	2
3	45672187870	maria.silva@email.com	Maria Silva	11987654321	2	8	3
\.


--
-- Data for Name: prontuario; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.prontuario (id, classificacao) FROM stdin;
1	TERCIARIA
2	PRIMARIA
3	PRIMARIA
\.


--
-- Data for Name: prontuario_medicamento_prescrito; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.prontuario_medicamento_prescrito (prontuario_id, medicamento_prescrito_id) FROM stdin;
1	1
1	2
1	3
2	4
2	5
3	6
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.role (id, name) FROM stdin;
\.


--
-- Data for Name: solicitacao; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.solicitacao (id, horario_solicitacao, motivo, documento_id, medico_id, paciente_id) FROM stdin;
\.


--
-- Data for Name: solicitacao_especialidade; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.solicitacao_especialidade (solicitacao_id, especialidade_id) FROM stdin;
\.


--
-- Data for Name: transferencia; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.transferencia (id, distancia, horario_previsto_chegada, horario_saida, meio_transporte, destino_id, documento_transferencia_id, medico_destino_id, medico_origem_id, medico_regulador_id, origem_id, paciente_id, solicitacao_id) FROM stdin;
\.


--
-- Data for Name: unidade_hospitalar; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.unidade_hospitalar (id, dados_pessoal, disponibilidade_leitos, email, latitude, longitude, telefone, temuti, endereco_id) FROM stdin;
2	Hospital de referência em cardiologia.	10	contato@hospital.com	-23.55052	-46.633308	1122334455	t	3
3	Clínica especializada em tratamentos ortopédicos e esportivos.	5	contato@clinicaortopedica.com	-22.906847	-43.172897	3344556677	f	4
4	Maternidade com atendimento humanizado e foco no bem-estar da mãe e do bebê.	15	contato@maternidadefeliz.com	-19.917299	-43.934559	5566778899	t	5
5	Centro de tratamento oncológico com tecnologia de ponta e equipe multidisciplinar.	20	contato@centrooncologico.com	-30.034647	-51.217658	7788990011	t	6
\.


--
-- Data for Name: unidade_hospitalar_especialidades; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.unidade_hospitalar_especialidades (unidade_hospitalar_id, especialidade_id) FROM stdin;
2	1
3	2
4	3
5	4
\.


--
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.user_roles (user_id, role_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.users (id, enabled, password, username) FROM stdin;
1	t	$2a$10$4/.l3nbySMoS80P.cpvT6.SVMzRRNRn0FfnxXLxOovz16jbVb/yly	admin
\.


--
-- Name: documento_transferencia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.documento_transferencia_id_seq', 1, false);


--
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.endereco_id_seq', 9, true);


--
-- Name: especialidade_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.especialidade_id_seq', 5, true);


--
-- Name: medicamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.medicamento_id_seq', 8, true);


--
-- Name: medicamento_prescrito_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.medicamento_prescrito_id_seq', 6, true);


--
-- Name: medico_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.medico_id_seq', 1, false);


--
-- Name: paciente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.paciente_id_seq', 3, true);


--
-- Name: prontuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.prontuario_id_seq', 4, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.role_id_seq', 1, false);


--
-- Name: solicitacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.solicitacao_id_seq', 1, false);


--
-- Name: transferencia_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.transferencia_id_seq', 1, false);


--
-- Name: unidade_hospitalar_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.unidade_hospitalar_id_seq', 5, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- Name: documento_transferencia documento_transferencia_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.documento_transferencia
    ADD CONSTRAINT documento_transferencia_pkey PRIMARY KEY (id);


--
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- Name: especialidade especialidade_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.especialidade
    ADD CONSTRAINT especialidade_pkey PRIMARY KEY (id);


--
-- Name: medicamento medicamento_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medicamento
    ADD CONSTRAINT medicamento_pkey PRIMARY KEY (id);


--
-- Name: medicamento_prescrito medicamento_prescrito_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medicamento_prescrito
    ADD CONSTRAINT medicamento_prescrito_pkey PRIMARY KEY (id);


--
-- Name: medico medico_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT medico_pkey PRIMARY KEY (id);


--
-- Name: paciente paciente_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (id);


--
-- Name: prontuario prontuario_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.prontuario
    ADD CONSTRAINT prontuario_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: solicitacao solicitacao_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.solicitacao
    ADD CONSTRAINT solicitacao_pkey PRIMARY KEY (id);


--
-- Name: transferencia transferencia_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT transferencia_pkey PRIMARY KEY (id);


--
-- Name: medicamento uk4keana7obxxwlq4ic44mlu5bt; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medicamento
    ADD CONSTRAINT uk4keana7obxxwlq4ic44mlu5bt UNIQUE (nome);


--
-- Name: unidade_hospitalar uk5lw3gb2br1v00ev7mp7pet5ob; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.unidade_hospitalar
    ADD CONSTRAINT uk5lw3gb2br1v00ev7mp7pet5ob UNIQUE (endereco_id);


--
-- Name: paciente uk6s9loeiqkady7343u6od04jc6; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT uk6s9loeiqkady7343u6od04jc6 UNIQUE (prontuario_id);


--
-- Name: transferencia ukfful1ll299jia9to65ik37asw; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT ukfful1ll299jia9to65ik37asw UNIQUE (solicitacao_id);


--
-- Name: paciente ukfvlo8m5kqpr7knbyw4rjyer2s; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT ukfvlo8m5kqpr7knbyw4rjyer2s UNIQUE (cpf);


--
-- Name: medico ukgchpdsdnxfv1vuayn3tkspvpj; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT ukgchpdsdnxfv1vuayn3tkspvpj UNIQUE (crm);


--
-- Name: solicitacao ukmwon5udcfr43vd6mki1lbprfb; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.solicitacao
    ADD CONSTRAINT ukmwon5udcfr43vd6mki1lbprfb UNIQUE (documento_id);


--
-- Name: transferencia ukqm21tisixg0xgs402op2vlw6t; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT ukqm21tisixg0xgs402op2vlw6t UNIQUE (documento_transferencia_id);


--
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- Name: especialidade ukrpkedtjcgmd2qw3euipqv5fhu; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.especialidade
    ADD CONSTRAINT ukrpkedtjcgmd2qw3euipqv5fhu UNIQUE (nome);


--
-- Name: paciente ukt03xff2qg2dnq4vd538qt9vsc; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT ukt03xff2qg2dnq4vd538qt9vsc UNIQUE (endereco_id);


--
-- Name: unidade_hospitalar unidade_hospitalar_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.unidade_hospitalar
    ADD CONSTRAINT unidade_hospitalar_pkey PRIMARY KEY (id);


--
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: transferencia fk53oysk5w92lmi38mln25cpyu; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fk53oysk5w92lmi38mln25cpyu FOREIGN KEY (paciente_id) REFERENCES public.paciente(id);


--
-- Name: documento_transferencia_medicamento fk5fymqmt37iuma9nbo5buleiyp; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.documento_transferencia_medicamento
    ADD CONSTRAINT fk5fymqmt37iuma9nbo5buleiyp FOREIGN KEY (documento_transferencia_id) REFERENCES public.documento_transferencia(id);


--
-- Name: prontuario_medicamento_prescrito fk5u2teuokli8k2hfgdeuk5g4jj; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.prontuario_medicamento_prescrito
    ADD CONSTRAINT fk5u2teuokli8k2hfgdeuk5g4jj FOREIGN KEY (medicamento_prescrito_id) REFERENCES public.medicamento_prescrito(id);


--
-- Name: unidade_hospitalar_especialidades fk5x97ljawo49nlceams0garlgu; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.unidade_hospitalar_especialidades
    ADD CONSTRAINT fk5x97ljawo49nlceams0garlgu FOREIGN KEY (unidade_hospitalar_id) REFERENCES public.unidade_hospitalar(id);


--
-- Name: medico fka38dmbx2nluc8fuh8dfw0ngf1; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT fka38dmbx2nluc8fuh8dfw0ngf1 FOREIGN KEY (unidade_hospitalar_id) REFERENCES public.unidade_hospitalar(id);


--
-- Name: documento_transferencia_medicamento fkb2r12je7sr44a1uwaglv3av1i; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.documento_transferencia_medicamento
    ADD CONSTRAINT fkb2r12je7sr44a1uwaglv3av1i FOREIGN KEY (medicamento_id) REFERENCES public.medicamento(id);


--
-- Name: solicitacao_especialidade fkdt9gyxi86y06b3sgujanqvwmp; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.solicitacao_especialidade
    ADD CONSTRAINT fkdt9gyxi86y06b3sgujanqvwmp FOREIGN KEY (solicitacao_id) REFERENCES public.solicitacao(id);


--
-- Name: transferencia fkdwrwrvchihqq6o3muidu5yvmb; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fkdwrwrvchihqq6o3muidu5yvmb FOREIGN KEY (medico_regulador_id) REFERENCES public.medico(id);


--
-- Name: user_roles fkhfh9dx7w3ubf1co1vdev94g3f; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: transferencia fkip2kreeti9mgf4tg4gyoxfvca; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fkip2kreeti9mgf4tg4gyoxfvca FOREIGN KEY (medico_origem_id) REFERENCES public.medico(id);


--
-- Name: solicitacao fkkbh3bfkb2iiipiwfs2uld4obv; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.solicitacao
    ADD CONSTRAINT fkkbh3bfkb2iiipiwfs2uld4obv FOREIGN KEY (documento_id) REFERENCES public.documento_transferencia(id);


--
-- Name: transferencia fkkkvir0t3qtb9jas4rtqa8ukak; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fkkkvir0t3qtb9jas4rtqa8ukak FOREIGN KEY (origem_id) REFERENCES public.unidade_hospitalar(id);


--
-- Name: medico fkm4iro8s8g0v1rxsgrkb10ya27; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT fkm4iro8s8g0v1rxsgrkb10ya27 FOREIGN KEY (especialidade_id) REFERENCES public.especialidade(id);


--
-- Name: unidade_hospitalar fkmg2wlgd9xoh8o4g986pufo84m; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.unidade_hospitalar
    ADD CONSTRAINT fkmg2wlgd9xoh8o4g986pufo84m FOREIGN KEY (endereco_id) REFERENCES public.endereco(id);


--
-- Name: paciente fkmibfawkbype9v98sv6vhh3015; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT fkmibfawkbype9v98sv6vhh3015 FOREIGN KEY (prontuario_id) REFERENCES public.prontuario(id);


--
-- Name: unidade_hospitalar_especialidades fkmkqo86gg73iftpn5qt7opkund; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.unidade_hospitalar_especialidades
    ADD CONSTRAINT fkmkqo86gg73iftpn5qt7opkund FOREIGN KEY (especialidade_id) REFERENCES public.especialidade(id);


--
-- Name: solicitacao fkmuvrw4451a9v0xh6u7xkdbf2l; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.solicitacao
    ADD CONSTRAINT fkmuvrw4451a9v0xh6u7xkdbf2l FOREIGN KEY (paciente_id) REFERENCES public.paciente(id);


--
-- Name: transferencia fknoinduf39l9w4vsgw5dt7crwd; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fknoinduf39l9w4vsgw5dt7crwd FOREIGN KEY (medico_destino_id) REFERENCES public.medico(id);


--
-- Name: solicitacao fknp7uaump8ry1me7er45vvus8c; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.solicitacao
    ADD CONSTRAINT fknp7uaump8ry1me7er45vvus8c FOREIGN KEY (medico_id) REFERENCES public.medico(id);


--
-- Name: solicitacao_especialidade fko6y6jctntruq86rlbr2sc8t0; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.solicitacao_especialidade
    ADD CONSTRAINT fko6y6jctntruq86rlbr2sc8t0 FOREIGN KEY (especialidade_id) REFERENCES public.especialidade(id);


--
-- Name: prontuario_medicamento_prescrito fkp6k8s4vpg07v5n89v9g5nswus; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.prontuario_medicamento_prescrito
    ADD CONSTRAINT fkp6k8s4vpg07v5n89v9g5nswus FOREIGN KEY (prontuario_id) REFERENCES public.prontuario(id);


--
-- Name: transferencia fkpfpa4vsvrjts6fb57b55xyhut; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fkpfpa4vsvrjts6fb57b55xyhut FOREIGN KEY (destino_id) REFERENCES public.unidade_hospitalar(id);


--
-- Name: paciente fkpus64vtl67yxnw2kimogd7abx; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT fkpus64vtl67yxnw2kimogd7abx FOREIGN KEY (endereco_id) REFERENCES public.endereco(id);


--
-- Name: medicamento_prescrito fkqfpkj2sxtkx2uy6pnkk6ymqyw; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.medicamento_prescrito
    ADD CONSTRAINT fkqfpkj2sxtkx2uy6pnkk6ymqyw FOREIGN KEY (medicamento_id) REFERENCES public.medicamento(id);


--
-- Name: user_roles fkrhfovtciq1l558cw6udg0h0d3; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkrhfovtciq1l558cw6udg0h0d3 FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: transferencia fkro0ju21k3dyjwvxlmnxqxp8o2; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fkro0ju21k3dyjwvxlmnxqxp8o2 FOREIGN KEY (solicitacao_id) REFERENCES public.solicitacao(id);


--
-- Name: transferencia fktgxefl7hxa7yde4tgnqvwunhg; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.transferencia
    ADD CONSTRAINT fktgxefl7hxa7yde4tgnqvwunhg FOREIGN KEY (documento_transferencia_id) REFERENCES public.documento_transferencia(id);


--
-- PostgreSQL database dump complete
--

