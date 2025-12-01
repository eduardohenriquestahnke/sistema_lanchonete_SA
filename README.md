🍔 Sistema de Gestão de Lanchonete
📋 Descrição do Projeto
Sistema completo de gestão para lanchonetes, desenvolvido para gerenciar todo o fluxo de pedidos, clientes, produtos e avaliações de forma organizada e eficiente.

🎯 Funcionalidades Principais
Gestão de Usuários: Controle de acesso com diferentes níveis de permissão

Cadastro de Clientes: Registro completo com múltiplos endereços

Cardápio Digital: Organização de produtos por categorias

Sistema de Pedidos: Acompanhamento completo do ciclo do pedido

Cupons de Desconto: Sistema promocional com validade e status

Avaliações: Feedback dos clientes sobre os pedidos realizados

Relatórios: Consultas organizadas para acompanhamento do negócio

🏗️ Estrutura do Banco de Dados
Tabelas Principais
Tabela	Descrição
t_sgl_usuario	Usuários do sistema com níveis de acesso
t_sgl_cliente	Clientes cadastrados na lanchonete
t_sgl_produto	Produtos do cardápio organizados por categoria
t_sgl_pedido	Registro de todos os pedidos realizados
t_sgl_ItemPedido	Itens específicos de cada pedido
t_sgl_cupom	Cupons de desconto disponíveis
t_sgl_avaliacao	Avaliações dos clientes sobre os pedidos
Relacionamentos Chave
Cada cliente está vinculado a um usuário

Cada pedido pertence a um cliente e pode ter um cupom

Cada item do pedido está ligado a um produto específico

Cada produto pertence a uma categoria

Cada endereço está associado a uma cidade e estado

🚀 Como Funciona o Fluxo
Cadastro Inicial

Funcionários criam categorias de produtos

Produtos são cadastrados com preços

Clientes se registram no sistema

Realização do Pedido

Cliente acessa o sistema

Seleciona produtos desejados

Aplica cupom (se disponível)

Escolhe forma de pagamento

Confirma o pedido

Acompanhamento

Status do pedido é atualizado em tempo real

Cliente pode acompanhar cada etapa

Após entrega, pode deixar avaliação

🔧 Tecnologias Utilizadas
Backend: Java Spring Boot / Node.js

Banco de Dados: PostgreSQL / MySQL

Frontend: React / Angular

Autenticação: JWT Tokens

Documentação: Swagger/OpenAPI

📊 Principais Consultas
sql
-- Pedidos de um cliente específico
SELECT * FROM t_sgl_pedido WHERE id_cliente = ?;

-- Produtos mais vendidos
SELECT p.nm_produto, COUNT(ip.id_itemPedido) as total_vendido
FROM t_sgl_produto p
JOIN t_sgl_ItemPedido ip ON p.id_produto = ip.id_produto
GROUP BY p.id_produto
ORDER BY total_vendido DESC;

-- Cupons válidos
SELECT * FROM t_sgl_cupom 
WHERE dt_validade >= CURDATE() 
AND id_statusCupom = ?;

-- Avaliações por nota
SELECT nr_nota, COUNT(*) as total
FROM t_sgl_avaliacao
GROUP BY nr_nota
ORDER BY nr_nota DESC;
🛠️ Configuração do Ambiente
Pré-requisitos
Java 11+ ou Node.js 16+

Banco de dados PostgreSQL 12+

Maven ou npm/yarn

Passos para Executar
Clone o repositório

bash
git clone https://github.com/seu-usuario/sistema-lanchonete.git
Configure o banco de dados

sql
CREATE DATABASE lanchonete_db;
Configure as variáveis de ambiente

env
DB_URL=jdbc:postgresql://localhost:5432/lanchonete_db
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
JWT_SECRET=sua_chave_secreta
Execute a aplicação

bash
mvn spring-boot:run
# ou
npm start
👥 Níveis de Acesso
Nível	Permissões
Administrador	Acesso total ao sistema
Gerente	Cadastro de produtos e cupons
Atendente	Registrar pedidos, atualizar status
Cliente	Fazer pedidos, avaliar, ver histórico
📈 Benefícios do Sistema
Organização: Tudo registrado e fácil de encontrar

Controle: Acompanhamento preciso dos pedidos

Insights: Dados para melhorar o negócio

Experiência: Processo simples para o cliente

Segurança: Dados protegidos e acessos controlados

🤝 Contribuindo
Faça um fork do projeto

Crie uma branch para sua feature

Commit suas mudanças

Push para a branch

Abra um Pull Request

📄 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

📞 Suporte
Para dúvidas ou sugestões:

Abra uma issue

Entre em contato: suporte@lanchonete.com

Desenvolvido com ❤️ para lanchonetes que querem crescer de forma organizada!

