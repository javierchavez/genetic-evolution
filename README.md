# Genetic Evolution
We are to evolve by means of reproduction creatures. Since we are merely
crossing genes, the selection process is very important. We need to maintain diversity
while still improving the overall fitness of the population. This type of evolution
is a little different in that we are not evolving based on a environment but rather
a fitness. This means we have to evaluate after every crossover to decide how to
handle the offspring.


#Contributing
How to set up your env
###Get the code

Clone the repo.
```bash
git clone https://github.com/javierchavez/genetic-evolution.git
```

Get the submodules.
```bash
git submodule init
git submodule update
```

###Intellij ENV
---
The following are if you are using Intellij

1.    Import project
2.    Choose 'Import project from external model' and select Gradle
3.    On next screen choose, Use customizable gradle wrapper.
4.    After the IDE is finished open Project Settings->Modules and
mark the `src` directory as Sources.
5.    Click ok and right click on MainSim and choose Run 'MainSim.Main()'

###Simple ENV
---
The following are if you are compiling with via command line without a IDE.
######Prereq
Make sure these are installed in your system (brew is great for this).
*    Gradle
*    Java 1.8

###Running
Running via the command line
```bash
# gui
gradle run

# headless
gradle server
```

###Building
```bash
gradle build
```


---
######Authors
- Alexander Baker <alexebaker@unm.edu>
- Dominic Salas <dominic.salas@gmail.com>
- Cari Martinez <cari1zuni@gmail.com>
- Javier Chavez <javierc@cs.unm.edu>


Adding stuff

NOTE: change parameters pctMutations = 90; and pctCrossover = 90; in the GA to modify the performance
Alternative methods crossover2 and selection3 may be substituted in the createNextGeneration method in the Genetic Algorithm class to explore different heuristics
