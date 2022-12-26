# Wave Maker

# Controls

You can click the 2D simulation to make certain waves. You can also drag your mouse to draw waves. Also, you can rotate the 3D simulation with the W and S keys.

# 2D and 3D

If you want to change the simulation from 2D to 3D, change Main.SPACIAL from false to true. I wish you could switch it mid-simulation, but processing does not let you do that.

# Drawing Walls and Excite

If you want to create a wall of a certain shape, use the raise methods. If you want to create a disturbance of a certain shape, use the excite methods.

The coordinate system is from [0,1] for both the x and y. 0 means all the way to the left or at the top while 1 means all the way to the right or the bottom.

Circles are defined by a point and a radius.
Lines are defined by two points.
Conic sections are defined by three points (two to define the line and one for the focus) and the eccentricity. (Ellipse < 1, Parabola = 1, Hyperbola > 1).

Use the raise and excite methods in the Mesh class.

# Math

![eq](https://user-images.githubusercontent.com/57644413/150157265-23e91d39-69c1-4cb4-9ee6-bdc71697e282.PNG)

Technically, this project is just finding a solution for the differential equation 
Phi is the height of each point on the surface.
c is the speed of the wave.
The equation says that the second rate of change (acceleration) of any height is proportional to the lapacian of the height (laplacian just is a difference of the height compared to neighboring points, so a hill would have a negative laplacian but a valley would have a positive laplacian)

To find a solution, I used the RK4 integration method. (You can change the amount of steps with the Main.STEPS variable; more means more accurate but less means it runs faster.)

Have fun playing with this!!!
