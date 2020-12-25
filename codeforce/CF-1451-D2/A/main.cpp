#include <bits/stdc++.h>
using namespace std;

 void push_divs(vector<int> &divs, int n) {
    for(int i=2; i<n; i++) {
        if (n%i==0) {
            divs.push_back(i);
        }
    }
}

int main() {
    int t; 
    cin >> t; 
    vector<int> arr; 
    arr.push_back(0);
    arr.push_back(0);
    arr.push_back(1);
    for(int i=0; i<t; i++) {
        cout << INT_MAX << endl;
        int n;
        cin >> n;
        for(int ii=arr.size(); ii <=n; ii++) {
            cout << ii << endl;
            vector<int> steps; 
            // sub
            steps.push_back(arr[ii-1]+1); 
            // div 
            vector<int> divs;
            push_divs(divs, ii); 
            for(auto d:divs) {
                steps.push_back(arr[n/d]+1); 
            }
            arr.push_back(*min_element(steps.begin(), steps.end()));
        }
        cout << arr[n] << endl;
    }
    return 0;
}