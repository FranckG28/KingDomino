# A31 - KingDomino v2 - Rapport

## **Membres du groupe**

* GUTMANN Franck
* MANSOURI Sofiane


## **Vocabulaire**

Lors de la conception de notre jeu, nous avons choisi certains termes dans notre code :
- Une tuile est une *Tile*
- Un royaume est un *Kingdom*
- Un roi est un *King*
- Le nombre de couronnes sur les tuiles est `crowns`
- *Deck* correspond à la pioche complète
- *Draw* correspond à un tirage de 4 *Domino*

## **Interface**

Afin de définir les couleurs, les polices d’écritures, ainsi que les tailles des textes, nous avons créé une classe *KingDominoDesign* qui regroupe tous ces éléments. Nous avons choisi de garder les propriétés Java *Color* et *Font* dans notre UML, car c'est une partie importante de notre conception. De plus, les noms *Color* et *Font* sont génériques et existe dans de très nombreux languages. La majorité de ces propriétés sont statiques et publiques afin d’être accessibles n’importe où, à l'exception des polices d’écritures qui nécessitent une initialisation préalable. Pour qu’elles ne soient initialisées qu’une seule fois, nous avons fait de cette classe un Singleton.

Nous avons initialement conçu les composants de manière à ce qu’on leur passe le modèle à représenter dans leur constructeur, et ils étaient ainsi dans leur état “final” : pour le mettre à jour, on crée une nouvelle instance du composant. Néanmoins, pour des raisons d'optimisation, nous avons permis à la plupart de ces composants d’être actualisés. Ainsi, les instances de tous les composants sont créées dès le départ, et se mettent à jour au fil du jeu.

Pour finir, afin que l’interface reste cohérente, nous avons défini des constantes statiques propres à chaques vues afin que ces valeurs soient facilement accessibles et ne soient pas dispersées au milieu du code. C’est le cas par exemple pour les tailles de bordures, les marges, les couleurs …

## **Création de la partie**

Initialement, c'était le `main` qui appelait la fonction `getDominos()` de *CSVReader*, et la passait au *GameCreator*. Nous avons modifié cela, c’est désormais la fonction `createGame()` de *GameCreator* qui lit directement les *Domino* via la classe *CSVReader*. Nous avons fait cela principalement pour éviter les conflits entre les parties. En effet, les dominos gardaient leurs rois et leurs orientations précédentes, car c’étaient les mêmes instances qui étaient utilisées d’une partie à l’autre.


## **Système de placement des châteaux et dominos**

Nous avons choisi de supprimer *KingdomEditor* ainsi que son contrôleur *KingdomController*, accompagné du système de preview *DominoPreview*. La première raison à cela était qu’il nous fallait un système d’aperçu de placement qui fonctionne pour un chateau ou un domino.

Mais la raison principale est que nous étions initialement partis sur un système de boutons avec des flèches. Néanmoins, après réflexion, nous avons décidé qu’il était plus pertinent de cliquer directement sur la case sur laquelle l’on souhaite placer notre domino. En effet, les emplacements libres pour placer un domino ne sont pas forcément reliés les uns aux autres, certains seraient donc inaccessibles avec un système de flèches qui permettrait un déplacement case par case.

Suite à cela, notre système d’aperçu n'a donc plus d'intérêt. Seul l’aperçu de la rotation et du sens du *Domino* sera nécessaire. Pour cela, nous avons rendu le Domino observable via l'interface _DominoObserver_. Nous avons également supprimé les orientations et seulement 2 booléens sont nécessaires : `estVertical` et `estInversé`. Ces deux booléens sont directement des attributs d’un _Domino_.

En conséquence de ces changements, nous n’avons plus besoin de contrôleur de royaume, car le _KingdomView_ se charge simplement d’envoyer au contrôleur les coordonnées de la case cliqué. En revanche, il reste un contrôleur indépendant de _GameController_ : _DominoController_. Ce dernier est très basique et gère les 3 actions possibles sur un domino lors de son placement : le tourner, l’inverser, ou le défausser.

