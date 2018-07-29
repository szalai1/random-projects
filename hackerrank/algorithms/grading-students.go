package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

// this function implements the logic, which converts grades.
func computeGrade(original int) int {
	if original < 38 {
		return original
	}
	if original%5 >= 3 {
		return original/5*5 + 5
	}
	return original
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	line, _, readerr := reader.ReadLine()
	if readerr != nil {
		log.Fatal("could not read line")
		return
	}
	n, err := strconv.Atoi(strings.TrimSuffix(string(line), "\n"))
	if err != nil {
		log.Fatalf("could not convert to int the first line(%s).", line)
		return
	}
	for ii := 0; ii < n; ii++ {
		g, _, rerr := reader.ReadLine()
		if rerr != nil {
			log.Fatalf("could not read %d. grade", ii)
		}
		grade, gerr := strconv.Atoi(strings.TrimSuffix(string(g), "\n"))
		if gerr != nil {
			log.Fatalf("could not convert the %d. grade: %s", ii, grade)
		}
		fmt.Println(computeGrade(grade))
	}
}
