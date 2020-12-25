#include <bits/stdc++.h>
using namespace std;

int gcd(int a, int b) {
    if (a==0) {
        return b;
    }
    if (b==0) {
        return a;
    }
    return gcd(b, a%b);
}

bool is_prime(int n) {
    if (n % 2 == 0 || n%3 == 0) {
        return false;
    }
    for (int i = 1; i*6 < sqrt(n); i+=6) {
        if (n%i == 0 || n%(i+2) == 0) {
            return false;
        }
    }
    return true;
}

int main() {
  vector<int> prime{2,3,5,7}; 
  vector<int> twinprime{3,5};  
  int n;
  while(cin >> n) {
    cout << n ;
    if(n >= twinprime.size()) {
        while(twinprime.size() != n) {
            for(int i = twinprime[twinprime.size()-1] - twinprime[twinprime.size()-1]%6;; i+=6) {

                if (is_prime(i) && is_prime(i+2)) {
                    twinprime.push_back(i);
                    break; 
                }
            }
        }
    }
    cout << "(" << twinprime[n] << ", " << twinprime[n]+2 << ")" << endl; 

  }

}