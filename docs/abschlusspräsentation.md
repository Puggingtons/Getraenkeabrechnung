---
theme: moon
revealOptions:
	 pdfSeparateFragments: false
---

# ThirstyCalc

Paul Bader, Sarah Ficht & Kayra Güler

---



## Einführung (S)

- Übersicht über die Applikation **(1 P.)**
- Starten der Applikation **(1 P.)**
- Technischer Überblick **(2 P.)**

--

### Übersicht

- [ ] Übersicht über die Applikation **(1 P.)**

--

### Starten der Applikation

- [ ] Übersicht über die Applikation **(1 P.)**

--

### Technischer Überblick

- [ ] Übersicht über die Applikation **(1 P.)**


---





## Softwarearchitektur (Paul)

- [ ] 2\. Softwarearchitektur **(8 P.)**
    - [ ] Gewählte Architektur **(4 P.)**
    - [ ] Domain Code **(1 P.)**
    - [ ] Analyse der Dependency Rule **(3 P.)**
        - [ ] Positiv-Beispiel
        - [ ] Negativ-Beispiel

---



## SOLID (Paul)

- [ ] 3\. SOLID **(8 P.)**
    - [ ] Analyse SRP **(3 P.)**
        - [ ] Positiv-Beispiel
        - [ ] Negativ-Beispiel
    - [ ] Analyse OCP **(3 P.)**
        - [ ] Positiv-Beispiel
        - [ ] Negativ-Beispiel
    - [ ] Analyse [LSP/ISP/DIP] **(2 P.)**
        - [ ] Positiv-Beispiel
        - [ ] Negativ-Beispiel

---



## Weitere Prinzipien (Paul)

- [ ] 4\. Weitere Prinzipien **(8 P.)**
    - [ ] Analyse GRASP: Geringe Kopplung **(3 P.)**
    - [ ] Analyse GRASP: [Polymophismus/Pure Fabrication] **(3 P.)**
    - [ ] DRY **(2 P.)**

---





## Unit Tests (S)

- 10 Unit Tests **(2 P.)**
- ATRIP: Automatic, Thorough und Professional **(2 P.)**
- Fakes und Mocks **(4 P.)**

--

### 10 Unit Tests

- [Zeigen und Beschreiben von 10 Unit-Tests und Beschreibung, was getestet wird] **(2 P.)**

--

### ATRIP

- ATRIP: Automatic, Thorough und Professional (2P)
[je Begründung/Erläuterung, wie ‘Automatic’, ‘Thorough’ und ‘Professional’ realisiert wurde – bei
‘Thorough’ zusätzlich Analyse und Bewertung zur Testabdeckung]

--

### Automatic

- [Begründung/Erläuterung, wie ‘Automatic’ realisiert wurde]
    + alles wird in IDE auf einmal ausgeführt zum individuellen testing vor push / pr in main
    + pipeline führt bei jedem merge in main branch die tests aus

--


### Thorough
- [Code Coverage im Projekt analysieren und begründen]
    + SonarQubeCloud bietet Übersicht über Codecoverage spezifisch für lines und branches

--

### Professional
- [1 positves Beispiel zu ‘Professional’; Code-Beispiel, Analyse und Begründung, was professionell ist]
- Arrange-Act-Assert Pattern: Durch Branchabdeckung werden Randfälle aufgedeckt

--



### Fakes und Mocks

- [Analyse und Begründung des Einsatzes von 2 Fake/Mock-Objekten (die Fake/Mocks sind ohne Dritthersteller-Bibliothek/Framework zu implementieren)]
- Zeigen der Implementierung und Nutzung; zusätzlich jeweils UML Diagramm mit
Beziehungen zwischen Mock, zu mockender Klasse und Aufrufer des Mocks]


---



## Domain Driven Design (S)

- Ubiquitous Language **(2 P.)**
- Repositories **(1,5 P.)**
- Aggregates **(1,5 P.)**
- Entities **(1,5 P.)**
- Value Objects **(1.5 P.)**

--

### Ubiquitous Language

- Ubiquitous Language: gemeinsame, strenge Sprache zwischen Entwicklern und Benutzern, basierend auf dem Domänenmodell.
- Ziel ist es, Mehrdeutigkeiten zu minimieren und die Kommunikation und das Verständnis zwischen allen am Softwareentwicklungsprozess Beteiligten zu verbessern.

--


- Bezeichnung
- Bedeutung
- Begründung

--

### Repositories

- [UML, Beschreibung und Begründung des Einsatzes eines Repositories; falls kein Repository
vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist – NICHT,
warum es nicht implementiert wurde]

--

### Aggregates

- [UML, Beschreibung und Begründung des Einsatzes eines Aggregates; falls kein Aggregate
vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist– NICHT,
warum es nicht implementiert wurde]

--

### Entities

- [UML, Beschreibung und Begründung des Einsatzes einer Entity; falls keine Entity vorhanden:
ausführliche Begründung, warum es keine geben kann/hier nicht sinnvoll ist– NICHT, warum es nicht
implementiert wurde]

--

### Value Objects

- [UML, Beschreibung und Begründung des Einsatzes eines Value Objects; falls kein Value Object
vorhanden: ausführliche Begründung, warum es keines geben kann/hier nicht sinnvoll ist– NICHT,
warum es nicht implementiert wurde]

---




## Refactoring (K)

- [ ] 7\. Refactoring **(8 P.)**
    - [ ] Code Smells **(2 P.)**
        - [ ] CODE SMELL 1
        - [ ] CODE SMELL 2
    - [ ] Refactorings **(6 P.)**

---



## Entwurfsmuster (K)

- [ ] 8\. Entwurfsmuster **(8 P.)**
    - [ ] Entwurfsmuster: [Name] **(4 P.)**
    - [ ] Entwurfsmuster: [Name] **(4 P.)**

---



# Vielen Dank für die Aufmerksamkeit
