package main

import "container/list"

type NodePosition struct {
	i, j int
}

func bfs(rooms [][]int, i, j int) {
	q := list.New()
	q.PushBack(NodePosition{i, j})
	for q.Len() > 0 {
		for _, i := range []int{-1, 0, 1} {
			for _, j := range []int{-1, 0, 1} {

			}
		}
	}
}

func wallsAndGates(rooms [][]int) {
	for i := 0; i < len(rooms); i++ {
		for j := 0; j < len(rooms[i]); j++ {
			if rooms[i][j] == 0 {
				bfs(rooms, i, j)
			}
		}
	}
}
