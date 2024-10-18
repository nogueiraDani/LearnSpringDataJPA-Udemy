
# App Clinica médica

Projeto de uma API RESTful para uma clinica médica, afim de fixar estudos sobre JPA com SpringData.




## Documentação da API

#### Cadastrar médico

```http
  POST /medicos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `crm` | `int` | **Obrigatório**. A chave da sua API |
| `nome` | `String` | **Obrigatório**. Nome do médico |
| `sobrenome` | `String` | **Obrigatório**. Sobrenome do médico |
| `telefone` | `String` | Telefone do médico|
| `email` | `String` | Email do médico |
| `especialidade/descricao` | `String` | **Obrigatório**. Descricao da especialidade do médico |



