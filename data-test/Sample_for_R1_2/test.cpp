
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
	input: a array, min , maxx, tcnt, vcnt
	output: caculate averge value of the first 100 elements in given array, which is in [min, max].
	
*/
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


