# Getränkeabrechnung

## SonarQube Analyse
https://sonarcloud.io/summary/overall?id=Puggingtons_Getraenkeabrechnung&branch=main

## cloc Ausgabe

<!-- CLOC-REPORT-START -->
cloc|github.com/AlDanial/cloc v 1.96  T=0.17 s (437.6 files/s, 28892.8 lines/s)
--- | ---

Language|files|blank|comment|code
:-------|-------:|-------:|-------:|-------:
Java|75|748|132|4072
--------|--------|--------|--------|--------
SUM:|75|748|132|4072
<!-- CLOC-REPORT-END -->

## Jar bauen
mit dem folgenden Command wird die Jar mit allen Dependencies gebaut.
```bash
./gradlew shadowJar
```

Die gebaute `.jar` ist dann in `build/libs` zu finden.