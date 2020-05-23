
# [Name Here] (no graphical user interface)

## Deveplopment enviroment
    * IDE: Eclipse Java EE IDE for Web Developers (Oxygen.3a Release)
    * Java 8
    * Window 10

## Set up 
    * import CFT4cpp-core to Eclipse
    * Install IDE DEV-cpp ([Download](https://sourceforge.net/projects/orwelldevcpp/))

## Take the experiment

### Step 1
    * Go to cft4cpp-core\local\setting.properties , and set:
        * Z3_SOLVER_PATH : the absolute path of z3.exe.
        * GNU_MAKE_PATH: the absolute path of mingw32-make.exe
        * GNU_GPlusPlus_PATH: the absolute path of g++.exe.
        * GNU_GCC_PATH: the absolute path of gcc.exe.
    * Input *unit function* for test
        * go to cft4cpp-core\testFunction.txt and input tested function with following format:
            * remove all arguments and space. Example:change `maxx(int x)` to `maxx(int)`
### Step 2
    * go to cft4cpp-core\src\Khamd\Main.java and run 
    * after finish, open file REPORT.html with your favorite Browser, and view result
    

    


