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
    for case in range(cases):
        n, m = read_2_ints()
        solveCase(case, n, m)

def valid_perm(perm):
    prev = None
    for i in perm:
        x, y, = i
        if prev is None:
            prev = i
            continue
        if x - prev[0] == y - prev[1] or x + prev[0] == y + prev[1] :
            return False
        elif x == prev[0] or y == prev[1]:
            return False
        prev = i
    return True


def solveCase(case, n, m):
    impossible = "IMPOSSIBLE"
    possible = "POSSIBLE"
    all_coordinates = []
    for i in range(n):
        for j in range(m):
            all_coordinates.append((i,j))

    for perm in itertools.permutations(all_coordinates):
        if valid_perm(perm):
            print_ans(case, possible)
            for coo in perm:
                print("{x} {y}".format(x = coo[0]+1, y= coo[1]+1))
            return
    print_ans(case, impossible)

main()
