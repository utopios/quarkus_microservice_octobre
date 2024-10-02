### Sujet d'exercice : Composition de microservices dans une application de gestion de commandes

#### Contexte
Dans cet exercice, vous allez étendre votre microservice de gestion de **commandes** en introduisant une **composition de microservices**. L'objectif est de composer plusieurs microservices pour enrichir les informations d'une commande en récupérant des détails supplémentaires depuis d'autres microservices.

Vous devrez intégrer les services d'un **microservice Client** et d'un **microservice Inventaire** pour obtenir les informations complètes sur une commande, incluant les détails du client et des articles.

#### Objectifs

1. **Microservice Commande** : Ce microservice continue de gérer la création, la modification, et la suppression des commandes. Il doit maintenant interagir avec d'autres microservices pour obtenir des informations supplémentaires.
   
2. **Microservice Client** :
   - Gérer les informations des clients (identifiant, nom, adresse, etc.).
   - Fournir un service pour récupérer les informations d'un client via une API REST en fonction de son identifiant.

3. **Microservice Inventaire** :
   - Gérer les informations des articles (identifiant, nom, stock disponible, prix).
   - Fournir un service pour récupérer les détails d’un article via une API REST en fonction de son identifiant.

4. **Composition dans le Microservice Commande** :
   - Lorsqu'une commande est récupérée, le microservice de gestion des commandes doit composer avec le **microservice Client** pour obtenir les informations du client associé à la commande.
   - Il doit également composer avec le **microservice Inventaire** pour enrichir chaque article de la commande avec des détails comme le nom, le prix et le stock disponible.
   




#### API REST des Microservices

1. **API du microservice Client** :
   - `GET /clients/{clientId}` : Renvoie les informations du client pour un identifiant donné.

2. **API du microservice Inventaire** :
   - `GET /articles/{articleId}` : Renvoie les détails d'un article (nom, prix, stock) pour un identifiant donné.

3. **API du microservice Commande** (après la composition) :
   - `GET /commandes/{commandeId}/details` : Renvoie les détails complets de la commande, incluant les informations du client et des articles enrichis. 

