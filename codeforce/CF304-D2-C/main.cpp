#include <bits/stdc++.h>
using namespace std;


int main() {
     int n; 

    cin >> n ;
    if (n %2 == 0) {
        cout << -1;
        return 0;
    }
    vector<int> a;
    vector<int> b;
    vector<int> c; 
    for(int i = 0; i < n; i+=1) {
        int _a = i;
        int _b = (i+2)%n;
        int _c = (_a+_b)%n;
        c.push_back(_c);
        b.push_back(_b);
        a.push_back(_a );
    }
    for( int i = 0; i < n ; i++) {
        cout << a[i];
        if (i==n-1) cout<<endl;
        else cout << " "; 
    } 
        for( int i = 0; i < n ; i++) {
        cout << b[i];
        if (i==n-1) cout<<endl;
        else cout << " "; 
    }
        for( int i = 0; i < n ; i++) {
        cout << c[i];
        if (i==n-1) cout<<endl;
        else cout << " "; 
    }
}