## **Observers et encapsulation**

**Nous avons désormais 4 observers :**

- **GameObserver** : observe notamment le changement de joueur courant.
- **KingdomObserver** : observe les changements de tuiles dans un royaume.
- **DominoObserver** : observe les changements de sens et d’orientation d’un domino.
- **DrawObserver** : observe les changements dans une pioche : dominos ajoutés/retirés, rois, etc…

Les vues représentant un modèle implémentent ces interfaces afin d’observer leur modèle associé. De cette façon, nous n’avons pas besoin d’enregistrer les instances de ces vues en attributs, leur création dans le constructeur suffit.

Nous avons eu recours à l’encapsulation pour les pioches, en créant la classe _Draw_, qui encapsule des _Queues_ (files). Elles implémentent le principe _First In First Out_, car on ne commence pas par les derniers dominos, mais par les premiers. Nous avons choisi l’implémentation _LinkedList_<> de l’interface _Queues_ car elle est très flexible et parfaitement fonctionnelle dans notre usage.


## **Calcul du score**

Nous avons créé une classe _ScoreCounter_ qui a pour responsabilité de réaliser le total des points d’un joueur. Cette dernière contient toutes les fonctions et attributs nécessaires au calcul, qui est lancé via la méthode `calculate()`. Le résultat est directement stocké dans l’attribut `score` du _Player_ en question.


## **Modes de jeu**

Les règles empire du milieu et harmonie étant de simple bonus de points et ne changeant en rien le calcul des points, nous avons décidé dès le début ne n’utiliser aucun patron de conception particulier. À la place, nous les représentons sous forme de deux booléens indiquant si elles sont actives ou non dans la classe _Game_. Cette conception est simple et fonctionne bien dans notre cas, néanmoins, elle n’est en rien flexible et ne permet pas d’ajouter de règles supplémentaires. Avec un peu plus de temps, nous aurions probablement implémenté le patron `Decorator` pour résoudre cette problématique.


## **Classes hors du MVC**

Nous avons placé les classes _CSVReader_ et _ScoreCounter_ car d’après nous elles ne rentrent pas dans le modèle MVC. _CSVReader_ est une classe qui retourne la liste de domino à partir du fichier `Dominos.csv`. ScoreCounter permet de calculer les points d’un joueur.
Ces classes ne sont évidemment pas des vues. Elles ne sont pas non plus des modèles, car elles ne servent pas à structurer ou modéliser un objet. Et elles ne sont pas des contrôleurs : elles ne gèrent pas des actions déclenchées par la vue et n’en contrôlent pas.


## **Double dépendances**

Après remarques de notre professeur sur notre première version, nous nous sommes rendu compte que la classe _King_ n’était pas nécessaire, car elle se contentait de contenir son joueur associé en attribut. Placer directement le _Player_ sur un _Domino_ aurait le même effet.

Il en est de même pour le royaume _Kingdom_ : un _Player_ a forcément un _Kingdom_ et un _Kingdom_ a forcément un _Player_. Les classes _Player_ et _Kingdom_ auraient pu être fusionnées.

Malheureusement, nous n’avons pas eu le temps d’implémenter ces changements dans notre projet.


## **Comparateurs**

Afin de pouvoir trier les _Player_ selon leurs scores et les _Domino_ selon leur numéro, nous avons créé des classes _DominoComparator_ et _PlayerComparator_, qui étendent de la classe java _Comparator_. Ces dernières n'apparaissent pas dans le diagramme UML car elles sont spécifiques au langage JAVA.


## **Java 8**

Notre projet a initialement été développé avec Java 15. Nous l’avons passé sous Java 8 afin qu’il soit exécutable sous toutes les machines, car le déploiement de Java 15 est très limité.
