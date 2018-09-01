class Solution {
public:
    vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
        std::unordered_map<std::string, int> m; 
        std::vector<std::string> result; 
        int minSum = list1.size() + list2.size(); 
        for( int i = 0; i < list1.size(); ++i) {
            m.insert(std::pair<std::string, int>(list1[i],i));
        }
        for ( int i = 0; i < list2.size(); ++i) {
            std::string w = list2[i];
            auto it = m.find(w);
            if (it != m.end()) {
                int score  = it->second + i;
                if (  score < minSum) {
                    minSum = it->second + i;
                    result = std::vector<std::string>({w});
                } else if ( score == minSum ) {
                    result.push_back(w);
                } 
            }
        }
        return result;
    }
};
