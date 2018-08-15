package main

import "sort"

type Action struct {
	Position int32
	Value    int32
	Start    bool
}

func arrayManipulation(n int32, queries [][]int32) int64 {
	actions := make([]Action, 2*len(queries))
	for i, q := range queries {
		actions[i] = Action{Position: q[0], Value: q[2], Start: true}
		actions[len(queries)+i] = Action{Position: q[1], Value: q[2], Start: false}
	}
	sort.Slice(actions, func(i, j int) bool {
		return actions[i].Position < actions[j].Position
	})
	max := int64(0)
	currentVal := int64(0)
	for _, q := range actions {
		if q.Start {
			currentVal += int64(q.Value)
		} else {
			currentVal -= int64(q.Value)
		}
		if currentVal > max {
			max = currentVal
		}
	}
	return max
}
