package main

import (
	"fmt"
)

func isOpening(b rune) bool {
	return b == '(' || b == '{' || b == '['
}

func match(b1 rune, b2 rune) bool {
	return (b1 == '(' && b2 == ')') ||
		(b1 == '[' && b2 == ']') ||
		(b1 == '{' && b2 == '}')
}

func isBalanced(s string) bool {
	//println(s)
	stack := []rune{}
	for _, b := range s {
		//println(b, stack)
		if isOpening(b) {
			stack = append(stack, b)
			continue
		}
		if len(stack) != 0 && match(stack[len(stack)-1], b) {
			stack = stack[0 : len(stack)-1]
			continue
		}
		return false
	}
	if len(stack) != 0 {
		return false
	}
	return true
}

func main() {
	var T int
	fmt.Scanf("%d", &T)
	for i := 0; i < T; i++ {
		var line string
		fmt.Scanf("%s", &line)
		if isBalanced(line) {
			fmt.Println("YES")
		} else {
			fmt.Println("NO")
		}
	}
}
