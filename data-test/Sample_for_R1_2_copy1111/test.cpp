
/*
	input: year
	The function should return 1 if year is leap year, otherwise returns 0
	
	Error in function
	Number of errors: 1
	There is a mistake at line 2, if not ( year%4=0 and year%100!=0) and year%400==0, the function will return unexpected result
	
*/
int leapYear(int year){ // line 1
	if(((year % 4 == 0) && (year % 100 != 0)) || (year % 400 != 0)){ // line 2
		return 1; // line 3
	}             // line 4
	return 0;	  // line 5
} 

/*
 
 input: a,b,c
 Should return 1, if a,b,c represent a Triangle, otherwise return 0;
 Detail: a<b+c && b<a+c && c<a+b, EO: 1
 		otherwise, EO: 0

 Error in function
 Number of errors: 2
 if a=b+c or b= a+c, the function will return unexpected result
*/

int isTriangle(int a, int b, int c){
	if((a <= b+c) && (b <= a+c) && (c< a+b)){
		return 1;
	}
	else return 0;
}

/*
	input: x,minn,maxx
	Should return  probability density function of the continuous uniform distribution
	if maxx<minn or maxx=minn or x = maxx or x = minn the function may will return unexpected result
*/

float PDF(int x, int minn, int maxx){
	if(x <= minn || x >= maxx){
		return 0;
	}
	return 1/(maxx-minn);
}


/*

	input: a,b
	output: if a+b < -10, the function should return 1/(a+b)
			if a+b = 0 or a+b+1 = 0, the function should return 0;
			other wise, the function should return 1/(a+b+1)
	
	
	Error in function: 2
	Number of mistakes: if a+b = 0 or a+b+10 = 0, the function may return unexpected result;
	
*/

int divisionTest(int a, int b){
	int x = a+b;
	int y = x +10;
	if(x<10){
		return 1/x;
	}
	else return 1/y;
}

/*
	input: day, month, year
	Ouput: check wether given date is valid, return 1. if not, return 0.
*/
int CheckValidDay(int day, int month, int year){ 
	if (day>=1 && month>=1 && year>=1 && day<=31 && month<=12 && year<=2020){
		if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month==12) && (day<=31))  return 1;
		if((month == 4 || month == 6 || month == 9 ||month == 11) && (day<=30)) return 1;
		if((month == 2) && (day <= 28))  return 1;
		if((month == 2) && (day == 29) && (year%4 == 0)&&(year%400 != 0)) return 1;
	}
	return 0;
}


/* 
	input: 3 interger values correspond to 3 edges of a Triangle.
   	output: Should return the type of the triangle
   	which has sides of these lengths
   	3 = not a triangle
   	2 = equilateral triangle
   	1 = isoceles triangle
   	0 = other triangle
   	
   
   Error in function
   Number of errors: 3
   There are 2 mistakes in line 5. if i,j,k are all equal or greater than 0 and (j+k=i or k+i = j) ,the function will return unexpected result
   At line 7, if got condition at line 7, but i==j, the function may also return unexprected result
   
*/	


int Tritype(double i, double j, double k){ 		// line 1
 int trityp = 0;								// line 2
 if (i < 0.0 || j < 0.0 || k < 0.0)   			// line 3
 	return 3;									// line 4
 if (i + j <= k || j + k < i || k + i < j) 		// line 5
 	return 3;									// line 6
 if (i > j) trityp = trityp + 1; 				// line 7
 if (i == k) trityp = trityp + 1; 				// line 8	
 if (j == k) trityp = trityp + 1; 				// line 9
 if (trityp >=2) 								// line 10
 	trityp = 2;									// line 11
 return trityp;									// line 12
}


/* 
	intput : averageGrade
 	output: return charecter correspond to given averageGrade
 	in 90-100, return  A
 	in 80-89, return  B
 	in 70-79, return  C
 	in 60-69, return  D
 	in 0-59, return  F
 	else, return  I

 	Error in function
 	Number of error: 5
	if averageGrade is in {90,80,70,60,0} the function will return unexpected result
*/

