## Medicamento:
- [ ] id: Long
- [ ] nome: String
- [ ] principioAtivo: string
- [ ] descricao: String

## Endereco:
- [ ] cep: String(char 8)
- [ ] rua: String
- [ ] complemento: String
- [ ] bairro: String
- [ ] cidade: String
- [ ] estado: String

## Especialidade:
- [ ] id: Long
- [ ] nome: String
- [ ] descricao: String


## UnidadeHospitalar:
- [ ] telefone: String
- [ ] email: String
- [ ] endereco: Endereco
- [ ] dadosPessoal: String
- [ ] latitude: Double
- [ ] longitude: Double
- [ ] disponibilidadeLeitos: Integer
- [ ] especalidades: List<Especialidade>
- [ ] temUTI: boolean

## MecamentoPrescrito:
- [ ] id: Long
- [ ] mecamento: Medicamento
- [ ] polologia: String
- [ ] dosagem: Double
- [ ] unidadeDosagem: String (ml, g, cp)
- [ ] viaAdministracao: String
    
## Medico:
- [ ] crm: String
- [ ] nome: String
- [ ] telefone: String
- [ ] unidadeHospitalar: UnidadeHospitalar
- [ ] papel: usuario, regulador
- [ ] especialidade: Especialidade

## DocumentoTransferencia:
- [ ] drogasAdministradas; List<MedicamentoPrecrito>
- [ ] procedimentosAcondicionamento: List<String>
- [ ] procedimentosRecebimento: List<String>

## Prontuario:
- [ ] id: Long
- [ ] classificacao: primaria, cecundaria, terciaria
- [ ] medicamentosAtuais: List<MedicamentoPrecrito>

## Paciente:
- [ ] cpf: String (char 11)
- [ ] name: String
- [ ] telefone: String
- [ ] email: String
- [ ] endereco: Endereco
- [ ] tipoSanguineo: Enum()
- [ ] prontuario: Protuario

## Solicitacao:
- [ ] medico: Medico
- [ ] paciente: Paciente
- [ ] motivo: String
- [ ] documento DocumentoTransferencia
- [ ] especialidadesRequisitada: List<Especialidade>
- [ ] horarioSolicitacao: Date


## Transferencia:
- [ ] meioTransporte: ambulancia, helicoptero, aviao, 
- [ ] destino UnidadeHospitalar
- [ ] medicoDestino: Medico
- [ ] origem: UnidadeHospitalar
- [ ] medicoOrigem: Medico
- [ ] medicoRegulador: Medico
- [ ] horarioSaida: Date
- [ ] horarioPrevistoChegada: Date
- [ ] distancia: Double
- [ ] documento: DocumentoTransferencia
- [ ] paciente: Paciente
- [ ] solicitacao: Solicitacao