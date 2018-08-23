func twoSum(nums []int, target int) []int {
	values := make(map[int]int)
	for i, v := range nums {
		if otherIndex, ok := values[v]; ok {
			return []int{otherIndex, i}
		}
		values[target-v] = i
	}
	return nil
}