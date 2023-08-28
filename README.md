# Challenge-3

O Projeto tem o objetivo de consumir a seguinte [API externa](https://jsonplaceholder.typicode.com/) e cumprir com os requisitos abaixo.

# API / Features

1. Process Post
    - Description: Processes a post.
    - Method: POST
    - Path: /posts/{postId}
    - Requirements:
        - postId must be a number between 1 and 100.
        - Existing postId should not be accepted.
2. Disable Post
    - Description: Disables a post that is in the "ENABLED" state.
    - Method: DELETE
    - Path: /posts/{postId}
    - Requirements:
        - postId must be a number between 1 and 100.
        - postId should be in the "ENABLED" state.
3. Reprocess Post
    - Description: Reprocesses a post that is in the "ENABLED" or "DISABLED" state.
    - Method: PUT
    - Path: /posts/{postId}
    - Requirements:
        - postId must be a number between 1 and 100.
        - postId should be in the "ENABLED" or "DISABLED" state.
4. Query Posts
    - Description: Provides a list of posts.
    - Method: GET
    - Path: /posts
    - Response:

![image](https://github.com/fernando427/Pos_Desafio/assets/48501389/970a6c8d-e7bf-476e-bd9b-07c1b4ff78b4)

- **`title`**: Can be null or empty depending on the state.
- **`body`**: Can be null or empty depending on the state.
- **`comments`**: Can be null or empty depending on the state.
- **`history`**: Cannot be null or empty; it must always have a value.

# Estrutura do Projeto

![image](https://github.com/fernando427/Pos_Desafio/assets/48501389/7be56a2d-8171-4cbc-bc89-ee1dbcd1700f)

# Estrutura do Postman

![image](https://github.com/fernando427/Pos_Desafio/assets/48501389/c44c7b3b-df1e-4de9-9c29-537ceb84a9b1)

# 1. Process Post

![image](https://github.com/fernando427/Pos_Desafio/assets/48501389/9f7dbdb1-dddc-49a6-bf6c-ee710ddc1fc4)

A implementação da classe service para salvar um Post que está vindo através do uso do FeignClient. Também incluindo a criação do history dessa ação para que o mesmo não fique como nulo. O history estará sendo atualizado sempre que ocorrer um Process, Reprocess ou Disable.

![image](https://github.com/fernando427/Pos_Desafio/assets/48501389/49999036-3c51-49b0-b963-c0363bb5fee0)

Aqui algumas exceções foram criadas para sempre tratadas pelo handle global na pasta de exceptions.

![image](https://github.com/fernando427/Challenge-3/assets/48501389/46ca38a0-5544-4b3e-be15-487b57b39332)

O controller estando o mais limpo possível, seguindo o padrão de arquitetura limpa.

![image](https://github.com/fernando427/Pos_Desafio/assets/48501389/de942997-6c54-4e25-b9b5-157b4b622dfd)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/bd1c157f-b0d1-4681-abcc-245febe4a35e)

Apesar de que na criação aparece o history como nulo, ele foi adicionado ao banco e é possível visuar sua área usando o get.

![image](https://github.com/fernando427/Challenge-3/assets/48501389/4f8d242f-aa90-4a71-894f-67a92fbf248a)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/f4487e15-70cd-43cf-a749-c0f57f9e3b68)

Aqui alguns exemplos de exceções que podem acontecer caso seja um id inválido ou criar um Post já existente.

# 2. Disable Post

![image](https://github.com/fernando427/Challenge-3/assets/48501389/6f97bfc2-4830-4e7a-a427-010aafc18691)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/70af5720-8d9c-404f-8983-9cf3302398a0)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/40c6041e-d1e2-4922-8794-34534dae761c)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/7e8b1c1d-61e5-4b82-aaa6-2c18d09d6324)

O código implementado no service do disable, seu controller e o seu uso no Postman. Importante saber que enquanto um Post estiver como disable, o mesmo não será mostrado na listagem.

# 3. Reprocess Post

![image](https://github.com/fernando427/Challenge-3/assets/48501389/a971653a-7713-43f1-b1d8-c14ac864e4ba)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/0822c3d4-dda7-4633-8ada-74f54e0395e4)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/0e99f137-89ea-473f-bd6c-0820c5dc0806)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/fa2765c2-b4cc-4958-9802-37a9aea17629)

O código implementado no service do reprocess, seu controller e seu respectivo uso no Postman. Importante saber que todo reprocesso será registrado no history, independente se for um disable ou um enable. Quando for um disable ele será atualizado para um enable.

# 4. Query Posts

![image](https://github.com/fernando427/Challenge-3/assets/48501389/98235550-8cb5-486d-979a-1db260caf95a)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/ed0424ce-e9be-4748-8373-1c9fa4a9dc75)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/47713eed-70d6-4b0a-a2db-d49c701fd359)

O código implementado no service do query posts, seu controlador e seu respectivo uso no Postman. Importante saber que quando não houver request params no Postman, os valores padrão para a página será 0 e 10 para o tamanho da página.

![image](https://github.com/fernando427/Challenge-3/assets/48501389/02bce97a-7342-449f-8cf8-176e5a981c20)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/abd6517b-71b4-419f-94e6-3f42c6a22049)

![image](https://github.com/fernando427/Challenge-3/assets/48501389/4aba680a-d302-4202-8fab-6a156ba9a101)

Também é possível adicionar comentários, enriquecendo o Post e, podendo tudo ser visualizado no query posts.

