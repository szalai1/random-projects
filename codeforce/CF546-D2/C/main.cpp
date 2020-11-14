#include <bits/stdc++.h>

using namespace std;



int main() {
    int n; 
    cin >> n;

    int n_x, n_y; 
    cin >> n_x;
    vector<int> x(n_x);
    for (int i = 0; i<n_x; i++) {
        int num;
        cin >> num; 
        x[n_x - i - 1] = num;
    }
    cin >> n_y;
    vector<int> y(n_y);
    for (int i = 0; i<n_y; i++) {
        int num;
        cin >> num; 
        y[n_y - i - 1] = num;
    }
    map<vector<int>, bool> states;
    vector<int> state = x;  
;
    while((x.size() != n && x.size() != 0 )) {
        // cout << dhd"================\n\t>>";
        state = x;
        state.push_back(-1);
        state.insert(state.end(), y.begin(), y.end());
        if (states.find(state) != states.end()) {
            cout << -1;
            return 0;
        }
        states[state] = true;
        // for (auto xx : x) cout << xx << " ";
        // cout << endl << "\t>>";
        // for (auto xx : y) cout << xx <<  " ";
        //cout << endl;

        // take two 
        int a = x.back();
        x.pop_back();
        int b = y.back();
        y.pop_back();
        // fight
        // cout << ">> " << a << " " << b << endl;
        // cout << ">> ";
        if ( a > b ) {
            x.insert(x.begin(), b);
            x.insert(x.begin(), a);
        } else { 
            y.insert(y.begin(), a);
            y.insert(y.begin(), b);
        }
    }

    cout << states.size() << " ";
    if (x.size() == n) {
        cout << 1;
    } else {
        cout << 2; 
    }
}