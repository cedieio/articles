# Articles exercise
### Setup and Installation
- Run `./mvnw clean install` or `./mvwn clean package`
- Execute `java -jar target/articles-1.0.jar `
- run `curl http://localhost:8080/articles`

### Notes
Used java spring boot for fast prototyping.

Used Service/Repository Dependency injection using `default` as the qualifier.

Used a single Entity design for `Article` that housed `List<String> tags`.

Had to code HQL `Query` find manually due to HQL being case sensitive in string search.

- @Query can be seen on `ArticleRepository`
  Opted to use HQL for simple setup for entities.

In the backend it created a separate `Tag` table but we queried from the `Article` table.

Error scenario for `GET /article/{id}` would throw `404`.

- Created `ArticleNotFoundException` for the `404`error.

The tag endpoints would only throw empty as if not found because I assumed it was a search functionality.

Assumed that the last 10 articles are sorted by id descending.


Could be improved by creating a separate `TagRepository` for tag related services, and separating the business concerns.

Use of SQL, also suggested to persist data.

Unit tests could also be added for testing the `Service` Layer.

Unit tests was not added due to time constraint but it could be improved on by mocking repository calls and validating if the service would return the expected outcome.


### Endpoints
- `GET /articles`
- `GET /articles/{id}`
- `POST /articles`
- `GET /tags/{tag}`
- `GET /tags/{tag}/{date} format yyyyMMdd e.g. 20220704`

### Sample curl
- `GET /articles`
  Request
```
curl --location --request GET 'http://localhost:8080/articles'
```
Response
```
[
    {
        "id": 1,
        "title": "Fish and chips!",
        "body": "Fish and chips are good!",
        "date": "2022-07-05",
        "tags": [
            "Health",
            "Lunch",
            "Dinner"
        ]
    },
    {
        "id": 2,
        "title": "Running is good!",
        "body": "Running is great exercise",
        "date": "2022-07-05",
        "tags": [
            "Health",
            "Exercise",
            "Cardio"
        ]
    }
]
```
- `GET /articles/{id}`
  Request
```
curl --location --request GET 'http://localhost:8080/articles/1'
```
- `POST /articles`
  Request
```
curl --location --request POST 'http://localhost:8080/articles' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "C++ Programming",
    "body": "C++ Programming for dummies!",
    "date": "2022-06-27",
    "tags":[
        "Technology",
        "Programming"
        ]
}'
```

Response
```
{
    "id": 4,
    "title": "C++ Programming",
    "body": "C++ Programming for dummies!",
    "date": "2022-06-27",
    "tags": [
        "Technology",
        "Programming"
    ]
}
```
- `GET /tags/{tag}`
  Request
```
curl --location --request GET 'http://localhost:8080/tags/health'
```

Response
```
{
    "tag": "health",
    "count": 2,
    "articles": [
        2,
        1
    ],
    "relatedTags": [
        "Exercise",
        "Cardio",
        "Dinner",
        "Lunch"
    ]
}
```
- `GET /tags/{tag}/{date}`
  Request
```
curl --location --request GET 'http://localhost:8080/tags/health/20220705'
```

Response
```
{
    "tag": "health",
    "count": 2,
    "articles": [
        2,
        1
    ],
    "relatedTags": [
        "Exercise",
        "Cardio",
        "Dinner",
        "Lunch"
    ]
}
```