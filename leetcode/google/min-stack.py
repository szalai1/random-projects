class MinStack:

    def __init__(self):
        self.min_so_far = None
        self.stack  = []
        

    def push(self, val: int) -> None:
        if self.min_so_far is None:
            self.min_so_far = val
        if val < self.min_so_far:
            self.min_so_far = val 
        self.stack.append((val,self.min_so_far))

    def pop(self) -> None:
        a = self.stack.pop()
        if len(self.stack) > 0:
            self.min_so_far = self.stack[-1][1]
        else:
            self.min_so_far = None
        

    def top(self) -> int:
        return self.stack[-1][0]
        

    def getMin(self) -> int:
        return self.stack[-1][1]

