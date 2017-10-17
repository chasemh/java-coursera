# Baby Names MiniProject

## Overview
* Question:
  * Given your name and the year you were born, which name this year has the same popularity rank as your name in your birth year?

## Data Overview
* Given .txt files for each year
* Each role in the file is of the format Name, Gender, Number of babies with that name
* Female names always listed before male names in the files
* Data has no header row
  * Making a CSVParser without a header row
    * `fileResource.getCSVParser( false );`
  * Getting records from the CSVParser without using header names
    * `csvRecord.get( index );` where `index` is the index of the entry in the record
      * For example, `csvRecord.get(0);` on this data would get the first entry in the record, which would be the Name field

## Total Births
