#include <bits/stdc++.h>
using namespace std;


int main() {
    int t; 
    cin >> t; 
    for (int i = 0; i<t; i++) {
        int n, m;
        cin >> n >> m;
        vector<vector<int>> matrix; 
        for (int nn = 0; nn < n; nn++) {
            matrix.push_back(vector<int>());
            for (int mm = 0; mm < m; mm++) {
                int num; 
                cin >> num;
                matrix[nn].push_back(num);
            }
        }
        for (int nn = 0; nn < n; nn++) {
            for (int mm = 0; mm < m; mm++) {
                bool ok = true;
                if (nn != 0 && matrix[nn-1][mm] != matrix[nn-1][mm] ) {

                }
            }
        }
    }
}