package main

import (
	"fmt"
)

func main() {
	var lenBoard int
	fmt.Scanf("%d", &lenBoard)
	board := []int{}
	for i := 0; i < lenBoard; i++ {
		var score int
		fmt.Scanf("%d", &score)
		if len(board) == 0 || score != board[len(board)-1] {
			board = append(board, score)
		}
	}
	var numOfGames int
	fmt.Scanf("%d", &numOfGames)
	currentPos := len(board)
	var score int
	for i := 0; i < numOfGames; i++ {
		fmt.Scanf("%d", &score)
		for currentPos > 0 && score > board[currentPos-1] {
			currentPos--
		}
		//tie
		if currentPos != 0 && board[currentPos-1] == score {
			fmt.Println(currentPos)
		} else {
			fmt.Println(currentPos + 1)
		}
	}
}
