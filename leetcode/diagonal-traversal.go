package main

import "fmt"

func findDiagonalOrder(matrix [][]int) []int {
	n := len(matrix)
	m := len(matrix[0])
	flattened := make([]int, n*m)
	counter := 0
	for i := 0; i < n+m-1; i++ {
		fmt.Println("======", i, "======")
		from := 0
		to := m
		if i > n {
			from = i - n + 1
		}
		if i < m {
			to = i - 1
		}
		fmt.Println(from, to)
		if i%2 == 0 {
			for j := from; j <= to; j++ {
				fmt.Println("\te ", i, j)
				flattened[counter] = matrix[j][i-j]
				counter++
			}
		} else {
			for j := from; j <= to; j++ {
				fmt.Println("\to ", i, j)
				flattened[counter] = matrix[j][i-j]
				counter++
			}
		}
	}
	return flattened
}

func main() {
	input := [][]int{[]int{1, 2, 3}, []int{4, 5, 6}, []int{7, 8, 9}}
	fmt.Println(findDiagonalOrder(input))
}
