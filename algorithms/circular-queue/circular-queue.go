package queue

type MyCircularQueue struct {
	queue []int
	head  int
	size  int
	cap   int
}

/** Initialize your data structure here. Set the size of the queue to be k. */
func NewMyCircularQueue(k int) MyCircularQueue {
	return MyCircularQueue{queue: make([]int, k), cap: k}
}

/** Insert an element into the circular queue. Return true if the operation is successful. */
func (this *MyCircularQueue) EnQueue(value int) bool {
	if this.size >= this.cap {
		return false
	}
	this.queue[(this.head+this.size)%this.cap] = value
	this.size += 1
	return true
}

/** Delete an element from the circular queue. Return true if the operation is successful. */
func (this *MyCircularQueue) DeQueue() bool {
	if this.size <= 0 {
		return false
	}
	this.head = (this.head + 1) % this.cap
	this.size--
	return true
}

/** Get the front item from the queue. */
func (this *MyCircularQueue) Front() int {
	if this.size <= 0 {
		return -1
	}
	return this.queue[this.head]
}

/** Get the last item from the queue. */
func (this *MyCircularQueue) Rear() int {
	if this.size == 0 {
		return -1
	}
	return this.queue[(this.head+this.size-1)%this.cap]
}

/** Checks whether the circular queue is empty or not. */
func (this *MyCircularQueue) IsEmpty() bool {
	return this.size == 0
}

/** Checks whether the circular queue is full or not. */
func (this *MyCircularQueue) IsFull() bool {
	return this.cap == this.size
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * obj := Constructor(k);
 * param_1 := obj.EnQueue(value);
 * param_2 := obj.DeQueue();
 * param_3 := obj.Front();
 * param_4 := obj.Rear();
 * param_5 := obj.IsEmpty();
 * param_6 := obj.IsFull();
 */
