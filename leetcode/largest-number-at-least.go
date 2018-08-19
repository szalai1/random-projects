func dominantIndex(nums []int) int {
	var largest, second int
	var largestI int
	for i, v := range nums {
		if v > largest {
			second = largest
			largest = v
			largestI = i
		} else if v > second {
			second = v
		}
	}
	if second<<1 > largest {
		return -1
	}
	return largestI
}