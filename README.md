# Getränkeabrechnung

## SonarQube Analyse
https://sonarcloud.io/summary/overall?id=Puggingtons_Getraenkeabrechnung&branch=main

## cloc Ausgabe

<!-- CLOC-REPORT-START -->
cloc|github.com/AlDanial/cloc v 1.96  T=0.10 s (650.1 files/s, 28704.6 lines/s)
--- | ---

Language|files|blank|comment|code
:-------|-------:|-------:|-------:|-------:
Java|66|645|113|2156
--------|--------|--------|--------|--------
SUM:|66|645|113|2156
<!-- CLOC-REPORT-END -->

## Jar bauen
mit dem folgenden Command wird die Jar mit allen Dependencies gebaut.
```bash
./gradlew shadowJar
```

Die gebaute `.jar` ist dann in `build/libs` zu finden.