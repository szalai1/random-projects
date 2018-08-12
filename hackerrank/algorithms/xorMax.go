package main

func maxXor(arr []int32, queries []int32) []int32 {
	retArr := make([]int32, len(queries))
	for i, q := range queries {
		xorMax := q & arr[0]
		for _, n := range arr {
			xor := q ^ n
			if xor > xorMax {
				xorMax = xor
			}
		}
		retArr[i] = xorMax
	}
	return retArr
}
