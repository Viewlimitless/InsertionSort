������:
1. \src\main\java � ����� �������� ���������� ������ � ����������� ��� ����� ����� �������� javac *.java
2. java Sort C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a
��� ������� �������� ���������� ����������� ������ � C:\testSort\ ������� �������.
type=i ��� ����� �����, type=s ��� �����
mode=a �� �����������, mode=b �� ��������

��������:
C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a
C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=b
C:\testSort\ --out-prefix=sorted_ --content- type=s --sort-mode=a

������ ������ ������� ����� ���������� jar ����:
1. \out\artifacts\insertion_jar � ���� ����� �������� ��������� ������ 
java �jar sort-it.jar C:\testSort\ --out-prefix=sorted_ --content- type=i --sort-mode=a

