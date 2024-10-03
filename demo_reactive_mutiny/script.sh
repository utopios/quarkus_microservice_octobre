#! /bin/bash
for i in {2..1000000}
do
  curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"title": "1984", "author": "George Orwell $i", "id": $i}'
done