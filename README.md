# Spring Boot 3: desenvolva uma API REST em Java

## Para saber mais: Spring e Spring Boot

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
Você pode ver a lista completa de novidades da versão 3 do Spring Boot no site: [Spring Boot 3.0 Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes)

Atenção! Este curso não terá como foco principal explorar as novidades e recursos da versão 3 do Spring Boot, mas sim o desenvolvimento de uma API Rest utilizando o Spring Boot como framework, sendo que algumas novidades da versão 3 serão utilizadas apenas quando fizerem sentido no projeto.

Trello - https://trello.com/b/O0lGCsKb/api-voll-med

Layout mobile da app Voll.med - https://www.figma.com/file/N4CgpJqsg7gjbKuDmra3EV/Voll.med

### CORS
Quando desenvolvemos APIs e queremos que todos os seus recursos fiquem disponíveis a qualquer cliente HTTP, uma das coisas que vem à nossa cabeça é o CORS (Cross-Origin Resource Sharing), em português, “compartilhamento de recursos com origens diferentes”. Se ainda não aconteceu com você, fique tranquilo, é normal termos erros de CORS na hora de consumir e disponibilizar APIs.

Mas afinal, o que é CORS, o que causa os erros e como evitá-los em nossas APIs com Spring Boot?

O CORS é um mecanismo utilizado para adicionar cabeçalhos HTTP que informam aos navegadores para permitir que uma aplicação Web seja executada em uma origem e acesse recursos de outra origem diferente. Esse tipo de ação é chamada de requisição cross-origin HTTP. Na prática, então, ele informa aos navegadores se um determinado recurso pode ou não ser acessado.

Mas por que os erros acontecem? Chegou a hora de entender!

Same-origin policy
Por padrão, uma aplicação Front-end, escrita em JavaScript, só consegue acessar recursos localizados na mesma origem da solicitação. Isso acontece por conta da política de mesma origem (same-origin policy), que é um mecanismo de segurança dos Browsers que restringe a maneira de um documento ou script de uma origem interagir com recursos de outra origem. Essa política possui o objetivo de frear ataques maliciosos.

Duas URLs compartilham a mesma origem se o protocolo, porta (caso especificado) e host são os mesmos. Vamos comparar possíveis variações considerando a URL https://cursos.alura.com.br/category/programacao:

```
URL							Resultado	Motivo
---							---------	------
https://cursos.alura.com.br/category/front-end		Mesma origem	Só o caminho difere
http://cursos.alura.com.br/category/programacao		Erro de CORS	Protocolo diferente (http)
https://faculdade.alura.com.br:80/category/programacao	Erro de CORS	Host diferente
```

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

## Para saber mais: Java Record

Lançado oficialmente no Java 16, mas disponível desde o Java 14 de maneira experimental, o Record é um recurso que permite representar uma classe imutável, contendo apenas atributos, construtor e métodos de leitura, de uma maneira muito simples e enxuta.

Esse tipo de classe se encaixa perfeitamente para representar classes DTO, já que seu objetivo é apenas representar dados que serão recebidos ou devolvidos pela API, sem nenhum tipo de comportamento.

Para se criar uma classe DTO imutável, sem a utilização do Record, era necessário escrever muito código. Vejamos um exemplo de uma classe DTO que representa um telefone:

```
public final class Telefone {

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
}
```

Agora com o Record, todo esse código pode ser resumido com uma única linha:

```public record Telefone(String ddd, String numero){}```

Muito mais simples, não?!

Por baixo dos panos, o Java vai transformar esse Record em uma classe imutável, muito similar ao código exibido anteriormente.

