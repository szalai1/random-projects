input = """QR-da
QR-end
QR-al
start-op
zh-iw
zh-start
da-PF
op-bj
iw-QR
end-HR
bj-PF
da-LY
op-PF
bj-iw
end-da
bj-zh
HR-iw
zh-op
zh-PF
HR-bj
start-PF
HR-da
QR-bj"""

graph = {}
for l in input.split("\n"):
    edge = l.split("-")
    a = edge[0]
    b = edge[1]
    if a in graph:
        graph[a].add(b)
    else:
        graph[a] = {b}
    if b in graph:
        graph[b].add(a)
    else:
        graph[b] = {a}

def all_valid_paths(graph, current_node="start", visited={"start"}, path=[], visited_small_cave=False):
    allready_visited = visited.copy()
    allready_visited.add(current_node)
    if current_node == "end":
        return [path+[current_node]]
    neighbours = graph[current_node]
    paths = []
    for n in neighbours:
        if n == "start":
            continue
        if n.lower() == n: 
            if n not in  allready_visited:
                paths+=all_valid_paths(graph, n, allready_visited , path.copy() + [current_node], visited_small_cave)
            elif not visited_small_cave:
                paths+=all_valid_paths(graph, n, allready_visited , path.copy() + [current_node], True)
        else:
            paths+=all_valid_paths(graph, n, allready_visited , path.copy() + [current_node], visited_small_cave)
        
    return paths

print("#1:", len(all_valid_paths(graph, visited_small_cave=True)))
print("#2:", len(all_valid_paths(graph, visited_small_cave=False)))
