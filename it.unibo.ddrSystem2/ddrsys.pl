%====================================================================================
% ddrsys description   
%====================================================================================
context(ctx, "localhost",  "TCP", "8090" ).
 qactor( console, ctx, "it.unibo.console.Console").
  qactor( basicrobot, ctx, "it.unibo.basicrobot.Basicrobot").
