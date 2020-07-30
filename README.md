# La Salle Communify Api

Este projeto foi desenvolvido utilizando Spring Boot e tem como objetivo a integração de ambientes virtuais de aprendizagem, mais especificamente Moodle e Google Classroom. Desenvolvido como Trabalho de Conclusão de Curso na Universidade La Salle.

## Serve

`mvn spring-boot:run`

## Front-end

Disponível em [Communify APP](https://github.com/marcoatjunior/lasalle-communify-app).

## Online

Disponível em `https://communify-api.herokuapp.com`

## Documentação

Disponível em `https://communify-api.herokuapp.com/swagger-ui.html`

## Moodle Fake

### endpoint -> courses
```json
[
  {
    fullname: "courseName",
    students: [
      {
        "email": $studentEmail
      }
    ],
    lessons: [
      {
        name: "lessonName",
        deadline: "lessonDeadline",
        activityLink: "lessonActivityLink"
      }
    ]
  }
]
`
