%====================================================================================
% ddrsys description   
%====================================================================================
context(ctx, "localhost",  "TCP", "8090" ).
 qactor( console, ctx, "it.unibo.console.Console").
  qactor( robot, ctx, "it.unibo.robot.Robot").
