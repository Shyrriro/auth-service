# auth-service

# 🔐 Auth Service

Serviço de autenticação desenvolvido com foco em **Clean Architecture + DDD (Domain-Driven Design)** utilizando **Spring Boot**.

---

# 👨‍💻 Sobre o Desenvolvedor

Alexandre Amorim de Jesus
Desenvolvedor focado em backend Java, com interesse em:

Arquitetura de Software
Clean Code & SOLID
Domain-Driven Design (DDD)
APIs robustas e escaláveis

Este projeto foi construído por mim com o objetivo de aplicar conceitos avançados utilizados em sistemas profissionais.

---

## 📌 Visão Geral

Este projeto implementa um serviço de autenticação com gerenciamento de usuários, seguindo boas práticas de arquitetura para garantir:

* Baixo acoplamento
* Alta coesão
* Facilidade de manutenção
* Escalabilidade
* Testabilidade
* Segurança

---

## 🏗️ Arquitetura

O projeto segue uma estrutura baseada em **Camadas + Ports & Adapters (Hexagonal)**:

```
auth_service
├── adapters        # Entrada e saída (controllers, DTOs, persistence)
├── core
│   ├── application # Use cases (orquestração)
│   └── domain      # Regras de negócio (entidades, VO, portas)
├── config          # Configurações do Spring
├── security        # Configurações de segurança
├── exceptions      # Tratamento global de erros
└── startup         # Inicializações (seed de admin)
```

---

## 🧠 Conceitos Aplicados

### ✅ DDD (Domain-Driven Design)

* Entidade rica: `User`
* Value Objects:

    * `Email`
    * `Username`
    * `Password`
* Enum:

    * `Role`

### ✅ Clean Architecture

* `domain`: regras puras
* `application`: orquestra casos de uso
* `adapters`: comunicação externa

### ✅ SOLID

* Cada classe possui responsabilidade única
* UseCases isolados (ex: `CreateUserUseCase`)

---

## 🚀 Funcionalidades

* ✅ Criar usuário
* ✅ Buscar usuário por username
* ✅ Atualizar usuário
* ✅ Deletar usuário
* ✅ Seed automático de admin
* ✅ Tratamento global de exceções
* ✅ Documentação com Swagger

---

## 📦 Estrutura Detalhada

### 🔹 Adapters (Entrada/Saída)

**Entrada (Web)**

* `UserController`
* DTOs:

    * Request: `UserRequestDTO`, `UserUpdateDTO`
    * Response: `UserResponseDTO`, `ErrorResponseDTO`

**Saída (Persistência)**

* `UserEntity`
* `JpaUserRepository`
* `UserRepositoryImpl`

---

### 🔹 Core

#### 📌 Application (Use Cases)

Casos de uso isolados:

* `CreateUserUseCase`
* `GetUserByUsernameUseCase`
* `UpdateUserUseCase`
* `DeleteUserUseCase`

Implementações:

* `CreateUserUseCaseImpl`
* `GetUserByUsernameUseCaseImpl`
* `UpdateUserUseCaseImpl`
* `DeleteUserUseCaseImpl`
---

#### 📌 Domain

* Entidade: `User`
* Value Objects:

    * `Email`
    * `Password`
    * `Username`
* Porta de saída:

    * `UserRepository`

---

## 🔐 Segurança

Configuração via:

* `SecurityConfig`
* `PasswordConfig`

Possui:

* Hash de senha
* Controle de acesso por role

---

## ⚠️ Tratamento de Erros

* `GlobalExceptionHandler`
* Exceções customizadas:

    * `UserAlreadyExistsException`
    * (possível expansão para outras regras de negócio)

---

## 📄 Documentação da API

Disponível via Swagger:

```
/swagger-ui.html
```

Configuração em:

* `OpenApiConfig`

---

## 🌱 Seed de Admin

Classe:

* `AdminSeeder`

Responsável por criar um usuário admin automaticamente ao iniciar a aplicação.

---

## 🛠️ Tecnologias

* Java (versão 17+)
* Spring Boot (versão 3+)
* Spring Security (para autenticação e autorização)
* Spring Data JPA (para persistência)
* Swagger / OpenAPI (para documentação)
* H2 Database (para desenvolvimento)
* Maven (gerenciamento de dependências)
* Lombok (para reduzir boilerplate)

---

## ⚡ Possíveis Melhorias

* Adicionar testes unitários (JUnit + Mockito)
* Implementar autenticação JWT
* Criar logs estruturados
* Adicionar paginação nas buscas
* Implementar refresh tokens para autenticação
* Adicionar suporte a OAuth2 para autenticação via redes sociais
* Criar mais exceções específicas (ex: InvalidPasswordException)
* Mapear entidades para DTOs usando MapStruct ou similar

---

## ▶️ Como Rodar o Projeto

```bash
# Clonar repositório
git clone https://github.com/Shyrriro/auth-service.git

# Entrar na pasta
cd auth-service

# Rodar aplicação
./mvnw spring-boot:run

# Acessar API via Swagger
http://localhost:8080/swagger-ui.html

# Acessar H2 Console
http://localhost:8080/h2-console
```
