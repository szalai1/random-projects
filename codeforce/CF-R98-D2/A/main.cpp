#include <bits/stdc++.h>
using namespace std;


int main() {
    int x , y, t;
    cin >> t ;
    for(; t>0; t--) {
        cin >> x >> y; 
        int steps = 2*min(abs(x),abs(y)) ;
        if (abs(x) - abs(y) != 0) {
            steps += ((abs(abs(x) - abs(y)) -1)*2 +1);
        }
        cout << steps << endl;;
    }
}