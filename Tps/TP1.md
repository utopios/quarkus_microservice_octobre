### Sujet d'exercice : Création d'un microservice de gestion de commandes en Quarkus avec architecture hexagonale

#### Contexte
Dans cet exercice, vous allez créer un microservice en utilisant **Quarkus** et adopter l'**architecture hexagonale** pour gérer les **commandes** dans un système e-commerce. Ce microservice doit permettre de créer, consulter, mettre à jour et annuler des commandes. Le service se concentrera uniquement sur les informations liées aux **commandes**, sans inclure les détails des clients ou des articles.

#### Objectifs
L'objectif est d'implémenter un microservice RESTful en Quarkus, en suivant les principes de l'architecture hexagonale pour séparer la logique métier des interfaces et des adaptateurs, tout en manipulant uniquement les informations des commandes.

#### Fonctionnalités du microservice :
1. **Créer une commande** : Un client peut créer une commande avec une liste simplifiée d'articles (identifiant et quantité) et un statut initial.
2. **Lister les commandes** : Afficher toutes les commandes disponibles.
3. **Mettre à jour une commande** : Permettre la modification du statut ou des articles d'une commande existante.
4. **Annuler une commande** : Permettre l'annulation d'une commande.
5. **Consulter une commande** : Récupérer les détails d'une commande spécifique.

#### Modèles utilisés :

1. **Commande** :
   - **Identifiant de commande** : Un identifiant unique pour chaque commande.
   - **ClientId** : Un identifiant représentant le client, sans inclure les détails du client.
   - **Articles** : Une liste d'articles simplifiée contenant uniquement les identifiants d'articles et les quantités.
   - **Statut** : Le statut de la commande (en cours, validée, annulée).
   - **Date de création** : La date de création de la commande.
   - **Date de mise à jour** : La dernière date de modification de la commande.

2. **Article** (simplifié) :
   - **Identifiant d'article** : Un identifiant unique pour chaque article.
   - **Quantité** : Le nombre d'exemplaires de l'article dans la commande.

3. **StatutCommande** (Enum) :
   - **En cours** : La commande est en cours de traitement.
   - **Validée** : La commande a été validée.
   - **Annulée** : La commande a été annulée.

#### Exigences techniques :
- **Langage et framework** : Java avec Quarkus.
- **Base de données** : Utilisation d'une base de données relationnelle pour stocker les commandes.
- **Architecture hexagonale** : Implémentez une séparation claire entre la couche métier, les ports et les adaptateurs.