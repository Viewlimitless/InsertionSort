Запуск:
1. \src\main\java в папке вызываем комамндную строку и компилируем все файлы сразу командой javac *.java
2. java Sort C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a
эта команда запустит сортировку содержимого файлов в C:\testSort\ методом вставок.
type=i для целых чисел, type=s для строк
mode=a по возрастанию, mode=b по убыванию

Например:
C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a
C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=b
C:\testSort\ --out-prefix=sorted_ --content- type=s --sort-mode=a

другой способ запуска через исполяемый jar файл:
1. \out\artifacts\insertion_jar в этой папке вызываем командную строку 
java –jar sort-it.jar C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a

