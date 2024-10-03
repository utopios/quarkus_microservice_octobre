#!/bin/bash
for i in {3..4}
do
  curl -X POST http://localhost:8080/books -v \
  -H "Content-Type: application/json" \
  -d '{"title": "1984", "author": "George Orwell $i", "id": $i}'
done