char grade(int averageGrade){
	if(averageGrade>95 && averageGrade <100)
		return 'A';
	else if(averageGrade>80 && averageGrade<90)
		return 'B';
	else if(averageGrade>70 && averageGrade<80)
		return 'C';
	else if(averageGrade>60 && averageGrade<70)
		return 'D';
	else if(averageGrade>0 && averageGrade < 60)
		return 'F';
	return 'I';
}


/*
	input: a, b, c, d
*/
float foo(int a, int b, int c, int d)
{
	float e;
	if(a==0) return 0;
	int x=0;
	if((a==b)||(c==d))
		x=1;
	e=1/x;
	return e;
}

/*
	input: date of birth
	output: this function return zodiac given date.
*/
struct Date{
	int date;
	int month;
	int year;
};

int calculateZodiac(Date born){
	int date1 = born.date;
	int month1 = born.month;
	int year1 = born.year;	
	int t;
	{

		if (((month1 == 3) && (date1 >= 21) && (date1 <= 31)) || ((month1 == 4) && (date1 <= 19))){
			
			t = 1;
		}

		else if (((month1 == 4) && (date1 >= 20) && (date1 <= 30)) || ((month1 == 5) && (date1 <= 20))) {
		
			t = 2;
		}

		else if (((month1 == 5) && (date1 >= 21) && (date1 <= 31)) || ((month1 == 6) && (date1 <= 20))) {
			
			t = 3;
		}

		else if (((month1 == 6) && (date1 >= 21) && (date1 <= 30)) || ((month1 == 7) && (date1 <= 22))) {
			t = 4;
		}

		else if (((month1 == 7) && (date1 >= 23) && (date1 <= 31)) || ((month1 == 8) && (date1 <= 22))) {
			
			t = 5;
		}

		else if (((month1 == 8) && (date1 >= 23) && (date1 <= 31)) || ((month1 == 9) && (date1 <= 22))) {
			
			t = 6;
		}

		else if (((month1 == 9) && (date1 >= 23) && (date1 <= 30)) || ((month1 == 10) && (date1 <= 22))) {
			
			t = 7;
		}

		else if (((month1 == 10) && (date1 >= 23) && (date1 <= 31)) || ((month1 == 11) && (date1 <= 21))) {
			
			t = 8;
		}

		else if (((month1 == 11) && (date1 >= 22) && (date1 <= 31)) || ((month1 == 12) && (date1 <= 21))) {
			
			t = 9;
		}

		else if (((month1 == 12) && (date1 >= 22) && (date1 <= 30)) || ((month1 == 1) && (date1 <= 19))) {
			
			t = 10;
		}

		else if (((month1 == 1) && (date1 >= 20) && (date1 <= 31)) || ((month1 == 2) && (date1 <= 18))) {
			
			t = 11;
		}

		else if (((month1 == 2) && (date1 >= 19) && (date1 <= 29)) || ((month1 == 3) && (date1 <= 20))) {
			
			t = 12;
		}
		else {
			t = -1;
		}

	}
	
	return t;
}


/*
	input: x, y
	This function is created for loop testing.
*/
int simpleWhileTest(int x, int y){
	while(x < y){
		x+=1;
	}
	return 1;
}


/*
	input: a, b
	output: This function return Greatest common divisor of 2 numbers.
*/
int gcd(int a, int b)
{
	int temp;

	while (b > 0) {
		temp = a % b;
		a = b;
		b = temp;
	}

	return a;
}

/*
	input: a array, min , max
	output: caculate averge value of elements in given array, which is in [min, max]
*/
double average(double value[], double min, double max) 
;









//-------------------------------
// Generate automatically
//-------------------------------

#include <iostream>
#include <fstream>
#include <cstring>
#include <cstddef>
#include <string>
#include <vector>
#include <typeinfo>
using namespace std;

