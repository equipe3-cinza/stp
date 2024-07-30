

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
