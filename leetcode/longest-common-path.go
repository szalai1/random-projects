package main

import "fmt"

func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}
	common := make([]byte, 0)
	for i := 0; ; i++ {
		var c byte
		if len(strs[0]) == i {
			return string(common)
		}
		c = strs[0][i]
		for _, s := range strs {
			if len(s) == i || s[i] != c {
				return string(common)
			}
		}
		common = append(common, c)
	}
	return string(common)
}

func main() {
	fmt.Println(longestCommonPrefix([]string{""}))
}
