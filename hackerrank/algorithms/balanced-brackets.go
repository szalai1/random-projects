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
	stack := []rune{}
	for _, b := range s {
		if isOpening(b) {
			stack = append(stack, b)
			continue
		}
		if match(stack[len(stack)-1], b) {
			stack = stack[0 : len(stack)-1]
			continue
		}
		return false
	}
	return true
}

func main() {
	fmt.Println(isBalanced("{[()]}"))
	fmt.Println(isBalanced("{[(])}"))
	fmt.Println(isBalanced("{{[[(())]]}}"))
}
