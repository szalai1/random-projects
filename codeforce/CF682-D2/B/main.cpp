#include <bits/stdc++.h>
using namespace std;


int main() {
    int t; 
    cin >> t; 
    for (int j = 0; j < t; j++) {
        int len; 
        cin >> len;
        vector<int> arr; 
        for(int ii = 0; ii < len; ii++) {
            int nn;
            cin >> nn;
            arr.push_back(nn);
            sort(arr.begin(), arr.end());
        }
        int prev = -1; 
        bool no = true; 
        for (auto x : arr) {
            if (prev == x ) {
                cout << "YES" << endl;
                no = false;
                break;
            }
            prev = x; 
        }
        if (no) {
            cout << "NO" << endl;
        }
    }
}