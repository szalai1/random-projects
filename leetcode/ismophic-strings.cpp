class Solution {
public:
    bool isIsomorphic(string s, string t) {
        if (s.length() != t.length()) {
            return false;
        }
        std::map<char, char> mapping;
        std::set<char> seen;
        for( int i = 0; i < t.length(); ++i) {
            std::map<char, char>::iterator mappedChar = mapping.find(s[i]);
            std::set<char>::iterator seenChar = seen.find(t[i]);
            if ( mappedChar == mapping.end() && seenChar == seen.end() ) {
                mapping[s[i]] = t[i];
                seen.insert(t[i]);
            } else if (mappedChar->second != t[i]) {
                return false;
            }
        }
        return true;
    }
};
