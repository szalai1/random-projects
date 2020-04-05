import sys
import time
from time import strftime
import datetime
import itertools
import math

def read_int():
    return int(sys.stdin.readline()[:-1])

def read_2_ints():
    times = sys.stdin.readline().split(" ")
    return int(times[0]), int(times[1])

def read_2_int_1_str():
    sp = sys.stdin.readline().split(" ")
    return int(sp[0]), int(sp[1]), sp[2][:-1]

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

def print_ans_3(case_counter, ans1, ans2, ans3):
    print("Case #{case}: {ans1} {ans2} {ans3}".format(case=case_counter+1, ans1=ans1, ans2=ans2, ans3=ans3))

def main():
    T = read_int()
    for case in range(T):
        N = read_int()
        num_of_not_u_row = 0
        num_of_not_u_columns = 0
        tracking_cols = [set({}) for _ in range(N)]
        col_flags = [0] * N
        trace = 0
        for n in range(N):
            row = map(lambda x: int(x), read_str().split(" "))
            if len(row) != len(set(row)):
                num_of_not_u_row += 1
            for nn in range(N):
                if nn == n:
                    trace += row[nn]
                if row[nn] in tracking_cols[nn]:
                    col_flags[nn] = 1
                else:
                    tracking_cols[nn].add(row[nn])

        print_ans_3(case, trace, num_of_not_u_row, sum(col_flags))

main()