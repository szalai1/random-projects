from re import L


def biggerIsGreater(w):
    w = list(w)
    for i in range(len(w)-1, 0, -1):
        a = w[i]
        b = w[i-1]
        print(a, b)
        if a > b:
            minmax = i-1
            for j in range(i, len(w)):
                print(i, j, minmax)
                if w[j] > w[i-1] and w[minmax] > w[j]:
                    minmax=j
            print(minmax, i)
            w[minmax], w[i-1] = w[i-1], w[minmax]
            return "".join(list(w[0:i-1]) + list(sorted(w[i:])))
    return "no answer" 

print(biggerIsGreater("dkhc"))