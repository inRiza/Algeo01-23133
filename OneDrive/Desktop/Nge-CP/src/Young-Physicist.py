def youngPhysicist(): 
    n = int(input()) 
    vector = [list(map(int, input().split())) for i in range(n)]
    x = sum([vector[i][0] for i in range(n)])
    y = sum([vector[i][1] for i in range(n)])
    z = sum([vector[i][2] for i in range(n)])
    if x == 0 and y == 0 and z == 0:
        print("YES")
    else:
        print("NO") 

youngPhysicist()