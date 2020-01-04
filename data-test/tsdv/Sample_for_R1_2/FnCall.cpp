
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
int myTest(int x){
	
	if(x>0 && x<3 ){
		if(x==1){
			return 1;
		}
		return 0;
	}
	else if(x>=3 && x<=5){
		
		return 1;
	}
	else return 2;
}
int myTest1(int x){
	int c = x/2;
	if(c>1) return 1;
	else return 2;
}

int myTest2(int x, int y){
	x = x+y;
	if(x>0 && x<2 ){
		return 0;
	}
	else if(x>=2 && x<=5){
		return 1;
	}
	else return 2;
}
int myTest3(int arr[]){
	if(arr[0]<0) return 0;
	else if(arr[0] >=0 && arr[1] <=1) return 1;
	return 2;
}
int myTest5(int s[],int n)
{
	int t;
	for(int i=0; i<n; i++)
	{
		
		if (s[i] %2 ==1) t=t+s[i];
	}
	return t;
}
bool primeNumber(int n){
	if(n<3) return true;
	else {
		for(int i=2;i<n;i++){
			if(n%i==0) return false;
		}
		return true;
	}
	
}



// Calculate 1 + 2 + ... + m
int SumRecursive(int m){
	if (m <= 0)
		return 0;
	else
		return m + SumRecursive(m-1);
}





















