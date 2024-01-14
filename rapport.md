# Rapport A31

## Rapport 1 - 3 décembre

Ce diagramme représente l'avancement actuel du projet
![](image_rapport/image_rapport_3decembre.svg)

On y retrouve 'Game', qui représente une partie, mais aussi 'Round', qui correspond à une manche. 
Une partie possède de 3 à 5 manches et a un attribut statique décrivant le nombre de manches.

Une manche est composée de lignes de tentatives. Cette classe sert à faire le lien entre une combinaison et ses indices. 
Elle assure également la synchronisation et s'appuie sur les classes 'Combination' et 'HintLine', 
elles-mêmes se servant d'énumérations.

La combinaison secrète est un attribut de type 'Combination' d'une manche. C'est à la manche de générer sa combinaison secrète.

## Rapport 1 - 10 décembre
![](image_rapport/image_rapport_10decembre.svg)

Voici donc notre UML pour le rendu 1. On y retrouve un modèle retravaillé dans lequel 'AttemptLine' n'apparaît plus. 
En effet, son rôle était limité à la synchronisation, elle n'était donc pas réellement utile.

Mais pour pallier à sa disparition, sont apparus 'SecretCombination', qui encapsule la logique de génération de combinaison aléatoire, ainsi que 'Settings', dont l'objectif est de limiter la duplication de l'information, 
notamment des paramètres de jeu qui se retrouvaient dupliqués pour chaque instance de 'Round'.

Les attributs statiques ont ainsi été totalement abandonnés, permettant de créer plusieurs parties avec des paramètres différents en même temps.

La classe 'Player' qui encombrait a aussi été retirée et est devenue une chaîne de caractères dans 'Game'.

On notera aussi l'apparition des 'HintDisplayMode', qui servent à modéliser les différentes manières 
de traiter les indices au niveau de la vue. On retrouve une classe pour le mode 'CLASSIC' et une pour le mode 'EASY'. 
Le mode numérique se modélise simplement comme le mode 'EASY' mais en changeant 
les composants graphiques associés à l'affichage (non représentés pour le moment).

'Game' et 'Round' possèdent des observateurs. Cela se justifie par le fait qu'une manche ne peut mettre fin à une partie 
et une partie ne peut mettre fin à une manche. Il faut donc qu'ils signalent eux-mêmes lorsqu'ils ont terminé.

Pour ce qui est du code rendu, le modèle fonctionne. Seulement, le 'main' est construit de manière à appeler tous les 'rounds' lui-même. Ce ne sera bien sûr pas le cas dans la version finale, mais en l'absence de réelle interface graphique pour accueillir un contrôleur classique, il était difficile de tester le modèle.

Enfin, mis à part 'Announcer', aucune autre classe 'vue' de l'UML n'est codée. 
Elles sont pour le moment simplement représentatives du découpage qui devrait être effectué dans les prochaines semaines.

## Rapport 2 - 11 décembre

Suite au rendu 1, nous nous sommes rendu compte qu'il manquait quelques justifications et corrections que voici :

Lorsque l'on joue, il n'y à que 4 couleurs possibles (pour l'instant) qui doivent être entré dans la console entre 0 et 3 compris séparer par des espaces.

La classe 'SecretCombinaison' n'a pas de variable car elle est contenue dans 'Round'. 
En effet, il y a une combinaison secrète par manche, et c'est 'Round' qui génère la combinaison seule.

Le constructeur de 'Combinaison' prend en argument une liste de pions car c'est le joueur qui la lui passe 
lorsqu'il tente une combinaison.

'HintDisplayMode', 'EasyMode' et 'ClassiqueMode' sont déplacés dans le package 'vue' car ils n'ont un rôle que purement graphique.

L'énumération 'Mode' est désormais dissociée de 'EasyMode' et 'ClassiqueMode', car cette énumération permet de définir 
le mode courant dans le modèle, alors que 'EasyMode' et 'ClassiqueMode' ne sont là que pour l'affichage.
Cependant, ils sont corrélés dans 'GameController'.

--
'Announcer' a été retiré car il ne servait que pour l'affichage non graphique dans le rendu 1.
--

## Rapport 3 - 14 janvier

Le modèle ayant été terminé il y a plusieurs semaines, nos efforts se sont concentrés sur les vues et les contrôleurs.
Nous avons notamment mis en place différentes classes graphiques, en premier lieu MastermindWindow, qui hérite des JFrame.
Cette classe se compose de 3 JPanel personnalisés, StartPanel, GamePanel et EndPanel, ils ont pour responsabilités
de contenir les éléments graphiques des 3 phases de l'application, à savoir : le démarrage, avec le choix des paramètres,
le jeu, avec le choix des pions et la possibilités d'abandonner la partie, ainsi que la fin de partie, avec l'affichage
des scores.

Nous avons fais le choix d'1 seule et unique fenêtre afin d'éviter les potentiels conflits (une fenêtre qui ne se ferme pas)
mais aussi aussi afin de ne pas faire subir à l'utilisateur la gestion de ces dernières, ou leur apparition et disparition 
qui aurait pu être troublant.

Nous avons également développés plusieurs classes graphiques, par exemple CombinationBox, qui contient des composants appellés
Circle, également développés par nous, le principe étant de pouvoir afficher les combinaisons à l'écran a l'aide de cercle de couleur.

L'interface HintBox a été développée afin de fournir une abstraction permettant d'ajouter ou d'autres manière de représenter 
les indices à l'écran, nous avons 2 classes concrètes qui ont ce rôle, ClassicHintBox et NumericHintBox.

Pour ce qui est de la logique relatives aux indices, des classes concrètes impleméntant HintDisplayMode permettent de renvoyer 
une tableau de couleur en fonction des indices qui lui sont donnés, il n'y a que 2 modes, car le mode numérique se base
sur le mode facile et utilise une NumericHintBox afin de ne pas comptabiliser les +4 points du mode classique.

La classe GameBoard sert de plateau de jeu, une notion explicitement absente du modèle, mais qui se retrouve implicitement
dans l'implémentation sous forme de liste des combinaisons et des lignes d'indices, le plateau se rempli au fur et à mesure
que la partie se déroule, retraçant chaque tentative du joueur jusqu'à ce qu'il trouve la bonne combinaison.

Les contrôleurs sont au nombre de 2, GameController a un rôle assez large de gestion, c'est à lui qu'est confié la tâche
d'ordonner l'affichage d'un panel à la MastermindWindow, il gère également les ordres de configuration et de passage de tour
vis à vis de la classe Game.
Le roundController a des responsabilités qui portent uniquement sur les rounds, il récupère le round avec lequel il travail
du GameController, et il veille à la communication de l'utilisateur avec ce dernier, notamment dans la gestion des combinaisons
mais aussi de l'abandon de la manche en cours.

CombinationConverter a été développé dans le but de faire le lien entre pions et couleurs à un seul et unique endroit,
ce qui nous permet d'éviter d'avoir à modifier cette correspondance à plusieurs endroits dans les classes les utilisant.

Nous aurions bien aimé ajouter les choses suivantes à notre projet :
- Des couleurs aléatoires parmis les 8 possibles à chaque partie
- Une base de données pour conserver l'historique des parties et des scores en fonction des joueurs