# Rapport A31

## Rapport 1 - 3 décembre

Ce diagramme représente l'avancement actuel du projet
![](image_rapport/image_rapport_3decembre.svg)
On y retrouve Game, qui représente une partie, mais aussi Round qui correspond à une manche, 
une partie possède 3 à 5 manches et a un attribut statique décrivant le nombre de manches.

Une manche est composé de ligne de tentatives, cette classe sert à faire le lien entre une combinaison et ses indices
Elle assure également la synchronisation et s'appuie sur les classes Combination et HintLine elles-mêmes se servant
d'énumération.

La combinaison secrète est un attribue Combination d'une manche, c'est à la manche de générer sa combinaison.

## Rapport 1 - 10 décembre
![](image_rapport/image_rapport_10decembre.svg)
Voici donc notre uml pour le rendu1, on y retrouve un modèle retravaillé dans lequel AttemptLine n'apparaît plus.
En effet, son rôle était limité à la synchronisation, elle n'était donc pas réellement utile.

Mais pour palier à sa disparition sont apparus SecretCombination, qui encapsule la logique de génération de combinaison 
aléatoire, ainsi que Settings, qui a pour objectif de limiter la duplication de l'information, et notamment des 
paramètres de jeu qui se retrouvait être dupliqués pour chaque instance de Round.

Les attributs statiques ont ainsi été totalement abandonnées, permettant de créer plusieurs parties avec des paramètres
différents en même temps.

On notera aussi l'apparition des HintDisplayMode, qui servent à modéliser les différentes manières de traiter les 
indices au niveau de la vue, on retrouve une classe pour le mode CLASSIC et une pour le mode EASY, le mode numérique
se modélisant simplement comme le mode EASY mais en changeant les composants graphiques associés à l'affichage 
(non représentés pour le moment)

Game et Round possèdent des observers, ceci se justifie par le fait qu'une manche ne peut mettre fin à une partie 
et une partie ne peut mettre fin à une manche, il faut donc qu'ils signalent eux-mêmes lorsqu'ils ont terminés.

Pour ce qui est du code rendu, le modèle fonctionne, seulement le main est contruit de manière à appeler tous les rounds
lui-même, ce ne sera bien sûr pas le cas dans la version finale, mais en l'absence de réelle interface graphique pour 
accueillir un controller classique il était difficile de tester le modèle.

Enfin, mis à part Announcer, aucune autre classe "vue" de l'uml n'est codée, elles sont pour le moment 
simplement représentative du découpage qui devrait être effectué dans les prochaines semaines.
