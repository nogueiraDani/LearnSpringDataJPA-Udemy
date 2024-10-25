
# App Clinica médica

Projeto de uma API RESTful para uma clinica médica, afim de fixar estudos sobre JPA com SpringData.




## Documentação da API



#### Cadastrar especialidade

```http
  POST /especialidades
```

| Parâmetro           | Tipo     | Descrição                                                |
|:--------------------|:---------|:---------------------------------------------------------|
| `nomeEspecialidade` | `String` | **Obrigatório**.  nome da especialidade                  |



#### Buscar especialiade por descrição

```http
  GET /especialidades/nome/{nome}
```


#### Buscar especialiade por id

```http
  GET /especialidades/{id}
```

#### Atualizar especialiade por id

```http
  PUT /especialidades/{id}
```
| Parâmetro           | Tipo     | Descrição                                                |
|:--------------------|:---------|:---------------------------------------------------------|
| `nomeEspecialidade` | `String` | **Obrigatório**.  nome da especialidade                  |


#### Deletar especialiade por id

```http
  DELETE /especialidades/{id}
```


---

#### Cadastrar médico

```http
  POST /medicos
```

| Parâmetro         | Tipo     | Descrição                                                |
|:------------------|:---------|:---------------------------------------------------------|
| `crm`             | `int`    | **Obrigatório**.  Chave primaria                         |
| `especialidadeId` | `Long`   | **Obrigatório**. Chave estrangeira - id da especialidade |
| `nome`            | `String` | **Obrigatório**. Nome do médico                          |
| `sobrenome`       | `String` | **Obrigatório**. Sobrenome do médico                     |
| `telefone`        | `String` | Telefone do médico                                       |
| `email`           | `String` | Email do médico                                          |