/**
 * CONFIGURATION
 */
// Count the number of recursive call
static int NUM_RECURSIVE = 0; // by default

// The maximum recursive calls of a function, e.g., Fibonaxi
static int MAX_RECURSIVE = 10; // by default

// The maximum iteration for loop
static int MAX_LOOP_ITERATIONS = 1000; // by default
static int iteration[100] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

static int DEFAULT_VALUE_FOR_NUMBER = 0; // by default
static int DEFAULT_VALUE_FOR_CHARACTER = 58; // by default

/**
 * END CONFIGURATION
 */

namespace utils {
char* convertStringToCharPointer(string original) {
	char * S = new char[original.length() + 1];
	strcpy(S, original.c_str());
	return S;
}

float division(int length) {
	float division = 1;
	for (int i = 0; i < length; i++)
		division /= 10;
	return division;
}

template<class myType>
myType toInteger2(char* str) {
	myType mult = 1;
	myType re = 0;

	int len = strlen(str);
	for (int i = len - 1; i >= 0; i--) {
		re = re + ((int) str[i] - 48) * mult;
		mult = mult * 10;
	}
	return re;
}
char* integer_to_string(int x) {
	char* buffer = new char[10];
	sprintf(buffer, "%d", x);
	return buffer;
}
template<class myType>
myType toInteger(char* str) {
	myType mult = 1;
	myType re = 0;

	string clone = str;
	string beforePoint = "";
	string afterPoint = "";
	if (clone.find(".")) {
		beforePoint = clone.substr(0, clone.find_first_of("."));
		afterPoint = clone.substr(clone.find_first_of(".") + 1, clone.length());
	} else {
		afterPoint = "0";
		beforePoint = clone;
	}

	if (afterPoint.compare("0") == 0) {
		return toInteger2<myType>(convertStringToCharPointer(beforePoint));
	} else
		return toInteger2<myType>(convertStringToCharPointer(beforePoint))
				+ division(afterPoint.length())
						* toInteger2<myType>(
								convertStringToCharPointer(afterPoint));
}
}

using namespace utils;
/**
 * Represent a line in the input file
 */
class Element {
private:
	string leftSide;
	string rightSide;
public:

	string getLeftSide(string data) {
		return data.substr(0, data.find_last_of("="));
	}
	string getRightSide(string data) {
		return data.substr(data.find_last_of("=") + 1, data.length() - 1);
	}

	Element(string data) {
		leftSide = getLeftSide(data);
		rightSide = getRightSide(data);
	}
	string getRight() {
		return rightSide;
	}
	string getLeft() {
		return leftSide;
	}

	virtual string toString() {
		return "[Element] " + getLeft() + "=" + getRight();
	}

	bool equalLeftSide(string str) {
		return strcmp(leftSide.c_str(), str.c_str()) == 0;
	}
};

class OneDimensionIndexElement: public virtual Element {
public:
	OneDimensionIndexElement(string data) :
			Element(data) {
	}

	int getIndex() {
		int l = getLeft().find_last_of("[");
		int r = getLeft().find_last_of("]");
		string index = getLeft().substr(l + 1, r - l - 1);
		return toInteger<int>(convertStringToCharPointer(index));
	}

	string getNameElement() {
		return getLeft().substr(0, getLeft().find_last_of("["));
	}

	string toString() {
		return "[OneDimensionIndexElement] " + getLeft() + "=" + getRight();
	}
};

class StructureElement: public virtual Element {
public:
	StructureElement(string data) :
			Element(data) {
	}

	string toString() {
		return "[StructureElement] " + getLeft() + "=" + getRight();
	}
};

class BasicElement: public virtual Element {
public:
	BasicElement(string data) :
			Element(data) {
	}
	string toString() {
		return "[BasicElement] " + getLeft() + "=" + getRight();
	}
};

