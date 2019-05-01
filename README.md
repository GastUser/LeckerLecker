# README #

Beschreibung zum Projekt....

### Wofür ist dieses Repo? ###

* Quick summary
* Version

### Deployment zu Cloud Foundry ###

* Projekt >> "Clean and Build"
* CMD öffnen
* cf login
    * API-Endpunkt: https://api.run.pivotal.io
    * E-mail Adresse und Passwort
* in das "target" Verzeichnis vom Projekt wechseln (cd ...)
* cf push NAME-DER-APP -p lecker-platform-0.0.1-SNAPSHOT.jar 
* fertig >> Aufruf im Browser über NAME-DER-APP.cfapps.io

### Deployment zu heroku Cloud ###

* Projekt im heroku Account anlegen (https://dashboard.heroku.com)
* Projekt >> "Clean and Build"
* CMD öffnen
* heroku login
* Im Browser login bestätigen 
* in das Projektverzeichnis vom Projekt wechseln (cd ...)
* [Beim ersten deploy muss das heroku remote Repo hinzugefügt werden]
* git push heroku master 
* fertig >> Aufruf im Browser

## Codacy "Code": [![Codacy Badge](https://api.codacy.com/project/badge/Grade/835653c13e4146d691de4db79dc0acbc)](https://www.codacy.com?utm_source=git@bitbucket.org&amp;utm_medium=referral&amp;utm_content=IchHabeGC/leckerlecker&amp;utm_campaign=Badge_Grade)