# Getränkeabrechnung

## SonarQube Analyse
https://sonarcloud.io/summary/overall?id=Puggingtons_Getraenkeabrechnung&branch=main

## cloc Ausgabe

<!-- CLOC-REPORT-START -->
cloc|github.com/AlDanial/cloc v 1.96  T=0.15 s (437.8 files/s, 19454.8 lines/s)
--- | ---

Language|files|blank|comment|code
:-------|-------:|-------:|-------:|-------:
Java|66|644|112|2177
--------|--------|--------|--------|--------
SUM:|66|644|112|2177
<!-- CLOC-REPORT-END -->

## Jar bauen
mit dem folgenden Command wird die Jar mit allen Dependencies gebaut.
```bash
./gradlew shadowJar
```

Die gebaute `.jar` ist dann in `build/libs` zu finden.