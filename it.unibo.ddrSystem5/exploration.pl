%====================================================================================
% exploration description   
%====================================================================================
mqttBroker("localhost", "1883").
context(robotmindctx, "localhost",  "MQTT", "0" ).
context(robotresourcectx, "localhost",  "MQTT", "0" ).
 qactor( robotmind, robotmindctx, "it.unibo.robotmind.Robotmind").
  qactor( onestepahead, robotmindctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, robotmindctx, "it.unibo.robotactuator.Robotactuator").
  qactor( resourcemodel, robotmindctx, "it.unibo.resourcemodel.Resourcemodel").
