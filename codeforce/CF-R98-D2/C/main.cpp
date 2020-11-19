#include <bits/stdc++.h>
using namespace std;


int main() {
    int t;
    cin >> t ;
    char character;
    cin.get(character);
    for(; t>0; t--) {
        string line; 
        getline(cin, line);
        int r = 0;
        int s = 0;
        int counter = 0;
        for(int i=0; i< line.size(); i++) {
            char c = line[i];
            if(c=='(') {
                r++;
            } else if (c==')') {
                if (r != 0) {
                    counter++;
                    r--;
                }
            }else if (c=='[') {
               s++; 
            }else if (c==']') {
                if(s!=0) {
                    s--;
                    counter++;
                }
            }
        }
        cout << counter << endl;
    }
}