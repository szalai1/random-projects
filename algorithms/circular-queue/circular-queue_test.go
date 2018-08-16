package queue

import (
	"log"
	"testing"
)

func TestCircularQueue(t *testing.T) {
	q := NewMyCircularQueue(3)
	if !q.EnQueue(1) {
		t.Error("there should be space for this operation")
	}
	log.Printf("after inserting 1: %v", q)
	q.EnQueue(2)
	log.Printf("after inserting 2: %v", q)
	q.EnQueue(3)
	log.Printf("after inserting 3: %v", q)

	if q.EnQueue(4) {
		t.Error("ther should not be capacity for this ")
	}
	r := q.Rear()
	if r != 3 {
		t.Errorf("got: %d, should be: %d", r, 3)
	}
	if !q.IsFull() {
		t.Error("it should be full")
	}
	if !q.DeQueue() {
		t.Error("could not dequeue!")
	}
	log.Printf("after dequeue: %v", q)

	if !q.EnQueue(4) {
		t.Error("could not enqueue")
	}
	r = q.Rear()
	if r != 4 {
		t.Errorf("got: %d, should be: %d", r, 4)
	}
}

func TestComplex(t *testing.T) {
	q := NewMyCircularQueue(81)
	q.EnQueue(69)
	log.Printf("Enqueue 69: %v", q)
	q.DeQueue()
	log.Printf("dequeue: %v", q)
	q.EnQueue(92)
	log.Printf("Enqueue 92: %v", q)
	q.EnQueue(12)
	log.Printf("Enqueue 12: %v", q)
	q.DeQueue()
	log.Printf("dequeue: %v", q)
	f := q.Front()
	if f != 12 {
		t.Errorf("got: %d, expected: %d", f, 12)
	}

}
