[![CI/CD Pipeline](https://github.com/BryanMedrano/Lab2CI-CD/actions/workflows/build.yml/badge.svg)](https://github.com/BryanMedrano/Lab2CI-CD/actions/workflows/build.yml)
[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=coverage)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Maintainability issues](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=software_quality_maintainability_issues)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=BryanMedrano_Lab2CI-CD&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=BryanMedrano_Lab2CI-CD)
[![Coverage Status](https://coveralls.io/repos/github/BryanMedrano/Lab2CI-CD/badge.svg?branch=main)](https://coveralls.io/github/BryanMedrano/Lab2CI-CD?branch=main)

# Lab2CI-CD

API REST para administrar clientes, cuentas y transferencias entre cuentas,
desarrollada con Spring Boot. El proyecto demuestra un flujo de integración y
despliegue continuos (CI/CD) con GitHub Actions.

### ¿Qué contiene?

- API REST para clientes:
  - `GET /api/customers`
  - `GET /api/customers/{id}`
  - `POST /api/customers`
  - `DELETE /api/customers/{id}`
- API REST para transacciones:
  - `POST /api/transactions`
  - `GET /api/transactions/{accountNumber}`
- Arquitectura por capas: controladores, servicios, repositorios, entidades,
  DTO y manejo global de excepciones.
- Persistencia con Spring Data JPA e Hibernate.
- Validación de datos con Jakarta Validation.
- Pruebas unitarias y de contexto con JUnit 5 y Mockito.
- Reporte de cobertura con JaCoCo, Coveralls y SonarCloud.
- Construcción de una imagen Docker para el despliegue.

### Tecnologías principales

Java 21, Spring Boot 4, Maven, PostgreSQL, H2, Docker, GitHub Actions,
SonarCloud, Coveralls, Render y Vercel.
