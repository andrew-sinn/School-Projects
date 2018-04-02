#File name: Transpose.py

#
#This program will ask for rows of a matrix as input and will output
#the transpose of the matrix in python (v3.2.2).
#
#Author: Andrew Sinn
#

import re

#
#Function - printMatrix
#Prints the matrix out for display.
#
#Args - matrix: The matrix to be printed.
#

def printMatrix (matrix):
	for i in range(0, len(matrix)):
		print(matrix[i])

#The matrix to be requested from the user.
matrix = []

#The transpose of the matrix.
transpose = []

#The number of rows (lists).
numRows = 0

#Get a row from the user.
row = input('Enter a row: ')

#The number of columns.
numColumns = 0;

#While the rows are not empty, parse the rows and append them to the matrix.
#Also get the number of columns of the first row.

while (row):
	row = re.split(", ", row)	
	matrix.append(row)
	if numRows == 0:
		numColumns = len(row)
	numRows += 1
	row = input('Enter a row: ')

#The new row of a matrix to be generated.
newRow = []

#Gets the first element of each list and stores it in a list.
#This list will be the first row of the new matrix.
#Repeat for the all elements.

for i in range (0, numColumns):
	newRow = []
	for j in range(0, numRows):
		newRow.append(matrix[j][i])
	transpose.append(newRow)

#Print the input and output matrix.
print ('\nInput Matrix:')
printMatrix(matrix)
print('\nOutput Matrix:')
printMatrix(transpose)
		