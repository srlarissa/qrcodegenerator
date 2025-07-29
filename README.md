![Licença MIT](https://img.shields.io/badge/Licença-MIT-green.svg)

# QR Code Generator com Spring Boot e AWS S3

---

## Descrição

---

Este projeto é uma aplicação Spring Boot que gera QR Codes e os armazena em um bucket do Amazon S3. A aplicação contém uma API REST para receber solicitações de geração de QR Codes, processa essas solicitações e retorna a URL do QR Code armazenado no S3.

---
## O que ter em mente antes de abrir um Pool Request?

---

- Crie um fork do repositório e uma branch para as suas alterações.
- Faça commit das suas alterações com uma mensagem clara e descritiva.
- Escolha um título claro e descritivo.
- Descreva claramente o que foi alterado e por quê.
- Certifique-se de que o código segue as diretrizes de estilo do projeto.
 
---
## Pré-requisitos

---

- **Java 21** ou superior.
- **Maven 3.9.11** ou superior.
- **Spring Boot 3.2.6** ou superior.
- **Docker** (opcional).
- Conta na AWS com credenciais configuradas para acesso ao S3.

---
## Setup de ambiente

---

1. Abra o terminal e digite:
    ```bash
    git clone git@github.com:srlarissa/qrcodegenerator.git
    ``` 
2. Navegue até o diretório do projeto:
   ```bash
   cd qrcodegenerator
   ```
3. Certifique-se de que os pré-requisitos estão instalados e configurados corretamente.
   - Verifique a versão do Java:
   ```bash
   java -version
   ```
   - Verifique a versão do Maven:
   ```bash
   mvn -v
   ```
4. Instale as dependências do projeto:
   ```bash
   ./mvnw clean install
   ```
5. Configure seu bucket S3 e as credenciais da AWS de acordo com a [documentação da AWS](https://docs.aws.amazon.com/pt_br/s3/?icmpid=docs_homepage_featuredsvcs).
6. Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis de ambiente:

   ```plaintext
   AWS_ACCESS_KEY_ID=seu-access-key
   AWS_SECRET_ACCESS_KEY=sua-secret-key
   ```

---
## Executando a aplicação

---

### Utilizando Docker:

1. Certifique-se de que o Docker está instalado e em execução.
2. Construa a imagem Docker:
   ```bash
   docker build -t qrcode-generator:1.0 .
   ```
3. Execute o container Docker:
   ```bash
   docker run -p 8080:8080 --env-file .env qrcode-generator:1.0
   ```
   **Obs:** Cada mudança no código requer a reconstrução da imagem Docker, o que significa a mudança do número da versão da imagem.
4. Acesse a aplicação em `http://localhost:8080/qrcode` utilizando um cliente HTTP como Postman ou cURL.
5. Passe o seguinte JSON no corpo da requisição:

   ```json
   {
     "text": "URL ou texto para gerar QR Code"
   }
   ```
6. A resposta será um JSON contendo a URL do QR Code gerado e armazenado no S3.

---
## Arquitetura e Estrutura do Projeto

---

### Decisões de Arquitetura:

---

A aplicação foi estruturada seguindo as melhores práticas de desenvolvimento com Spring Boot, utilizando uma arquitetura limpa e modular.
- **Arquitetura em Camadas**: Separação de responsabilidades entre camadas bem definidas com controladores para lidar com apresentação, serviços e adaptadores para lidar com estruturas externas;
- **Injeção de Dependência**: Utilizando o Spring Boot para o gerenciamento das dependências e a injeção de dependências;
- **DTOs (Data Transfer Objects)**: Utilização de objetos de transferência de dados para comunicação com a infraestrutura externa além de entrada e saída de dados.

---
### AWS S3

---

AWS S3 foi escolhida principalmente pelo potencial de escalabilidade. Ao delegar o armazenamento de arquivos para o AWS S3, a aplicação em si (lógica de negócio) fica isolada da infraestrutura  de gerenciamento local de arquivos, respeitando o SRP (Single Responsibility Principle).

---
### Docker

---
Docker garante que a aplicação seja executada sempre no mesmo ambiente, independentemente de onde ela esteja sendo executada, mitigando erros relacionados a compatibilidade. Além disso, utilizando ambientes em nuvem, aplicações em contêiner podem ser escaladas mais facilmente. 

---
### Google ZXing

---
Google ZXing foi escolhida por ser a mais consolidada em Java. É mantida pela comunidade desde que foi criada pelo Google, portanto, é madura, amplamente adotada e com documentação sólida.

---
## Estrutura do Projeto

---
- **`QrcodeGeneratorApplication`**: Classe principal que inicializa a aplicação Spring Boot.
- **`QrCodeGeneratorService`**: Serviço responsável pela lógica de geração de QR Codes através da biblioteca [Google ZXing](https://zxing.github.io/zxing/apidocs/);
- **`QrCodeController`**: Controlador dos endpoints para geração de QR Codes.
- **`S3StorageAdapter`**: Adaptador para integração com o AWS S3.
- **DTOs**: Objetos de transferência de dados, como `QrCodeGenerateRequest` e `QrCodeGenerateResponse`.
- **`application.properties`**: Configurações da aplicação, incluindo as credenciais do AWS S3.

---
## Documentação da API

---
A documentação da API está disponível através do Swagger UI. Após iniciar a aplicação, acesse `http://localhost:8080/swagger-ui/index.html` para visualizar os endpoints disponíveis e testar suas funcionalidades.