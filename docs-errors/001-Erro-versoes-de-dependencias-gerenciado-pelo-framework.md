# 001 - Erro de Versões de Dependências Gerenciadas pelo Framework

## Tipo de Erro

### Erro no Console

```
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory'
defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]:
EntityManagerFactory interface [interface org.hibernate.SessionFactory] seems to conflict with Spring's
EntityManagerFactoryInfo mixin - consider resetting the 'entityManagerFactoryInterface' property to plain
[jakarta.persistence.EntityManagerFactory]
```

### Causa Raiz

```
Caused by: java.lang.IllegalArgumentException: methods with same signature getSchemaManager() but incompatible
return types: [interface org.hibernate.relational.SchemaManager, interface jakarta.persistence.SchemaManager]
```

## Descrição do Problema

Este erro ocorre quando há **incompatibilidade entre versões** de dependências JPA/Hibernate causada por:

1. **Forçar versões manualmente** de dependências que o Spring Boot já gerencia
2. **Conflito de interfaces** no proxy dinâmico do Java
3. Uso de **Java versões muito recentes** (ex: Java 25) com frameworks ainda não totalmente compatíveis

### Por que isso acontece?

O Java tenta criar um **proxy dinâmico** que combina múltiplas interfaces (`Hibernate SessionFactory` + `Jakarta Persistence EntityManagerFactory`). Quando essas interfaces têm métodos com:
- Mesma assinatura: `getSchemaManager()`
- Tipos de retorno DIFERENTES: `org.hibernate.relational.SchemaManager` vs `jakarta.persistence.SchemaManager`

O proxy não pode ser criado, resultando em falha na inicialização da aplicação.

## Código Problemático

### pom.xml (ERRADO)

```xml
<properties>
    <java.version>25</java.version>
    <jakarta.version>3.2.0</jakarta.version>     <!-- ❌ Forçando versão -->
    <h2.version>2.3.232</h2.version>              <!-- ❌ Forçando versão -->
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- ❌ ERRADO - Forçando versão manualmente -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>${jakarta.version}</version>  <!-- Conflito com Spring Boot -->
    </dependency>

    <!-- ❌ ERRADO - Forçando versão do H2 -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>${h2.version}</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## Solução

### Remover Versões Forçadas

Deixe o **Spring Boot Parent POM** gerenciar as versões automaticamente através do **BOM (Bill of Materials)**.

### pom.xml (CORRETO)

```xml
<properties>
    <java.version>25</java.version>
    <!-- ✅ Remover propriedades de versão forçadas -->
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
        <!-- ✅ Versão gerenciada pelo Spring Boot Parent -->
    </dependency>

    <!-- ✅ CORRETO - Jakarta Persistence já vem com spring-boot-starter-data-jpa -->
    <!-- Não é necessário declarar explicitamente -->

    <!-- ✅ CORRETO - Versão gerenciada pelo Spring Boot -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
        <!-- Versão definida automaticamente pelo Spring Boot Parent -->
    </dependency>
</dependencies>
```

## Tabela de Compatibilidade

| Spring Boot | Java | Hibernate | Jakarta Persistence | Recomendação |
|-------------|------|-----------|---------------------|--------------|
| 3.5.x       | 17-21| 6.6.x     | 3.1.x               | ✅ Estável   |
| 3.5.x       | 25   | 6.6.x     | 3.2.x               | ⚠️ Experimental |
| 3.4.x       | 17-21| 6.5.x     | 3.1.x               | ✅ Estável   |
| 3.3.x       | 17-21| 6.4.x     | 3.1.x               | ✅ Estável   |

## Como o Spring Boot Gerencia Dependências

O Spring Boot usa um **BOM (Bill of Materials)** no Parent POM que:

1. Define versões compatíveis de TODAS as dependências
2. Testa essas versões juntas
3. Garante que não haja conflitos de classpath
4. É atualizado a cada release do Spring Boot

### Estrutura do Spring Boot Parent

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.7</version>
    <!-- Este parent já define TODAS as versões compatíveis -->
</parent>
```

## Comandos de Verificação

### Verificar Árvore de Dependências

```bash
# Ver todas as dependências e suas versões
mvn dependency:tree

# Procurar conflitos específicos
mvn dependency:tree | grep jakarta.persistence

# Ver versões efetivas
mvn help:effective-pom | grep -A 5 "artifactId>h2<"
```

### Verificar Versões Gerenciadas

```bash
# Mostrar todas as versões gerenciadas pelo Spring Boot
mvn dependency:tree -Dverbose

# Verificar versões de dependências específicas
mvn dependency:list | grep -E "(hibernate|jakarta|h2)"
```

## Boas Práticas

### ✅ FAÇA

1. **Confie no Spring Boot Parent** para gerenciar versões
2. **Use starters oficiais** (spring-boot-starter-*)
3. **Atualize o Spring Boot** regularmente para obter correções
4. **Declare apenas groupId e artifactId**, sem version
5. **Use Java LTS** (17 ou 21) para produção

### ❌ NÃO FAÇA

1. **Não force versões** de dependências do Spring Boot
2. **Não misture versões** de Jakarta EE e javax
3. **Não use Java experimental** em produção
4. **Não adicione dependências redundantes** (já incluídas em starters)
5. **Não ignore warnings** de deprecation

## Outras Dependências Gerenciadas

Além de JPA/Hibernate, o Spring Boot gerencia versões de:

```xml
<!-- ✅ Todas gerenciadas automaticamente -->
<dependencies>
    <!-- Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Bancos de Dados -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Flyway -->
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
</dependencies>
```

## Quando Forçar Versões?

Em casos MUITO raros, você pode precisar forçar uma versão específica:

```xml
<properties>
    <!-- ⚠️ Apenas se houver um bug crítico corrigido em versão específica -->
    <hibernate.version>6.6.35.Final</hibernate.version>
</properties>
```

**Antes de forçar uma versão:**
1. Verifique se há uma issue no Spring Boot sobre o problema
2. Teste EXAUSTIVAMENTE a compatibilidade
3. Documente o motivo no código
4. Planeje remover o override na próxima versão do Spring Boot

## Solução de Problemas

### Se o erro persistir:

```bash
# 1. Limpar cache do Maven
mvn clean
rm -rf ~/.m2/repository/com/h2database
rm -rf ~/.m2/repository/jakarta/persistence

# 2. Recompilar
mvn clean compile

# 3. Verificar versões efetivas
mvn help:effective-pom > effective-pom.xml
cat effective-pom.xml | grep -A 5 "version"

# 4. Forçar atualização
mvn clean install -U
```

## Referências

- [Spring Boot Dependency Management](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.build-systems.dependency-management)
- [Spring Boot Version Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/dependency-versions.html)
- [Jakarta EE 9+ Migration](https://jakarta.ee/resources/)
- [Hibernate Compatibility Matrix](https://hibernate.org/orm/releases/)

## Resumo

**Problema:** Forçar versões de dependências causa incompatibilidade de interfaces
**Solução:** Remover versões forçadas e confiar no Spring Boot Parent POM
**Prevenção:** Use starters oficiais sem especificar versões manualmente
