
**Структура проекта**

```
│── src/
│   ├── test/
│       └── java/
│           └── com/example/tests/
│               ├── CreateItemTest.java    - тест для создания объявления
│               ├── GetIdTest.java         - тест для получения объявления по его id
│               ├── GetSellerIdTest.java   - тест для получения объявлений по sellerId
|               ├── GetStatisticsTest.java - тест для получения статистики по itemId
|               ├── SetUpTests.java -      - класс для создания тестового объявления перед запуском тестов по созданию объявления
|               ├── TestCaseData.java      - класс-модель для создания тестовых данных
|               ├── testData.json          - файл с данными для теста по созданию объявления
|               ├── TestDataProvider.java  - класс для параметризации тестов, данными из testData.json
|
└── pom.xml - зависимости
└── BUGS.md - файл с описание найденных багов для 2-ого задания
└── TESTCASES.md - тест кейсы ко 2-ому заданию
└── BUGS_FROM_SCREENSHOT.md - файл с описанием найденных багов для 1-ого задания
└── README.md - описание проекта и инструкция
```
 🔧**Инструкция** 

1. Склонируйте к себе репозиторий, в котором хранится проект тестового задания, через выполнение команды в терминале  <br/>
      ```sh
   git clone https://github.com/alinaarmut/avito-QA-2025.git
      ```
2. Убедитесь, что на вашем компьютере установлена Java. В командной строке/терминале выполните команду  <br/>
   ```sh
   java -version
   ```
   Если Java не установлена, то установите с официального [сайта Java](https://www.oracle.com/java/technologies/downloads/?er=221886), выбрав подходящую версию для вашей     операционной системы. <br/>
3.  После установки добавьте Java в переменные окружения. <br/>

    Если у вас Windows OS: <br/>
       Откройте «Системные переменные среды» и добавьте новую переменную с именем JAVA_HOME и значением — путь к установленной Java.
       Пример:
     ```sh
     C:\Program Files\Java\jdk-16.0.1
     ```
    Если у вас MacOS: <br/>
    Добавьте в файл .bash_profile или .zshrc (в зависимости от используемого шелла) следующую строку
    ```sh
    export JAVA_HOME=$(/usr/libexec/java_home)
    ```
    
5. Убедитесь, что Java компилятор правильно установился. В командной строке/терминале выполните команду  <br/>
   ```sh
   javac -version
   ```
   И пройдите шаг 2 сначала.
6. Убедитесь, что на вашем компьютере установлен maven.  В командной строке/терминале выполните команду  <br/>
   ```sh
   mvn -version
   ```
   Если Maven не установлен, то установите с официального [сайта Maven](https://maven.apache.org/download.cgi), и пройдите шаг сначала.
7. Через командную строку/терминал перейдите в корневую директорию проекта, выполнив команду <br/>
   ```sh
   cd /здесь укажите путь до директории с проектом
   ```
8. Наконец, запустите тесты, выполнив команду  <br/>
   Зависимости автоматически загрузятся перед выполнением команды  <br/>
   ```sh
   mvn test
   ```
Если у вас возникнут проблемы при установке Java, обратитесь к инструкции с офицального [сайта Java](https://www.java.com/ru/download/help/download_options_ru.html)
