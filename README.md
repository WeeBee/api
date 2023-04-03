#Spring Boot 3: desenvolva uma API REST em Java

Para saber mais: Spring e Spring Boot

Spring e Spring Boot não são a mesma coisa com nomes distintos.
Spring é um framework para desenvolvimento de aplicações em Java, criado em meados de 2002 por Rod Johnson, que se tornou bastante popular e adotado ao redor do mundo devido a sua simplicidade e facilidade de integração com outras tecnologias.
O framework foi desenvolvido de maneira modular, na qual cada recurso que ele disponibiliza é representado por um módulo, que pode ser adicionado em uma aplicação conforme as necessidades. Com isso, em cada aplicação podemos adicionar apenas os módulos que fizerem sentido, fazendo assim com que ela seja mais leve. Existem diversos módulos no Spring, cada um com uma finalidade distinta, como por exemplo: o módulo MVC, para desenvolvimento de aplicações Web e API's Rest; o módulo Security, para lidar com controle de autenticação e autorização da aplicação; e o módulo Transactions, para gerenciar o controle transacional.
Entretanto, um dos grandes problemas existentes em aplicações que utilizavam o Spring era a parte de configurações de seus módulos, que era feita toda com arquivos XML, sendo que depois de alguns anos o framework também passou a dar suporte a configurações via classes Java, utilizando, principalmente, anotações. Em ambos os casos, dependendo do tamanho e complexidade da aplicação, e também da quantidade de módulos do Spring utilizados nela, tais configurações eram bastante extensas e de difícil manutenção.
Além disso, iniciar um novo projeto com o Spring era uma tarefa um tanto quanto complicada, devido a necessidade de realizar tais configurações no projeto.
Justamente para resolver tais dificuldades é que foi criado um novo módulo do Spring, chamado de Boot, em meados de 2014, com o propósito de agilizar a criação de um projeto que utilize o Spring como framework, bem como simplificar as configurações de seus módulos.
O lançamento do Spring Boot foi um marco para o desenvolvimento de aplicações Java, pois tornou tal tarefa mais simples e ágil de se realizar, facilitando bastante a vida das pessoas que utilizam a linguagem Java para desenvolver suas aplicações.
Ao longo do curso aprenderemos como desenvolver uma aplicação utilizando o Spring Boot, em conjunto com diversos outros módulos do Spring, de maneira simples e produtiva.

A versão 3 do Spring Boot foi lançada em novembro de 2022, trazendo algumas novidades em relação à versão anterior. Dentre as principais novidades, se destacam:

Suporte ao Java 17
Migração das especificações do Java EE para o Jakarta EE
Suporte a imagens nativas
Você pode ver a lista completa de novidades da versão 3 do Spring Boot no site: Spring Boot 3.0 Release Notes (https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes)

Atenção! Este curso não terá como foco principal explorar as novidades e recursos da versão 3 do Spring Boot, mas sim o desenvolvimento de uma API Rest utilizando o Spring Boot como framework, sendo que algumas novidades da versão 3 serão utilizadas apenas quando fizerem sentido no projeto.

Trello - https://trello.com/b/O0lGCsKb/api-voll-med
Layout mobile da app Voll.med https://www.figma.com/file/N4CgpJqsg7gjbKuDmra3EV/Voll.med
