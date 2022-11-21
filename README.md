---------------------BANCO DE DADOS---------------------------------

Banco de dados utilizado: MySQL 8.0.30
Login: root
Password: 123456
Database: pedidosloja (precisa criar esse banco para que o JPA crie as tabelas)

Caso necessite usar outrou usuário e senha para o banco de dados, modificar no "arquivo application.properties" dentro de src/main/resources linhas 2 e 3.
por padrão a aplicação usa o banco no link localhost porta 3306, linha 1.

---------------------IDE PARA RODAR A APLICAÇÃO---------------------------------

IDE de Desenvolvimento e para rodar o projeto: Spring Tool Suite 4 Version: 4.16.1.RELEASE
Para subir a aplicação basta startar o arquivo "PedidoslojaApplication.java" dentro do package com.pedidosloja
Versão do JDK: jdk-17.0.4

Por padrão as chamadas da API serão realizadas pelo localhost porta 8080, esse caminho foi usada em todas as chamadas do Postman.

--------------------------LOGS DA APLICAÇÃO--------------------------------------
São salvos na pasta raiz do projeto conforme configurado no arquivo "apllication.properties" na linha 7