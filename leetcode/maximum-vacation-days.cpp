class Solution {
public:
    int maxVacationDays(vector<vector<int>>& flights, vector<vector<int>>& days) {
        int n = flights.size();
        int k = days[0].size();
        vector<int> dPrevious(n,0);
        std::set<int> reached;
        reached.insert(0);
        for(int day = 0; day < k; ++day) {
            std::vector<int> minV(n,0);
                for(auto previousCity: reached) {
                    for (int city = 0; city < n; city++) {
                    if (flights[previousCity][city] == 1 || previousCity == city) {
                        reached.insert(city);
                        if (days[city][day]+ dPrevious[previousCity] > minV[city]) {
                            minV[city] = days[city][day]+ dPrevious[previousCity];
                        }
                    }
                }
                
            }
            dPrevious = minV;
        }
        int max = 0;
        for ( auto v : dPrevious) {
            if ( v > max) {
                max = v;
            }
        }
        return max;
    }
};
