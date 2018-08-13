package main

import "fmt"

func maxSubsetSum(arr []int32) int32 {
	helper := make([]int32, len(arr))
	helper[0] = arr[0]
	if len(arr) >= 2 {
		if arr[1] > arr[0] {
			helper[1] = arr[1]
		} else {
			helper[1] = arr[0]
		}
	}
	for i := 2; i < len(arr); i++ {
		if helper[i-1] >= arr[i]+helper[i-2] {
			helper[i] = helper[i-1]
			continue
		}
		if arr[i] > arr[i]+helper[i-2] {
			helper[i] = arr[i]
		} else {
			helper[i] = arr[i] + helper[i-2]
		}
	}
	return helper[len(helper)-1]
}

func main() {
	var T int
	fmt.Scanf("%d", &T)
	arr := make([]int32, T)
	for t := 0; t < T; t++ {
		var n int32
		fmt.Scanf("%d", &n)
		arr[t] = n
	}
	fmt.Println(maxSubsetSum(arr))

}
