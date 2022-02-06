class Logger:

    def __init__(self):
        self.messages = {}
        

    def shouldPrintMessage(self, timestamp: int, message: str) -> bool:
        t = self.messages.get(message, -11)
        
        res = timestamp - t >= 10
        if res:
            self.messages[message] = timestamp
        return res
