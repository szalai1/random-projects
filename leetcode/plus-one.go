func plusOne(digits []int) []int {
	result := make([]int, len(digits)+1)
	remaining := 1
	for i := len(result); i > 1; i-- {
		num := digits[i-2] + remaining
		result[i-1] = num % 10
		remaining = num / 10
	}
	if remaining == 1 {
		result[0] = 1
	} else {
		result = result[1:]
	}
	return result
}