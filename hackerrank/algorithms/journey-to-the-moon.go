package main

import (
	"fmt"
)

type DisjointSet struct {
	parent map[int32]int32
	depth  map[int32]int
	size   map[int32]int
}

func (d DisjointSet) Union(a, b int32) {
	rootA := d.Find(a)
	rootB := d.Find(b)
	if rootA == rootB {
		return
	}
	var depthA, depthB int
	var ok bool
	if depthA, ok = d.depth[rootA]; !ok {
		d.size[rootA] = 1
		depthA = 1
	}
	if depthB, ok = d.depth[rootB]; !ok {
		d.size[rootB] = 1
		depthB = 1
	}
	if depthA > depthB {
		rootA, rootB = rootB, rootA
		depthA, depthB = depthB, depthA
	}
	d.size[rootB] += d.size[rootA]
	delete(d.size, rootA)
	d.parent[rootA] = rootB
	d.depth[rootB] = depthB + 1
	delete(d.depth, rootA)
}

func (d DisjointSet) Find(a int32) int32 {
	root := a
	for parent, ok := d.parent[a]; ok; parent, ok = d.parent[parent] {
		root = parent
	}
	return root
}

func NewDisjointSet() DisjointSet {
	return DisjointSet{
		size:   make(map[int32]int),
		parent: make(map[int32]int32),
		depth:  make(map[int32]int),
	}
}

func journeyToMoon(n int32, astronaut [][]int32) int64 {
	distSet := NewDisjointSet()
	for _, v := range astronaut {
		e, f := v[0], v[1]
		distSet.Union(e, f)
	}
	size := []int{}
	sum := int32(0)
	for _, v := range distSet.size {
		sum += int32(v)
		size = append(size, v)
	}

	fmt.Println(size)
	retVal := int32(0)
	for i := range size {
		for j := i + 1; j < len(size); j++ {
			retVal += int32(size[i] * size[j])
		}
	}
	return int64(retVal) + int64(sum)*int64(n-sum) + int64(n-sum)*int64(n-sum-1)/2
}

func main() {
	fmt.Println(journeyToMoon(100000, [][]int32{[]int32{1, 2}, []int32{3, 4}}))
}
