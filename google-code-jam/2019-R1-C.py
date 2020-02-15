import sys
import time
from time import strftime
import datetime
import itertools

def read_int():
    return int(sys.stdin.readline()[:-1])

def read_2_ints():
    times = sys.stdin.readline().split(" ")
    return int(times[0]), int(times[1])

def read_N_strs(N):
    strs = []
    for i in range(N):
        strs.append(read_str()[::-1])
    return strs

def read_str():
    return sys.stdin.readline()[:-1]

def read_two_times_as_ints():
    times = sys.stdin.readline()[:-1].split(" ")
    t1 = time.strptime(times[0], "%H:%M")
    t2 = time.strptime(times[1], "%H:%M")
    t1 = t1.tm_min + t1.tm_hour*60
    t2 = t2.tm_min + t2.tm_hour*60
    return t1, t2

def print_ans(case_counter, ans):
    print("Case #{case}: {ans}".format(case=case_counter+1, ans=ans))

def print_ans_2(case_counter, ans1, ans2):
    print("Case #{case}: {ans1} {ans2}".format(case=case_counter+1, ans1=ans1, ans2=ans2))

def solver(ws):
    if len(ws) <= 1:
        return 0, 1
    if len(ws) == 2:
        return 2, 0
    new_w = []
    n = 0
    for w in ws:
        if len(w) == 1:
            n+=1
            continue
        new_w.append(w[1:])
    n_ = 0
    y_ = 0
    used = False
    for k, g in itertools.groupby(new_w, lambda w: w[0]):
        x, y = solver(list(g))
        n_ += x
        y_ += y

    if n + y_ >= 2:
        n_ += 2
        if n == 0:
            y_ -= 2
        if n == 1:
            y_ -= 1
            n -= 1
        if n == 2:
            n -= 2
    return n_, n + y_

def main():
    cases = read_int()
    for case in range(cases):
        N = read_int()
        words = read_N_strs(N)
        words.sort()
        n = 0
        x_ = 0
        y_ = 0
        for k, g in itertools.groupby(words, lambda w: w[0]):
            x, y  = solver(list(g))
            x_  += x
            y_ += y
        print_ans(case, x_)

main()
