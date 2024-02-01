## Vac App

### Descrição do Sistema

O Vac App é um sistema que possibilita o cadastro de Alergias, Usuários e Vacinas. Além disso, oferece funcionalidades para agendamento de vacinação para usuários.

##
### Ecossistema

O Sistema foi desenvolvido utilizando Angular e Spring Boot. Os dados são armazenados em um banco de dados PostgreSQL. Em seguida, as tecnologias serão melhor descritas.

##
### Angular

<b>O que é o Angular?</b>

Angular é um framework de desenvolvimento front-end mantido pelo Google. Ele permite a criação de aplicações web dinâmicas e escaláveis, utilizando a linguagem TypeScript.

<b>Requisitos para execução de uma aplicação Angular</b>

Node.js (versão 17 ou superior) - Download Node.js https://nodejs.org/en/download

<b>Execução da Aplicação Angular</b>

- Certifique-se de ter o Node.js instalado. Abra o Prompt de Comando e execute node --version. Caso não, realize a instalação no link acima.
- Instale o Angular CLI e o TypeScript. Execute os comando `npm install -g @angular/cli typescript tsc`
- Abra o terminal na pasta do projeto Angular
- Execute o comando `npm install` para instalar as dependências
- Em seguida, execute `npm run start` para iniciar o servidor de desenvolvimento
- Acesse a aplicação no navegador através de http://localhost:4200.

##
### Banco de Dados PostgreSQL

É necessário, para execução da aplicação Spring Boot a criação de um banco de dados local PostgreSQL.

### Spring Boot

<b>O que é o Spring Boot?<b>

Spring Boot é um framework para desenvolvimento de aplicações Java, focado em facilitar a criação de aplicações Java baseadas em Spring.

<b>Requisitos para execução de uma aplicação Angular</b>

Java JDK (versão 17 ou superior) - Download JDK - https://www.oracle.com/br/java/technologies/downloads/

Maven - https://maven.apache.org/install.html

Uma opção é utilizar uma IDE como o IntelliJ, esta facilita a execução de aplicações Java

<b>Execução da Aplicação Spring Boot</b>

- Acesse a pasta da aplicação e procure o arquivo `application.yml` no diretório `./src/main/resources/application.yml` e altere as credenciais de acordo com o banco de dados criado
- Certifique-se de ter o Java JDK instalado
- Abra o terminal na pasta do projeto Spring Boot
- Execute o comando ./mvnw spring-boot:run para iniciar a aplicação
- Acesse a aplicação no navegador através de http://localhost:8080
- Certifique-se de seguir os requisitos específicos de cada framework antes da execução

Para mais detalhes sobre configurações e funcionalidades, consulte a documentação oficial do Angular e do Spring Boot.