%====================================================================================
% exploration description   
%====================================================================================
mqttBroker("localhost", "1883").
context(robotmindctx, "localhost",  "MQTT", "0" ).
context(robotresourcectx, "192.168.1.6",  "MQTT", "0" ).
 qactor( robotmind, robotmindctx, "it.unibo.robotmind.Robotmind").
  qactor( onestepahead, robotmindctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, robotresourcectx, "it.unibo.robotactuator.Robotactuator").
  qactor( resourcemodel, robotresourcectx, "it.unibo.resourcemodel.Resourcemodel").
