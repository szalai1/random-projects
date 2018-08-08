package main

import (
	"fmt"
)

// Complete the stepPerms function below.
func stepPerms(n int32) int32 {
	if n == 1 || n == 0 {
		return 1
	} else if n == 2 {
		return 2
	} else if n == 3 {
		return 4
	} else {
		currentStair := n / 2
		halfWay := stepPerms(currentStair)
		otherHalfWay := stepPerms(n - currentStair)
		beforeHalfWay := stepPerms(currentStair - 1)
		beforeBeforeHalfWay := stepPerms(currentStair - 2)
		afterOtherHalfWay := stepPerms(n - currentStair - 1)
		afterAfterOtherHalfWay := stepPerms(n - currentStair - 2)
		return halfWay*otherHalfWay +
			beforeHalfWay*afterOtherHalfWay +
			beforeBeforeHalfWay*afterOtherHalfWay +
			beforeHalfWay*afterAfterOtherHalfWay
	}
}

func main() {
	var T int
	fmt.Scanf("%d", &T)
	for t := 0; t < T; t++ {
		var n int32
		fmt.Scanf("%d", &n)
		fmt.Println(stepPerms(n))
	}
}
