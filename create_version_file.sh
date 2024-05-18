#!/bin/bash

# Получаем номер последнего коммита
commit_hash=$(git rev-parse HEAD)

# Путь к папке ресурсов
resources_dir="src/main/resources"

# Создаем директорию, если она еще не существует
mkdir -p "$resources_dir"

# Создаем файл version.txt в директории ресурсов и записываем в него номер коммита
echo "$commit_hash" > "$resources_dir/version.txt"
