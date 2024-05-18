#!/bin/bash

# Получаем номер последнего коммита
commit_hash=$(git rev-parse HEAD)

# Создаем файл version.txt и записываем в него номер коммита
echo "$commit_hash" > version.txt
