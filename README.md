#StCal

##Cahier des charges

###Introduction

StCal est un logiciel de gestion de stages en entreprise dédié aux enseignants. Ce projet sera réalisé de septembre 2013 à février 2014 en vue du projet tuteuré de Mehdi Loisel, Ismail Taleb, Valentin Jeanroy, Jean Mercadier, Nicolas Devillers, Willem Verdeaux et Florian Barrois, étudiants en deuxième année à l'Institut Universitaire de Technologie de Belfort. 

###Préequis

[JDateChooser](http://sourceforge.net/projects/jdatechooser/)

[CommonsPool](http://commons.apache.org/proper/commons-pool/download_pool.cgi)

[CommonsDBCP](http://commons.apache.org/proper/commons-dbcp/downloads.html)

[MYSQLconnector](http://dev.mysql.com/downloads/connector/j/)

###Fonctionnalités

L'application développée devra simplifier la gestion des stages des étudiants par les professeurs en s'acquittant des contraintes et proposer les fonctionnalités suivantes :

- Association enseignant/étudiant
    - Contrainte limitant l'association d'un étudiant à un seul enseignant.
- Gestion du planning des soutenances de stage
    - Affichage de l'horaire choisi lors de la sélection du couple enseignant/étudiant ?
    - Affichage du couple enseignant/étudiant lors de la sélection de l'horaire choisi ?    
    - Drag & drop des étudiants et enseignants sur l'horaire choisi ?






###Développement

Le développement s'effectuera en trois étapes, qui contiendront différentes tâches. Ces étapes et tâches seront ordonnées tel que le côté fonctionnel de l'application soit prioritaire :

####Étape 1

L'application devra avant tout considérer des étudiants et des professeurs. Le produit permettra ainsi la création de couples professeur/étudiant en respectant les contraintes suivantes :  
        - à partir d'une liste de professeurs et d'une liste d'étudiants existantes, associer un étudiant à un professeur;  
        - chaque étudiant sera relié à un et un seul enseignant;  
        - un enseignant pourra être relié à aucun, un ou plusieurs étudiants;  
        - définir éventuellement l'enseignant candide comme deuxième enseignant assistant à la soutenance en utilisant la liste d'enseignants.
                
####Étape 2

L’application comportera une fonction de création d’emploi du temps vide et une seconde permettant le remplissage de celui-ci avec les soutenances de chacun des étudiants, ceci en fonction des disponibilités des enseignants tuteur et candide.
Les plages horaires réservées contiendront les informations suivantes :
Le nom de l’enseignant tuteur
Le nom de l’enseignant candide
Le nom de l’étudiant
Le numéro de la salle d’entretien
Éventuellement le sujet de la soutenance



Le produit devra être capable de résoudre les cas suivants en affichant un message d’erreur et en ignorant la dernière action effectuée :
- en cas de sélection d’un enseignant à un horaire incompatible pour cet enseignant ;
- en cas d’utilisation d’une salle pour deux soutenances différentes prévues au même horaire ;
- en cas de présence de deux soutenances dont les sujets sont identiques ;
- en cas de présence de deux soutenances dont les noms des étudiants sont identiques ;

Si toutes les salles d’entretien sont occupées à un horaire donné, cette plage horaire devra être inaccessible pour placer d’autres soutenances. 

L’application possèdera également une option d’export du calendrier généré au format ICS compatible avec Google et iCal.

####Étape 3

L’application contiendra un système de sauvegarde : lors de l’ouverture de l’application par l’utilisateur, le système effectuera une restauration de session qui permettra l’affichage de la dernière page affichée avant la fermeture de l’application lors la session de travail précédente.

####Étape 4

Le produit possèdera un outil de création de base de données dans laquelle les stages pourront être stockés. Les informations enregistrées seront identiques à celles affichées sur les horaires de soutenances.
La base de données créée pourra ensuite être reliée à un client léger (site PHP) et sera consultable par les étudiants.

####Étapes supplémentaires :

L’application pourra éventuellement permettre à l’utilisateur d’effectuer des drag and drop, notamment pour l’attribution des horaires au professeurs et étudiants, dans la partie calendrier.
De plus, l’interface pourra être plus aboutie en fonction du délai restant.
L’interface idéale comporterait une zone réservée à l’affichage des listes d’étudiants et de professeurs et une zone affichant tous les étudiants affiliés à un professeur lors de la sélection de ce dernier, en plus des différents boutons de création, modification ou suppression des couples.

