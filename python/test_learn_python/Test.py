__author__ = 'MES'

print("Hello world")
anArray = [[1, 2, 3, 4, 5],
           [6, 7, 8, 9, 10],
           [11, 12, 13, 14, 15]]

for x in anArray:
    print(x)
    print("hello")
print("out of loop")

def testFunction(array, row):
    for x in array[row]:
        print(x)
    
testFunction(anArray, 2)