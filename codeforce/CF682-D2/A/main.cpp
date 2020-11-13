#include <bits/stdc++.h>
using namespace std;


int main() {
    int n; 
    cin >> n; 
    for (int i = 0; i<n; i++) {
        int x; 
        cin >> x;
        for (int j = 0; j < x; j++) {
            cout << 1; 
            if (j == x-1)
                cout << endl;
            else
                cout << " ";
        }

    }
}