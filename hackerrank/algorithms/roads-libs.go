package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)

type DisjointSet struct {
	parent map[int32]int32
	size   map[int32]int
}

func (d DisjointSet) Union(a, b int32) {
	rootA := d.Find(a)
	rootB := d.Find(b)
	if rootA == rootB {
		return
	}
	var sizeA, sizeB int
	var ok bool
	if sizeA, ok = d.size[rootA]; !ok {
		sizeA = 1
	}
	if sizeB, ok = d.size[rootB]; !ok {
		sizeB = 1
	}
	if sizeA > sizeB {
		rootA, rootB = rootB, rootA
		sizeA, sizeB = sizeB, sizeA
	}
	d.parent[rootA] = rootB
	d.size[rootB] = sizeB + 1
	delete(d.size, rootA)
}

func (d DisjointSet) Find(a int32) {
	root := a
	for parent, ok := d.parent[a]; ok; parent, ok = d.parent[parent] {
		root = parent
	}
	return root
}

func roadsAndLibraries(n int32, c_lib int32, c_road int32, cities [][]int32) int64 {
	if c_lib < c_road {
		return int64(n * c_lib)
	}
	disJoinSet := DisjointSet{size: make(map[int32]int), parent: make(map[int32]int32)}
	counter := 0
	for _, v := range cities {
		e := v[0]
		f := v[1]
		if disJoinSet.Find(e) != disJoinSet.Find(f) {
			disJoinSet.Union(e, f)
			counter++
		}
	}
	counter = n - counter - 1
	fmt.Println(counter)
	return counter*int64(c_lib) + int64(c_road)*int64(n) - int64(c_road)*counter
}

func main() {
	reader := bufio.NewReaderSize(os.Stdin, 1024*1024)

	stdout := os.Stdin

	writer := bufio.NewWriterSize(stdout, 1024*1024)

	qTemp, err := strconv.ParseInt(readLine(reader), 10, 64)
	checkError(err)
	q := int32(qTemp)

	for qItr := 0; qItr < int(q); qItr++ {
		nmC_libC_road := strings.Split(readLine(reader), " ")

		nTemp, err := strconv.ParseInt(nmC_libC_road[0], 10, 64)
		checkError(err)
		n := int32(nTemp)

		mTemp, err := strconv.ParseInt(nmC_libC_road[1], 10, 64)
		checkError(err)
		m := int32(mTemp)

		c_libTemp, err := strconv.ParseInt(nmC_libC_road[2], 10, 64)
		checkError(err)
		c_lib := int32(c_libTemp)

		c_roadTemp, err := strconv.ParseInt(nmC_libC_road[3], 10, 64)
		checkError(err)
		c_road := int32(c_roadTemp)

		var cities [][]int32
		for i := 0; i < int(m); i++ {
			citiesRowTemp := strings.Split(readLine(reader), " ")

			var citiesRow []int32
			for _, citiesRowItem := range citiesRowTemp {
				citiesItemTemp, err := strconv.ParseInt(citiesRowItem, 10, 64)
				checkError(err)
				citiesItem := int32(citiesItemTemp)
				citiesRow = append(citiesRow, citiesItem)
			}

			if len(citiesRow) != 2 {
				panic("Bad input")
			}

			cities = append(cities, citiesRow)
		}

		result := roadsAndLibraries(n, c_lib, c_road, cities)

		fmt.Fprintf(writer, "%d\n", result)
	}

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
