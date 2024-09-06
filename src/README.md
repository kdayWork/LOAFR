
## How to run:

As of now, only batch processing functionality has been implemented.

A Loafr.jar file has been created, and to run it, do:

    java -jar Loafr.jar <filepath> <routine>

The filepath should point to the file of the logfile containing entries. The routine should correspond to the name of a routine defined in config.json.

After running, a file containing the analyzed logs will be created in the same directory as the original LogFile, with the name "filepath.out.txt"

### Logfile
The logfiles format should contain one entry per line. Each entrys format should be as follows:

    <id>,<timestamp>,<entryType>,<component>,<field>,<value>

No spaces, commas separating each value. ID and timestamp should be integers, value is a double, the rest are strings

### config
As of now, the config file should have the path "src/main/java/org/example/config.json" from the working directory. This will remain so throughout development, and will likely change to the working directory once complete.

The root element of config.json is a JSONArray, and each element of the array is a "routine". Routines define what
analysis that will be run on a logfile. The base format for a routine is as follows:
```
{
    "routineName": string,
    "eventName": string,
    "analysis": {
        "type": string,
        "component_name": string,
        "value": double,
        "field": string
    }
}
```


"type" is a list of the names of the analyses you wish to perform in this routine. For example, if "type" contains "greaterThan" and "existsComponent", than the resulting entries will be greater than value and of the specified component.

As of now, there are seven different types, with the capability to easily add more in the future. They are as follows:

* greaterThan
    * succeeds if entry.value is greater than analysis.value
* lessThan
    * succeeds if entry.value is less than analysis.value
* equals
    * succeeds if entry.value is equal to analysis.value (allows for 0.0001 difference due to floating point inaccuracy)
* existsComponent
    * succeeds if entry.component equals analysis.component_name
* existsField
    * succeeds if entry.field equals analysis.field
* existsType
    * succeeds if entry.entryType equals routine.eventType
* time
    * requires additional data in analysis:
        * ```"timediff": int```
        * ```"type1": string```
        * ```"type2": string```
    * succeeds if entry is of type1 and there does not exists an entry of type2 with a timestamp less than or equal to entry.timestamp + timediff

## Project Structure 
The project file structure resembles the system diagrams represented in Section 2.* of the design requirements documentation.
The system diagram can be broken down into its individual systems given by the 
file organization in `src/main/java/org/example`:
```
src/main/java/org/example/*:
     - analysis         (Analysis System)
     - batch            (Batch Processing System)
     - interactive      (Interactive System)
     - io               (I/O System)
```

## How To Build

Because we are developing with IntelliJ, to build we have just used the built in Build functionality. We then move the outputted jar file into the base directory to run.

## Associated Files:
[TestScript_ILogFilter](https://docs.google.com/document/d/1BIMgyLfp30sBo6SjvavGF1FxLLtvMYR3oXXsUYz50hw/edit?usp=sharing)  
[TestScript_LogSort](https://docs.google.com/document/d/1L0700gbTxRre7x_hWlIH0RS4H_5BPwzMLVJxTaz_Mfg/edit?usp=sharing)  
[TestScript_Analyzer](https://docs.google.com/document/d/1A77AHTq9SLcA_KFrVnGmN4AfCZ1DhiJYAZ4qhbjSXgc/edit?usp=sharing)  
[TestScript_ConfigReader](https://docs.google.com/document/d/1K5N5XmuxJcAUhK914aDT8sCHeSPpElWos1xV0HPjF74/edit?usp=sharing)
[TestScript_FileHandler](https://docs.google.com/document/d/18UiLFsWSql3rzpG8iP8TkprHsbOJpAWn1XSI1OPRaW0/edit?usp=sharing)
[TestScript_NameChecker](https://docs.google.com/document/d/10ngZQihAGasG2ZWOTHGnM9UOhwQn3EA1OhiYGj3CR4k/edit?usp=sharing)

## End to End Scenario (Batch Processing):

**Using this routine:**

```
{
    "routineName": "1",
    "eventName": "B",
    "analysis": {
      "type": [
        "greaterThan",
        "existsType"
      ],
      "component_name": "Y",
      "value": 3,
      "field": "g"
    }
  }
```

**On this logfile:**

1,1001,A,y,g,1  
2,1002,A,x,g,2  
3,1003,B,s,g,3  
4,1004,A,h,g,4  
5,1005,B,u,g,5  
6,1006,B,c,g,6

**Produced this output:**

5,1005,B,u,g,5.0  
6,1006,B,c,g,6.0

**Step by step**

Loafr.jar was called with filepath "src/main/java/org/example/file.txt" and routineName "1".

BatchProcessing calls FileHandler.readFile(filepath), which constructs a JSONObject for each line of the log file, 
verifying the format in the process. A JSONArray of the entries is returned to BatchProcessing.

BatchProcessing calls ConfigReader.fetchRoutine(routineName), which reads through the JSONARray of config.json file, and 
returns the JSONObject with the corresponding routineName.

Using the routine and entries, BatchProcessing creates an instance of Analyzer, and calls Analyzer.analyze(). analyze 
then loops through each element of the "type" array in the routine. analyze maintains a list of entries, with it being
set to each list of entries returned after each type of analysis is run. In this case, greaterThan is run first, so 
for each entry in entries, if entry.value is greater than 3, it is added to the new list of entries. Then, using the new
list, existsType is run. So for each entry in entries, if entry.entryType equals "B", the entry is added to a new list.
Because that was the last analysis type, the list is returned to BatchProcessing.

Using the new list of entries, BatchProcessing calls FileHandler.writeFile(entries, filepath), where the entries are 
written to "filepath_out.txt" in the same format as the file they were read in from.


### Requirements Traceability
| Section in SRS | Class Name(s)                                |
|----------------|----------------------------------------------|
| 4.5, 4.6       | FileHandler                                  | 
| 4.14           | ConfigReader                                 |
| 4.7            | BatchProcessingHandler                       | 
| 4.12           | FileHandler(Format Checker req), NameChecker |
| 6              | BatchProcessing                              |
| 1.1, 2.2, 4    | Analyzer                                     |

## Changes / Version History:

We made some changes to the Context Diagram in the design template, as follows

**Changed Return Types to JSON**

**Trimmed ID Filter from Diagram**

**Got Rid of the "LogFile" object**

**Removed FORMATChecker as a class, and placed it inside FileHandler**

Here is the updated Context Diagram
[Context Diagram](https://lucid.app/lucidchart/534284dd-3c88-44bc-a8bc-8d90d52a50ff/edit?invitationId=inv_1def9a05-6892-43be-99e0-1d0e7ab915e1&page=0_0#)

