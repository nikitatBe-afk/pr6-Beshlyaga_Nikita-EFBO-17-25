# Практическая работа 3

Небольшая логическая задача с ветвлениями и конечным набором решений.

Студент: Бешляга Никита, ЭФБО-17-25. Вариант 4.

В проекте реализованы правила маршрутизации служебных заявок, 17 unit-проверок, data-driven-проверки из `data/sample.csv` и интерактивный режим. Проект собирается без Maven и JUnit.

## Сборка и запуск

Linux/macOS:

```bash
./run_selfcheck.sh
```

Windows:

```bat
run_selfcheck.bat
```

Ручная сборка в Linux/macOS:

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out ru.edu.pr03.SelfCheck
```

После unit- и data-driven-проверок программа автоматически переходит в интерактивный режим. Формат строки: `amount;urgent;risk;personal`. Для выхода введите `exit`.

## Диаграммы

Готовые PNG находятся в каталоге `diagrams`. При установленном Graphviz их можно пересобрать из DOT-файлов:

```bash
dot -Tpng diagrams/activity_partial.dot -o diagrams/activity_partial.png
dot -Tpng diagrams/class_partial.dot -o diagrams/class_partial.png
```
