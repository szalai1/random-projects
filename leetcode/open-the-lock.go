package main

import "fmt"

func turnDownwards(a byte) byte {
	if a == '0' {
		return '9'
	}
	return a - 1
}

func turnUpwards(a byte) byte {
	if a == '9' {
		return '0'
	}
	return a + 1
}

type Node struct {
	dist int
	word [4]byte
}

func openLock(deadends []string, target string) int {
	visited := make(map[string]bool)
	for i := range deadends {
		visited[deadends[i]] = true
	}
	queue := []Node{Node{dist: 0, word: [4]byte{'0', '0', '0', '0'}}}
	for len(queue) != 0 {
		next := queue[0]
		queue = queue[1:]
		if string(next.word[:4]) == target {
			return next.dist
			break
		}
		if _, ok := visited[string(next.word[:4])]; ok {
			continue
		}
		visited[string(next.word[:4])] = true
		for i := range next.word {
			var copyW [4]byte
			copyW = next.word
			copyW[i] = turnDownwards(copyW[i])
			queue = append(queue, Node{word: copyW, dist: next.dist + 1})
			copyW = next.word
			copyW[i] = turnUpwards(copyW[i])
			queue = append(queue, Node{word: copyW, dist: next.dist + 1})
		}
	}
	return -1
}

func main() {
	deadends := []string{"0201", "0101", "0102", "1212", "2002"}
	target := "0202"
	fmt.Println(openLock(deadends, target))
}
