#include <bits/stdc++.h>
using namespace std;


int main() {
    int t; 
    cin >> t; 
    for (int i = 0; i<t; i++) {
        int n, m; 
        cin >> n >> m; 
        vector<int> a, b; 
        for (int ii =0; ii< n; ii++) {
            int _n; 
            cin >> _n; 
            a.push_back(_n);
        }
        for (int ii =0; ii< m; ii++) {
            int _n; 
            cin >> _n; 
            b.push_back(_n);
        }
        sort(a.begin(), a.end());
        sort(b.begin(), b.end()); 
        vector<int> intersect(min(n,m)); 
        auto it = set_intersection(a.begin(), a.end(), b.begin(), b.end(), intersect.begin());
        cout << it - intersect.begin() << endl; 
    }
}