DROP DATABASE IF EXISTS `stcal`;
CREATE DATABASE IF NOT EXISTS `stcal` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `stcal`;


-- phpMyAdmin SQL Dump
-- version 4.0.0
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le: Dim 16 Mars 2014 à 19:50
-- Version du serveur: 5.6.11
-- Version de PHP: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

--
-- Base de données: `stcal`
--

-- --------------------------------------------------------

--
-- Structure de la table `Couple`
--

CREATE TABLE IF NOT EXISTS `Couple` (
  `id_etu` int(11) NOT NULL,
  `id_prof_candide` int(11) NOT NULL,
  `id_prof_tuteur` int(11) NOT NULL,
  `id_soutenance` int(11) NOT NULL,
  KEY `id_etu` (`id_etu`,`id_prof_candide`,`id_prof_tuteur`,`id_soutenance`)
) DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Etudiants`
--

CREATE TABLE IF NOT EXISTS `Etudiants` (
  `etu_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_etu` text NOT NULL,
  `pre_etu` text NOT NULL,
  `info_etu` text NOT NULL,
  `lie_etu` tinyint(1) NOT NULL,
  PRIMARY KEY (`etu_id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Professeur`
--

CREATE TABLE IF NOT EXISTS `professeur` (
  `id_prof` int(11) NOT NULL AUTO_INCREMENT,
  `nom_prof` text NOT NULL,
  `pre_prof` text NOT NULL,
  `info_prof` text NOT NULL,
  `dimi_prof` text NOT NULL,

  PRIMARY KEY (`id_prof`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Salle`
--

CREATE TABLE IF NOT EXISTS `Salle` (
  `id_salle` int(11) NOT NULL AUTO_INCREMENT,
  `libelle_salle` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_salle`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Soutenance`
--

CREATE TABLE IF NOT EXISTS `Soutenance` (
  `id_sout` int(11) NOT NULL AUTO_INCREMENT,
  `id_salle` int(11) NOT NULL,
  `id_crenau` int(11) NOT NULL,
  PRIMARY KEY (`id_sout`),
  KEY `id_salle` (`id_salle`,`id_crenau`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

--
--Structure de la table creneau
--

CREATE TABLE IF NOT EXISTS `creneau` (
  `id_creneau` int(11) NOT NULL AUTO_INCREMENT,
  `max_soutenance` int(11) NOT NULL,
  `date-debut` date NOT NULL,
  `date-fin` date NOT NULL,
  PRIMARY KEY (`id_creneau`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;