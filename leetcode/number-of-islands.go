package main

import "fmt"

func numIslands(grid [][]byte) int {
	if len(grid) == 0 {
		return 0
	}
	n := len(grid)
	m := len(grid[0])
	counter := 0
	for i := 0; i < n; i++ {
		for j := 0; j < m; j++ {
			if grid[i][j] == '1' {
				counter++
				sinkIt(grid, i, j)
			}
		}
	}
	return counter
}

func sinkIt(grid [][]byte, i, j int) {
	if i < 0 || i >= len(grid) || j < 0 || j >= len(grid[0]) || grid[i][j] == '0' {
		return
	}
	grid[i][j] = '0'
	sinkIt(grid, i-1, j)
	sinkIt(grid, i+1, j)
	sinkIt(grid, i, j-1)
	sinkIt(grid, i, j+1)
}

func main() {
	inp := [][]byte{[]byte{'1', '1', '0', '0', '0'},
		[]byte{'1', '1', '0', '0', '0'},
		[]byte{'0', '0', '1', '0', '0'},
		[]byte{'0', '0', '0', '1', '1'}}
	fmt.Println(numIslands(inp))
}
