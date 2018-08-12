package main

import (
	"container/heap"
	"fmt"
)

// this is the example code from https://golang.org/pkg/container/heap/
type MinIntHeap []int

func (h MinIntHeap) Len() int           { return len(h) }
func (h MinIntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinIntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MinIntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *MinIntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *MinIntHeap) ReadMinInt() int {
	return (*h)[0]
}

type MaxIntHeap []int

func (h MaxIntHeap) Len() int           { return len(h) }
func (h MaxIntHeap) Less(i, j int) bool { return h[i] > h[j] }
func (h MaxIntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MaxIntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *MaxIntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *MaxIntHeap) ReadMaxInt() int {
	return (*h)[0]
}

type IntMedian struct {
	minHeap *MinIntHeap
	maxHeap *MaxIntHeap
}

func NewIntMedian() *IntMedian {
	m := &IntMedian{
		minHeap: &MinIntHeap{},
		maxHeap: &MaxIntHeap{},
	}
	heap.Init(m.minHeap)
	heap.Init(m.maxHeap)
	return m
}

func (m *IntMedian) Push(n int) {
	maxHeapLen := m.maxHeap.Len()
	minHeapLen := m.minHeap.Len()
	//median := m.Median()
	if maxHeapLen == minHeapLen {
		if maxHeapLen == 0 {
			heap.Push(m.maxHeap, n)
		} else if m.minHeap.ReadMinInt() < n {
			heap.Push(m.maxHeap, heap.Pop(m.minHeap))
			heap.Push(m.minHeap, n)
		} else {
			heap.Push(m.maxHeap, n)
		}
	} else {
		var median int
		if maxHeapLen > minHeapLen {
			median = heap.Pop(m.maxHeap).(int)
		} else {
			median = heap.Pop(m.minHeap).(int)
		}
		if n < median {
			n, median = median, n
		}
		heap.Push(m.maxHeap, median)
		heap.Push(m.minHeap, n)
	}
}

func (m *IntMedian) Median() float64 {
	maxHeapLen := m.maxHeap.Len()
	minHeapLen := m.minHeap.Len()
	if minHeapLen == maxHeapLen {
		if minHeapLen == 0 {
			return 0.0
		}
		minOfLarges := m.minHeap.ReadMinInt()
		maxOfSmalls := m.maxHeap.ReadMaxInt()
		median := float64(minOfLarges+maxOfSmalls) / 2.0
		return median
	} else {
		minOfLarges := m.maxHeap.ReadMaxInt()
		return float64(minOfLarges)
	}
}

func main() {
	m := NewIntMedian()
	var T int
	fmt.Scanf("%d", &T)
	for t := 0; t < T; t++ {
		var n int
		fmt.Scanf("%d", &n)
		m.Push(n)
		fmt.Printf("%.1f\n", m.Median())
		//	fmt.Println(n, m.maxHeap, m.minHeap)
		//		fmt.Println("\t====\n")
	}

}
