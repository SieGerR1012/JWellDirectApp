# JWellDirectApp

**JWellDirectApp** — это веб-приложение на Java Spring Boot для ведения справочника нефтяных и газовых скважин. Предоставляет REST API и веб-интерфейс для управления скважинами и связанными справочными сущностями.

## Функциональность

- **Управление скважинами** — создание, редактирование, просмотр и удаление скважин с привязкой к организации, региону, участку недр, месторождению, кусту, буровой бригаде и заказчику.
- **Справочники** — полный CRUD для вспомогательных сущностей:
  - Организации (название, ИНН, КПП)
  - Регионы
  - Участки недр
  - Месторождения
  - Кусты
  - Буровые бригады (номер)
  - Заказчики
- **REST API** — полный набор JSON-эндпоинтов для интеграции с внешними системами.
- **Веб-интерфейс** — UI на Thymeleaf с формами для создания и редактирования записей.

## Технологический стек

- **Язык:** Java 25
- **Фреймворк:** Spring Boot 3.4.4
- **Доступ к данным:** Spring Data JPA / Hibernate 6
- **База данных:** PostgreSQL
- **Шаблонизатор:** Thymeleaf
- **Сборка:** Maven
- **Тестирование:** JUnit 5 + Mockito + Spring Test
- **Сервер:** Встроенный Apache Tomcat

## Модель данных

Основная сущность — `Well`, имеющая связи `@ManyToOne` с семью справочными сущностями:

| Сущность       | Таблица             | Ключевые поля                        |
|----------------|---------------------|--------------------------------------|
| Organisation   | `organisations`     | name, INN (уникальный, 10 символов), KPP (9 символов) |
| Region         | `regions`           | name                                 |
| SubsurfacePlot | `subsurface_plots`  | name (участок недр)                  |
| Field          | `fields`            | name (месторождение)                 |
| Cluster        | `clusters`          | name (куст)                          |
| DrillingCrew   | `drilling_crews`    | number (буровая бригада)             |
| Customer       | `customers`         | name (опционально)                   |

- `wellNumber` — уникальный, обязательный.
- Связь с `customer` опциональна (`customerId` может быть `null`).

## REST API

Все эндпоинты возвращают JSON. Базовый URL: `http://localhost:8080`

### Скважины (`/api/wells`)

| Метод  | Endpoint              | Описание             | Статус |
|--------|-----------------------|----------------------|--------|
| GET    | `/api/wells`          | Список всех скважин  | 200    |
| GET    | `/api/wells/{id}`     | Скважина по ID       | 200    |
| POST   | `/api/wells`          | Создать скважину     | 201    |
| PUT    | `/api/wells/{id}`     | Полное обновление    | 200    |
| PATCH  | `/api/wells/{id}`     | Частичное обновление | 200    |
| DELETE | `/api/wells/{id}`     | Удалить скважину     | 204    |

Тело запроса (POST / PUT / PATCH):
```json
{
  "wellNumber": "SKV-001",
  "organisationId": 1,
  "regionId": 1,
  "subsurfacePlotId": 1,
  "fieldId": 1,
  "clusterId": 1,
  "drillingCrewId": 1,
  "customerId": 1
}
```

`customerId` опционален (может быть `null`).

### Справочные сущности

Для каждой справочной сущности реализованы однотипные эндпоинты:

| Метод  | Endpoint                              | Описание               | Статус |
|--------|---------------------------------------|------------------------|--------|
| GET    | `/api/organisations`                  | Список                 | 200    |
| GET    | `/api/organisations/{id}`             | По ID                  | 200    |
| POST   | `/api/organisations`                  | Создать                | 201    |
| PUT    | `/api/organisations/{id}`             | Полное обновление      | 200    |
| PATCH  | `/api/organisations/{id}`             | Частичное обновление   | 200    |
| DELETE | `/api/organisations/{id}`             | Удалить                | 204    |

Аналогично для:
- `/api/regions`
- `/api/subsurface-plots`
- `/api/fields`
- `/api/clusters`
- `/api/drilling-crews`
- `/api/customers`

Тело запроса (POST / PUT / PATCH) — JSON с полями сущности.  
`PUT` заменяет все поля, `PATCH` обновляет только переданные (не `null`).

## Веб-интерфейс

Веб-интерфейс доступен по адресу `http://localhost:8080` и предоставляет:

- Главную страницу со ссылками на все разделы.
- Страницы списков для каждой сущности (`/wells`, `/organisations`, `/customers` и т.д.).
- Формы создания и редактирования для каждой сущности.
- Удаление записей через POST-формы.

Построен на Bootstrap 5 и Thymeleaf.

## Запуск

### Требования

- Java 25+
- PostgreSQL

### Настройка БД

Настройте подключение к базе данных в `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jwell_directory
spring.datasource.username=login_postgres
spring.datasource.password=passwodr_postgres
spring.jpa.hibernate.ddl-auto=update
```

Приложение использует `spring.jpa.hibernate.ddl-auto=update`, поэтому таблицы создаются автоматически.

### Сборка и запуск

```bash
./mvnw spring-boot:run
```

Или соберите JAR и запустите:

```bash
mvn package
java -jar target/JWellDirectApp-*.jar
```

### Доступ

- Веб-интерфейс: `http://localhost:8080`
- REST API: `http://localhost:8080/api/wells`

