#include <bits/stdc++.h>

using namespace std;



int main() {
    long long n;
    cin >> n;
    long long lovely = 1; 
    if (n%2== 0) {
        lovely = 2;
        while(n%2 == 0) {
                n/=2;
         }
    }
    long long sq = sqrt(n);
    for(long long i = 3; i<=sq; i+=2) {
        if (n%i == 0) {
            while(n%i == 0) {
                n/=i;
            }
            lovely*=i;
        }
    }
    if (n!=1){
        cout << n*lovely;
        return 0;
    }
    cout << lovely;
}