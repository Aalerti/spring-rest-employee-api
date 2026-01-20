# Employee Management REST API

Проект представляет собой RESTful API для управления сотрудниками компании.
Реализован классический CRUD (Create, Read, Update, Delete) с использованием современных практик Java разработки.

## Технологический стек
* **Java 17**
* **Spring Framework 6** (Spring MVC, Spring ORM, Transaction Management)
* **Hibernate 6** (JPA Implementation)
* **MySQL 8**
* **Maven**
* **Tomcat 10/11** (Jakarta EE)

## Ключевые особенности
* Архитектура **Controller - Service - DAO**.
* Использование **DTO (Data Transfer Objects)** для передачи данных (скрытие внутренней структуры БД).
* **Global Exception Handling** (@ControllerAdvice) для грамотных сообщений об ошибках (JSON).
* Конфигурация на чистой **Java (No XML)**.
* **MapStruct** для автоматического маппинга Entity

## Как запустить
1.  Клонируйте репозиторий.
2.  Создайте базу данных `my_db` в MySQL.
3.  Настройте `db.properties` (или класс `MyConfig`) под вашего пользователя MySQL.
4.  Соберите проект через Maven: `mvn clean package`.
5.  Разверните полученный WAR файл на сервере Tomcat.

## API Endpoints

| Method | Url | Description |
| ------ | --- | ----------- |
| GET | /api/employees | Получить всех сотрудников |
| GET | /api/employees/{id} | Получить сотрудника по ID |
| POST | /api/employees | Добавить нового сотрудника |
| PUT | /api/employees | Обновить данные сотрудника |
| DELETE | /api/employees/{id} | Удалить сотрудника |