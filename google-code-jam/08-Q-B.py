import sys
import time
from time import strftime
import datetime

def read_int():
    return int(sys.stdin.readline()[:-1])

def read_2_ints():
    times = sys.stdin.readline().split(" ")
    return int(times[0]), int(times[1])

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

def main():
    cases = read_int()
    for i in range(cases):
        minp = read_int()
        na, nb = read_2_ints()
        a_trains = []
        b_trains = []
        for a in range(na):
            t1, t2 = read_two_times_as_ints()
            t2 += minp
            a_trains.append((t1, 1))
            b_trains.append((t2, -1))
        for b in range(nb):
            t1, t2 = read_two_times_as_ints()
            t2 += minp
            a_trains.append((t2, -1))
            b_trains.append((t1, 1))

        a_trains.sort(key = lambda x: x[0] + 0.1*x[1])
        b_trains.sort(key = lambda x: x[0] + 0.1*x[1])
        a_max = 0
        a_current = 0
        for x in a_trains:
            a_current += x[1]
            a_max = max(a_current, a_max)

        b_max = 0
        b_current = 0
        for x in b_trains:
            b_current += x[1]
            b_max = max(b_current, b_max)
        print_ans_2(i, a_max, b_max)
main()