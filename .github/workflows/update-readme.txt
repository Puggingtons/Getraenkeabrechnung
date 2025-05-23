name: Update README

on:
  push:
    branches:
      - main
  schedule:
    - cron: '0 * * * *' # Optional: Aktualisierung jede Stunde

jobs:
  update-readme:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Run your script
        id: calculate
        run: |
          cat $GITHUB_STEP_SUMMARY

      - name: Update README
        run: |
          sed -i "s|Der aktuelle Code-Count ist: [0-9]*|Der aktuelle Code-Count ist: $GITHUB_STEP_SUMMARY|g" README.md

      - name: Commit and push changes
        run: |
          git config --global user.name "github-actions[bot]"
          git config --global user.email "github-actions[bot]@users.noreply.github.com"
          git add README.md
          git commit -m "Update README with latest count"
          git push
