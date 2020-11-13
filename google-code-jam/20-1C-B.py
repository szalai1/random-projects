import sys
import time
from time import strftime
import datetime
import itertools
import math
from collections import Counter

def read_int():
    return int(sys.stdin.readline()[:-1])

def read_2_ints():
    times = sys.stdin.readline().split(" ")
    return int(times[0]), int(times[1])

def read_2_int_1_str():
    sp = sys.stdin.readline().split(" ")
    return int(sp[0]), int(sp[1]), sp[2][:-1]

def read_1_int_1_str():
    sp = sys.stdin.readline().split(" ")
    return int(sp[0]), sp[1][:-1]

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
        U = read_int()
        queries = []
        dist1 = Counter()
        dist2 = Counter()
        for q in range(10**4):
            M, R = read_1_int_1_str()
            queries.append((M,R))
            dist1.update(R[0])
            if len(R) > 1:
                dist2.update(R[1])
        print(dist1.most_common(10))
        print(dist2.most_common(10))
        string = []
        for i in dist2.elements():
            if i not in dist1:
                string = [i]
                dist2[i] = 0

        for l in dist1.most_common(9):
            string.append(l[0])
        print_ans(case, "".join(string))

main()