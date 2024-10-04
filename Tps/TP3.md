
### TP : Ajout d'un microservice de gestion des avis (Review) avec Kafka, OpenTelemetry et Jaeger

**Objectif du TP** : 
Vous allez ajouter un microservice "review-service" à une application existante, qui permet de gérer les avis clients sur les produits. Ce microservice communiquera avec les autres services via Kafka pour assurer l'asynchronisme des échanges. Vous intégrerez OpenTelemetry et Jaeger pour assurer le monitoring et le traçage distribué. Enfin, vous ajouterez une logique de composition entre les avis et les produits.

#### Partie 1 : Création du microservice "review-service"
1. **Structure du projet** :
   - Créez un nouveau microservice `review-service` dans le dossier existant.
   - Ce microservice doit permettre de créer, lire, mettre à jour et supprimer (CRUD) les avis sur les produits.
   - Les avis contiendront les informations suivantes :
     - ID de l'avis
     - ID du produit
     - Nom du client
     - Note (de 1 à 5)
     - Commentaire

2. **Connexion à Kafka** :
   - Configurez Kafka pour permettre la communication entre `review-service` et `product-service`.
   - Utilisez Kafka pour publier un événement lorsqu'un avis est ajouté ou mis à jour (Topic `review-events`).
   - `product-service` doit être consommateur de cet événement afin de mettre à jour les informations des produits, comme la note moyenne.

#### Partie 2 : Monitoring et traçage distribué
1. **Monitoring avec OpenTelemetry et Jaeger** :
   - Intégrez OpenTelemetry dans `review-service` pour collecter des traces sur les appels d'API.
   - Configurez Jaeger pour visualiser les traces des requêtes traversant plusieurs microservices, notamment entre `review-service` et `product-service`.
   - Ajoutez des annotations `@WithSpan` pour tracer les méthodes importantes, comme la création d'avis ou la réception d'événements Kafka.

2. **Traçage complet** :
   - Assurez-vous que les requêtesa sont traçables et que les événements sont bien capturés par Jaeger.
   - Testez la traçabilité d'une transaction complète, par exemple, de la création d'un avis dans `review-service` jusqu'à la mise à jour d'un produit dans `product-service`.

#### Partie 3 : Composition produits et avis
1. **Composition des données entre `product-service` et `review-service`** :
   - Modifiez `product-service` pour inclure une route qui renvoie les détails du produit avec ses avis associés.
   - Lorsque le service renvoie les détails d’un produit, il doit également récupérer les avis associés à ce produit depuis `review-service`.
   - Utilisez une communication synchrone (par exemple via REST) pour obtenir les avis d'un produit lors de la récupération des détails de ce produit.

#### Partie 4 : Tests et validation
1. **Tests unitaires** :
   - Écrivez des tests unitaires pour vérifier les fonctionnalités de gestion des avis dans `review-service`.
   - Testez la réception des événements Kafka dans `product-service` et la mise à jour correcte des informations sur les produits.

2. **Tests d'intégration** :
   - Mettez en place des tests d'intégration pour valider la communication via Kafka et la composition des avis dans `product-service`.
   - Vérifiez que les avis créés ou mis à jour sont bien reflétés dans les détails des produits.

