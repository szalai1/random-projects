#include <bits/stdc++.h>
using namespace std;


int main() {
    int t; 
    cin >> t; 
    for (int tt=0; tt <t; tt++) {
        int n;
        cin >> n ;
        for(int i = 0; i < n; i++ ) {
            cout << (i+1+n)%n + 1;
            if (i != n-1) cout << " ";
        }
        cout << endl;
    }
}