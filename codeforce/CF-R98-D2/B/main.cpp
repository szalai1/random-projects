#include <bits/stdc++.h>
using namespace std;


int main() {
    int t;
    cin >> t ;
    for(; t>0; t--) {
        int n; 
        int sum = 0;
        int max_= 0;
        cin >> n;
        for (int i = 0; i<n; i++) {
            int _t; 
            cin >> _t;
            sum+=_t;
            int _max = _t;
            //if (_t%(n-1) !=0) _max+=1;
            if (_max > max_) max_ = _max;
        }
        if( sum %(n-1) == 0) {
            cout << (n-1)*max_-sum << endl;
        } else {
           int a = (n-1)- (sum %(n-1));
           int b = (n-1)*max_-sum; 
           cout << max(a,b) << endl; 
        }

    }
}