Mais detalhes sobre esse recurso podem ser encontrados na [documentação oficial](https://docs.oracle.com/en/java/javase/16/language/records.html).

## Para saber mais: arquivo properties ou yaml?
As configurações de uma aplicação Spring Boot são feitas em arquivos externos, sendo que podemos usar arquivo de propriedades ou arquivo YAML. Neste “Para saber mais”, vamos abordar as principais diferenças entre eles.

### Arquivo de propriedades
Por padrão, o Spring Boot acessa as configurações definidas no arquivo application.properties, que usa um formato de chave=valor:
```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/clinica
spring.datasource.username=root
spring.datasource.password=root
```
Cada linha é uma configuração única, então é preciso expressar dados hierárquicos usando os mesmos prefixos para nossas chaves, ou seja, precisamos repetir prefixos, neste caso, spring e datasource.

### YAML Configuration
YAML é um outro formato bastante utilizado para definir dados de configuração hierárquica, como é feito no Spring Boot.

Pegando o mesmo exemplo do nosso arquivo application.properties, podemos convertê-lo para YAML alterando seu nome para application.yml e modificando seu conteúdo para:
```
spring:
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/clinica
        username: root
        password: root
```
Com YAML, a configuração se tornou mais legível, pois não contém prefixos repetidos. Além de legibilidade e redução de repetição, o uso de YAML facilita o armazenamento de variáveis de configuração de ambiente, conforme recomenda o [12 Factor App](https://12factor.net/), uma metodologia bastante conhecida e utilizada que define 12 boas práticas para criar uma aplicação moderna, escalável e de manutenção simples.

### Mas afinal, qual formato usar?
Apesar dos benefícios que os arquivos YAML nos trazem em comparação ao arquivo properties, a decisão de escolher um ou outro é de gosto pessoal. Além disso, não é recomendável ter ao mesmo tempo os dois tipos de arquivo em um mesmo projeto, pois isso pode levar a problemas inesperados na aplicação.

Caso opte por utilizar YAML, fique atento, pois escrevê-lo no início pode ser um pouco trabalhoso devido às suas regras de indentação.

##  Para saber mais: e as classes DAO?
Em alguns projetos em Java, dependendo da tecnologia escolhida, é comum encontrarmos classes que seguem o padrão DAO, utilizado para isolar o acesso aos dados. Entretanto, neste curso utilizaremos um outro padrão, conhecido como **Repository**.

Mas aí podem surgir algumas dúvidas: qual a diferença entre as duas abordagens e o porquê dessa escolha?

### Padrão DAO
O padrão de projeto DAO, conhecido também por Data Access Object, é utilizado para persistência de dados, onde seu principal objetivo é separar regras de negócio de regras de acesso a banco de dados. Nas classes que seguem esse padrão, isolamos todos os códigos que lidam com conexões, comandos SQLs e funções diretas ao banco de dados, para que assim tais códigos não se espalhem por outros pontos da aplicação, algo que dificultaria a manutenção do código e também a troca das tecnologias e do mecanismo de persistência.
### Implementação
Vamos supor que temos uma tabela de produtos em nosso banco de dados. A implementação do padrão DAO seria o seguinte:

Primeiro, seria necessário criar uma classe básica de domínio Produto:
```
public class Produto {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String descricao;

    // construtores, getters e setters
}
```
Em seguida, precisaríamos criar a classe ProdutoDao, que fornece operações de persistência para a classe de domínio Produto:
```
public class ProdutoDao {

    private final EntityManager entityManager;

    public ProdutoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Produto produto) {
        entityManager.persist(produto);
    }

    public Produto read(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public void update(Produto produto) {
        entityManger.merge(produto);
    }

    public void remove(Produto produto) {
        entityManger.remove(produto);
   }

}
```
No exemplo anterior foi utilizado a JPA como tecnologia de persistência dos dados da aplicação.
### Padrão Repository
De acordo com o famoso livro Domain-Driven Design, de Eric Evans:
> O repositório é um mecanismo para encapsular armazenamento, recuperação e comportamento de pesquisa, que emula uma coleção de objetos.

Simplificando, um repositório também lida com dados e oculta consultas semelhantes ao DAO. No entanto, ele fica em um nível mais alto, mais próximo da lógica de negócios de uma aplicação. Um repositório está vinculado à regra de negócio da aplicação e está associado ao agregado dos seus objetos de negócio, retornando-os quando preciso.

Só que devemos ficar atentos, pois assim como no padrão DAO, regras de negócio que estão envolvidas com processamento de informações não devem estar presentes nos repositórios. Os repositórios não devem ter a responsabilidade de tomar decisões, aplicar algoritmos de transformação de dados ou prover serviços diretamente a outras camadas ou módulos da aplicação. Mapear entidades de domínio e prover as funcionalidades da aplicação são responsabilidades muito distintas.

Um repositório fica entre as regras de negócio e a camada de persistência:

  1. Ele provê uma interface para as regras de negócio onde os objetos são acessados como em uma coleção;
  2. Ele usa a camada de persistência para gravar e recuperar os dados necessários para persistir e recuperar os objetos de negócio.

Portanto, é possível até utilizar um ou mais DAOs em um repositório.

### Por que o padrão repository ao invés do DAO utilizando Spring?
O padrão de repositório incentiva um design orientado a domínio, fornecendo uma compreensão mais fácil do domínio e da estrutura de dados. Além disso, utilizando o repository do Spring não temos que nos preocupar em utilizar diretamente a API da JPA, bastando apenas criar os métodos que o Spring cria a implementação em tempo de execução, deixando o código muito mais simples, menor e legível.

## Para saber mais: anotações do Bean Validation
Como explicado no vídeo anterior, o Bean Validation é composto por diversas anotações que devem ser adicionadas nos atributos em que desejamos realizar as validações. Vimos algumas dessas anotações, como a @NotBlank, que indica que um atributo do tipo String não pode ser nulo e nem vazio.

Entretanto, existem dezenas de outras anotações que podemos utilizar em nosso projeto, para os mais diversos tipos de atributos. Você pode conferir uma lista com as principais anotações do Bean Validation na [documentação oficial](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints) da especificação.

## Para saber mais: DTOs ou entidades?
Estamos utilizando DTOs para representar os dados que recebemos e devolvemos pela API, mas você provavelmente deve estar se perguntando “Por que ao invés de criar um DTO não devolvemos diretamente a entidade JPA no Controller?”. Para fazer isso, bastaria alterar o método listar no Controller para:
```
@GetMapping
public List<Medico> listar() {
    return repository.findAll();
}
```
Desse jeito o código ficaria mais enxuto e não precisaríamos criar o DTO no projeto. Mas, será que isso realmente é uma boa ideia?

### Os problemas de receber/devolver entidades JPA
De fato é muito mais simples e cômodo não utilizar DTOs e sim lidar diretamente com as entidades JPA nos controllers. Porém, essa abordagem tem algumas desvantagens, inclusive causando vulnerabilidade na aplicação para ataques do tipo [Mass Assignment](https://cheatsheetseries.owasp.org/cheatsheets/Mass_Assignment_Cheat_Sheet.html).
Um dos problemas consiste no fato de que, ao retornar uma entidade JPA em um método de um Controller, o Spring vai gerar o JSON contendo todos os atributos dela, sendo que nem sempre esse é o comportamento que desejamos.

Eventualmente podemos ter atributos que não desejamos que sejam devolvidos no JSON, seja por motivos de segurança, no caso de dados sensíveis, ou mesmo por não serem utilizados pelos clientes da API.

### Utilização da anotação @JsonIgnore
Nessa situação, poderíamos utilizar a anotação @JsonIgnore, que nos ajuda a ignorar certas propriedades de uma classe Java quando ela for serializada para um objeto JSON.

Sua utilização consiste em adicionar a anotação nos atributos que desejamos ignorar quando o JSON for gerado. Por exemplo, suponha que em um projeto exista uma entidade JPA Funcionario, na qual desejamos ignorar o atributo salario:
```
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Funcionario")
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @JsonIgnore
    private BigDecimal salario;

    //restante do código omitido…
}
```
No exemplo anterior, o atributo salario da classe Funcionario não será exibido nas respostas JSON e o problema estaria solucionado.

Entretanto, pode acontecer de existir algum outro endpoint da API na qual precisamos enviar no JSON o salário dos funcionários, sendo que nesse caso teríamos problemas, pois com a anotação @JsonIgnore tal atributo nunca será enviado no JSON, e ao remover a anotação o atributo sempre será enviado. Perdemos, com isso, a flexibilidade de controlar quando determinados atributos devem ser enviados no JSON e quando não.

### DTO
O padrão DTO (Data Transfer Object) é um padrão de arquitetura que era bastante utilizado antigamente em aplicações Java distribuídas (arquitetura cliente/servidor) para representar os dados que eram enviados e recebidos entre as aplicações cliente e servidor.

O padrão DTO pode (e deve) ser utilizado quando não queremos expor todos os atributos de alguma entidade do nosso projeto, situação igual a dos salários dos funcionários mostrado no exemplo de código anterior. Além disso, com a flexibilidade e a opção de filtrar quais dados serão transmitidos, podemos poupar tempo de processamento.

### Loop infinito causando StackOverflowError
Outro problema muito recorrente ao se trabalhar diretamente com entidades JPA acontece quando uma entidade possui algum autorrelacionamento ou relacionamento bidirecional. Por exemplo, considere as seguintes entidades JPA:
```
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Produto")
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = “id_categoria”)
    private Categoria categoria;

    //restante do código omitido…
}
```

```
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Categoria")
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = “categoria”)
    private List<Produto> produtos = new ArrayList<>();

    //restante do código omitido…
}
```
Ao retornar um objeto do tipo Produto no Controller, o Spring teria problemas para gerar o JSON desse objeto, causando uma exception do tipo StackOverflowError. Esse problema ocorre porque o objeto produto tem um atributo do tipo Categoria, que por sua vez tem um atributo do tipo List<Produto>, causando assim um loop infinito no processo de serialização para JSON.

Tal problema pode ser resolvido com a utilização da anotação @JsonIgnore ou com a utilização das anotações @JsonBackReference e @JsonManagedReference, mas também poderia ser evitado com a utilização de um DTO que representa apenas os dados que devem ser devolvidos no JSON.

## Para saber mais: parâmetros de paginação
Conforme aprendemos nos vídeos anteriores, por padrão, os parâmetros utilizados para realizar a paginação e a ordenação devem se chamar page, size e sort. Entretanto, o Spring Boot permite que os nomes de tais parâmetros sejam modificados via configuração no arquivo application.properties.

Por exemplo, poderíamos traduzir para português os nomes desses parâmetros com as seguintes propriedades:
```
spring.data.web.pageable.page-parameter=pagina
spring.data.web.pageable.size-parameter=tamanho
spring.data.web.sort.sort-parameter=ordem
```
Com isso, nas requisições que utilizam paginação, devemos utilizar esses nomes que foram definidos. Por exemplo, para listar os médicos de nossa API trazendo apenas 5 registros da página 2, ordenados pelo e-mail e de maneira decrescente, a URL da requisição deve ser:
```
http://localhost:8080/medicos?tamanho=5&pagina=1&ordem=email,desc
```
