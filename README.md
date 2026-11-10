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

Итоговые Activity-диаграмма и диаграмма классов находятся в `diagrams` в форматах DOT и PNG. Файлы с суффиксом `_partial` оставлены как исходные заготовки. При установленном Graphviz итоговые PNG можно пересобрать так:

```bash
dot -Tpng diagrams/activity.dot -o diagrams/activity.png
dot -Tpng diagrams/class.dot -o diagrams/class.png
```
