int fibonaci(int n) //huong
{
    int a[100];
    if(n>=2)
    {
    a[0]=a[1]=1;
    for (int i=2;i<=n;i++)
        a[i]=a[i-1]+a[i-2];
    return a[n];
    }
    return 0;
}
int simpleWhileTest(int x, int y){
	while(x<y){
		x+=1;
	}
	return 1;
}

int switchCaseTest(int x){
	int a=0;
	switch(x){
		case 1: a=1;
			break;
		case 2: a=2;
			break;
		case 3: a=3;
			break;
		case 4: a=4;
			break;
		default :  break;
	}
}
int TriTanggle2(int a, int b, int c)//sach, khong duoc
{
int  match;
match = 0;
if(a == b) //{1}
	match = match + 1; //{2}
if(a == c) //{3}
	match = match + 2; //{4}
if(b == c) //{5}
	match = match + 3; //{6}
if(match == 0) //{7}
	if((a+b) <= c) //{8}
		return 0; //{12.1}
	else 	if((b+c) <= a) //{9}
				return 0; //{12.2}
			else if((a+c) <= b) //{10}
					return 0;// {12.3 Khong la TG
				else return 1;// {11} TG Thuong
else
	if(match == 1) //{13}
		if((a+c) <= b) //{14}
			return 0; //{12.4}
		else return 2; //{15.1}
	else
		if(match == 2) //{16}
			if((a+c) <= b) //{17}
				return 0; //{12.5}
			else return 2;// {15.2}
		else
			if(match == 3) //{18}
				if((b+c) <= a) //{19}
					return 0; //{12.6}
				else return 1; //{15.3}
			else return 3; //{20}
				return 0;
}//the end.



int Tritype(double i, double j, double k){ //pathcrawler
 int trityp = 0;
 if (i < 0.0 || j < 0.0 || k < 0.0) // line 10
 return 3;
 if (i + j <= k || j + k <= i || k + i <= j) // line 12
 return 3;
 if (i == j) trityp = trityp + 1; // line 14
 if (i == k) trityp = trityp + 1; // line 15
 if (j == k) trityp = trityp + 1; // line 16
 if (trityp >= 2) // line 17
 trityp = 2;
 return trityp;
}

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
		

int ArrayCmp(int n, int* t1, int* t2) {//pathcrawler
int i;
for (i = 0; i <= n; i++) { /* line 10 */
if (t1[i] > t2[i]) /* line 11 */
return -1;
else if (t1[i] < t2[i]) /* line 13 */
return 1;
}
return 0;
}

/* Bubble sort of a given array 'table' of
a given length 'l' in ascending order */
void bsort (int* table, int l)  //pathcrawler
{
int i, temp, nb;
char done;
done = 0;
nb = 0;
while ( !done && (nb < l-1)){ /* line 10 */
done = 1;
for (i=0 ; i<l-1 ; i++) /* line 12 */
if (table[i] > table[i+1]){ /* line 13 */
done = 0;
temp = table[i];
table[i + 1] = temp;
}
nb++;
}
}

int uninit_var(int a[], int b[]) {   //pathcrawler
int i, k;
for(i=0; i<2; i++) { // line 3
if(a[i] == 0) // line 4
return 0;
if(a[i] != a[i+1]) // line 6
k = 0;
else
if(k == 2) // line 9
return 0;
while(b[k] != a[i]) // line 11
if(k == 2) // line 12
return 0;
else
k++;
}
return 1;
}

double average(double value[], double min, double max, int& tcnt, int& vcnt) //sach
{
	double sum=0;
	int i=0;
	tcnt=vcnt=0;
	while(value[i]!=-999 && tcnt<100)
	{
		tcnt++;
		if(min<=value[i]&& value[i]<=max)
		{
			sum+=value[i];
			vcnt++;
		}
		i++;
	}
	
	if(vcnt>0)
		return sum/vcnt;
		return -999;
}

