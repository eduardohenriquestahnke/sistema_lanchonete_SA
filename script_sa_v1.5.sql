DROP DATABASE IF EXISTS db_sa;
CREATE DATABASE db_sa;

CREATE TABLE IF NOT EXISTS `db_sa`.`endereco` (
  `id_endereco` INT NOT NULL AUTO_INCREMENT,
  `nm_cidade` VARCHAR(65) NOT NULL,
  `nm_bairro` VARCHAR(65) NOT NULL,
  `tp_endereco` CHAR(1) NOT NULL,
  `nr_residencia` INT NOT NULL,
  `ob_complemento` VARCHAR(145) NULL,
  PRIMARY KEY (`id_endereco`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_sa`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nr_cpf` VARCHAR(14) NOT NULL,
  `nm_cliente` VARCHAR(45) NOT NULL,
  `dt_nascimento` DATE NOT NULL,
  `nr_telefone` VARCHAR(12) NOT NULL,
  `endereco_id_endereco` INT NOT NULL,
  PRIMARY KEY (`id_cliente`),
  INDEX `fk_cliente_endereco1_idx` (`endereco_id_endereco` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_endereco1`
    FOREIGN KEY (`endereco_id_endereco`)
    REFERENCES `db_sa`.`endereco` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_sa`.`pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `cliente_id_cliente` INT NOT NULL,
  `dt_pedido` DATE NOT NULL,
  `vl_total` DECIMAL(9,2) NOT NULL,
  `st_pedido` VARCHAR(95) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_pedido_cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `db_sa`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_sa`.`produto` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `ds_produto` VARCHAR(95) NOT NULL,
  `nm_produto` VARCHAR(45) NOT NULL,
  `vl_unitario` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id_produto`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_sa`.`itemPedido` (
  `id_itemPedido` INT NOT NULL AUTO_INCREMENT,
  `produto_id_produto` INT NOT NULL,
  `pedido_id_pedido` INT NOT NULL,
  `nr_qtd` INT NOT NULL,
  `vl_unitario` DECIMAL(9,2) NOT NULL,
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
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_sa`.`avaliacao` (
  `id_avaliacao` INT NOT NULL AUTO_INCREMENT,
  `pedido_id_pedido` INT NOT NULL,
  `cliente_id_cliente` INT NOT NULL,
  `nr_nota` INT NOT NULL,
  `ob_comentario` VARCHAR(155) NOT NULL,
  `dt_avaliacao` DATE NOT NULL,
  PRIMARY KEY (`id_avaliacao`),
  INDEX `fk_avaliacao_pedido1_idx` (`pedido_id_pedido` ASC) VISIBLE,
  INDEX `fk_avaliacao_cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_avaliacao_pedido1`
    FOREIGN KEY (`pedido_id_pedido`)
    REFERENCES `db_sa`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_avaliacao_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `db_sa`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
