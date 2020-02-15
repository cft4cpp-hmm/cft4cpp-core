
int mmin(int x, int y){
	if  (x < y)
		return x;
	else
		return y;
}

int mmin3(int x, int y, int z){
	int r = mmin(x, y);
	
	if (r < z)
		return r;
	else
		return z;
}


int simpleTest(int x){
	
	if(x>0 && x<=2 ){
		if(x==1) return 1;
		return 0;
	}
	else if(x>2 && x<=5){
		
		return 1;
	}
	else return 2;
}
int simpleTest1(int x){
	if(x==1){
		return 1;
	}
	else if(x==2){
		return 2;
	}
	else if(x==3){
		return 3;
	}
	else if(x==4){
		return 4;
	}
	else return x;
}

bool oddNumber(int x){
	if(x%2==0) return false;
	return true;
}

int arrTest(int arr[]){
	if(arr[0]==0) return 0;
	else if(arr[0]!=0 && arr[1]==1) return 1;
	return 2;
}
int arrTest1(int arr[]);
bool primeNumber(int n){
	if(n < 3) return true;
	else {
		for(int i=2;i< n;i++){
			if(n%i==0) return false;
		}
		return true;
	}
	
}
int PlusTest2(int x, int y){
	//int z = y + x;
	
	if (321 + y * x *1234> 1000)
		return 1;
	else
		return 0;
}
int PlusTest3(int x, int y){

	int z = y * x * x;

	if (z > 100)
		return 1;
	else
		return 0;
}
int simpleWhileTest(int x, int y){
	while(x< y){
		x+=1;
	}
	return 1;
}
int whileTest1(int x){
	int i=0;
	while(i< x ){
		if(x%2==0) i+=2;
		else i+=3;
	}
}
int switchCaseTest(int x) {
	int a = 0;
	switch (x) {
		case 1 : break;
		case 2 : break;
		default : a += 2;
		
	}
}

int switchCaseTest1(int x){
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

int multiConditionTest1(int x, int y){
	if(x==y){
		if(x>0) return 0;
		else return 1;
	}
	else if (x==y-1){
		if(x>0) return 2;
		else return 3;
	}
	else if(x==y+1){
		return 4;
	}
	return 5;
}
int multiConditionTest2(int x){
	if(x>=0 && x<10){
		if(x<5){
			if(x<2){
				return 0;
			}
			else return 1;
		}
		else {
			if(x>8) {
				return 2;
			}
			else if(x==6) return 3;
			else return 4;
		}
	}
	else if(x>=10 && x<20){
		if(x>=12 && x <=15){
			if(x==12) return 5;
			if(x==13) return 6;
			else return 7;
		}
		else if(x<17){
			if(x!=16) return 8;
			else return 9;
		}
		else return 10;
	}
	else if (x==20) return 11;
	else {
		if(x==30) return 12;
		else if (x!=30) return 13;
	} 
	
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

double doubleTest(double x){
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
int arrTest1(int arr[]);















#include <string>#include <iostream>#include <fstream>#include <stdlib.h>using namespace std;void writeContentToFile(char* path, string content){ofstream myfile;myfile.open(path);myfile << content;myfile.close();}string build = "";bool mark(string append){build += append + "\n";writeContentToFile("D:/cft4cpp-core/data-test/tsdv/Sample_for_R1_2_copy1/10.txt", build);return true;}int arrTest1(int arr[]){mark("statement={###line-of-blockin-function=1###openning-function=true");
	mark("line-in-function=2###offset=27###statement=int size = sizeof(arr)/sizeof(arr[0]);");int size = sizeof(arr)/sizeof(arr[0]);

	mark("line-in-function=3###offset=68###statement=int s = 0;");int s = 0;

	mark("statement={###additional-code=true###surrounding-control-block=for");mark("line-in-function=4###offset=85###statement=int i=0;");for (int i=0;
			mark("line-in-function=4###offset=93###statement=i< size") && i< size;
			mark("line-in-function=4###offset=101###statement=i++") && (i++)) {mark("statement={###line-of-blockin-function=4");
				mark("line-in-function=5###offset=110###statement=s+=arr[i];");s+=arr[i];

	mark("statement=}###line-of-blockin-function=4");}mark("statement=}###additional-code=true###surrounding-control-block=for");

	if (mark("line-in-function=7###offset=130###statement=s%2==0###control-block=if") && (s%2==0)) {
				mark("line-in-function=7###offset=138###statement=return 0;");return 0;
	}

	mark("line-in-function=8###offset=150###statement=return 1;");return 1;

mark("statement=}###line-of-blockin-function=1");}int main(){try{int arr[3];arr[2]=16730;arr[0]=1;arr[1]=3592;arrTest1(arr); }catch(exception& error){build="Phat hien loi runtime";}return 0;}
