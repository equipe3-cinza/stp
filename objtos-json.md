---
tags: ufg, fullstack, stp
---

# STP objetos json

### unidadeHospitalar / endereço / especialidade
```json=
{
    "telefone": "1122334455",
    "email": "contato@hospital.com",
    "endereco": {
      "cep": "12345678",
      "rua": "Rua das Flores",
      "complemento": "Apto 101",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "estado": "SP"
    },
    "dadosPessoal": "Hospital de referência em cardiologia.",
    "latitude": -23.550520,
    "longitude": -46.633308,
    "disponibilidadeLeitos": 10,
    "especialidades": [
      {
        "nome": "Cardiologia",
        "descricao": "Especialidade médica que trata doenças do coração."
      }
    ],
    "temUTI": true
  }
  ```
  ```json=
  {
  "telefone": "3344556677",
  "email": "contato@clinicaortopedica.com",
  "endereco": {
    "cep": "87654321",
    "rua": "Avenida dos Esportes",
    "complemento": "Sala 202",
    "bairro": "Jardim Olímpico",
    "cidade": "Rio de Janeiro",
    "estado": "RJ"
  },
  "dadosPessoal": "Clínica especializada em tratamentos ortopédicos e esportivos.",
  "latitude": -22.906847,
  "longitude": -43.172897,
  "disponibilidadeLeitos": 5,
  "especialidades": [
    {
      "nome": "Ortopedia",
      "descricao": "Especialidade médica que trata de lesões e doenças do sistema musculoesquelético."
    }
  ],
  "temUTI": false
}
```
```json=
{
  "telefone": "5566778899",
  "email": "contato@maternidadefeliz.com",
  "endereco": {
    "cep": "54321098",
    "rua": "Rua dos Bebês",
    "complemento": "Bloco C",
    "bairro": "Jardim Esperança",
    "cidade": "Belo Horizonte",
    "estado": "MG"
  },
  "dadosPessoal": "Maternidade com atendimento humanizado e foco no bem-estar da mãe e do bebê.",
  "latitude": -19.917299,
  "longitude": -43.934559,
  "disponibilidadeLeitos": 15,
  "especialidades": [
    {
      "nome": "Obstetrícia",
      "descricao": "Especialidade médica dedicada ao cuidado da mulher durante a gravidez, parto e pós-parto."
    }
  ],
  "temUTI": true
}
```
```json=
{
  "telefone": "7788990011",
  "email": "contato@centrooncologico.com",
  "endereco": {
    "cep": "13579246",
    "rua": "Avenida da Saúde",
    "complemento": "Torre 2, 5º andar",
    "bairro": "Parque Médico",
    "cidade": "Porto Alegre",
    "estado": "RS"
  },
  "dadosPessoal": "Centro de tratamento oncológico com tecnologia de ponta e equipe multidisciplinar.",
  "latitude": -30.034647,
  "longitude": -51.217658,
  "disponibilidadeLeitos": 20,
  "especialidades": [
    {
      "nome": "Oncologia",
      "descricao": "Especialidade médica que estuda e trata o câncer em suas diversas formas."
    }
  ],
  "temUTI": true
}
```

## PACIENTES / prontuario / medicamento

```json=
{
    "cpf": "43705563173",
    "name": "João Santos",
    "telefone": "21976543210",
    "email": "joao.santos@email.com",
    "endereco": {
      "cep": "22334455",
      "rua": "Rua Copacabana",
      "complemento": "Casa 2",
      "bairro": "Copacabana",
      "cidade": "Rio de Janeiro",
      "estado": "RJ"
    },
    "tipoSanguineo": "A_NEGATIVO",
    "prontuario": {
      "classificacao": "PRIMARIA",
      "medicamentosPrescritos": [
        {
          "medicamento": {
            "nome": "Losartana",
            "principioAtivo": "Losartana potássica",
            "descricao": "Anti-hipertensivo"
          },
          "posologia": "2 vezes ao dia",
          "dosagem": 50,
          "unidadeDosagem": "mg",
          "viaAdministracao": "oral"
        },
        {
          "medicamento": {
            "nome": "Metformina",
            "principioAtivo": "Cloridrato de metformina",
            "descricao": "Antidiabético"
          },
          "posologia": "3 vezes ao dia",
          "dosagem": 500,
          "unidadeDosagem": "mg",
          "viaAdministracao": "oral"
        }
      ]
    }
}
```

```json=
{
    "cpf": "45672187870",
    "name": "Maria Silva",
    "telefone": "11987654321",
    "email": "maria.silva@email.com",
    "endereco": {
      "cep": "04567890",
      "rua": "Avenida Paulista",
      "complemento": "Apto 1001",
      "bairro": "Bela Vista",
      "cidade": "São Paulo",
      "estado": "SP"
    },
    "tipoSanguineo": "B_POSITIVO",
    "prontuario": {
      "classificacao": "PRIMARIA",
      "medicamentosPrescritos": [
        {
          "medicamento": {
            "nome": "Omeprazol",
            "principioAtivo": "Omeprazol",
            "descricao": "Inibidor da bomba de prótons"
          },
          "posologia": "1 vez ao dia",
          "dosagem": 20,
          "unidadeDosagem": "mg",
          "viaAdministracao": "oral"
        }
      ]
    }
  }
```

```json=
{
    "cpf": "04753549070",
    "name": "Ana Oliveira",
    "telefone": "31912345678",
    "email": "ana.oliveira@email.com",
    "endereco": {
      "cep": "30112233",
      "rua": "Avenida Afonso Pena",
      "complemento": "Sala 502",
      "bairro": "Centro",
      "cidade": "Belo Horizonte",
      "estado": "MG"
    },
    "tipoSanguineo": "B_NEGATIVO",
    "prontuario": {
      "classificacao": "TERCIARIA",
      "medicamentosPrescritos": [
        {
          "medicamento": {
            "nome": "Insulina",
            "principioAtivo": "Insulina humana",
            "descricao": "Hormônio para controle de glicemia"
          },
          "posologia": "3 vezes ao dia",
          "dosagem": 10,
          "unidadeDosagem": "UI",
          "viaAdministracao": "subcutânea"
        },
        {
          "medicamento": {
            "nome": "Enalapril",
            "principioAtivo": "Maleato de enalapril",
            "descricao": "Inibidor da ECA"
          },
          "posologia": "2 vezes ao dia",
          "dosagem": 10,
          "unidadeDosagem": "mg",
          "viaAdministracao": "oral"
        },
        {
          "medicamento": {
            "nome": "Sinvastatina",
            "principioAtivo": "Sinvastatina",
            "descricao": "Estatina para controle de colesterol"
          },
          "posologia": "1 vez ao dia",
          "dosagem": 20,
          "unidadeDosagem": "mg",
          "viaAdministracao": "oral"
        }
      ]
    }
  }

```

```json=


```

```json=


```

```json=


```

```json=


```

```json=


```

```json=


```

```json=


```

```json=


```

```json=


```

