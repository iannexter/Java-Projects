# Java-Projects

Kissorvete_hibernate: Trata-se de um sistema PDV para o ramo de sorveteria, com autenticação de usuário para realizar o login, sendo usuário: "admin", "funcionario" e "user". O projeto foi desenvolvido através da linguagem Java, utilizando a IDE Eclipse e o framework Primefaces, com o mapeamento objeto relacional feito através do JPA hibernate e salvo no banco de dados MySQL. É possível cadastrar sorvetes ou picolés, bem como editá-los ou excluí-los. Além disso é possível realizar uma venda incluindo uma quantidade de gelatos ao carrinho. Por fim, há, também a visualização do histórico de vendas realizadas, bem como a emissão de um documento PDF dessas vendas. Para execução do projeto, baixe-o, abra ele e altere as classes VendaDAO.java, dentro da pasta br.com.gelatos, Database.java, dentro da pasta br.com.login e persitence.xml, dentro da pasta META-INF para a senha do seu banco de dados mysql. É importante que você crie o banco de dados com:

CREATE DATABASE IF NOT EXISTS gelatos_hibernate;
USE gelatos_hibernate;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

no mysql, descomentar o método que está na classe "Main.java" dentro de br.com.gelatos.main e rodar a classe como "java aplication" para criar os usuários com as senhas definidas pelas variáveis que estão no método. Depois que rodar, baixe o tomcat e rode o projeto usando a versão escolhida por você. Por fim, basta apenas rodar o projeto e acessar no navegador colocando o link "http://localhost:8080/Gelatos_hibernate/faces/login.xhtml" : 

<img width="960" height="509" alt="Screenshot_4" src="https://github.com/user-attachments/assets/fa542346-9470-41c1-a9a2-dec1cc81fb8c" />

<img width="949" height="510" alt="Screenshot_5" src="https://github.com/user-attachments/assets/1c071200-3dbf-47ca-94ae-d0f09bd72eba" />

<img width="954" height="508" alt="Screenshot_6" src="https://github.com/user-attachments/assets/d3851560-44d4-4804-9045-e81b48daa48c" />

<img width="954" height="510" alt="Screenshot_7" src="https://github.com/user-attachments/assets/c7f8a8a6-0254-4bb0-ba7b-29ebf2a73a84" />

<img width="954" height="509" alt="Screenshot_8" src="https://github.com/user-attachments/assets/f5a22212-09f5-44e1-87e7-bff8f6135abf" />

<img width="954" height="508" alt="Screenshot_9" src="https://github.com/user-attachments/assets/d3ff901b-00d7-4e4e-885e-8dc784ccac08" />



Gerenciamento de produtos: Este projeto trata-se uma aplicação para gerenciar vendas. Têm-se duas entidades(Categoria e Produto), na qual 1 produto precisa estar diretamente associado a uma categoria. Além disso há a realização de vendas, onde cada produto pode ser adicionado ao carrinho com uma quantidade fornecida pelo usuário para cada produto e realizar uma venda. Ademais, há, também, a visualização do histórico de vendas dos produtos vendidos. O Sistema foi desenvolvido utilizando a linguagem java, através do framework Spring Boot, para a criação da API, e o uso de html, css e javascript, para o consumo desse serviço.

<img width="947" height="491" alt="Screenshot_1" src="https://github.com/user-attachments/assets/ba72ed2c-75a6-45b4-a76a-444a61d3ca23" />

<img width="954" height="509" alt="Screenshot_2" src="https://github.com/user-attachments/assets/37f44e75-f111-44e7-a5f4-0c40c8766218" />

<img width="955" height="510" alt="Screenshot_3" src="https://github.com/user-attachments/assets/79d81dfe-b728-454a-8da9-eefc372682f6" />


