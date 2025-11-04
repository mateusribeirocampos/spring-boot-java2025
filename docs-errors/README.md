# Documentação de Erros e Soluções

Este diretório contém documentação detalhada de erros encontrados durante o desenvolvimento e suas respectivas soluções.

## Índice de Documentos

### 001 - Erro de Versões de Dependências Gerenciadas pelo Framework
**Arquivo:** [001-Erro-versoes-de-dependencias-gerenciado-pelo-framework.md](./001-Erro-versoes-de-dependencias-gerenciado-pelo-framework.md)

**Erro:**
```
EntityManagerFactory interface seems to conflict with Spring's EntityManagerFactoryInfo mixin
methods with same signature getSchemaManager() but incompatible return types
```

**Causa:** Forçar versões de dependências JPA/Hibernate que o Spring Boot já gerencia

**Solução:** Remover versões forçadas do pom.xml e confiar no Spring Boot Parent POM

**Tópicos Cobertos:**
- Por que o Spring Boot gerencia dependências
- Como funciona o BOM (Bill of Materials)
- Tabela de compatibilidade de versões
- Quando (raramente) forçar versões
- Comandos de verificação
- Boas práticas

---

### 002 - Erro de Indentação em Arquivos YAML
**Arquivo:** [002-Erro-indentacao-yaml-properties.md](./002-Erro-indentacao-yaml-properties.md)

**Erro:**
```
No active profile set, falling back to 1 default profile: "default"
Property 'spring.application.Profile.active' is not recognized
```

**Causa:** Indentação incorreta ou hierarquia errada no arquivo YAML

**Solução:** Corrigir indentação (2 espaços) e estrutura do YAML

**Tópicos Cobertos:**
- Regras do YAML
- Exemplos completos para H2, PostgreSQL e MySQL
- Configurações com e sem Flyway
- Estrutura multi-profile
- Hibernate DDL-auto: quando usar cada modo
- Erros comuns e soluções
- Ferramentas de validação
- Comparação YAML vs Properties
- Configuração de editores

---

## Como Usar Este Guia

### Para Resolver um Erro Específico

1. Identifique a mensagem de erro no console
2. Procure nos documentos pela mensagem (use Ctrl+F)
3. Siga as soluções propostas
4. Verifique os comandos de diagnóstico

### Para Prevenir Erros

1. Leia as seções de "Boas Práticas"
2. Configure seu editor conforme recomendado
3. Use os checklists antes de commit
4. Revise a tabela de compatibilidades

### Para Aprender

1. Leia a seção "Descrição do Problema"
2. Compare código ERRADO vs CORRETO
3. Execute os comandos de verificação
4. Consulte as referências

## Padrão de Documentação

Cada documento segue esta estrutura:

```
1. Tipo de Erro (mensagem real)
2. Descrição do Problema (por que acontece)
3. Código Problemático (exemplo ERRADO)
4. Solução (código CORRETO)
5. Explicação Detalhada
6. Exemplos Adicionais
7. Boas Práticas
8. Ferramentas e Comandos
9. Referências
10. Resumo
```

## Quick Reference

### Comandos Úteis

```bash
# Verificar dependências
mvn dependency:tree

# Limpar cache do Maven
mvn clean
rm -rf ~/.m2/repository

# Validar YAML
yamllint src/main/resources/application.yml

# Ver propriedades efetivas
mvn help:effective-pom

# Debug da aplicação
mvn spring-boot:run -Dspring-boot.run.arguments=--debug
```

### Configurações Essenciais

#### pom.xml

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.7</version>
</parent>

<properties>
    <java.version>21</java.version>
    <!-- Não force outras versões -->
</properties>
```

#### application.yml

```yaml
spring:
  application:
    name: app-name
  profiles:
    active: ${ACTIVE_PROFILE:dev}
  datasource:
    # configurações aqui
  jpa:
    hibernate:
      ddl-auto: validate  # produção
```

## Contribuindo

Para adicionar nova documentação de erro:

1. Crie arquivo numerado sequencialmente (003-, 004-, etc.)
2. Siga o padrão estabelecido
3. Inclua exemplos ERRADO e CORRETO
4. Adicione comandos de diagnóstico
5. Atualize este README.md

### Template para Novo Documento

```markdown
# XXX - Título do Erro

## Tipo de Erro
[Mensagem de erro real]

## Descrição do Problema
[Explicação técnica]

## Código Problemático
[Exemplo ERRADO com comentários ❌]

## Solução
[Exemplo CORRETO com comentários ✅]

## Detalhes
[Explicação aprofundada]

## Boas Práticas
[Lista de recomendações]

## Ferramentas
[Comandos e ferramentas úteis]

## Referências
[Links oficiais]

## Resumo
[Problema/Solução/Prevenção em 1-2 linhas]
```

## Erros Futuros a Documentar

Sugestões de próximos documentos:

- [ ] 003 - Erros de Conexão com Banco de Dados
- [ ] 004 - Problemas com Flyway Migrations
- [ ] 005 - Erros de Serialização JSON
- [ ] 006 - Problemas com CORS
- [ ] 007 - Erros de Validação Bean Validation
- [ ] 008 - Issues com Testcontainers
- [ ] 009 - Problemas de Encoding UTF-8
- [ ] 010 - Erros de Circular Dependency

## Recursos Adicionais

### Documentação Oficial

- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Hibernate ORM](https://hibernate.org/orm/documentation/)
- [Flyway](https://flywaydb.org/documentation/)

### Ferramentas Online

- [YAML Lint](http://www.yamllint.com/)
- [Maven Repository](https://mvnrepository.com/)
- [Spring Initializr](https://start.spring.io/)

### Comunidade

- [Stack Overflow - Spring Boot](https://stackoverflow.com/questions/tagged/spring-boot)
- [Spring Community Forums](https://spring.io/community)
- [GitHub - Spring Projects](https://github.com/spring-projects)

## Changelog

### 2025-11-04
- Criado documento 001: Erro de Versões de Dependências
- Criado documento 002: Erro de Indentação YAML
- Criado README.md com índice e guia de uso

---

**Última atualização:** 2025-11-04
**Mantenedor:** Projeto testcontainer
**Versão:** 1.0.0
