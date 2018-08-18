func freqQuery(queries [][]int32) []int32 {
	m := make(map[int32]int32)
	counter := make(map[int32]int32)
	resp := []int32{}
	for _, v := range queries {
		action := v[0]
		num := v[1]
		if action == 1 {
			m[num]++
			numberOfOccurence := m[num]
			counter[numberOfOccurence]++
			if numberOfOccurence != 1 {
				counter[numberOfOccurence-1]--
			}
		} else if action == 2 {
			if _, ok := m[num]; ok {
				numberOfOccurances := m[num]
				if numberOfOccurances > 0 {
					m[num]--
				}
				counter[numberOfOccurances-1]++
				counter[numberOfOccurances]--
			}
		} else if action == 3 {
			numberOfOccurance := counter[num]
			if numberOfOccurance != 0 {
				numberOfOccurance = 1
			}
			resp = append(resp, numberOfOccurance)
		}
	}
	return resp
}