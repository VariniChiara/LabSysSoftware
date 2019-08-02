%====================================================================================
% ddrsys description   
%====================================================================================
context(ctx, "localhost",  "TCP", "8090" ).
 qactor( robotdatasource, ctx, "external").
  qactor( console, ctx, "it.unibo.console.Console").
  qactor( robotmind, ctx, "it.unibo.robotmind.Robotmind").
  qactor( onestepahead, ctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, ctx, "it.unibo.robotactuator.Robotactuator").
