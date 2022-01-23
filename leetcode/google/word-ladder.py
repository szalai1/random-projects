class Solution:
    
    def get_neighbours(self, word):
        w = list(word)
        for n in range(len(word)):
            for c in range(ord('a'), ord('z')+1):
                ww  = w.copy()
                ww[n] = c
                yield tuple(ww)
    
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        wordList = [tuple([ord(c) for c in w]) for w in wordList]
        endWord = tuple([ord(c) for c in endWord])
        beginWord = tuple([ord(c) for c in beginWord])
        words = set(wordList)
        if endWord not in words:
            return 0
        visited = set()
        queue = [(beginWord, 0)]
        while queue:
            next_word, distance = queue.pop()
            if next_word in visited:
                continue 
            visited.add(next_word)
            if next_word == endWord:
                return distance  + 1
            neighbours = self.get_neighbours(next_word)
            for n in neighbours:
                if n in words and n not in visited:
                    queue = [(n, distance + 1)] + queue
        return 0