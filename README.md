
# [Name Here] 

## Deveplopment enviroment

* IDE: Eclipse Java EE IDE for Web Developers (Oxygen.3a Release)
* Java 8
* Window 10

## Set up 

* import CFT4cpp-core to Eclipse
* Install IDE DEV-Cpp ([Download](https://sourceforge.net/projects/orwelldevcpp/))
* SMT-Solver Z3 ([Download](https://z3.codeplex.com/releases))
* Mingw compiler. The installation can be done by setting up DEV-Cpp

## Take the experiment

***Clone/Download project ([Github link](https://github.com/cft4cpp-hmm/cft4cpp-core))***
### Step 1 - Configure
* Go to *cft4cpp-core\local\setting.properties* , and set:
    1. Z3_SOLVER_PATH : the absolute path of z3.exe.
    2. GNU_MAKE_PATH: the absolute path of mingw32-make.exe
    3. GNU_GPlusPlus_PATH: the absolute path of g++.exe.
    4. GNU_GCC_PATH: the absolute path of gcc.exe.
### Step 2 - Input
* The input of demostration is a C/C++ projects. The demonstrated version only accepts projects written in Dev-Cpp (its make file name: Makefile.win).
1. Input your function at *cft4cpp-core\data-test\tsdv\Sample_for_R1*
2. go to *cft4cpp-core\testFunction.txt* and type your function name with following format:
    * remove all arguments and white space. For example: change `maxx(int x)` to `maxx(int)`
### Step 3 - Run
* go to *cft4cpp-core\src\Khamd\Main.java* and run 
* after finishing, open file *REPORT.html* with your favorite Browser, and view result

## Output

The output of this demostration is a html file, includes:
1. All testpath with weight for each edge and its test data
2. If there are deadccode in test path, it will be red
3. If tested function has loop, the result will show test-data for 2, k iterations. (k is a random number, and is greater then 2)
4. At the end, the file will show test data generation time for your coverage criteria, C2 or C3 coverage. And loop coverage, test data generation time for loop, if your function has loop