class SizeofElement: public virtual Element {
public:
	SizeofElement(string data) :
			Element(data) {
	}
	string getNameElement() {
		return getLeft().substr(getLeft().find_first_of("(") + 1,
				getLeft().find_first_of(")") - getLeft().find_first_of("(") - 1);
	}
	string toString() {
		return "[SizeofElement] " + getLeft() + "=" + getRight();
	}
};

class Elements {
private:
	vector<Element*> data;
public:
	vector<Element*>& getList() {
		return data;
	}

	void addElement(string str) {
		// If element e is represent in format of "A=B"
		if (str.find("=") != std::string::npos) {
			if (str.find("sizeof") != std::string::npos) {
				Element* e = new SizeofElement(str);
				data.push_back(e);

			} else if (str.find("[") != std::string::npos) {
				Element* e = new OneDimensionIndexElement(str);
				data.push_back(e);

			} else {
				Element* e = new BasicElement(str);
				data.push_back(e);
			}
		}
	}

	string toString() {
		string output = "data=\r";
		for (int i = 0; i < data.size(); i++) {
			output += "\t" + data[i]->toString() + "\n";
		}
		return output;
	}
	template<class myType>
	myType findValueByName(string nameElement) {
		for (int i = 0; i < data.size(); i++)
			if (dynamic_cast<BasicElement*>(data[i])) {
				if (data[i]->equalLeftSide(nameElement.c_str())) {
					cout << "Initialize " << nameElement << " to "
							<< data[i]->getRight() << endl;
					return utils::toInteger<myType>(
							utils::convertStringToCharPointer(
									data[i]->getRight()));
				}
			}
		return 0;
	}
	// TYPE: ONE DIMENSION, ONE LEVEL POINTER
	// Case 1: int*, int[], char*, char[], etc.
	template<class myType>
	myType* findOneDimensionOrLevelBasicByName(string nameElement,
			myType defaultValue) {
		cout << "Initialize " << nameElement << endl;
		myType* newVar = NULL;
		// Allocate size for the variable corresponding to the given name
		// Ex1: "sizeof(student.name)=10" (size=10)
		// Ex1: "sizeof(student.name)=-1" (size=-1, means NULL)
		bool foundNameElement = false;
		for (int i = 0; i < data.size(); i++)
			if (dynamic_cast<SizeofElement*>(data[i])) {

				string nameElementInSizeOf =
						(dynamic_cast<SizeofElement*>(data[i]))->getNameElement();
				if (nameElementInSizeOf.compare(nameElement) == 0) {
					int size =
							utils::toInteger<int>(
									convertStringToCharPointer(
											dynamic_cast<SizeofElement*>(data[i])->getRight()));
					// Case in Ex2
					if (size <= 0) {
						newVar = NULL;
						cout << "\tset to NULL" << endl;
					} else {
						// Case in Ex1
						cout << "\tsize=" << size << endl;
						newVar = new myType[size];

						for (int j = 0; j < size; j++)
							newVar[j] = defaultValue;
					}
					foundNameElement = true;
					break;
				} else
					continue;
			}

		// Assign value for each one of elements in the given one dimension array, or one level pointer
		if (newVar != NULL && foundNameElement)
			for (int i = 0; i < data.size(); i++)
				if (dynamic_cast<OneDimensionIndexElement*>(data[i])) {
					OneDimensionIndexElement* element =
							dynamic_cast<OneDimensionIndexElement*>(data[i]);
					string nameElementCast = element->getNameElement();
					if (nameElementCast.compare(nameElement) == 0) {
						cout << "\tassign " << nameElementCast << "["
								<< element->getIndex() << "] to "
								<< element->getRight() << endl;
						newVar[element->getIndex()] = utils::toInteger<myType>(
								convertStringToCharPointer(
										element->getRight()));
					}
				}
		return newVar;
	}
	// Case 1: People[], People*, Student[], Student*, etc.
	int RECENT_STRUCTURE_SIZE = 0;
	template<class myStructureType>
	myStructureType* findOneDimensionOrLevelStructureByName(
			string nameElement) {
		cout << "Initialize " << nameElement << endl;
		myStructureType* newVar = NULL;
		for (int i = 0; i < data.size(); i++)
			if (dynamic_cast<SizeofElement*>(data[i])) {
				string nameElementInSizeOf =
						(dynamic_cast<SizeofElement*>(data[i]))->getNameElement();
				if (nameElementInSizeOf.compare(nameElement) == 0) {
					int size =
							utils::toInteger<int>(
									convertStringToCharPointer(
											dynamic_cast<SizeofElement*>(data[i])->getRight()));
					if (size <= 0) {
						newVar = NULL;
						cout << "\tassign to NULL" << endl;
					} else {
						RECENT_STRUCTURE_SIZE = size;
						newVar = new myStructureType[size];
						cout << "\tsize=" << size << endl;
					}
					break;
				} else
					continue;
			}
		return newVar;
	}
	// TYPE: STRUCTURE

};

