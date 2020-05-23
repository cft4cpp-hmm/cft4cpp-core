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
float maxx(int a){
			int maxx = 5;
			float e;
			int x=0;
			 if (a < maxx){
				x = 1;
				if (a == maxx){
					x = 2;
					x = 4;
				}
			}
			e = 1/x;
			return e;
}

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


