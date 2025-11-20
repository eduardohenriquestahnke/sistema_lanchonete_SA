DROP DATABASE IF EXISTS db_sa;
CREATE DATABASE db_sa;
USE db_sa;

-- StatusPedido
CREATE TABLE t_sgl_statusPedido (
  id_statusPedido INT NOT NULL AUTO_INCREMENT,
  ds_statusPedido VARCHAR(20) NOT NULL,
  CONSTRAINT PK_STATUSPEDIDO PRIMARY KEY (id_statusPedido)
);

-- FormaPagamento
CREATE TABLE t_sgl_formaPagamento (
  id_formaPagamento INT NOT NULL AUTO_INCREMENT,
  nm_descricao VARCHAR(150) NOT NULL,
  CONSTRAINT PK_FORMAPAGAMENTO PRIMARY KEY (id_formaPagamento)
);

-- NivelAcesso
CREATE TABLE t_sgl_nivelAcesso (
  id_nivelAcesso INT NOT NULL AUTO_INCREMENT,
  ds_nivelAcesso VARCHAR(150) NOT NULL,
  CONSTRAINT PK_NIVELACESSO PRIMARY KEY (id_nivelAcesso)
);

-- Usuario
CREATE TABLE t_sgl_usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  id_nivelAcesso INT NOT NULL,
  nm_usuario VARCHAR(150) NOT NULL,
  ds_login VARCHAR(100) UNIQUE NOT NULL,
  ds_senha VARCHAR(250) NOT NULL,
  CONSTRAINT PK_USUARIO PRIMARY KEY (id_usuario),
  CONSTRAINT UN_USUARIO_LOGIN UNIQUE (ds_login),
  CONSTRAINT FK_USUARIO__NIVELACESSO
    FOREIGN KEY (id_nivelAcesso)
    REFERENCES t_sgl_nivelAcesso (id_nivelAcesso)
);

-- Cliente
CREATE TABLE t_sgl_cliente (
  id_cliente INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  nr_cpf VARCHAR(14) NOT NULL,
  nm_cliente VARCHAR(45) NOT NULL,
  nr_telefone VARCHAR(12) NOT NULL,
  dt_nascimento DATE NOT NULL,
  CONSTRAINT PK_CLIENTE PRIMARY KEY (id_cliente),
  CONSTRAINT UN_CLIENTE_CPF UNIQUE (nr_cpf),
  CONSTRAINT FK_CLIENTE__USUARIO
    FOREIGN KEY (id_usuario)
    REFERENCES t_sgl_usuario (id_usuario)
);

-- StatusCupom
CREATE TABLE t_sgl_statusCupom (
  id_statusCupom INT NOT NULL AUTO_INCREMENT,
  ds_statusCupom VARCHAR(20) NOT NULL,
  CONSTRAINT PK_STATUSCUPOM PRIMARY KEY (id_statusCupom)
);

-- Cupom
CREATE TABLE t_sgl_cupom (
  id_cupom INT NOT NULL AUTO_INCREMENT,
  id_statusCupom INT NOT NULL,
  nm_cupom VARCHAR(95) NOT NULL,
  vl_desconto DECIMAL(5,2) NOT NULL,
  dt_validade DATE NOT NULL,
  CONSTRAINT PK_CUPOM PRIMARY KEY (id_cupom),
  CONSTRAINT FK_CUPOM__STATUSCUPOM
    FOREIGN KEY (id_statusCupom)
    REFERENCES t_sgl_statusCupom (id_statusCupom)
);

-- Pedido
CREATE TABLE t_sgl_pedido (
  id_pedido INT NOT NULL AUTO_INCREMENT,
  id_statusPedido INT NOT NULL,
  id_formaPagamento INT NOT NULL,
  id_cliente INT NOT NULL,
  id_cupom INT NULL,
  dt_pedido DATETIME NOT NULL,
  CONSTRAINT PK_PEDIDO PRIMARY KEY (id_pedido),
  CONSTRAINT FK_PEDIDO__STATUSPEDIDO
    FOREIGN KEY (id_statusPedido)
    REFERENCES t_sgl_statusPedido (id_statusPedido),
  CONSTRAINT FK_PEDIDO__FORMAPAGAMENTO
    FOREIGN KEY (id_formaPagamento)
    REFERENCES t_sgl_formaPagamento (id_formaPagamento),
  CONSTRAINT FK_PEDIDO__CLIENTE
    FOREIGN KEY (id_cliente)
    REFERENCES t_sgl_cliente (id_cliente),
  CONSTRAINT FK_PEDIDO__CUPOM
    FOREIGN KEY (id_cupom)
    REFERENCES t_sgl_cupom (id_cupom)
);

