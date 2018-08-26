type Elem struct {
	value int
	min   int
}
type MinStack struct {
	elements []Elem
}

/** initialize your data structure here. */
func Constructor() MinStack {
	return MinStack{[]Elem{}}
}

func (this *MinStack) Push(x int) {
	if len(this.elements) == 0 {
		this.elements = append(this.elements, Elem{x, x})
	} else {
		min := this.elements[len(this.elements)-1].min
		if min > x {
			min = x
		}
		this.elements = append(this.elements, Elem{x, min})
	}

}

func (this *MinStack) Pop() {
	if len(this.elements) != 0 {
		this.elements = this.elements[:len(this.elements)-1]
	}
}

func (this *MinStack) Top() int {
	if len(this.elements) != 0 {
		return this.elements[len(this.elements)-1].value
	}
	return -1
}

func (this *MinStack) GetMin() int {
	if len(this.elements) != 0 {
		return this.elements[len(this.elements)-1].min
	}
	return -1
}

/**
 * Your MinStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(x);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.GetMin();
 */