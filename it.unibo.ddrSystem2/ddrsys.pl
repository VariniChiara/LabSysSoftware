%====================================================================================
% ddrsys description   
%====================================================================================
context(ctx, "localhost",  "TCP", "8090" ).
 qactor( console, ctx, "it.unibo.console.Console").
  qactor( robotmind, ctx, "it.unibo.robotmind.Robotmind").
  qactor( robotactuator, ctx, "it.unibo.robotactuator.Robotactuator").
