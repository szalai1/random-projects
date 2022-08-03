class MyCalendar:

    def __init__(self):
        self._events = []
        

    def book(self, start: int, end: int) -> bool:
        if self._events == []:
            self._events.append((start, end))
            return True
        n = len(self._events)
        pos = bisect.bisect_left(self._events, start, key=lambda e: e[0])
        if pos < n:
            if end > self._events[pos][0]:
                return False 
        
        if pos > 0:
            if start < self._events[pos-1][1]:
                return False 
        self._events.insert(pos, (start, end))
        return True
