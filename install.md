## Pour Linux et macOs

Pour lancer notre application sur Linux et MacOS il suffit d'aller dans le répertoire où se trouve le .jar (./out/artifacts/mastermind_jar/mastermind.jar) et de lancer
la commande ``java -jar mastermind.jar``.
Si une erreur survient, vérifier que vous diposez bien d'une version suffisamment récente de java.
Si ce n'était pas le cas l'application devrait fonctionner après mise à jour du JDK.

## Pour Windows

Pour lancer notre application sur Windows, il suffit d'aller dans le répertoire où se trouve le fichier .jar (./out/artifacts/mastermind_jar/mastermind.jar) et de lancer la commande ``java -jar mastermind.jar``. Vous pouvez également double-cliquer sur le fichier .jar et l'ouvrir avec l'application : `Java(TM) Platform SE binary` si elle est disponible.

Si une erreur survient, vérifiez que vous disposez bien d'une version suffisamment récente de Java. Si ce n'est pas le cas, installez la version 21.0.1 du JDK Java pour Windows/x64 (si vous pouvez obtenir la version x64). Vous pouvez le trouver en [cliquant ici](https://jdk.java.net/21/).

Ensuite, décompressez le dossier où vous le souhaitez. Pour que cela fonctionne, suivez les étapes suivantes :

1. Cliquez droit sur le bouton "Démarrer" dans la barre des tâches.
2. Sélectionnez "Système".
3. Cliquez sur "Informations système" (si cela ne vous met pas dedans par défaut).
4. Cliquez sur "Paramètres système avancés" dans la section `Spécifications de l'appareil`.
5. Cliquez sur le bouton "Variables d'environnement" vers le bas de la fenêtre.
6. Dans la section `Variables du système`, sélectionnez la Variable `Path`.
7. Si vous voyez quelque chose qui ressemble à : `C:\Program Files\Java\jdk-xx\bin`, supprimez-le.
8. Cliquez sur "Nouveau".
9. Ajoutez le chemin complet du dossier "bin" de la nouvelle version d'OpenJDK (là où vous l'avez décompressé).
10. Lorsque vous avez fini, selectionner se que vous avez ajouter et cliquer sur "Déplacer vers le haut" jusqu'à tout en haut. Une fois cela fais, cliquez sur "OK" jusqu'à ce que vous retourniez dans les paramètres du PC.
11. Fermez l'invite de commandes, rouvrez celle où se trouve `mastermind.jar`, puis refaites ``java -jar mastermind.jar``.

Si cela ne fonctionne toujours pas, consultez un spécialiste.
