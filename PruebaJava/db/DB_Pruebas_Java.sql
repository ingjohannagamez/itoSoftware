-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: db_pruebas_java
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mercancias`
--

DROP TABLE IF EXISTS `mercancias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mercancias` (
  `id` int(11) NOT NULL,
  `nombre_producto` varchar(20) DEFAULT NULL,
  `ciudad_destino` varchar(20) DEFAULT NULL,
  `direccion` varchar(20) DEFAULT NULL,
  `fecha_salida` time DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `estado_envio` varchar(10) DEFAULT NULL,
  `destinatario_id` int(11) DEFAULT NULL,
  `usuario_registro_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mercancias_idx` (`usuario_registro_id`),
  KEY `mercancias2_idx` (`destinatario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mercancias`
--

LOCK TABLES `mercancias` WRITE;
/*!40000 ALTER TABLE `mercancias` DISABLE KEYS */;
/*!40000 ALTER TABLE `mercancias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfiles` (
  `id` time NOT NULL,
  `perfil` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `id` time NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellido` varchar(20) DEFAULT NULL,
  `numero_documento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(20) DEFAULT NULL,
  `perfil_id` int(11) DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `contrasena` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuarios_perfil_id_idx` (`perfil_id`),
  KEY `usuarios_idx` (`persona_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_pruebas_java'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-28  9:27:10


ALTER TABLE `afsolu_db_pruebas_java`.`perfiles` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL ,
CHANGE COLUMN `perfil` `perfil` VARCHAR(20) NOT NULL ,
ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `afsolu_db_pruebas_java`.`perfiles` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `afsolu_db_pruebas_java`.`personas` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `numero_documento` `numero_documento` INT(11) NOT NULL ,
ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `afsolu_db_pruebas_java`.`usuarios` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `usuario` `usuario` VARCHAR(20) NOT NULL ,
CHANGE COLUMN `perfil_id` `perfil_id` INT(11) NOT NULL ,
CHANGE COLUMN `persona_id` `persona_id` INT(11) NOT NULL ,
CHANGE COLUMN `contrasena` `contrasena` VARCHAR(20) NOT NULL ,
ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC),
ADD UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC),
ADD UNIQUE INDEX `persona_id_UNIQUE` (`persona_id` ASC);

ALTER TABLE `afsolu_db_pruebas_java`.`personas` 
ADD UNIQUE INDEX `numero_documento_UNIQUE` (`numero_documento` ASC);

ALTER TABLE `afsolu_db_pruebas_java`.`mercancias` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ,
ADD UNIQUE INDEX `id_UNIQUE` (`id` ASC);

ALTER TABLE `afsolu_db_pruebas_java`.`usuarios` 
ADD CONSTRAINT `usuario_fk_perfil`
  FOREIGN KEY (`perfil_id`)
  REFERENCES `afsolu_db_pruebas_java`.`perfiles` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT `usuario_fk_persona`
  FOREIGN KEY (`persona_id`)
  REFERENCES `afsolu_db_pruebas_java`.`personas` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
ALTER TABLE `afsolu_db_pruebas_java`.`mercancias` 
ADD CONSTRAINT `mercancia_fk_persona`
  FOREIGN KEY (`destinatario_id`)
  REFERENCES `afsolu_db_pruebas_java`.`personas` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE,
ADD CONSTRAINT `mercancia_fk_usuario`
  FOREIGN KEY (`usuario_registro_id`)
  REFERENCES `afsolu_db_pruebas_java`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;
  
ALTER TABLE `afsolu_db_pruebas_java`.`usuarios` 
DROP FOREIGN KEY `usuario_fk_perfil`,
DROP FOREIGN KEY `usuario_fk_persona`;
ALTER TABLE `afsolu_db_pruebas_java`.`usuarios` 
ADD CONSTRAINT `usuario_fk_perfil`
  FOREIGN KEY (`perfil_id`)
  REFERENCES `afsolu_db_pruebas_java`.`perfiles` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE,
ADD CONSTRAINT `usuario_fk_persona`
  FOREIGN KEY (`persona_id`)
  REFERENCES `afsolu_db_pruebas_java`.`personas` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;
  
ALTER TABLE `afsolu_db_pruebas_java`.`mercancias` 
DROP FOREIGN KEY `mercancia_fk_persona`,
DROP FOREIGN KEY `mercancia_fk_usuario`;
ALTER TABLE `afsolu_db_pruebas_java`.`mercancias` 
CHANGE COLUMN `fecha_salida` `fecha_salida` DATETIME NULL DEFAULT NULL ,
CHANGE COLUMN `destinatario_id` `destinatario_id` INT(11) NOT NULL ,
CHANGE COLUMN `usuario_registro_id` `usuario_registro_id` INT(11) NOT NULL ;
ALTER TABLE `afsolu_db_pruebas_java`.`mercancias` 
ADD CONSTRAINT `mercancia_fk_persona`
  FOREIGN KEY (`destinatario_id`)
  REFERENCES `afsolu_db_pruebas_java`.`personas` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE,
ADD CONSTRAINT `mercancia_fk_usuario`
  FOREIGN KEY (`usuario_registro_id`)
  REFERENCES `afsolu_db_pruebas_java`.`usuarios` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;
