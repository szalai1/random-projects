class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        pointer = head
        length = 0
        while pointer:
            length += 1
            pointer=pointer.next
        if length == 1 and n == 1:
            return None
        elif length == n:
            return head.next
        pointer = head
        for _ in range(length-n-1):
            pointer = pointer.next
        if pointer:
            pointer.next = pointer.next.next
        
        return head 