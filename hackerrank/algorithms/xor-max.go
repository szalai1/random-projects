package main

import "fmt"

type Trie struct {
	leaves [2]*Trie
}

func (t *Trie) addIntToTrie(num int32) {
	if t == nil {
		return
	}
	currentTrie := t
	for i := 31; i >= 0; i-- {
		currentBit := num >> uint(i) % 2
		if currentTrie.leaves[currentBit] == nil {
			currentTrie.leaves[currentBit] = &Trie{}
		}
		currentTrie = currentTrie.leaves[currentBit]
	}
}

func (t *Trie) findMaxComplement(num int32) int32 {
	if t == nil || (t.leaves[0] == nil && t.leaves[1] == nil) {
		return 0
	}
	currentTrie := t
	currentMax := int32(0)
	for i := 31; i >= 0; i-- {
		currentBit := num >> uint(i) % 2
		if currentTrie.leaves[currentBit^1] == nil {
			currentBit = currentBit ^ 1
		}
		currentMax = (currentMax << 1) + currentBit ^ 1
		currentTrie = currentTrie.leaves[currentBit^1]
	}
	return currentMax
}

func main() {
	trie := new(Trie)
	for i := 0; i < 100; i++ {
		trie.addIntToTrie(int32(i))
	}
	fmt.Println(trie.findMaxComplement(1))
}
