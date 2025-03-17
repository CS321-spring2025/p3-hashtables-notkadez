# Project 3: Hashtables

* Author: Kayden Humphries
* Class: CS321 Section 002
* Semester: Spring 2025

## Overview

This project implements an abstract Hashtable class using open addressing and 
is implemented using both Linear Probing and Double Hashing. It then examines 
how the load factor affects the average number of probes required for linear 
probing versus double hashing for various types of inputs. 

## Reflection

This project was so much fun! I always enjoy working with abstraction, and 
this project made good use of it. I thought it was straight forward to implement
after I was fully able to understand some of the definitions. For example, when it
came to the word 'probing', I initially figured that it meant whenever the HashObject
was looked at, including search. Obviously looking back, that doesn't make any sense,
and I was able to figure that out by reading the textbook more closely.

A mistake that I am not proud of that cost me about 2 hours was how I initially made
my hash functions. When I was doing the positive mod operation on them, I was using
the current size of the table as the divisor, instead of the table capacity. When I
found this error, it was immediately obvious what I had done, and my code passed the
test.

## Compiling and Using

To use the full functionality of the hashtable experiment class, you can compile 
and use the tool yourself.
```shell
javac *.class
```

```shell
java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]
```
- \<dataSource>: 1 (random numbers), 2 (date values), 3 (word list)

- \<loadFactor>: Load factor α (e.g., 0.5, 0.7, 0.9)

- \<debugLevel>: 0 (summary) - default, 1 (save tables), 2 (detailed insert output)


To run the tester, use the command `./run-tests.sh`
## Results

### Random Numbers
_Differs run to run_

| Load Factor | Linear Probing Probes |  Double Hashing Probes  |
|:-----------:|:---------------------:|:-----------------------:|
|    0.50     |         1.50          |          1.38           |
|    0.60     |         1.74          |          1.53           |
|    0.70     |         2.18          |          1.72           |
|    0.80     |         2.98          |          2.01           |
|    0.90     |         5.54          |          2.57           |
|    0.95     |         10.32         |          3.14           |
|    0.99     |         42.09         |          4.63           |

### Dates
_Differs run to run_

| Load Factor  | Linear Probing Probes  | Double Hashing Probes |
|:------------:|:----------------------:|:---------------------:|
|     0.50     |          1.28          |         1.37          |
|     0.60     |          1.44          |         1.67          |
|     0.70     |          1.60          |         1.98          |
|     0.80     |          1.82          |         2.42          |
|     0.90     |          2.18          |         3.10          |
|     0.95     |          2.70          |         3.76          |
|     0.99     |          5.41          |         5.38          |

### Word List
_Consistent run to run_

| Load Factor | Linear Probing Probes | Double Hashing Probes |
|:-----------:|:---------------------:|:---------------------:|
|    0.50     |         1.60          |         1.39          |
|    0.60     |         2.15          |         1.53          |
|    0.70     |         3.60          |         1.72          |
|    0.80     |         6.71          |         2.02          |
|    0.90     |         19.81         |         2.57          |
|    0.95     |        110.59         |         3.19          |
|    0.99     |        471.67         |         4.70          |

## Sources used

- [Project Documentation](https://docs.google.com/document/d/1njPdAl_EvFZh4I0_WpCYHiDS_acrArVUklltvy0q3K4/edit?tab=t.0)
- [Default project files](https://github.com/BoiseState/CS321-resources/tree/master/projects/p3-hashing-experiments)
- [Markdown tables](https://www.tablesgenerator.com/markdown_tables)
- Introduction to Algorithms (4th Edition)