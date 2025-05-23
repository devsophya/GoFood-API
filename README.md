# GoFood API 🍔

GoFood é uma API RESTful desenvolvida em **Java** com **Spring Boot**, focada no gerenciamento completo de pedidos e cadastros para um sistema de delivery de alimentos.

Essa aplicação foi idealizada para servir como backend de um aplicativo de delivery, oferecendo recursos robustos de autenticação, controle de pedidos, gerenciamento de usuários, restaurantes, pratos e endereços.

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Bean Validation
- Banco de dados relacional (MySQL / PostgreSQL)
- Maven
- JavaMail (para recuperação de senha)
- Arquitetura em camadas (Controller, UseCase, Repository, Model)
- DTOs e ModelMapper

---

## ⚙️ Funcionalidades

- 🔐 Autenticação de usuários (login e registro)
- 🔁 Recuperação de senha por e-mail
- 👤 CRUD de usuários
- 🏪 CRUD de restaurantes
- 🍽️ CRUD de pratos/comidas
- 📍 CRUD de endereços
- 🛒 CRUD de pedidos (inclusão, visualização e controle de status)
- ✅ Validações com Bean Validation
- 📦 Integração com banco de dados relacional
- 📑 Organização RESTful com DTOs
- ⚠️ Tratamento de exceções e mensagens personalizadas

---

## 📁 Estrutura do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── com/gofood/gofood/
│   │       ├── controllers/
│   │       ├── usecases/
│   │       ├── models/
│   │       ├── repositories/
│   │       ├── dtos/
│   │       └── config/
│   └── resources/
│       ├── application.yml
│       └── static/
