def fold_x(m, x):
    matrix = m.copy()
    new_matrix = [row[:x] for row in matrix]
    for x_c in range(x+1, x*2+1):
        for y in range(0, len(matrix)):
            if matrix[y][x_c] == 1:
                new_matrix[y][x-x_c] = 1
    return new_matrix

def fold_y(m, y):
    matrix = m.copy()
    new_matrix = matrix[:y]
    for x in range(0, len(matrix[0])):
        for y_c in range(y+1, y*2+1):
            if matrix[y_c][x] == 1:
                new_matrix[y-y_c][x] = 1
    return new_matrix
  
def prettyprint(result):
  for l in result:
    for p in l:
        if p == 1:
            print('#', end="")
        else:
            print(".", end="")
    print("")
  
def main():
  coordinates = []
  for l in input.split("\n"):
      splitted = l.split(",")
      a = splitted[0]
      b = splitted[1]
      coordinates.append([int(a), int(b)])
  max_x = 655*2+1
  max_y = 447*2+1 
  matrix = [max_x*[0] for _ in range(max_y)]
  for c in coordinates:
      matrix[c[1]][c[0]] = 1
  result = matrix.copy()
  for x in [655, 327, 163, 81, 40]:
      result = fold_x(result, x)
  for y in [447, 223, 111, 55, 27, 13, 6]:
      result = fold_y(result, y)
  prettyprint(result)
  
main()
