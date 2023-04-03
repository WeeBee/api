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

#CORS
Quando desenvolvemos APIs e queremos que todos os seus recursos fiquem disponíveis a qualquer cliente HTTP, uma das coisas que vem à nossa cabeça é o CORS (Cross-Origin Resource Sharing), em português, “compartilhamento de recursos com origens diferentes”. Se ainda não aconteceu com você, fique tranquilo, é normal termos erros de CORS na hora de consumir e disponibilizar APIs.

Mas afinal, o que é CORS, o que causa os erros e como evitá-los em nossas APIs com Spring Boot?

CORS
O CORS é um mecanismo utilizado para adicionar cabeçalhos HTTP que informam aos navegadores para permitir que uma aplicação Web seja executada em uma origem e acesse recursos de outra origem diferente. Esse tipo de ação é chamada de requisição cross-origin HTTP. Na prática, então, ele informa aos navegadores se um determinado recurso pode ou não ser acessado.

Mas por que os erros acontecem? Chegou a hora de entender!

Same-origin policy
Por padrão, uma aplicação Front-end, escrita em JavaScript, só consegue acessar recursos localizados na mesma origem da solicitação. Isso acontece por conta da política de mesma origem (same-origin policy), que é um mecanismo de segurança dos Browsers que restringe a maneira de um documento ou script de uma origem interagir com recursos de outra origem. Essa política possui o objetivo de frear ataques maliciosos.

Duas URLs compartilham a mesma origem se o protocolo, porta (caso especificado) e host são os mesmos. Vamos comparar possíveis variações considerando a URL https://cursos.alura.com.br/category/programacao:

URL														Resultado			Motivo
https://cursos.alura.com.br/category/front-end	M		esma origem			Só o caminho difere
http://cursos.alura.com.br/category/programacao			Erro de CORS		Protocolo diferente (http)
https://faculdade.alura.com.br:80/category/programacao	Erro de CORS		Host diferente

Agora, fica a dúvida: o que fazer quando precisamos consumir uma API com URL diferente sem termos problemas com o CORS? Como, por exemplo, quando queremos consumir uma API que roda na porta 8000 a partir de uma aplicação React rodando na porta 3000. Veja só!

Ao enviar uma requisição para uma API de origem diferente, a API precisa retornar um header chamado Access-Control-Allow-Origin. Dentro dele, é necessário informar as diferentes origens que serão permitidas para consumir a API, em nosso caso: Access-Control-Allow-Origin: http://localhost:3000.

É possível permitir o acesso de qualquer origem utilizando o símbolo *(asterisco): Access-Control-Allow-Origin: *. Mas isso não é uma medida recomendada, pois permite que origens desconhecidas acessem o servidor, a não ser que seja intencional, como no caso de uma API pública. Agora vamos ver como fazer isso no Spring Boot de maneira correta.

Habilitando diferentes origens no Spring Boot
Para configurar o CORS e habilitar uma origem específica para consumir a API, basta criar uma classe de configuração como a seguinte:

	@Configuration
	public class CorsConfiguration implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
		}
	}

http://localhost:3000 seria o endereço da aplicação Front-end e .allowedMethods os métodos que serão permitidos para serem executados. Com isso, você poderá consumir a sua API sem problemas a partir de uma aplicação Front-end.

#Para saber mais: Java Record

Lançado oficialmente no Java 16, mas disponível desde o Java 14 de maneira experimental, o Record é um recurso que permite representar uma classe imutável, contendo apenas atributos, construtor e métodos de leitura, de uma maneira muito simples e enxuta.

Esse tipo de classe se encaixa perfeitamente para representar classes DTO, já que seu objetivo é apenas representar dados que serão recebidos ou devolvidos pela API, sem nenhum tipo de comportamento.

Para se criar uma classe DTO imutável, sem a utilização do Record, era necessário escrever muito código. Vejamos um exemplo de uma classe DTO que representa um telefone:

{public final class Telefone {

    private final String ddd;
    private final String numero;

    public Telefone(String ddd, String numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, numero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Telefone)) {
            return false;
        } else {
            Telefone other = (Telefone) obj;
            return Objects.equals(ddd, other.ddd)
              && Objects.equals(numero, other.numero);
        }
    }

    public String getDdd() {
        return this.ddd;
    }

    public String getNumero() {
        return this.numero;
    }
}}

Agora com o Record, todo esse código pode ser resumido com uma única linha:

public record Telefone(String ddd, String numero){}COPIAR CÓDIGO
Muito mais simples, não?!

Por baixo dos panos, o Java vai transformar esse Record em uma classe imutável, muito similar ao código exibido anteriormente.

Mais detalhes sobre esse recurso podem ser encontrados na documentação oficial (https://docs.oracle.com/en/java/javase/16/language/records.html).
