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

int main() {
    int a, b; 
    cin >> a >> b; 
    if (a==b) {
        cout << 0; 
        return 0;
    }
    int abgcd = gcd(a,b);
    a/=abgcd;
    b/=abgcd;
    int counter = 0;
    while(a%2==0) {
        a/=2;
        counter++;
    }
    while(b%2==0) {
        b/=2;
        counter++;
    }
    while(a%5==0) {
        a/=5;
        counter++;
    }
    while(b%5==0) {
        b/=5;
        counter++;
    }
    while(a%3==0) {
        a/=3;
        counter++;
    }
    while(b%3==0) {
        b/=3;
        counter++;
    }
    if (a == b) {
        cout << counter ;
    } else {
        cout << -1; 
    }
}