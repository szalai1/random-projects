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
	e, f  int32
}
type MaxHeap []Node

func (h MaxHeap) Len() int           { return len(h) }
func (h MaxHeap) Less(i, j int) bool { return h[i].value > h[j].value }
func (h MaxHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MaxHeap) Push(x interface{}) {
	*h = append(*h, x.(Node))
}

func (h *MaxHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

type DisjointSet struct {
	disset  map[int32]int32
	machine map[int32]int32
}

func (d DisjointSet) Union(a, b int32) {
	if d.Find(a) != d.Find(b) {
		if d.Machine(a) != -1 {
			d.machine[d.Find(b)] = d.Machine(a)
		}
		d.disset[d.Find(a)] = d.Find(b)
	}
}

func (d DisjointSet) Machine(a int32) int32 {
	if m, ok := d.machine[d.Find(a)]; ok {
		return m
	}
	return -1
}

func (d DisjointSet) Find(a int32) int32 {
	root := a
	for parent, ok := d.disset[root]; ok; parent, ok = d.disset[root] {
		root = parent
	}
	return root
}

func minTime(roads [][]int32, machines []int32) int32 {
	maxHeap := &MaxHeap{}
	for i := range roads {
		heap.Push(maxHeap, Node{
			e:     roads[i][0],
			f:     roads[i][1],
			value: roads[i][2],
		})
	}
	disSet := DisjointSet{disset: make(map[int32]int32), machine: make(map[int32]int32)}
	for i := range machines {
		disSet.machine[machines[i]] = machines[i]
	}
	sum := int32(0)
	counter := 0
	for maxHeap.Len() > 0 && counter != len(machines)-1 {
		node := heap.Pop(maxHeap).(Node)
		e, f := node.e, node.f
		if disSet.Find(e) != disSet.Find(f) {
			macE := disSet.Machine(e)
			macF := disSet.Machine(f)
			if macE != -1 && macF != -1 {
				sum += node.value
				counter++
				continue
			}
			disSet.Union(e, f)

		}
	}
	return sum
}

func main() {
	reader := bufio.NewReaderSize(os.Stdin, 1024*1024)

	stdout := os.Stdout
	defer stdout.Close()

	writer := bufio.NewWriterSize(stdout, 1024*1024)

	nk := strings.Split(readLine(reader), " ")

	nTemp, err := strconv.ParseInt(nk[0], 10, 64)
	checkError(err)
	n := int32(nTemp)

	kTemp, err := strconv.ParseInt(nk[1], 10, 64)
	checkError(err)
	k := int32(kTemp)

	var roads [][]int32
	for i := 0; i < int(n)-1; i++ {
		roadsRowTemp := strings.Split(readLine(reader), " ")

		var roadsRow []int32
		for _, roadsRowItem := range roadsRowTemp {
			roadsItemTemp, err := strconv.ParseInt(roadsRowItem, 10, 64)
			checkError(err)
			roadsItem := int32(roadsItemTemp)
			roadsRow = append(roadsRow, roadsItem)
		}

		if len(roadsRow) != 3 {
			panic("Bad input")
		}

		roads = append(roads, roadsRow)
	}

	var machines []int32

	for i := 0; i < int(k); i++ {
		machinesItemTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
		checkError(err)
		machinesItem := int32(machinesItemTemp)
		machines = append(machines, machinesItem)
	}

	result := minTime(roads, machines)

	fmt.Fprintf(writer, "%d\n", result)

	writer.Flush()
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
