package main

import (
	"fmt"
)

func numOfPeriods(N int) int {
    return N*(N+1)/2
}

func Solution(A []int) int {
    if len(A) < 3 {
        return 0
    }
    res := 0
    a := A[1]-A[0]
    b := A[2]-A[1]
    constLen := 0
    if a == b {
        constLen = 1
    }
    for i:=3; i < len(A); i++ {
        b = a
		a = A[i]-A[i-1]
		fmt.Println(a)
        if a != b {
			fmt.Println("\t", constLen, res)
            res += numOfPeriods(constLen)
            constLen = 0
            continue
        } else {
            constLen+=1
        }
	}
	res += numOfPeriods(constLen)
    
    if res > 1000000000 {
        return -1
    }
    return res
}

func main() {
		fmt.Println(Solution([]int{-1, 1, 3, 3, 3, 2, 3, 2, 1, 0}))
}