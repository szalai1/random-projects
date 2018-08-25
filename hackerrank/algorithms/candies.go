package main

func candies(n int, arr []int) int {
    helper := make([]int, n)
    helper[0] = 1
    for i := 1; i < n; i++ {
        if arr[i] > arr[i-1] {
            helper[i] = helper[i-1]+1
        } else {
            helper[i] = 1
        }
    }
    helper2 := make([]int, n)
    helper2[n-1] = 1
    for i := n-2; i >=0; i--{
        if arr[i] > arr[i+1] {
            helper2[i] = helper2[i+1]+1
        } else {
            helper2[i] = 1
        }
    }
    var counter int
    for i:= 0; i < n; i++ {
        if helper[i] > helper2[i] {
            counter+=helper[i]
        } else {
            counter+=helper2[i]
        }
    }
    return counter
}

func candies(n int32, arr []int32) int64 {
	counter := 0

	for i := 0; i < len(arr); i++ {

	}
}
