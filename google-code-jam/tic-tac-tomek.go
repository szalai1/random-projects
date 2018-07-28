package main

import (
	"fmt"
)

const (
	ENCODED_T   = 0
	ENCODED_X   = 1
	ENCODED_O   = 2
	ENCODED_DOT = 4
)

func checkLines(table [4][4]int) int {
	isDot := 0
	for i := 0; i < 4; i++ {
		resultRow := 0
		resultCol := 0
		for j := 0; j < 4; j++ {
			resultRow |= table[i][j]
			resultCol |= table[j][i]
		}
		if resultCol == ENCODED_X || resultCol == ENCODED_O {
			return resultCol
		}
		if resultRow == ENCODED_X || resultRow == ENCODED_O {
			return resultRow
		}
		isDot |= resultRow
		isDot |= resultCol
	}
	return (isDot >> 2) << 2
}

func checkDiag(table [4][4]int) int {
	resDiag := 0
	resSubDiag := 0
	for i := 0; i < 4; i++ {
		resDiag |= table[i][i]
		resSubDiag |= table[i][4-i-1]
	}
	if resDiag == ENCODED_O || resDiag == ENCODED_X {
		//		println("f diag", resDiag)
		return resDiag
	}
	if resSubDiag == ENCODED_O || resSubDiag == ENCODED_X {
		//		println("f subdiag", resSubDiag)
		return resSubDiag
	}
	return resSubDiag | resSubDiag
}

func main() {
	mapping := map[byte]int{
		'X': 1,
		'O': 2,
		'.': 4,
		'T': 0,
	}
	/*	inverseMapping := map[int]byte{
		0: 'T',
		1: 'X',
		2: '0',
		4: '.',
	}*/
	var T int
	fmt.Scanf("%d", &T)
	for t := 0; t < T; t++ {
		var table [4][4]int
		for r := 0; r < 4; r++ {
			for c := 0; c < 4; c++ {
				var b byte
				fmt.Scanf("%c", &b)
				table[r][c] = mapping[b]
			}
			fmt.Scanf("\n")
		}
		fmt.Scanf("\n")
		resLine := checkLines(table)
		resDiag := checkDiag(table)
		fmt.Printf("Case #%d: ", t+1)
		if resLine == ENCODED_X || resDiag == ENCODED_X {
			fmt.Println("X won")
			continue
		}
		if resLine == ENCODED_O || resDiag == ENCODED_O {
			fmt.Println("O won")
			continue
		}
		if resLine == ENCODED_DOT && (resDiag != ENCODED_X && resDiag != ENCODED_O) {
			fmt.Println("Game has not completed")
			continue
		}
		if resLine != ENCODED_DOT && (resDiag != ENCODED_X && resDiag != ENCODED_O) {
			fmt.Println("Draw")
			continue
		}
	}

}
