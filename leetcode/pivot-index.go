func pivotIndex(nums []int) int {
	if len(nums) == 0 {
		return -1
	}
	sum := 0
	for _, v := range nums {
		sum += v
	}
	rollingSum := 0
	for i := 0; i < len(nums); i++ {
		if rollingSum == sum-rollingSum-nums[i] {
			return i
		}
		rollingSum += nums[i]
	}
	return -1
}