package main

import (
	"bufio"
	"container/heap"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)

type Node struct {
	value int32
	index int
}
type MinHeap []Node

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i].value < h[j].value }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(Node))
}

func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

type DisjointSet map[int32]int32

func (d DisjointSet) Union(a, b int32) {
	if d.Find(a) != d.Find(b) {
		d[d.Find(a)] = d.Find(b)
	}
}

func (d DisjointSet) Find(a int32) int32 {
	root := a
	for parent, ok := d[root]; ok; parent, ok = d[root] {
		root = parent
	}
	return root
}

func MSTWeight(n int64, from []int32, to []int32, weight []int32) int32 {
	minHeap := &MinHeap{}
	heap.Init(minHeap)
	for i, v := range weight {
		heap.Push(minHeap, Node{value: v, index: i})
	}
	counter := int64(0)
	distjointSet := DisjointSet{}
	sum := int32(0)
	for counter != n-1 && minHeap.Len() > 0 {
		minEdge := heap.Pop(minHeap).(Node)
		i := minEdge.index
		e, f := from[i], to[i]
		if distjointSet.Find(e) != distjointSet.Find(f) {
			distjointSet.Union(e, f)
			counter++
			sum += minEdge.value
		}
	}
	return sum
}

func main() {
	reader := bufio.NewReaderSize(os.Stdin, 1024*1024)

	gNodesEdges := strings.Split(readLine(reader), " ")
	gNodes, err := strconv.ParseInt(gNodesEdges[0], 10, 64)
	checkError(err)

	gEdges, err := strconv.ParseInt(gNodesEdges[1], 10, 64)
	checkError(err)

	var gFrom, gTo, gWeight []int32
	for i := 0; i < int(gEdges); i++ {
		edgeFromToWeight := strings.Split(readLine(reader), " ")
		edgeFrom, err := strconv.ParseInt(edgeFromToWeight[0], 10, 64)
		checkError(err)

		edgeTo, err := strconv.ParseInt(edgeFromToWeight[1], 10, 64)
		checkError(err)

		edgeWeight, err := strconv.ParseInt(edgeFromToWeight[2], 10, 64)
		checkError(err)

		gFrom = append(gFrom, int32(edgeFrom))
		gTo = append(gTo, int32(edgeTo))
		gWeight = append(gWeight, int32(edgeWeight))
	}

	fmt.Println(MSTWeight(gNodes, gFrom, gTo, gWeight))
	// Write your code here.
}

func readLine(reader *bufio.Reader) string {
	str, _, err := reader.ReadLine()
	if err == io.EOF {
		return ""
	}

	return strings.TrimRight(string(str), "\r\n")
}

func checkError(err error) {
	if err != nil {
		panic(err)
	}
}
