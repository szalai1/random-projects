import sys

def read_int():
    return int(sys.stdin.readline()[:-1])

def read_str():
    return sys.stdin.readline()[:-1]

def main():
    cases = read_int()
    for i in range(cases):
        engs = read_int()
        engines = set()
        for _ii in range(engs):
            engines.add(read_str())
        wrds = read_int()
        counter = 0
        possib = engines.copy()
        for ii in range(wrds):
            w  = read_str()
            if w in possib:
                if len(possib) == 1:
                    counter+=1
                    possib = engines.copy()
                possib.remove(w)

        print("Case #{case}: {num}".format(case=i+1, num=counter) )

main()