#include <bits/stdc++.h>
using namespace std;

int main() {
    int t; cin>>t; 
    for(int tt=0; tt<t; tt++) {
        int a, b;
        cin >> a >> b; 
        cout << max(0,a-1) << " " << b << endl; 
    }
}