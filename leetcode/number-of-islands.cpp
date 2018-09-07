class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int counter  = 0;
        for(int i = 0; i < grid.size(); ++i) {
            for( int j = 0; j < grid[0].size(); ++j) {
                if (grid[i][j] == '1') {
                    counter += 1;
                    this->fill(grid, i, j);
                }
            }
        }
        return counter;
    }
    
    void fill(vector<vector<char>>& grid, int i, int j) {
        if ( i < 0 || j < 0 || i >= grid.size() || j >= grid[0].size()) { return; }
        if  (grid[i][j] == '0') { return; }
        grid[i][j] = '0';
        for (int ii = -1; ii < 2; ++ii ) {
            for (int jj = -1; jj < 2; ++jj ) {
                if (ii == 0 || jj == 0)
                  fill(grid, i+ii, j+jj );
            }
        }
        
    }
};