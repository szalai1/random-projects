#include <bits/stdc++.h>
using namespace std;

typedef long long LL;

bool is_prime(int n) {
    if (n == 2 || n == 3) {
        return true;
    }
    if (n % 2 == 0 || n%3 == 0) {
        return false;
    }
    for (int i = 5; i <= sqrt(n)+1; i+=6) {
        if (n%i == 0 || n%(i+2) == 0) {
            return false;
        }
    }
    return true;
}

vector<LL> primes_less_than(LL n) {
    vector<LL> primes{2,3}; 
    for (LL i = 5; i <=n; i+=6) {
        cout << ".";
        bool a_p = true;
        bool b_p = true; 
        LL a = i;
        LL b = i+2;
        for (auto p : primes) {
            if (a%p == 0) {
                a_p = false;
            }
            if(b%p == 0) {
                b_p = false;
            }
        }
        if(a_p) primes.push_back(a);
        if(b_p) primes.push_back(b);
    }
    return primes;
} 

map<LL, int> prim_fact(LL n) {
    cout << "PF " << endl; 
    map<LL, int> primes;
    vector<LL> primes_n = primes_less_than(n);
    for(auto p: primes_n) {
        while(n%p== 0) {
            n/=p;
            if(primes.find(p) == primes.end()) {
                primes[p] = 1;
            } else {
                primes[p] +=1;
            }
        }
    }
    return primes;
}

bool sortbysec(const pair<int, int> &a, const pair<int,int> &b) {
    return (a.second > b.second);
}

int main() {
    int t; cin>>t; 
    for(int tt=0; tt<t; tt++) {
        int n; cin >> n;
        map<LL, int> prims = prim_fact(n); 
        vector<pair<LL, int>> prim_v; 
        for (auto p: prims) {
            prim_v.push_back(p);
        }
        sort(prim_v.begin(), prim_v.end(), sortbysec);
        vector<LL> nums(prim_v[0].second, 1);
        int offs=0;
        for(auto p: prim_v) {
            for (int i =offs; i< p.second; i++) {
                nums[i]*=p.first;
            }
        }
        cout << nums.size() << endl;
        for (int i=nums.size()-1; i>=0; i--) {
            cout << nums[i];
            if(i!=0) {
                cout << " "; 
            }
        }
        cout << endl;
    }
}