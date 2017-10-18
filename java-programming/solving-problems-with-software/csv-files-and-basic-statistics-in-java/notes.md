# CSV Files and Basic Statistics in Java

## What Countries Export...?

### CSV Data: Common Separated Values
* CSV: Common Separated Values
* Standard format that can be interpreted by most spreadsheet formats
* Non-proprietary format

### Using CSV Libraries
* Use the Apache commons CSVParser class
  * `import org.apache.commons.csv.*;`
* Get CSVparser from FileResource
  * `CSVParser parser = fr.getCSVParser();`

## Weather CSV Problem

### Converting Strings to Numbers
* CSVParser reads data from CSV files as strings
* Need to convert this data to numeric values
* String to Integer: `Integer.parseInt( "1" );`
* String to Double: `Double.parseDouble( "3.14" );`

### Java for Nothing - null
* `null` means nothing or no object
* All expressions have types but what type is null?
  * It has it's own special type which cannot be used in Java code
  * Can be converted into any object type
* Primitive types cannot be null
* Object types can be null
