Программа осуществляет сортировку слиянием нескольких файлов.

Особенности реализации:

В качестве исходных данных выступают файлы, данные в которых записаны в столбик. В зависимости от режима работы поддерживаются строковые данные или целые числа. Для корректной работы сортировки слиянием необходимо чтобы данные в файлах были заранее отсортированы в требуемом порядке, поэтому все файлы с входными данными проверяются. Если невозможно открыть файл или он пустой, данный файл далее не рассматривается. Файлы, в которых нарушен тип данных считаются скомпрометированными и отбрасываются. Если файл отсортирован, но в другом порядке, то данные в файле обращаются. Если размер файла меньше 100 Мб, то обращение происходит в оперативной  памяти, в другом случает файлы разбивается на меньшие файлы (по умолчанию промежуточные файлы содержат не более 1000000 строк с данными), в этих файлах происходит обращение данных, после чего файлы объединяются. Если данные в файле не отсортированы, то производится сортировка слиянием. Аналогично обращению, если размер файла меньше 100 Мб, сортировка осуществляется в оперативной памяти, в ином случает производится разбиение файла на меньшие файлы, после чего происходит сортировка данных из промежуточных файлов в оперативной памяти, далее с помощью внешней сортировки слиянием происходит объединение файлов.

Проект имеет следующие зависимости:
JUnit 4.13
Apache Commons IO 2.6

Сборка и запуск
Сборка проекта осуществляется Maven с помощью команды 
-mvn clean compile assembly:single

Полученный jar – файл будет находиться в директории /target/

Запуск осуществляется через командную строку 
java –jar  merge-sort-cft-1.0-SNAPSHOT-jar-with-dependencies.jar –a –i –out.txt – in1.txt –in2.txt –in3.txt

Параметры запуска:
1.	Режим сортировки (-a или –d), не обязательный, по умолчанию сортируем по возрастанию;
2.	Тип данных (-s или –i), обязательный;
3.	Имя выходного файла, обязательное;
4.	Остальные параметры – имена входных файлов, не менее одного


Описание компонентов:

control.Analyzer – анализирует аргументы командной строки, введенные пользователем. В зависимости от полученных данных запускает control.Configurator.

control.Configurator – служит для инициализации и настройки компонентов.

control.Manager – обрабатывает входные файлы и запускает сортировку слиянием.

control.TempFilesFabric – служит для генерации временных файлов. Все файлы, созданные, control.TempFilesFabric будут уничтожены в конце работы программы.

filesOperations.FileChecker – проверяет входные файлы и для каждого из них получает следующую информацию: открывается ли файл, не пустой ли файл, не нарушен ли формат данных в файле, отсортированы ли данные в файле, отсортированы ли они в требуемом порядке.

filesOperations.FileDivider – вспомогательный класс, служит для разделения больших файлов на небольшие промежуточные для последующей обработки

filesOperations.FileListConverter – вспомогательный класс, служить для конвертации списков в файлы и обратно.

filesOperations.FileReverser – изменяет порядок данных в файле. Если файл небольшой (предельный размер задаётся, по умолчанию 100 Мб) читает данные из файла, конвертирует их в оперативной памяти и записывает во временный файл. В обратном случае разделяет исходный файл на части с помощью FileDivider, обращает данные по отдельности, а потом объединяет данные в один файл.

filesOperation.FileSorter – сортирует данные из одного файла. Если файл небольшой (предельный размер задаётся, по умолчанию 100 Мб) читает данные из файла, сортирует их в оперативной памяти и записывает во временный файл. В обратном случае разделяет исходный файл на части с помощью FileDivider сортирует данные по отдельности, а потом объединиет данные в один файл м помощью SortedFilesMerger.

filesOperation.SortedFilesMerger – осуществляет сортировку слиянием заранее отсортированных файлов.

operations.ListMergerSorter – осуществляет сортировку слиянием переданного списка.

operations.Parser – интерфейс, описывающий парсинг из строки в требуемый формат. Интерфейс реализуют operations.ParserInt и operations.ParserString

