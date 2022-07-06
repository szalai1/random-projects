#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'queensAttack' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER n
#  2. INTEGER k
#  3. INTEGER r_q
#  4. INTEGER c_q
#  5. 2D_INTEGER_ARRAY obstacles
#

def queensAttack(n, k, r_q, c_q, obstacles):
    max_squeres = (n - 1) + (n - 1) + (n - 1 - abs(r_q - c_q)) + n - 1

    diag = r_q - c_q
    adiag = r_q + c_q
    min_r, max_r = (0, r_q), (n+1, r_q)
    min_c, max_c =(c_q, 0), (c_q, n+1)
    if diag > 0:
        min_d, max_d = (0, diag), (n-diag+1, n+1)
    else:
        min_d, max_d = (-diag, 0), (n+1, n-diag+1)
    if adiag < n:
        min_a, max_a = (adiag, 0), (0, adiag)
    else:
        min_a, max_a = (n+1, adiag-n-1), (adiag-n-1, n+1)
    for o in obstacles:
        if o[1] == c_q:
            print(">", c_q,o, min_r)
            if o[0] < r_q and  min_r[0] < o[0]:
                min_r = o
            if o[0] > r_q and max_r[0] > o[0]:
                max_r = o
        elif o[0] == r_q:
            print("#", o)
            if o[1] < c_q and min_c[1] < o[1]:
                min_c = o
            if o[1] > c_q and  max_c[1] > o[1]:
                max_c = o
        elif diag == o[0] - o[1]:
            if o[0] < r_q and min_d[0] < o[0]:
                min_d = o
            if o[0] > r_q and max_d[0] > o[0]:
                max_d = o
        elif adiag == o[1] + o[0]:
            if o[0] < r_q and min_a[0]< o[0]:
                min_a = o
            if o[0] > r_q and max_a[0] > o[0]:
                max_a = o
        else:
            pass
    print(max_squeres)
    res = max_r[1] - min_r[1] - 2 + \
        max_c[0] - min_c[0] - 2 \
        + max_d[0] - min_d[0] - 2 \
        + max_a[1] - min_a[1] - 2
    print(    max_r[1] - min_r[1] - 2 ,
        max_c[0] - min_c[0] - 2 ,
         max_d[0] - min_d[0] - 2 ,
         max_a[1] - min_a[1] - 2)
    print(min_r, max_c)
    return  res 

print(queensAttack(5,4 , 3, 3, [(4,2), (2, 3), (5,5)]))
#print(queensAttack(12, 0, 5, 6, [(7, 8),(1, 6), (4, 6), (10, 6), (9,6), (11, 0), (0, 11), (1, 10), (10, 1), (4,7), (10, 20),(2, 1), (2,3), (2, 10), (5,4), (5, 3), (5, 10), (5, 8), (1, 5), (2,1), (20, 5)]))