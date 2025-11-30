# Sistema de Assistência Técnica — Como executar

Este README descreve como compilar e executar a aplicação Spring Boot deste repositório e como acessar a documentação OpenAPI/Swagger e o console H2 embutido.

Resumo rápido
- Aplicação: Spring Boot 3 (requere Java 17+)
- Banco: H2 (in-memory configurado por padrão)
- Swagger (OpenAPI): fornecido via springdoc (UI em /swagger-ui/index.html)

Pré-requisitos
- JDK 17 instalado (ex.: `C:\Program Files\Java\jdk-17`).
- Git (opcional)
- O projeto inclui o Maven Wrapper (`mvnw.cmd`) — não precisa ter Maven instalado globalmente.

1) Verificar Java (PowerShell)

```powershell
java -version
```
Deve mostrar algo como `java version "17.x.x"`.

Se não estiver apontando para o JDK 17, defina temporariamente nesta sessão PowerShell (ajuste o caminho se necessário):

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-17'
$env:Path = "$env:JAVA_HOME\bin;" + $env:Path
java -version
```

Build e execução

A partir da raiz do projeto (onde estão `mvnw.cmd` e `pom.xml`):

- Compilar / empacotar (gera o JAR em `target/`):

```powershell
.\mvnw.cmd clean package -DskipTests
```

- Rodar a aplicação com o Maven Wrapper (modo desenvolvimento):

```powershell
.\mvnw.cmd spring-boot:run
```

- Ou rodar o JAR gerado diretamente (recomendado para produção/deploy):

```powershell
& "$env:JAVA_HOME\bin\java.exe" -jar .\target\Sistema-de-Assistencia-Tecnica-0.0.1-SNAPSHOT.jar
```

A aplicação, por padrão, é iniciada na porta 8080. Para alterar a porta, edite `src/main/resources/application.properties` e adicione/alterar:

```
server.port=8081
```

Acessar a documentação OpenAPI / Swagger UI

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

(Se alterou a porta, substitua `8080` pela porta configurada.)

Console H2 (banco em memória)

O projeto já possui configuração do H2 em `src/main/resources/application.properties`. Valores padrão (conforme esse projeto):

- JDBC URL: `jdbc:h2:mem:testdb`
- Driver: `org.h2.Driver`
- Username: `sa`
- Password: `password`
- Console web: `http://localhost:8080/h2-console`

Para acessar o console do H2:
1. Abra o navegador em `http://localhost:8080/h2-console`.
2. No formulário de login do H2, ajuste os campos:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User Name: `sa`
   - Password: `password`
3. Clique em Connect. Você verá as tabelas criadas pelo JPA/Hibernate.

Observações sobre H2 in-memory
- O banco é mantido na memória e é reinicializado a cada restart da aplicação.
- Se quiser um banco persistente em disco, altere a URL no `application.properties`, por exemplo:

```
spring.datasource.url=jdbc:h2:file:./data/demo-db
```

Dicas de troubleshooting

- Erro: "Java version < 17" — verifique `java -version` e ajuste `JAVA_HOME` para apontar ao JDK 17.
- Erro de encoding ao copiar resources (MalformedInputException) — assegure que `src/main/resources/application.properties` esteja em UTF-8 (sem BOM). O POM do projeto já define `project.build.sourceEncoding` como UTF-8.
- Porta 8080 já em uso — ou pare o outro serviço ou altere `server.port` no `application.properties`.
- Se o Spring Boot não iniciar por ambiguidade de classe principal, o `pom.xml` já está configurado com `mainClass` apontando para `com.example.Sistema.SistemaApplication`.

Executando via IDE (IntelliJ / Eclipse)
- Importe o projeto como um projeto Maven.
- Configure o SDK para Java 17 no projeto/module.
- Execute a classe `com.example.Sistema.SistemaApplication` (Run as Java application / Run configuration). O console do IDE mostrará os logs e as mensagens de inicialização.

Segurança e produção
- O Swagger e o console H2 ficam públicos por padrão — proteja-os em ambientes de produção (Spring Security, profiles ou desabilite o console).
- Para habilitar Swagger apenas em dev, envolva a configuração com um profile `@Profile("dev")` ou condicione via propriedade `springdoc.api-docs.enabled`.

Ajuda adicional
- Se algo falhar ao rodar, copie as últimas linhas do terminal/`run.log` e cole aqui; eu ajudo a diagnosticar.


---
Arquivo criado automaticamente: `README.md` (raiz do projeto)

