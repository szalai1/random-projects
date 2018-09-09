class Solution {
public:
    
    bool validNum(char a, char b) {
        if ( a != '1' && a != '2') {
            return false;
        }
        if ( a == '2' && b > '6') {
            return false;
        }
        return true;
    }
    
    int numDecodings(string s) {
        if (s.length() == 1 && s[0] == '0') {
            return 0;
        } else if (s.length() == 1 && s[0] != '0'){
            return 1;
        }
        if( s[0] == '0') {
            return 0;
        }
        int res = 1;
        int prev = 1;
        for(int i = 1; i < s.length(); ++i) {
            int nextVal = 0;
            if (s[i] != '0') {
                nextVal += res;
            }
            if (validNum(s[i-1], s[i])) {
                nextVal += prev;
            }
            prev = res;
            res = nextVal;
        }
        return res;
    }
};
