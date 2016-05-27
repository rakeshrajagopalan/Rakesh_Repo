Game Name: Puyo-Puyo (8 rows X 4 columns)
Developer and author: Rakesh R
Typical Requirements: Java SDK 1.4 or higher, Windows XP.

Abstract logic:

		This game was developed purely in Java SE(Swing, few other APIs). The logic is, setting an icon to the label and clearing it after time out in a separate thread and resetting the icon to the subsequent label, giving an illusion to the user that the icon is falling down. When four icons of similar colors fall in place either vertically or horizontally, they are cleared, initiating the puyos to fall and fill the vacant spaces. When the user presses left arrow or right arrow, the application brings in the next component  to its immediate  left or right, depending upon the user choice. When the user presses the up or down arrow keys, the icons are flipped. When the user clears similar puyos, the score increases by 4 points. If the puyos reaches top row, the game ends there. 

Logic (Method by method): 
	
	For method by method logic, please refer the comments written at the beginning of every method.

Extra Features:
	
	1. When the user presses right or left arrow keys, the puyos move to the left/right side of the board. This feature was not given in the requirement.
	
Missing Feature:
	
	1. When 4 puyos of similar colors are joined row-wise or column-wise, they are cleared. However, in the requirement it was asked to clear 4 similar puyos row-wise/column-wise. Due to lack of time, this feature was skipped.