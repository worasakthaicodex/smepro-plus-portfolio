# SMEPro Plus

SMEPro Plus is a modular enterprise operations platform built as a Java web application. It brings document workflows, configurable forms, reporting, finance, HR, inventory, production, procurement, CRM, project work and internal service tools into one system.

This repository is a sanitized portfolio edition of an older production project. Runtime credentials, encryption material, licenses, customer uploads and operational data are intentionally excluded.

## Highlights

- Configurable approval and document workflows
- Dynamic forms, datasets and reusable business components
- Finance and accounting screens, ledgers, budgets and payments
- HR, attendance, payroll, leave and workforce operations
- Inventory, warehouse, procurement and production modules
- CRM, campaigns, sales follow-up and customer insight
- Project phases, resources, timesheets and progress tracking
- Dashboards, custom reports, PDF/Excel utilities and BI views
- Notifications, calendars, help desk and file-management tools
- Optional Google AI integration configured through an environment variable
- Thai and English message resources

## Technology

- Java 17
- Spring MVC 5 and Spring Security
- Spring JDBC, Spring Data JPA and Hibernate
- JSP/JSTL web views
- MySQL and HikariCP
- Maven WAR packaging and Tomcat 9
- WebSocket, Jackson, PDFBox/OpenPDF and Apache POI-compatible web tooling

## Project structure

```text
src/main/java/com/smepro/
  ai/           AI-assisted services
  config/       Spring application configuration
  controller/   MVC and API controllers
  dao/          Data-access implementation
  dto/          Request and response models
  entity/       Enterprise domain entities
  repository/   Repository abstractions
  security/     Authentication and web security
  service/      Business services

src/main/webapp/
  WEB-INF/views/  JSP screens and reusable view fragments
  resources/      JavaScript, CSS, fonts and UI assets
```

## Local setup

1. Install JDK 17, Maven, MySQL and Tomcat 9.
2. Copy `src/main/resources/ds/datasource.example.properties` to `datasource.properties`.
3. Fill in local database and runtime paths. Keep the real file out of Git.
4. If the AI module is needed, set `GOOGLE_AI_API_KEY` in the application environment.
5. Build the WAR:

```bash
mvn clean package -DskipTests
```

The generated WAR is written to `target/` and can be deployed to Tomcat.

## Portfolio and privacy scope

The public edition excludes:

- Database credentials and environment-specific configuration
- Keystores, certificates, license files and private keys
- Uploaded documents, generated PDFs and customer files
- Local IDE output, compiled artifacts and server runtime data
- The private repository history that previously contained operational files

No software license is granted by this repository. It is published for portfolio review.
