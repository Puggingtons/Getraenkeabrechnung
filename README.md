# Getränkeabrechnung

## SonarQube Analyse
https://sonarcloud.io/summary/overall?id=Puggingtons_Getraenkeabrechnung&branch=main

## cloc Ausgabe

<!-- CLOC-REPORT-START -->
cloc|github.com/AlDanial/cloc v 1.96  T=0.22 s (353.7 files/s, 23681.0 lines/s)
--- | ---

Language|files|blank|comment|code
:-------|-------:|-------:|-------:|-------:
Java|77|778|132|4246
--------|--------|--------|--------|--------
SUM:|77|778|132|4246
<!-- CLOC-REPORT-END -->

## Jar bauen
mit dem folgenden Command wird die Jar mit allen Dependencies gebaut.
```bash
./gradlew shadowJar
```

Die gebaute `.jar` ist dann in `build/libs` zu finden.