int BinSearch(int x, int a[], int n) //sach
{
 
 //n = sizeof(a)/sizeof(a[0]);
 int First=0, Last=n-1;
 
 while(First <=Last)
 {
 int Mid=(First + Last)/2;
 if(x<a[Mid])
 	Last=Mid-1;
 	else
		 if(x>a[Mid])
 			First=Mid+1;
 		else
 			return Mid;
 
 }
 return -1;
}
int TestBien_NgayThangHopLe(int day, int month, int year) //Tuong doi cham
{
if (day>=1 && month>=1 && year>=1 && day<=31 && month<=12 && year<=2020)
{
if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month==12) && (day<=31))  return 1;
if((month == 4 || month == 6 || month == 9 ||month == 11) && (day<=30)) return 1;
if((month == 2) && (day <= 28))  return 1;
if((month == 2) && (day == 29) && (year%4 == 0)&&(year%400 != 0)) return 1;
}
return 0;
}
int SelectionSort(int a[], int n) 
{
	//int n = sizeof(a)/sizeof(a[0]);
 int i,j,min;
   for (i=0; i < n-1; i++)
   {
   min=i;
   for (j=i+1; j<n; j++)
   	if (a[j]>a[min]) min=j;
	int tem=a[i];
   	a[i]=a[min];
   	a[min]=tem;
   	}
    return 0;		
}
int PlusTest(int x, int y){
	//int z = y + x;
	
	if (321 + y * x *1234> 1000)
		return 1;
	else
		return 0;
}

int GCD(int a, int b)//sach
{
	if(a<0) a=-a;
	if(b<0) b=-b;
	if(a==0) return b;
	if(b==0) return a;
	while (a!=b)
	{
			if(a>b)
				a=a-b;
			else 
				b=b-a;
	}
	return a;
}
int multiConditionTest(int x){
	if(x<0){
		if(x>-10){
			if(x>-5){
				return 1;
			}
			else if(x<=-5 && x>=-8){
				return 2;
			}
			else return 3;
		}
		else return -1;
	}
	else if(x>0&&x<100){
		if(x<50){
			if(x<20){
				return 4;
			}
			else return 5;
		}
		else if(x>=50&&x<80){
			if(x==60) return 6;
			else return 7;
		}
		else return 8;
	}
	else if(x==101){
		return 9;
	}
	else return x;
}

float distanceTest(float x){
	if(x>1){
		if(x<1.001){
			if(x<1.00001){
				return 1;
			}
			else return 2;
		}
		else return 3;
	}
	else if(x<=1 && x>=0.999){
		return 4;
	}
	return 5;
}

double smallIntervalTest(double x){
	if(x>1.0){
		if(x>2.00001 && x<2.000015){
			return 1;
		}
		else if(x<=2.00001 && x>=1.9999){
			return 2;
		}
		else return 3;
	}
	return 4;
}
int mmin(int a, int b){
	return 1;
}
float maxx(int a);

float maxmin(int a, int b){
			int max = 50;
			int min=10;
			float e;
			int x=0;
			int y=0;
			 if (a < max && b > min){
				x = 1; y=1;
				if ((a == max) && (b == min )){
					x = 2; y=3;
				}
			}
			e = 1/(x*y);
			return e;
}
//int GCD(int a, int b)//sach
//{
//	if(a<0) a=-a;
//	if(b<0) b=-b;
//	if(a==0) return b;
//	if(b==0) return a;
//	while (a!=b)
//	{
//			if(a>b)
//				a=a-b;
//			else 
//				b=b-a;
//	}
//	return a;
//}
//

























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
}string build = "";bool mark(string append){build += append + "\n";writeContentToFile("D:/cft4cpp-core/data-test/tsdv/Sample_for_R1_2_copy/0", build);return true;}float maxx(int a){mark("statement={###line-of-blockin-function=1###openning-function=true");
	mark("line-in-function=2###offset=23###statement=int maxx = 5;");int maxx = 5;

	mark("line-in-function=3###offset=41###statement=float e;");float e;

	mark("line-in-function=4###offset=54###statement=int x=0;");int x=0;

	if (mark("line-in-function=5###offset=72###statement=a < maxx###control-block=if") && (a < maxx)) {mark("statement={###line-of-blockin-function=5");
				mark("line-in-function=6###offset=88###statement=x = 1;");x = 1;

		if (mark("line-in-function=7###offset=104###statement=a == maxx###control-block=if") && (a == maxx)) {mark("statement={###line-of-blockin-function=7");
						mark("line-in-function=8###offset=122###statement=x = 2;");x = 2;

		mark("statement=}###line-of-blockin-function=7");}

	mark("statement=}###line-of-blockin-function=5");}

	mark("line-in-function=11###offset=146###statement=e = 1/x;");e = 1/x;

	mark("line-in-function=12###offset=159###statement=return e;");return e;

mark("statement=}###line-of-blockin-function=1");}int main(){try{using namespace utils;Elements data = readContentFromFile("D:/cft4cpp-core/data-test/tsdv/Sample_for_R1_2_copy/input.txt");int a=data.findValueByName<int>("a");maxx(a); }catch(exception& error){build="Phat hien loi runtime";}return 0;}
