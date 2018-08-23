package main

import "fmt"

func addBinary(a string, b string) string {
	lenA := len(a)
	lenB := len(b)
	if lenA < lenB {
		lenA, lenB = lenB, lenA
		a, b = b, a
	}
	result := make([]byte, lenA)
	diff := lenA - lenB
	var remaining byte
	for i := lenA - 1; i >= 0; i-- {
		digitA := a[i] - '0'
		var digitB byte
		if i-diff >= 0 {
			digitB = b[i-diff] - '0'
		}
		digit := digitA + digitB + remaining
		remaining = digit / 2
		result[i] = '0' + digit%2
	}
	if remaining == 0 {
		return string(result)
	}
	return "1" + string(result)
}

func main() {
	fmt.Println("vim-go")
}
