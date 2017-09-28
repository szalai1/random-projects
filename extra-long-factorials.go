package main

import (
	"bufio"
	"fmt"
	"log"
	"math/big"
	"os"
	"strconv"
	"strings"
)

func main() {
	reader := bufio.NewReader(os.Stdin)
	strn, _, readerr := reader.ReadLine()
	if readerr != nil {
		log.Fatal("could not read line")
		return
	}
	n, err := strconv.Atoi(strings.TrimSuffix(string(strn), "\n"))
	if err != nil {
		log.Fatalf("could not convert to int the first line(%s).", strn)
		return
	}
	result := big.NewInt(1)
	for ii := 1; ii <= n; ii++ {
		bigii := big.NewInt(int64(ii))
		result.Mul(bigii, result)
	}
	bstr, merr := result.MarshalText()
	if merr != nil {
		log.Fatalf("could not marshal the result.")
	}
	fmt.Println(string(bstr))
}
