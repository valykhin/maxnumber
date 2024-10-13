# MaxNumberApplication

### Описание
Приложение предоставляет функцию возврата 
максимального целого числа под номером index 
из файла в формате xlsx. Целые числа содержатся в файле xlsx
в первом столбце, если они не подходят по формату, то игнорируются.
Сортировка списка выполняется алгоритмом сортировки слиянием,
 сложность O(nlog(n)).

### Запуск
* Подготовка к запуску:
`./gradlew wrapper`
* Запуск приложения:
`./gradlew clean build bootRun`

### Документация
* [Swagger](http://127.0.0.1:8080/swagger-ui/index.html)
* [OpenAPI 3.0 spec](http://127.0.0.1:8080/v3/api-docs)

### Пример вызова функции
[http://127.0.0.1:8080/api/v1/number/1?filepath=src/main/resources/test.xlsx](http://127.0.0.1:8080/api/v1/number/1?filepath=src/main/resources/test.xlsx)
