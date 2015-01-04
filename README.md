MultClassificationOutputComaparer
=================================



###Input  
* Number of implementations, location of output file for different implementations 
* (Optional) Print output comparison result to new text file, 
* (Optional) Print (differing predictions) missed test cases for selected implemenation

Input file format- classification labels/predictions listed vertically in file 
	
	
Example of usage:

	6
	*file paths*
	Y
	2
	
To compare the 6 different output files the program creates a table such as below 
	
	
| ID  |  A  |  B  |  C  |  D  |  E  |  F  |
| --- | --- | --- | --- | --- | --- | --- |
|  1  |  -1 |  -1 |  -1 |  +1 |  +1 |  +1 |
|  2  |  -1 |  -1 |  +1 |  -1 |  -1 |  +1 |
|  3  |  +1 |  -1 |  +1 |  -1 |  -1 |  -1 |
|  4  |  +1 |  -1 |  +1 |  +1 |  +1 |  +1 |
|  5  |  -1 |  -1 |  -1 |  -1 |  -1 |  -1 |
|  6  |  +1 |  -1 |  +1 |  +1 |  +1 |  +1 |

	
	
	
	
###Output 
* Number of disparities in rows of input files = 3
* input file with most number of disparities = input file 2 (B)
* input file with least number of disparities = input file 1,4,5 (A,D,E)
	

| ID  |  Most common prediction  |  Differing predictions  |
| --- | ------------------------ |  ---------------------- |
|  1  |        -1, +1            |           -             |
|  2  |        -1                |         C, F            |   
|  3  |        -1                |         A, C            |
|  4  |        +1                |           B             | 
|  5  |        -1                |           -             | 
|  6  |        +1                |           B             |   
	

Since 2 was selected in the optional paramter, the program prints out the differing predictions (missed prediction cases) for B 


| ID  |  Most common prediction  |  B's prediction   |
| --- | ------------------------ |  ---------------- |
|  4  |        +1                |         -1        | 
|  6  |        +1                |         -1        |
