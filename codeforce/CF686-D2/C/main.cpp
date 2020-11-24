#include <bits/stdc++.h>
using namespace std;


int main() {
    int t; cin>>t; 
    for(int tt=0; tt<t; tt++) {
        int n; cin >> n;
        map<int, int> nums; 
        int prev = -1; 
        for(int i=0; i<n; i++) {
            int num;
            cin >> num; 
            int offs = 0;
            if (i==0 || i == n-1) {
                offs = -1;
            }
            if( num == prev) {
                offs-=1;
            }
            if(nums.find(num) == nums.end()) {
                nums[num] = (2+offs);
            } else {
                nums[num] += (1+offs);
            }
            prev = num;
        }

        if (n==1) {
            cout << 0 << endl; 
        } else {
            int min = n; 
            for(auto a = nums.begin(); a!= nums.end(); a++) {
                if (a->second < min ) {
                    min = a->second;
                }
            }            
            cout << min << endl;
        }
    }
}