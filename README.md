# Challenge ONE | Back End | Alura Forum 

### Tecnologias utilizadas:

- [IntelliJ](https://www.jetbrains.com/pt-br/idea/)
- [MySql](https://www.mysql.com/)
- [Java](https://www.java.com/pt-BR/)
- [Spring Boot](https://start.spring.io/)
- [Flyway Migration](https://start.spring.io/)

[//]: # (- [Spring Security]&#40;https://start.spring.io/&#41;)
[//]: # (- [Token JWT]&#40;https://jwt.io/&#41;)

## Endpoints - Usuario
- `GET /usuarios`: retorna todos os usuários
- `GET /usuarios/:id`: retorna um usuário específico
- `POST /usuarios`: cria um novo usuário com o nome, email e senha informados
- `PUT /usuarios`: atualiza os dados de um usuário específico existente
- `DELETE /usuarios/:id`: remove os dados de um usuário específico existente

[//]: # (## Endpoints - Login)

## Endpoints - Cursos
- `GET /cursos`: retorna todos os cursos
- `GET /cursos/:id`: retorna um curso específico
- `POST /cursos`: cria um novo curso com o nome e categoria informados
- `PUT /cursos`: atualiza os dados de um curso específico existente
- `DELETE /cursos`: remove os dados de um curso específico existente

## Endpoints - Topicos
- `GET /topicos`: retorna todos os tópicos
- `GET /topicos/:id`: retorna um tópico específico
- `POST /topicos`: cria um novo tópico com o título, mensagem, autor e curso informados
- `PUT /topicos`: atualiza os dados de um tópico específico existente
- `DELETE /topicos`: remove os dados de um tópico específico existente