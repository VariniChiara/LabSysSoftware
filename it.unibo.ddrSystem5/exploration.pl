%====================================================================================
% exploration description   
%====================================================================================
mqttBroker("192.168.1.7", "1883").
context(robotmindctx, "192.168.1.7",  "MQTT", "0" ).
context(robotresourcectx, "localhost",  "MQTT", "0" ).
 qactor( robotmind, robotmindctx, "it.unibo.robotmind.Robotmind").
  qactor( onestepahead, robotmindctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, robotresourcectx, "it.unibo.robotactuator.Robotactuator").
  qactor( resourcemodel, robotresourcectx, "it.unibo.resourcemodel.Resourcemodel").
