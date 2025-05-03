# Entwurfmuster

[2 unterschiedliche Entwurfsmuster aus der Vorlesung (oder nach Absprache auch andere) jeweils
benennen, sinnvoll einsetzen, begründen und UML-Diagramm]

## Entwurfsmuster #1: Adapter 

Adapter ermöglichen die Zusammenarbeit von Klassen, dessen Schnittstellen eigentlich nicht kompatibel sind.

### LogWriterLoggerAdapter

#### UML (vereinfacht)

![UML](umls/LogWriterLoggerAdapter.png)

Der Hauptzweck des ```LogWriterLoggerAdapter``` ist es, einen ```LogWriter``` als ```Logger``` verwenden zu können, da beide Klassen seperate Aufgaben besitzen.

Das ```Logger``` Interface ist für das aufsammeln der Aktivitäten innerhalb der Anwendung da, während der ```LogWriter``` für das Schreiben der eigentlichen Logeinträge in Dateien zuständig ist.

Durch den Adapter ist es möglich, dass Code, der mit der ```Logger```-Schnittstelle arbeitet, einen ```LogWriter``` zu übergeben, damit z.b. die gesammelten Logs in einer Datei gespeichert werden können.

## Entwurfsmuster #2: Decorator

Decorator sind eine Art Strukturmuster, die es ermöglichen, Objekten dynamisch zusätzliche Funktionalitäten hinzuzufügen, ohne deren Struktur zu verändern.

### Das Logger-System

![UML](umls/loggerSystem.png)

Das ```Logger``` Interface definiert die Grundoperationen, die jeder Logger unterstützen muss. Die ```getInnerLogger()``` Methode ermöglicht den Zugriff auf den eingewickelten Logger.

Die konkreten Decorator-Klassen hier sind die ```TimeLogger```, ```UserLogger``` und ```ClassLogger```. Sie fügen den Logeinträgen jeweils einen Zeitstempel, Benutzerinformationen und Klassennamen hinzu.

Mithilfe der ```LoggerFactory``` können die verschiedenen Decorator-Klassen flexibel kombiniert werden.

```java
// Beispielhafte Erstellung eines Loggers mit mehreren Decoratorn
Logger complexLogger = factory.addClassLogger(MyClass.class)
                              .addUserLogger(currentUser)
                              .addTimeLogger()
                              .build();
```