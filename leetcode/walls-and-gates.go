package main

import (
	"container/list"
	"fmt"
)

type NodePosition struct {
	i, j int
}

func bfs(rooms [][]int, i, j int) {
	q := list.New()
	q.PushBack(NodePosition{i, j})
	N := len(rooms)
	M := len(rooms[0])
	directions := [][]int{
		[]int{0, 1},
		[]int{0, -1},
		[]int{1, 0},
		[]int{-1, 0},
	}
	for q.Len() > 0 {
		next := q.Front().Value.(NodePosition)
		q.Remove(q.Front())
		for i := range directions {
			x := directions[i][0] + next.i
			y := directions[i][1] + next.j
			if x < 0 || x >= N || y < 0 || y >= M || rooms[x][y] == -1 || rooms[x][y] <= rooms[next.i][next.j] {
				continue
			}
			q.PushBack(NodePosition{x, y})
			rooms[x][y] = rooms[next.i][next.j] + 1
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

func main() {
	inf := 1 << 31
	rooms := [][]int{
		[]int{inf, -1, 0, inf},
		[]int{inf, inf, inf, -1},
		[]int{inf, -1, inf, -1},
		[]int{0, -1, inf, inf},
	}
	wallsAndGates(rooms)
	for i := range rooms {
		fmt.Println(rooms[i])
	}
}
