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

def solver(ws):
    x_min = -1
    y_min = -1
    N = []
    E = []
    W = []
    S = []
    for k, g in itertools.groupby(ws, key=lambda x: x[2]):
        g = list(g)
        if k == "N":
            g.sort(key=lambda x: x[1])
            N = list(map(lambda  x: x[1], g))
        if k == "E":
            g.sort(key=lambda x: x[0])
            E =  list(map(lambda  x: x[0], g))
        if k == "W":
            g.sort(key=lambda x: x[0])
            W = list(map(lambda  x: x[0], g))
        if k == "S":
            g.sort(key=lambda x: x[1])
            S = list(map(lambda  x: x[1], g))

    i , j = 0, 0
    current = 0
    v = len(S)
    max_v = v
    max_k = current
    print(N,S)
    while i + j < len(N) + len(S):
        if i < len(S) and S[i] < current:
            i+=1
        elif j < len(N) and N[j] < current:
            j+=1
        elif i < len(S) and S[i] == current:
            v -= 1
            i+=1
        elif j < len(N) and  N[j] == current:
            print(current, N[j], v+1)
            j+=1
            v += 1
            if v > max_v:
                max_k = current
                max_v = v
        else:
            current += 1
    y_min = max_k

    i , j = 0, 0
    current = 0
    v = len(W)
    max_v = v
    max_k = current
    while i + j < len(E) + len(W):
        if i < len(W) and W[i] < current:
            i+=1
        elif j < len(E) and E[j] < current:
            j+=1
        elif i < len(W) and W[i] == current:
            v -= 1
            i+=1
        elif j < len(E) and  E[j] == current:
            j+=1
            v += 1
            if v > max_v:
                max_k = current
                max_v = v
        else:
            current += 1
    x_min = max_k
    if not (x_min == 0 and len(E) == 0):
        x_min += 1
    if not (y_min == 0 and len(N) == 0):
        y_min += 1

    return x_min, y_min

def main():
    cases = read_int()
    for case in range(cases):
        P, Q = read_2_ints()
        peps = []
        for dir  in range(P):
            x ,y, d = read_2_int_1_str()
            peps.append((x,y,d))
        peps.sort(key= lambda x: x[2])
        x,y = solver(peps)
        print_ans_2(case, x, y)

main()
