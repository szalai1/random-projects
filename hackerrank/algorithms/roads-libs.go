package main

func roadsAndLibraries(n int32, c_lib int32, c_road int32, cities [][]int32) int64 {
	findTable := make([]int32, n, n)
	for _, v := range cities {
		e := v[0] - 1
		f := v[1] - 1
		root := e
		if findTable[e] != 0 {
			root = findTable[e]
		}
		findTable[e] = root
		findTable[f] = root
	}
	counter := int64(0)
	for i, k := range findTable {
		if int32(i) == k {
			counter++
		}
	}
	return counter*int64(c_lib) + int64(c_lib)*int64(n) - int64(c_lib)*counter
}
