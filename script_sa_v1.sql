
DROP DATABASE IF EXISTS sa;
CREATE DATABASE sa;


CREATE TABLE IF NOT EXISTS `sa`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nm_cliente` VARCHAR(45) NOT NULL,
  `dt_nascimento` DATE NOT NULL,
  `nr_cpf` VARCHAR(14) NOT NULL,
  `nr_telefone` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `sa`.`pedido` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `dt_pedido` DATE NOT NULL,
  `vl_total` DOUBLE(9,2) NOT NULL,
  `st_pedido` VARCHAR(95) NOT NULL,
  `cliente_id_cliente` INT NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `fk_pedido_cliente1_idx` (`cliente_id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente_id_cliente`)
    REFERENCES `sa`.`cliente` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `sa`.`produto` (
  `id_produto` INT NOT NULL AUTO_INCREMENT,
  `ds_produto` VARCHAR(95) NOT NULL,
  `nm_produto` VARCHAR(45) NOT NULL,
  `vl_unitario` DOUBLE(9,2) NOT NULL,
  PRIMARY KEY (`id_produto`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `sa`.`itemPedido` (
  `id_itemPedido` INT NOT NULL AUTO_INCREMENT,
  `produto_id_produto` INT NOT NULL,
  `pedido_id_pedido` INT NOT NULL,
  `nr_qtd` INT NOT NULL,
  `vl_unitario` DOUBLE(9,2) NOT NULL,
  INDEX `fk_itemPedido_produto_idx` (`produto_id_produto` ASC) VISIBLE,
  INDEX `fk_itemPedido_pedido1_idx` (`pedido_id_pedido` ASC) VISIBLE,
  PRIMARY KEY (`id_itemPedido`),
  CONSTRAINT `fk_itemPedido_produto`
    FOREIGN KEY (`produto_id_produto`)
    REFERENCES `sa`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itemPedido_pedido1`
    FOREIGN KEY (`pedido_id_pedido`)
    REFERENCES `sa`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



