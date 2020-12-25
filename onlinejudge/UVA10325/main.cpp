#include <bits/stdc++.h>
using namespace std;
typedef long long LL; 
int gcd(int a, int b) {
    if (a==0) {
        return b;
    }
    if (b==0) {
        return a;
    }
    return gcd(b, a%b);
}

int main() {
    int n, m;
    while(cin >> n >> m) {
        vector<int> nums;
        for(int i =0; i<m; i++) {
            int x;
            cin>>x;
            if (x > n/2) continue;
            nums.push_back(x);
        }
        sort(nums.begin(), nums.end());
        vector<int> rel_p;
        rel_p.push_back(nums[0]);
        for(int i=1;i<nums.size(); i++) {
            bool ok = true;
            for(int j=i-1; j>=0;j--) {
                 if(nums[i]%nums[j] == 0) {
                    ok = false;
                 }
            }
            if (ok) {
                rel_p.push_back(nums[i]);
            }
        }
        int num = 0;
        for(auto x: rel_p) {
            num += (n/x);
        }
        for(int i=0; i<rel_p.size(); i++) {
            for(int j =i+1; j<rel_p.size(); j++) {
                int x = (rel_p[i]*rel_p[j]/gcd(rel_p[i],rel_p[j])); 
                cout << "\t> " << x << endl;
                num -= (n/x);
            }
        }
        cout << n-num << endl;
    }
}