-- CategoriaProduto
CREATE TABLE t_sgl_categoriaProduto (
  id_categoriaProduto INT NOT NULL AUTO_INCREMENT,
  ds_descricao VARCHAR(150) NOT NULL,
  CONSTRAINT PK_CATEGORIAPRODUTO PRIMARY KEY (id_categoriaProduto)
);

-- Produto
CREATE TABLE t_sgl_produto (
  id_produto INT NOT NULL AUTO_INCREMENT,
  id_categoriaProduto INT NOT NULL,
  ds_produto VARCHAR(250) NOT NULL,
  nm_produto VARCHAR(90) NOT NULL,
  vl_unitario DECIMAL(5,2) NOT NULL,
  CONSTRAINT PK_PRODUTO PRIMARY KEY (id_produto),
  CONSTRAINT FK_PRODUTO__CATEGORIAPRODUTO
    FOREIGN KEY (id_categoriaProduto)
    REFERENCES t_sgl_categoriaProduto (id_categoriaProduto)
);

-- ItemPedido
CREATE TABLE t_sgl_itemPedido (
  id_itemPedido INT NOT NULL AUTO_INCREMENT,
  id_produto INT NOT NULL,
  id_pedido INT NOT NULL,
  qt_itemProduto INT NOT NULL,
  vl_unitario DECIMAL(5,2) NOT NULL,
  CONSTRAINT PK_ITEMPEDIDO PRIMARY KEY (id_itemPedido, id_produto, id_pedido),
  CONSTRAINT FK_ITEMPEDIDO__PRODUTO
    FOREIGN KEY (id_produto)
    REFERENCES t_sgl_produto (id_produto),
  CONSTRAINT FK_ITEMPEDIDO__PEDIDO
    FOREIGN KEY (id_pedido)
    REFERENCES t_sgl_pedido (id_pedido)
);

-- Estado
CREATE TABLE t_sgl_estado (
  id_estado INT NOT NULL AUTO_INCREMENT,
  nm_estado VARCHAR(15) NOT NULL,
  sg_estado CHAR(2) NOT NULL,
  CONSTRAINT PK_ESTADO PRIMARY KEY (id_estado)
);

-- Cidade
CREATE TABLE t_sgl_cidade (
  id_cidade INT NOT NULL AUTO_INCREMENT,
  id_estado INT NOT NULL,
  nm_cidade VARCHAR(150) NOT NULL,
  CONSTRAINT PK_CIDADE PRIMARY KEY (id_cidade),
  CONSTRAINT FK_CIDADE__ESTADO
    FOREIGN KEY (id_estado)
    REFERENCES t_sgl_estado (id_estado)
);

-- TipoEndereco
CREATE TABLE t_sgl_tipoEndereco (
  id_tipoEndereco INT NOT NULL AUTO_INCREMENT,
  ds_tipoEndereco VARCHAR(150) NOT NULL,
  CONSTRAINT PK_TIPOENDERECO PRIMARY KEY (id_tipoEndereco)
);

-- Endereco
CREATE TABLE t_sgl_endereco (
  id_endereco INT NOT NULL AUTO_INCREMENT,
  id_cidade INT NOT NULL,
  id_tipoEndereco INT NOT NULL,
  id_cliente INT NOT NULL,
  nm_bairro VARCHAR(100) NOT NULL,
  nm_logradouro VARCHAR(150) NOT NULL,
  nr_residencia VARCHAR(10) NOT NULL,
  nr_cep VARCHAR(10) NOT NULL,
  ob_complemento VARCHAR(150) NULL,
  CONSTRAINT PK_ENDERECO PRIMARY KEY (id_endereco),
  CONSTRAINT FK_ENDERECO__CIDADE
    FOREIGN KEY (id_cidade)
    REFERENCES t_sgl_cidade (id_cidade),
  CONSTRAINT FK_ENDERECO__TIPOENDERECO
    FOREIGN KEY (id_tipoEndereco)
    REFERENCES t_sgl_tipoEndereco (id_tipoEndereco),
  CONSTRAINT FK_ENDERECO__CLIENTE
    FOREIGN KEY (id_cliente)
    REFERENCES t_sgl_cliente (id_cliente)
);

-- Avaliacao
CREATE TABLE t_sgl_avaliacao (
  id_avaliacao INT NOT NULL AUTO_INCREMENT,
  id_pedido INT NOT NULL,
  nr_nota DECIMAL(2,1) NOT NULL,
  ob_comentario VARCHAR(250) NOT NULL,
  dt_avaliacao DATETIME NOT NULL,
  CONSTRAINT PK_AVALIACAO PRIMARY KEY (id_avaliacao),
  CONSTRAINT FK_AVALIACAO__PEDIDO
    FOREIGN KEY (id_pedido)
    REFERENCES t_sgl_pedido (id_pedido)
);
