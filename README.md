CodingChallenge01-Toy Robot Simulator Version 1.0 13/11/2017

----------------------
Desciption The Problem
----------------------
- The application is a simulation of a toy robot moving on a square tabletop,
  of dimensions 5 units x 5 units.
- There are no other obstructions on the table surface.
- The robot is free to roam around the surface of the table, but must be
  prevented from falling to destruction. Any movement that would result in the
  robot falling from the table must be prevented, however further valid
  movement commands must still be allowed.

----------------------
Command Line Arguments
----------------------

- Place robot to the table in position X, Y and facing NORTH, WEST, SOUTH, or EAST:
	PLACE X,Y,F

- Move the robot one unit forward in the direction which it is current facing
	MOVE

- Rotate the robot 90 degrees to the left or right without changing the position of the robot
	LEFT / RIGHT

Show the position and the direction of the robot
	REPORT

-----------
Constraints
-----------

- Moving or Placing robot to the position which is out of table will be ignored

----------
Test Cases
----------

- Test cases are in test case folder, user can use this command to test all cases which
is created in all files in the folder:
	.*           : test all cases
	name_folder  : test all files in this folder
	name_file.set: test all case in this file test


--------
Analysis
--------

- Create Position class to store the position of the robot in the table
		+ easy to add operation with position such as add, sub, mul.
		+ prepare to add more attribution to the positon such as color, dimensions.

- Using Circularly Linked List to store all possible direction of the robot
		+ easy to rotate
		+ easy add more possible direction such as NORTH-WEST, WEST_SOUTH, ... even rotation of degree likes step motor.
		+ be more likely to physical move than using Circularly Array because element in array can be accessed immediately (impossible in real world) compared with the sequence access of list.

- Create Direction State to generate a connection between the direcition in String (readable for human) and in Position form (convenient for programming)

- Using Singleton Pattern to ensure PLACE command can not create new instance of robot.

- Do not check valid move in Robot class and pass the responsibility to Table class, leading to have predictPos attribute and updatePos method. Because the information to check valid move belongs to Table such as the size of the table (may be the position of other robots when extending this simulator to multi-robot)

- Create 2 methods for users to make command to the robot such as console or file .set.

- Create CheckValidMove to prepare for more kind of check in the future such as check whether a move will conflict with other robots.