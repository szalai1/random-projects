#include <bits/stdc++.h>
using namespace std;

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

pair<int, int> two_prime(int n) {
    if (n == 4) {
        return pair<int, int>(2,2);
    } else if (n == 6) {
        return pair<int, int>(3,3);
    } else if (n == 8) {
        return pair<int, int>(3,5);
    } else {
        for(int i = 5; i <= n/2; i+=6) {
            if (is_prime(i) && is_prime(n-i)) {
                return pair<int, int>(i, n-i); 
            }
            if (is_prime(i+2) && is_prime(n-i-2)) {
                return pair<int, int>(i+2, n-i-2); 
            }
        }
    }
    return pair<int, int> (0,0);
}

int main() {
    int n; 
    while(cin >> n) {
        if (n < 8) {
            cout << "Impossible." << endl; 
            continue;
        }
        if (n%2==0) {
            cout << 2 << " " << 2; 
            auto tp = two_prime(n-4);
            cout << " " << tp.first << " " << tp.second << endl;
        } else {
           cout << 2 << " " << 3; 
            auto tp = two_prime(n-5);
            cout << " " << tp.first << " " << tp.second << endl; 
        }
    }
}