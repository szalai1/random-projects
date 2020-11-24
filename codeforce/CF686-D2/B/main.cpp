#include <bits/stdc++.h>
using namespace std;


int main() {
    int t; cin>>t; 
    for(int tt=0; tt<t; tt++) {
        int n; cin >> n;
        map<int, pair<int,int> > nums; 
        int min = -1;
        int min_i = -1;
        for(int i=0; i<n; i++) {
            int num;
            cin >> num; 
            if(nums.find(num) == nums.end()) {
                nums[num] = pair<int, int>(i, 1);
            } else {
                nums[num].second++;
            }
        }
        bool no_min = true;
        for(auto a = nums.begin(); a!= nums.end(); a++) {
            if (a->second.second == 1) {
                cout << a->second.first +1;
                no_min = false;
                break;
            }
        }
        if(no_min) {
            cout << -1;
        }
        cout << endl;
    }

}