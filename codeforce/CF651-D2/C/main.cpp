#include <bits/stdc++.h>
using namespace std;


bool sortbysec(const pair<int, int> &a, const pair<int,int> &b) {
    return (a.second < b.second);
}

int main() {
    int n; 
    cin >> n; 
    vector<pair<long long , long long >> coordinates;
    for (int i =0; i<n; i++) {
        long long x,y;
        cin >> x >> y;
        coordinates.push_back(pair<long long , long long >(x,y));
    }
    map<long long , int> f, s;
    map<pair<long long , long long >, int> b; 
    for (auto p : coordinates) {
        if (f.find(p.first) == f.end()) {
            f[p.first] = 1;
        } else {
            f[p.first] += 1;
        }
        if (s.find(p.second) == s.end()) {
            s[p.second] = 1;
        } else {
            s[p.second] += 1;
        }
        if (b.find(p) == b.end()) {
            b[p] = 1;
        } else {
            b[p] +=1;
        }
    }
    long long pairs = 0;
    long long dpairs = 0;
    for(auto kv : f) {
        if(kv.second != 1) {
            if(kv.second%2==0) {
                long long a = kv.second/2;
                pairs += a * (kv.second-1);
            } else {
                long long a = ((kv.second-1)/2);
                pairs += (kv.second * a ) ;
            }
        }
    }
    for(auto kv : s) {
            if(kv.second%2==0) {
                long long a = kv.second/2;
                pairs += a * (kv.second-1);
            } else {
                long long a = ((kv.second-1)/2);
                pairs += (kv.second * a ) ;
            }
    }
    for(auto kv : b) {
            if(kv.second%2==0) {
                long long a = kv.second/2;
                dpairs += a * (kv.second-1);
            } else {
                long long a = ((kv.second-1)/2);
                dpairs += (kv.second * a ) ;
            }
    }
    cout << (pairs-dpairs);
}