Elements readContentFromFile(const char* path) {
	Elements data;
	ifstream myReadFile;
	myReadFile.open(path);
	if (myReadFile.is_open()) {
		while (!myReadFile.eof()) {
			string dataItem = "";
			myReadFile >> dataItem;
			data.addElement(dataItem);
		}
	}
	myReadFile.close();
	return data;
}

void writeContentToFile(char* path, string content) {
	ofstream myfile;
	myfile.open(path);
	myfile << content;
	myfile.close();
}
// Avoiding-error checkers from here
bool mark(string);
#include <stdlib.h>
bool checkIndex(int index, string statement) {
	if (index < 0) {
		string negation = "!(" + statement + ")";
		mark(negation);
		exit(0);
	} else {
		return true;
	}
}string build = "";bool mark(string append){build += append + "\n";writeContentToFile("D:/cft4cpp-core/data-test/Sample_for_R1_2_copy1111/0", build);return true;}double average(double value[],double min,double max){mark("statement={###line-of-blockin-function=2###openning-function=true");
	mark("line-in-function=3###offset=59###statement=double sum=0;");double sum=0;

	mark("line-in-function=4###offset=75###statement=int i=0;");int i=0;

	mark("line-in-function=5###offset=86###statement=int arrSize = sizeof(arr)/sizeof(arr[0]);");int arrSize = sizeof(arr)/sizeof(arr[0]);

	while (mark("line-in-function=6###offset=136###statement=i< arrSize") && (i< arrSize)) {mark("statement={###line-of-blockin-function=7");
				if (mark("line-in-function=9###offset=161###statement=min<=value[i]&& value[i]<=max###control-block=if") && (min<=value[i]&& value[i]<=max)) {mark("statement={###line-of-blockin-function=10");
						mark("line-in-function=11###offset=201###statement=sum+=value[i];");sum+=value[i];

		mark("statement=}###line-of-blockin-function=10");}

		mark("line-in-function=14###offset=228###statement=i++;");i++;

	mark("statement=}###line-of-blockin-function=7");}

	if (mark("line-in-function=17###offset=245###statement=arrSize>0###control-block=if") && (arrSize>0)) {
				mark("line-in-function=18###offset=259###statement=return sum/arrSize;");return sum/arrSize;
	}

	mark("line-in-function=19###offset=281###statement=return -999;");return -999;

mark("statement=}###line-of-blockin-function=2");}int main(){try{using namespace utils;Elements data = readContentFromFile("D:/cft4cpp-core/data-test/Sample_for_R1_2_copy1111/input.txt");double* value=data.findOneDimensionOrLevelBasicByName<double>("value", DEFAULT_VALUE_FOR_NUMBER);double min=data.findValueByName<double>("min");double max=data.findValueByName<double>("max");average(value,min,max); }catch(exception& error){build="Phat hien loi runtime";}return 0;}
