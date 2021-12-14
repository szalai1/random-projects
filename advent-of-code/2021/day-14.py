from collections import Counter


input = "OHFNNCKCVOBHSSHONBNF"
rules_raw = """SV -> O
...
"""

rules = {}
for line in rules_raw.split("\n"):
    line_parts = line.split(" -> ")
    rules[line_parts[0]] = line_parts[1]

memoization = {}

def pair_after_n_steps(pair, n):
    if (pair, n) in memoization:
        return memoization[(pair, n)]
    if n == 0:
        memoization[(pair, n)] = Counter(pair)
        return memoization[(pair, n)]
    a = pair[0]
    b = pair[1]
    if pair in rules:
        to_insert = rules[pair]
        first_part = pair_after_n_steps(a+to_insert, n-1)
        second_part = pair_after_n_steps(to_insert+b, n-1)
        return_val = first_part+second_part 
        return_val.subtract([to_insert])
        memoization[(pair, n)] = return_val
        return return_val
    memoization[(pair, n)] = Counter(pair)
    return memoization[(pair, n)]
    
def polymer_iteration(polymer, n):
    counter = Counter()
    for i in range(1, len(polymer)):
        a = polymer[i-1]
        b = polymer[i]
        counter += pair_after_n_steps(a+b, n)
        if i != len(polymer)-1:
            counter.subtract([b])
     return counter
 
def main():
  freq = polymer_iteration(input, 10).most_common()
  most_common_freq = freq[0][-1]
  least_common_freq  = freq[-1][-1]
  print("#1: ",   most_common_freq - least_common_freq)
  freq = polymer_iteration(input, 40).most_common()
  most_common_freq = freq[0][-1]
  least_common_freq  = freq[-1][-1]
  print("#2: ",   most_common_freq - least_common_freq)
