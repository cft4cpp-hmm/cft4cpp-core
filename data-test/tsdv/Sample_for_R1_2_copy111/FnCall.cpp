
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
	if(x>0 && x<2 ){
		return 0;
	}
	else if(x>=2 && x<=5){
		return 1;
	}
	else return 2;
}

// Calculate 1 + 2 + ... + m
int SumRecursive(int m){
	if (m <= 0)
		return 0;
	else
		return m + SumRecursive(m-1);
}





















