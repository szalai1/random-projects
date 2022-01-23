from typing import List
import collections

class Solution:
    def findWords(self, current: List[str], prefix_map):
        #print(">>", current, words)

        if len(current) > 0 and len(current) == len(current[0]):
            return [current]
        else:
            res = []
            for w in prefix_map["".join(list(zip(*current))[len(current)])]:
                res += self.findWords(current+[w], prefix_map)
            return res

    def _build_tree(self, words): 
        prefix_map = collections.defaultdict(list)
        for w in words:
            for i in range(len(w)):
                prefix_map[w[:i]].append(w)
        return prefix_map
    
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        words = sorted(words)
        prefix_map = self._build_tree(words)
        res = []
        for w in words:
            res += self.findWords([w], prefix_map)
        return res 
