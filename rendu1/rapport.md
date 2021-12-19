# A31 - KingDomino

## **Membres du groupes**

    * GUTMANN Franck
    * MANSOURI Sofiane
    

## **Mise en place du projet**

Lorsque le projet a commencé, nous nous sommes principalement concentrés sur l’UML et avons attendu d’avoir une version relativement stable pour continuer sur le développement Java.

En ce qui concerne la répartition des tâches lors de la réalisation de l’UML, nous avons décidé de réfléchir et de produire ensemble.

Nous avons adopté, dès le commencement du projet, une architecture MVC, afin de débuter sur une bonne base qui sera flexible et facilement modifiable ensuite.

Au départ, nous nous sommes principalement focalisé sur la partie "modèle" de la conception, car il était assez compliqué de s'imaginer la partie vue, ainsi que les contrôleurs sans les modèles.

Ci-dessous vous trouverez nos premiers choix de conception :

- Ajout de tous les éléments du sujet sous forme de Class et d’Enum (Domino, Player, King, Kingdom, …).
- Séparation de la partie et des menus en 2 package distincts “game” et “menus”.
- Le package menus contient le menu de fin et de début de partie, ainsi qu’une classe GameCreator permettant la création de la partie.
- Diviser les différentes parties du jeu avec le patron Strategy :
- StrategyManager : S’occupe de la gestion des stratégies.
- EndMenu use Game : Afin de connaître et d’afficher ses infos en fin de partie.
- Utilisation du patron Observer avec l’interface GameObserver.

## **Modifications**

Le 8 décembre nous avons montré à notre professeur l’avancé de notre projet et il nous a suggéré ces modifications : 

**GameController** : Nous avons enlevé le patron Strategy, et avons pris la décision de tout diviser en fonction simple.

Nous avions créé un **package menu** pour … , cependant, ce dernier ne respectait pas le découpage MVC. Il a donc été supprimé et intégré dans les packages views, models et controllers.

Une fois ces changements faits, le diagramme a été validé par notre professeur et nous avons commencé à coder.

## **Début du développement**

Pour commencer, Sofiane s’est occupé de créer une classe CSVReader. Cette dernière permet de lire les données de Dominos.csv (Liste de tous les dominos disponible dans le jeu), de les convertir sous le format Stack et d’initialiser la pile de dominos.

La plupart du temps, nous avons opté pour des Stack au lieu de List, car les Stack proposent des méthodes plus intéressantes (par exemple pour la pile de domino). Parmi ces différentes méthodes, on retrouve : .pop(), .push(), … . 

Par la suite, nous avons créé différentes branches sur Git afin de mieux diviser le travail et gérer correctement d'éventuels conflits.

Après cela, Franck a commencé la création du Swing.

C'est en commençant la création de l'interface que l'on a pu commencer à se rendre compte des vues nécessaires ainsi que des contrôleurs qui en découlent. 

Après réflexion, nous avons découpé la partie vue en plusieurs composants réutilisables. Pour les composants qui auront besoin d'être mis à jour au cours du jeu, nous avons opté pour le patron Observer afin qu'elles s'actualisent à chaque changement du modèle qu'elles affichent. Il y en a actuellement 2.

Les vues permettant un contrôle par l'utilisateur, elles sont associées à un contrôleur qui manipule le modèle et actualise la vue.
Parmi elles, on retrouve notamment le menu principal, l'interface de jeu, ainsi que l'éditeur de royaume.

Globalement, les données définies à la création d'un modèle ont été modélisées sous forme de variables privées, accompagné d'un "getter".

Au niveau des vues, nous les avons décomposés au maximum en élément réutilisable afin de minimiser la charge de travail. De manière générale, chacune d'entre-elle affiche un modèle.

Au lancement du jeu, notre main, implémentée dans la classe KingDomino utilise la classe CSVReader pour lire les dominos du jeu. Il crée ensuite un contrôleur GameCreator à qui il passe les dominos, et le lie à une vue StartMenu. Ainsi, c'est la classe GameCreator qui gère toute la création d'une partie jusqu'à son lancement. 

Pour cela, ce dernier crée le modèle Game configuré conformément aux choix de l'utilisateur sur le menu principal, et crée un GameController, qui contrôle la vue principale de la partie, GameView. Afin de refléter en direct l'état du jeu, cette dernière observe le modèle Game.

Le modèle game contient tous les éléments d'un jeu. Les joueurs Player de la partie possèdent leur royaume Kingdom ainsi qu'un ou deux rois King. 

Pour que les vues affichent toujours le dernier état du domino pioché, ainsi que le joueur à qui c'est le tour et l'action qu'il doit effectuer, la vie GameView implémentera l'interface GameObserver, qui est déclenchée par le modèle Game.

Un domino est composé de deux cases, et est numéroté. Chacune des cases et d'un type de terrain Land, et possède un nombre de couronnes. 

Un royaume est un tableau de cases, car une fois posé, seules les cases comptent et non les dominos. 

Ainsi, nous pouvons seulement modéliser le royaume d'un joueur en un tableau 2D de cases Tile.

Afin de gérer le placement des dominos par l'utilisateur, nous avons créé un modèle DominoPreview qui contient toutes les informations nécessaires au placement d'un Domino. Il est créé par le modèle du Royaume lui-même. Le placement d'un Domino est proposé à l'utilisateur via la vue KingdomEditor, et les actions de l'utilisateur sont gérés par le KingdomController qui manipule un DominoPreview.

Nous avons créé une vue KingdomView, qui implémente l'interface KingdomObserver afin de se mettre à jour à chaque modification d'un Kingdom d'un joueur. 


## **L'exécutable**

Pour l’instant, notre exécutable est le fichier “Kingdomino” trouvable dans le dossier “src” du rendu1. 

Pour le moment, seul le menu principal avec la sélection du nombre de joueurs, leurs noms et leurs couleurs, ainsi que le choix des règles sont implémentés.

