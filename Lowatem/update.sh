#!/bin/bash

echo "ENVOI À LA REMISE..."
mv depot/sortie.log depot/last.log 2>/dev/null
rsync -avzu --delete depot/ edatrier@info-ssh1.iut.u-bordeaux.fr:~/IUT-Remise/lowatem/info_s1c/edatrier/depot
echo "ENVOI TERMINÉ"

while [ ! -f depot/sortie.log ]
do
   echo "ATTENTE: 10s"
   sleep 10
   echo
   echo
   echo
   echo "ESSAI DE LA RÉCUPÉRATION DES DONNÉES"
   rsync -avzu --exclude="lib" --exclude="depot/lowatem" --exclude="update.sh" edatrier@info-ssh1.iut.u-bordeaux.fr:~/IUT-Remise/lowatem/info_s1c/edatrier/ .
done

echo "TERMINÉ"
echo
echo
