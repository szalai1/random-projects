# The read4 API is already defined for you.
# def read4(buf4: List[str]) -> int:
import collections 

class Solution:
    def __init__(self):
        self.buffer = collections.deque([])
        
    def read(self, buf: List[str], n: int) -> int:
        bytes_counter = 0
        tmp_buf = ['']*4
        while n > 0:
            if self.buffer:
                c = self.buffer.popleft()
                buf[bytes_counter] = c
                n-=1
                bytes_counter += 1
            else:
                readn = read4(tmp_buf)
                if readn == 0:
                    break
                else:
                    self.buffer += tmp_buf[:readn]
        return bytes_counter