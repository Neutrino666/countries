## **Примеры запросов GraphQl**

#### [Ссылка](http://localhost:8282/graphiql?path=/graphql) для проверки 

#### 1. countries(page: Int!, size: Int!): CountryConnection!
```
query Country {
  countries(page: 0, size: 10) {
    edges {
      node {
        id,
        code,
        name
      }
    }
  }
}
```
#### 2. country(code: String!): Country!
```
query Country {
  country(code: "RU") {
    id,
    code,
    name
  }
}
```
#### 3. addCountry(input: CountryInput!): Country!
```
mutation {
  addCountry(input: { name: "Франция", code: "FR" }) {
    id
    name
    code
  }
}
```
