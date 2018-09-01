#include <iostream>

using namespace std;

bool isLower(char a) { return a >= 'a' && a <='z'; }
bool isUpper(char a) { return ! isLower(a); }

bool abbreviationRecrusiveCheck(string a, string b, bool hasLower) {
    if ( a.length() > b.length() ) { return false; }
    if (a.length() == 0) {
        if (b.length() == 0) {
            return true;
        } else if (hasLower || isUpper(b[0])) {
            return false;
        } else {
             return abbreviationRecrusiveCheck(
            a, 
            b.substr(1,b.length()-1), 
            hasLower);
        }
    }
    char A = a[0];
    char B = b[0];
    if ( isLower(A)) {
        if ( A == B ) {
            return true;
        }
        return abbreviationRecrusiveCheck(
            a.substr(1,a.length()-1), 
            b.substr(1,b.length()-1), 
            hasLower);
    } else {
        if ( A == B || A - 'A' == B - 'a' && hasLower) {
            return abbreviationRecrusiveCheck(
            a.substr(1,a.length()-1), 
            b.substr(1,b.length()-1), 
            hasLower);
        } else if ( A - 'A' == B - 'a' && !hasLower) {
            return abbreviationRecrusiveCheck(
                a.substr(1,a.length()-1), 
                b.substr(1,b.length()-1), 
                hasLower) || 
                abbreviationRecrusiveCheck(
                a, 
                b.substr(1,b.length()-1), 
                hasLower);
        } else if ( !hasLower) {
            return abbreviationRecrusiveCheck(
                a, 
                b.substr(1,b.length()-1), 
                hasLower);
        } else {
            return false;
        }
    }
}

// Complete the abbreviation function below.
string abbreviation(string b, string a) {
    bool hasLower = false;
    for ( auto i : a) {
        if ( isLower(i)) {
            hasLower = true;
            break;
        } 
    }
    if ( abbreviationRecrusiveCheck(a, b, hasLower)) {
        return "YES";
    } else {
        return "NO";
    }
}


int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int q;
    cin >> q;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    for (int q_itr = 0; q_itr < q; q_itr++) {
        string a;
        getline(cin, a);

        string b;
        getline(cin, b);

        string result = abbreviation(a, b);

        fout << result << "\n";
    }

    fout.close();

    return 0;
}

