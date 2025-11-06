DROP DATABASE IF EXISTS db_sa;
CREATE DATABASE db_sa;

CREATE TABLE IF NOT EXISTS `db_sa`.`nivelAcesso` (
  `id_nivelAcesso` INT NOT NULL AUTO_INCREMENT,
  `ds_nivelAcesso` VARCHAR(95) NOT NULL,
  PRIMARY KEY (`id_nivelAcesso`));
  
CREATE TABLE IF NOT EXISTS `db_sa`.`usuario` (
  `id_usuario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nivelAcesso_id_nivelAcesso` INT NOT NULL,
  `nm_usuario` VARCHAR(150) NOT NULL,
  `tx_login` VARCHAR(100) NOT NULL,
  `tx_senha` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_usuario_nivelAcesso1_idx` (`nivelAcesso_id_nivelAcesso` ASC) VISIBLE,
  UNIQUE INDEX `tx_login_UNIQUE` (`tx_login` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_nivelAcesso1`
    FOREIGN KEY (`nivelAcesso_id_nivelAcesso`)
    REFERENCES `db_sa`.`nivelAcesso` (`id_nivelAcesso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `db_sa`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `usuario_id_usuario` INT UNSIGNED NOT NULL,
  `nr_cpf` VARCHAR(14) NOT NULL,
  `nm_cliente` VARCHAR(45) NOT NULL,
  `nr_telefone` VARCHAR(12) NOT NULL,
  `dt_nascimento` DATE NOT NULL,
  PRIMARY KEY (`id_cliente`),
  INDEX `fk_cliente_usuario1_idx` (`usuario_id_usuario` ASC) VISIBLE,
  UNIQUE INDEX `nr_cpf_UNIQUE` (`nr_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_usuario1`
    FOREIGN KEY (`usuario_id_usuario`)
    REFERENCES `db_sa`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `db_sa`.`tipoCupomDesconto` (
  `id_tipoCupomDesconto` INT NOT NULL AUTO_INCREMENT,
  `ds_tipoCupomDesconto` VARCHAR(65) NOT NULL,
  PRIMARY KEY (`id_tipoCupomDesconto`));

CREATE TABLE IF NOT EXISTS `db_sa`.`statusCupomDesconto` (
  `id_statusCupomDesconto` INT NOT NULL AUTO_INCREMENT,
  `ds_statusCupomDesconto` VARCHAR(95) NOT NULL,
  PRIMARY KEY (`id_statusCupomDesconto`));

CREATE TABLE IF NOT EXISTS `db_sa`.`cupomDesconto` (
  `id_desconto` INT NOT NULL AUTO_INCREMENT,
  `tipoCupomDesconto_id_tipoCupomDesconto` INT NOT NULL,
  `statusCupomDesconto_id_statusCupomDesconto` INT NOT NULL,
  `nm_cupomDesconto` VARCHAR(95) NOT NULL,
  `vl_desconto` DECIMAL(5,2) NOT NULL,
  `dt_validade` DATE NOT NULL,
  PRIMARY KEY (`id_desconto`),
  INDEX `fk_cupomDesconto_tipoCupomDesconto1_idx` (`tipoCupomDesconto_id_tipoCupomDesconto` ASC) VISIBLE,
  INDEX `fk_cupomDesconto_statusCupomDesconto1_idx` (`statusCupomDesconto_id_statusCupomDesconto` ASC) VISIBLE,
  CONSTRAINT `fk_cupomDesconto_tipoCupomDesconto1`
    FOREIGN KEY (`tipoCupomDesconto_id_tipoCupomDesconto`)
    REFERENCES `db_sa`.`tipoCupomDesconto` (`id_tipoCupomDesconto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cupomDesconto_statusCupomDesconto1`
    FOREIGN KEY (`statusCupomDesconto_id_statusCupomDesconto`)
    REFERENCES `db_sa`.`statusCupomDesconto` (`id_statusCupomDesconto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `db_sa`.`statusPedido` (
  `id_statusPedido` INT NOT NULL AUTO_INCREMENT,
  `ds_statusPedido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_statusPedido`));

CREATE TABLE IF NOT EXISTS `db_sa`.`formaPagamento` (
  `idformaPagamento` INT NOT NULL AUTO_INCREMENT,
  `nm_descricao` VARCHAR(105) NOT NULL,
  PRIMARY KEY (`idformaPagamento`));

CREATE TABLE IF NOT EXISTS `db_sa`.`pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `cliente_id_cliente` INT NOT NULL,
  `statusPedido_id_statusPedido` INT NOT NULL,
  `formaPagamento_idformaPagamento` INT NOT NULL,
  `cupomDesconto_id_desconto` INT NULL,
  `dt_pedido` DATETIME NOT NULL,
  `vl_total` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_pedido_cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  INDEX `fk_pedido_cupomDesconto1_idx` (`cupomDesconto_id_desconto` ASC) VISIBLE,
  INDEX `fk_pedido_statusPedido1_idx` (`statusPedido_id_statusPedido` ASC) VISIBLE,
  INDEX `fk_pedido_formaPagamento1_idx` (`formaPagamento_idformaPagamento` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `db_sa`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_cupomDesconto1`
    FOREIGN KEY (`cupomDesconto_id_desconto`)
    REFERENCES `db_sa`.`cupomDesconto` (`id_desconto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_statusPedido1`
    FOREIGN KEY (`statusPedido_id_statusPedido`)
    REFERENCES `db_sa`.`statusPedido` (`id_statusPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_formaPagamento1`
    FOREIGN KEY (`formaPagamento_idformaPagamento`)
    REFERENCES `db_sa`.`formaPagamento` (`idformaPagamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `db_sa`.`categoriaProduto` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `nm_descricao` VARCHAR(95) NOT NULL,
  PRIMARY KEY (`id_categoria`));

CREATE TABLE IF NOT EXISTS `db_sa`.`produto` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `categoria_id_categoria` INT NOT NULL,
  `ds_produto` TEXT NOT NULL,
  `nm_produto` VARCHAR(45) NOT NULL,
  `vl_unitario` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id_produto`),
  INDEX `fk_produto_categoria1_idx` (`categoria_id_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_produto_categoria1`
    FOREIGN KEY (`categoria_id_categoria`)
    REFERENCES `db_sa`.`categoriaProduto` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `db_sa`.`itemPedido` (
  `id_itemPedido` INT NOT NULL AUTO_INCREMENT,
  `qt_itemProduto` INT NOT NULL,
  `vl_unitario` DECIMAL(5,2) NOT NULL,
  `produto_id_produto` INT NOT NULL,
  `pedido_id_pedido` INT NOT NULL,
  INDEX `fk_itemPedido_produto_idx` (`produto_id_produto` ASC) VISIBLE,
  INDEX `fk_itemPedido_pedido1_idx` (`pedido_id_pedido` ASC) VISIBLE,
  PRIMARY KEY (`id_itemPedido`),
  CONSTRAINT `fk_itemPedido_produto`
    FOREIGN KEY (`produto_id_produto`)
    REFERENCES `db_sa`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itemPedido_pedido1`
    FOREIGN KEY (`pedido_id_pedido`)
    REFERENCES `db_sa`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `db_sa`.`tipoEndereco` (
  `id_tipoEndereco` INT NOT NULL AUTO_INCREMENT,
  `ds_tipoEndereco` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_tipoEndereco`));

CREATE TABLE IF NOT EXISTS `db_sa`.`endereco` (
  `id_endereco` INT NOT NULL AUTO_INCREMENT,
  `tipoEndereco_id_tipoEndereco` INT NOT NULL,
  `cliente_id_cliente` INT NOT NULL,
  `nm_cidade` VARCHAR(65) NOT NULL,
  `nm_bairro` VARCHAR(65) NOT NULL,
  `nm_estado` VARCHAR(65) NOT NULL,
  `nr_residencia` VARCHAR(10) NOT NULL,
  `nr_cep` VARCHAR(9) NOT NULL,
  `ob_complemento` VARCHAR(145) NULL,
  PRIMARY KEY (`id_endereco`),
  INDEX `fk_endereco_tipoEndereco1_idx` (`tipoEndereco_id_tipoEndereco` ASC) VISIBLE,
  INDEX `fk_endereco_cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_tipoEndereco1`
    FOREIGN KEY (`tipoEndereco_id_tipoEndereco`)
    REFERENCES `db_sa`.`tipoEndereco` (`id_tipoEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `db_sa`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `db_sa`.`avaliacao` (
  `id_avaliacao` INT NOT NULL AUTO_INCREMENT,
  `pedido_id_pedido` INT NOT NULL,
  `nr_nota` DECIMAL(2,1) NOT NULL,
  `ob_comentario` VARCHAR(250) NOT NULL,
  `dt_avaliacao` DATETIME NOT NULL,
  PRIMARY KEY (`id_avaliacao`),
  INDEX `fk_avaliacao_pedido1_idx` (`pedido_id_pedido` ASC) VISIBLE,
  CONSTRAINT `fk_avaliacao_pedido1`
    FOREIGN KEY (`pedido_id_pedido`)
    REFERENCES `db_sa`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
