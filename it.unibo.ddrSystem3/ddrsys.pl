%====================================================================================
% ddrsys description   
%====================================================================================
mqttBroker("localhost", "1883").
context(ctx, "localhost",  "MQTT", "0" ).
 qactor( robotdatasource, ctx, "external").
  qactor( console, ctx, "it.unibo.console.Console").
  qactor( resourcemodel, ctx, "it.unibo.resourcemodel.Resourcemodel").
  qactor( robotmind, ctx, "it.unibo.robotmind.Robotmind").
  qactor( onestepahead, ctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, ctx, "it.unibo.robotactuator.Robotactuator").
