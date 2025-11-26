
**explicação de como o sistema vai funcionar, processo lógico**

O sistema funciona como uma forma de organizar tudo o que a lanchonete faz no atendimento. Primeiro, ele permite cadastrar todos os produtos que serão vendidos, colocando nome, descrição, preço e categoria. Também é possível registrar os clientes, guardando suas informações para quando fizerem pedidos. 

Quando um pedido é criado, o usuário cliente escolhe os produtos desejados. O sistema soma automaticamente os valores dos itens e mostra o total. Cada pedido recebe um estado que mostra em que fase ele está, como “em produção”, “pronto” ou “entregue”. Esse estado pode ser modificado conforme o pedido avança, e tudo isso fica registrado.

Na parte visual, o sistema possui telas simples, onde o usuário funcionário consegue cadastrar produtos e clientes, criar pedidos novos e acompanhar os pedidos que já foram feitos. **Há também a possibilidade de gerar arquivos de relatório mostrando, por exemplo, pedidos por cliente ou por situação.**

Por trás das telas, o sistema foi organizado de forma que cada parte tenha sua responsabilidade: algumas partes cuidam dos dados, outras cuidam da lógica de funcionamento e outras da comunicação com o usuário. Há ainda testes que verificam se as funções mais importantes estão funcionando corretamente.

No fim, o sistema transforma todo o processo manual da lanchonete em algo digital, organizado e fácil de acompanhar, reduzindo erros e trazendo mais agilidade ao atendimento.

Todas as informações ficam salvas no banco de dados.

**mais ou menos um tuturial de como usar o sistema, não precisa ser tão detalhado**

Ao abrir o sistema pela primeira vez, você verá a tela de login A primeira coisa que normalmente se faz é cadastrar os produtos. Para isso, basta ir até a tela de produtos e preencher as informações pedidas, como o nome do item, uma breve descrição e o preço. Depois de salvar, o produto já passa a fazer parte da lista e pode ser usado nos pedidos.

Da mesma forma, existe uma tela para cadastrar clientes. Nela você coloca os dados básicos do cliente e salva. Assim como nos produtos, os clientes aparecem em uma lista e ficam prontos para serem usados toda vez que for feito um pedido.

Quando alguém faz um pedido na lanchonete, você vai até a tela de pedidos. Lá, primeiro escolhe o cliente que está comprando. Em seguida, adiciona os produtos que ele quer. A cada produto que for colocado, o sistema já mostra o valor atualizado do pedido. Depois que tudo estiver certo, o pedido é criado e aparece na lista geral de pedidos.

Sempre que um pedido estiver sendo preparado, ficar pronto ou for entregue, você pode abrir a tela dele e mudar o estado em que ele está. Isso ajuda a acompanhar melhor o andamento de cada um e permite que outras pessoas também saibam o que está acontecendo.

O sistema tem ainda uma parte que permite consultar tudo o que foi cadastrado. Você pode ver a lista de clientes, a lista de produtos e a lista de pedidos já feitos. Também é possível gerar arquivos com informações importantes, como pedidos feitos por cada cliente ou pedidos separados pelo estado atual.

Todas as telas foram feitas para que qualquer pessoa consiga usar sem dificuldade. Os botões ficam bem visíveis, os campos são claros e tudo funciona de maneira direta: você preenche, confirma e o sistema guarda as informações. Assim, ele ajuda a lanchonete a trabalhar de maneira mais rápida, organizada e sem confusão, substituindo as anotações em papel por um processo mais simples e seguro.

**requisitos funcionais**

Código			Requisitos Funcionais					
RF01	O sistema deve permitir adicionar novos produtos.					
RF02	O sistema deve mostrar todos os produtos cadastrados.					
RF03	O sistema deve permitir alterar as informações de um produto.					
RF04	O sistema deve permitir remover um produto.					
RF05	O sistema deve permitir registrar novos clientes.					
RF06	O sistema deve mostrar todos os clientes cadastrados.					
RF07	O sistema deve permitir alterar as informações de um cliente.					
RF08	O sistema deve permitir excluir um cliente.					
RF09	O sistema deve permitir criar um pedido escolhendo um cliente e os produtos.					
RF10	O sistema deve mostrar todos os pedidos feitos.					
RF11	O sistema deve permitir alterar informações de um pedido.					
RF12	O sistema deve permitir excluir um pedido.					
RF13	O sistema deve permitir adicionar produtos dentro do pedido.					
RF14	O sistema deve permitir mudar o status do pedido.					
RF15	O sistema deve realizar salvamentos e buscas de forma segura.					
RF16	O sistema deve permitir que usuários façam login.					
RF17	O sistema deve ter uma tela para cadastrar e consultar produtos.					
RF18	O sistema deve ter uma tela para cadastrar e consultar clientes.					
RF19	O sistema deve ter uma tela para criar pedidos e atualizar o status.	

**diagramas de casos de uso, classe, atividade**

