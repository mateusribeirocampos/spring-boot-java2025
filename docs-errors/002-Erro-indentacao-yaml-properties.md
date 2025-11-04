# 002 - Erro de Indentação em Arquivos YAML

## Tipo de Erro

### Sintoma no Console

```
INFO: No active profile set, falling back to 1 default profile: "default"
```

ou

```
WARN: Property 'spring.application.Profile.active' is not recognized
```

### Comportamento Observado

- Profiles configurados não são ativados
- Configurações de datasource não são carregadas
- Application.yml é ignorado parcialmente
- Propriedades aninhadas não funcionam

## Descrição do Problema

YAML (YAML Ain't Markup Language) é **sensível à indentação**. Diferente de JSON ou XML, a hierarquia é definida por **espaços**, não por chaves ou tags.

### Regras do YAML

1. Use **2 espaços** para cada nível de indentação (não TABs)
2. Propriedades no mesmo nível devem ter a mesma indentação
3. Propriedades filhas devem ter indentação maior que a pai
4. Dois pontos (`:`) devem ser seguidos por espaço ou nova linha

## Código Problemático

### application.yml (ERRADO)

```yaml
spring:
  application:
    name: testcontainer

    Profile:           # ❌ ERRADO - "Profile" com P maiúsculo
      active: test     # ❌ ERRADO - indentado como filho de application

    profiles:          # ❌ ERRADO - indentado como filho de application
      active: test
```

**Problemas:**
1. `Profile` em vez de `profiles` (case-sensitive)
2. Indentação incorreta - `profiles` está dentro de `application`
3. O Spring espera `spring.profiles.active`, não `spring.application.profiles.active`

### Outro Exemplo Errado

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/mydb
      username: postgres    # ❌ ERRADO - indentação extra
    password: secret        # ✅ Correto
  jpa:
  hibernate:                # ❌ ERRADO - mesmo nível que jpa
    ddl-auto: update
```

## Solução

### application.yml (CORRETO)

```yaml
spring:
  application:
    name: testcontainer
  profiles:                 # ✅ Mesmo nível de application
    active: test            # ✅ Filho de profiles
```

## Configurações Completas por Banco de Dados

### H2 Database (Em Memória)

#### Com Flyway

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: test

  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: validate      # Com Flyway, use validate ou none
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
        format_sql: true

  flyway:
    enabled: true
    baseline-on-migrate: true
    locations: classpath:db/migration
    schemas: public

  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: false

logging:
  level:
    com.campos.testcontainer: DEBUG
    org.springframework.web: DEBUG
    org.hibernate.SQL: DEBUG
    org.flywaydb: DEBUG
```

#### Sem Flyway

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: test

  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: create-drop   # Sem Flyway, pode usar create-drop para testes
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.H2Dialect
        format_sql: true

  flyway:
    enabled: false            # Desabilitar Flyway explicitamente

  h2:
    console:
      enabled: true
      path: /h2-console

logging:
  level:
    com.campos.testcontainer: DEBUG
    org.hibernate.SQL: DEBUG
```

### PostgreSQL

#### Com Flyway

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: dev

  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/testdb
    username: postgres
    password: postgres
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 20000
      idle-timeout: 300000
      max-lifetime: 1200000

  jpa:
    hibernate:
      ddl-auto: validate        # Com Flyway, sempre validate ou none
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          time_zone: UTC

  flyway:
    enabled: true
    baseline-on-migrate: true
    locations: classpath:db/migration
    schemas: public
    validate-on-migrate: true
    out-of-order: false

logging:
  level:
    com.campos.testcontainer: INFO
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    org.flywaydb: INFO
```

#### Sem Flyway

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: dev

  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/testdb
    username: postgres
    password: postgres
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5

  jpa:
    hibernate:
      ddl-auto: update          # Sem Flyway, pode usar update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true

  flyway:
    enabled: false

logging:
  level:
    com.campos.testcontainer: INFO
    org.hibernate.SQL: DEBUG
```

#### PostgreSQL com Docker

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: docker

  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://postgres-db:5432/testdb    # Nome do service no docker-compose
    username: ${DB_USERNAME:postgres}                  # Variável de ambiente
    password: ${DB_PASSWORD:postgres}
    hikari:
      maximum-pool-size: 10

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

  flyway:
    enabled: true
    baseline-on-migrate: true

logging:
  level:
    com.campos.testcontainer: INFO
```

### MySQL

#### Com Flyway

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: dev

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/testdb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    username: root
    password: root
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 20000

  jpa:
    hibernate:
      ddl-auto: validate        # Com Flyway
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        format_sql: true

  flyway:
    enabled: true
    baseline-on-migrate: true
    locations: classpath:db/migration
    schemas: testdb
    validate-on-migrate: true

logging:
  level:
    com.campos.testcontainer: INFO
    org.hibernate.SQL: DEBUG
    org.flywaydb: INFO
```

#### Sem Flyway

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: dev

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/testdb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    username: root
    password: root
    hikari:
      maximum-pool-size: 10

  jpa:
    hibernate:
      ddl-auto: update          # Sem Flyway
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        format_sql: true

  flyway:
    enabled: false

logging:
  level:
    com.campos.testcontainer: INFO
    org.hibernate.SQL: DEBUG
```

#### MySQL com Docker

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: docker

  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://mysql-db:3306/testdb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:root}

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect

  flyway:
    enabled: true
    baseline-on-migrate: true

logging:
  level:
    com.campos.testcontainer: INFO
```

## Estrutura Multi-Profile

### application.yml (Base)

```yaml
spring:
  application:
    name: testcontainer
  profiles:
    active: ${ACTIVE_PROFILE:dev}     # Variável de ambiente ou dev como padrão

  jpa:
    show-sql: ${SHOW_SQL:false}
    properties:
      hibernate:
        format_sql: true

logging:
  level:
    root: INFO
```

### application-dev.yml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/testdb_dev
    username: dev_user
    password: dev_pass

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  flyway:
    enabled: false

logging:
  level:
    com.campos.testcontainer: DEBUG
```

### application-test.yml

```yaml
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:testdb
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: create-drop

  flyway:
    enabled: false

  h2:
    console:
      enabled: true
```

### application-prod.yml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    hikari:
      maximum-pool-size: 20
      minimum-idle: 10

  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

  flyway:
    enabled: true
    baseline-on-migrate: false
    validate-on-migrate: true

logging:
  level:
    com.campos.testcontainer: WARN
    org.hibernate.SQL: WARN
```

## Validação de YAML

### Ferramentas Online

- [YAML Lint](http://www.yamllint.com/)
- [JSON to YAML](https://jsonformatter.org/json-to-yaml)

### Validação via Linha de Comando

```bash
# Instalar yamllint
pip install yamllint

# Validar arquivo
yamllint src/main/resources/application.yml

# Validar com regras customizadas
yamllint -d relaxed src/main/resources/application.yml
```

### Validação no IntelliJ IDEA

1. Abrir o arquivo YAML
2. Verificar ícone no canto superior direito
3. Se houver erro, aparecerá sublinhado vermelho
4. Hover sobre o erro para ver detalhes

## Comparação: YAML vs Properties

### YAML (Recomendado)

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/testdb
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: validate
```

**Vantagens:**
- Mais legível
- Hierárquia clara
- Suporta listas e mapas
- Menos repetição

### Properties (Alternativa)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=validate
```

**Vantagens:**
- Não tem problema de indentação
- Mais simples para propriedades planas
- Suporte universal

## Hibernate DDL-Auto: Quando Usar Cada Opção

| Modo | Uso | Com Flyway? | Descrição |
|------|-----|-------------|-----------|
| `none` | Produção | ✅ Sim | Hibernate não toca no schema |
| `validate` | Produção | ✅ Sim | Valida schema sem modificar |
| `update` | Dev (sem Flyway) | ❌ Não | Atualiza schema automaticamente |
| `create` | Testes | ❌ Não | Cria schema novo sempre |
| `create-drop` | Testes | ❌ Não | Cria e destrói ao final |

### Recomendações

```yaml
# ✅ PRODUÇÃO
spring:
  jpa:
    hibernate:
      ddl-auto: validate      # Ou none
  flyway:
    enabled: true

# ✅ DESENVOLVIMENTO (com Flyway)
spring:
  jpa:
    hibernate:
      ddl-auto: validate
  flyway:
    enabled: true

# ✅ DESENVOLVIMENTO (sem Flyway)
spring:
  jpa:
    hibernate:
      ddl-auto: update
  flyway:
    enabled: false

# ✅ TESTES
spring:
  jpa:
    hibernate:
      ddl-auto: create-drop
  flyway:
    enabled: false
```

## Erros Comuns e Soluções

### Erro 1: Profile não ativado

```yaml
# ❌ ERRADO
spring:
  application:
    profiles:              # Dentro de application
      active: test

# ✅ CORRETO
spring:
  application:
    name: app
  profiles:                # Mesmo nível de application
    active: test
```

### Erro 2: Indentação Mista

```yaml
# ❌ ERRADO
spring:
  datasource:
      url: jdbc:h2:mem     # 6 espaços
    username: sa           # 4 espaços

# ✅ CORRETO
spring:
  datasource:
    url: jdbc:h2:mem       # 4 espaços
    username: sa           # 4 espaços
```

### Erro 3: TABs em vez de Espaços

```yaml
# ❌ ERRADO (contém TABs - invisível)
spring:
→datasource:               # TAB não é permitido

# ✅ CORRETO
spring:
  datasource:              # 2 espaços
```

### Erro 4: Falta de Espaço após Dois Pontos

```yaml
# ❌ ERRADO
spring:
  datasource:
    url:jdbc:h2:mem        # Sem espaço após ':'

# ✅ CORRETO
spring:
  datasource:
    url: jdbc:h2:mem       # Com espaço
```

## Configuração do Editor

### IntelliJ IDEA

```
Settings → Editor → Code Style → YAML
- Tab size: 2
- Indent: 2
- Use tab character: ❌ (desmarcar)
```

### VS Code (settings.json)

```json
{
  "[yaml]": {
    "editor.insertSpaces": true,
    "editor.tabSize": 2,
    "editor.autoIndent": "advanced"
  }
}
```

## Checklist de Verificação

- [ ] Profiles no nível correto (`spring.profiles`, não `spring.application.profiles`)
- [ ] Indentação consistente (2 espaços por nível)
- [ ] Sem TABs (só espaços)
- [ ] Espaço após dois pontos
- [ ] Case-sensitive correto (`profiles`, não `Profile`)
- [ ] DDL-auto compatível com Flyway
- [ ] Variáveis de ambiente configuradas para prod
- [ ] Passwords não commitadas (usar variáveis)

## Ferramentas de Debug

### Verificar Propriedades Carregadas

Adicione ao application.yml:

```yaml
logging:
  level:
    org.springframework.boot.context.config: DEBUG
```

### Ver Propriedades Efetivas

```bash
# Rodar aplicação com debug
java -jar app.jar --debug

# Ou via Maven
mvn spring-boot:run -Dspring-boot.run.arguments=--debug
```

### Actuator Endpoints

```yaml
management:
  endpoints:
    web:
      exposure:
        include: env, configprops
```

Acesse: `http://localhost:8080/actuator/env`

## Referências

- [Spring Boot Common Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
- [YAML Specification](https://yaml.org/spec/1.2/spec.html)
- [Spring Profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.profiles)
- [Flyway Documentation](https://flywaydb.org/documentation/)

## Resumo

**Problema:** Indentação incorreta impede o Spring de ler configurações
**Solução:** Use 2 espaços, mantenha hierarquia correta, evite TABs
**Prevenção:** Configure editor, use validadores YAML, teste profiles
