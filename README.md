<div align="center">

# 🐱 Catsgram

**REST API социальной сети для любителей котиков**

Пользователи, посты с фотографиями и лента друзей — на Spring Boot.

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge)

</div>

---

## 📖 О проекте

**Catsgram** — учебный backend-проект, в котором реализовано REST API небольшой фото-соцсети:

- 👤 регистрация и обновление пользователей;
- 🖼️ публикация постов с описанием и ссылкой на фото;
- 📄 постраничный вывод постов с сортировкой по дате;
- 📅 выборка постов пользователя за период;
- 👥 лента постов друзей;
- ⚠️ централизованная обработка ошибок с понятными HTTP-статусами.

Данные хранятся в памяти приложения — после перезапуска они сбрасываются.

## 🛠️ Технологии

| Технология | Назначение |
|---|---|
| **Java 17** | язык разработки |
| **Spring Boot 4.1** (Web) | REST-контроллеры, DI, обработка ошибок |
| **Jackson** | сериализация/десериализация JSON |
| **Lombok** | сокращение шаблонного кода |
| **Maven** (wrapper) | сборка и зависимости |

## 🏗️ Архитектура

```
src/main/java/com/practice/catsgram
├── CatsgramApplication.java   # точка входа
├── controller/                # REST-контроллеры и ErrorHandler
├── service/                   # бизнес-логика (UserService, PostService)
├── model/                     # модели: User, Post, PostFeed
└── exception/                 # собственные исключения
```

Классическая слоистая схема: **Controller → Service → in-memory хранилище**.

## 🚀 Запуск

Требуется **JDK 17+**. Maven устанавливать не нужно — в проекте есть wrapper.

```bash
git clone https://github.com/Adilbi/catsgram.git
cd catsgram

# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Приложение стартует на `http://localhost:8080`.

## 📡 API

### 🏠 Главная

| Метод | URL | Описание |
|---|---|---|
| `GET` | `/home` | Проверка работы сервиса — возвращает `Котограм` |

### 👤 Пользователи

| Метод | URL | Описание |
|---|---|---|
| `GET` | `/users` | Список всех пользователей |
| `POST` | `/users` | Создать пользователя |
| `PUT` | `/users` | Создать или обновить пользователя |
| `GET` | `/users/{email}` | Найти пользователя по email |
| `GET` | `/users/{email}/posts/list?from=dd.MM.yyyy&to=dd.MM.yyyy` | Посты пользователя за период |

<details>
<summary>Пример: создание пользователя</summary>

```http
POST /users
Content-Type: application/json

{
  "email": "murzik@cats.com",
  "nickname": "Murzik",
  "birthdate": "2020-05-01"
}
```
</details>

### 🖼️ Посты

| Метод | URL | Описание |
|---|---|---|
| `GET` | `/posts?page=0&size=10&sort=desc` | Посты постранично, сортировка `asc` / `desc` по дате |
| `POST` | `/posts` | Создать пост (автор должен существовать) |
| `GET` | `/posts/{postId}` | Пост по id |

<details>
<summary>Пример: создание поста</summary>

```http
POST /posts
Content-Type: application/json

{
  "author": "murzik@cats.com",
  "description": "Утро понедельника 😾",
  "photoUrl": "https://example.com/murzik.jpg"
}
```
</details>

### 👥 Лента друзей

| Метод | URL | Описание |
|---|---|---|
| `POST` | `/feed/friends` | Последние посты указанных друзей |

<details>
<summary>Пример запроса</summary>

Тело запроса — JSON-строка, внутри которой лежит объект ленты:

```http
POST /feed/friends
Content-Type: application/json

"{\"sort\":\"desc\",\"size\":5,\"friends\":[\"murzik@cats.com\",\"barsik@cats.com\"]}"
```
</details>

### ⚠️ Ошибки

Все ошибки возвращаются в едином формате:

```json
{ "error": "Пост № 42 не найден" }
```

| Статус | Когда |
|---|---|
| `400 Bad Request` | неверные параметры `page` / `size` / `sort`, пустой email |
| `404 Not Found` | пост или автор не найден |
| `409 Conflict` | пользователь с таким email уже существует |
| `500 Internal Server Error` | непредвиденная ошибка |

## 🗺️ Планы развития

- [ ] Хранение данных в PostgreSQL (Spring Data JPA)
- [ ] Валидация входных данных через `jakarta.validation`
- [ ] `404` при запросе несуществующего пользователя по email
- [ ] Упрощение формата запроса ленты друзей (обычный JSON-объект)
- [ ] Unit- и интеграционные тесты для контроллеров и сервисов
- [ ] Документация API через Swagger / OpenAPI

## 👨‍💻 Автор

**Adilbi** — [GitHub](https://github.com/Adilbi)
