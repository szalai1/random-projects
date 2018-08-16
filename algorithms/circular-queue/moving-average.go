package queue

type MovingAverage struct {
	queue *MyCircularQueue
	sum   int
}

/** Initialize your data structure here. */
func Constructor(size int) MovingAverage {
	q := NewMyCircularQueue(size)
	return MovingAverage{
		queue: &q,
		sum:   0,
	}
}

func (this *MovingAverage) Next(val int) float64 {
	if this.queue.IsFull() {
		this.sum -= this.queue.Front()
		this.queue.DeQueue()
	}
	this.queue.EnQueue(val)
	this.sum += val
	return float64(this.sum) / float64(this.queue.size)
}
