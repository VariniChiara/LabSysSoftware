%====================================================================================
% exploration description   
%====================================================================================
mqttBroker("localhost", "1883").
context(robotmindctx, "localhost",  "MQTT", "0" ).
context(robotresourcectx, "192.168.1.164",  "MQTT", "0" ).
 qactor( planexecutor, robotmindctx, "it.unibo.planexecutor.Planexecutor").
  qactor( robotmind, robotmindctx, "it.unibo.robotmind.Robotmind").
  qactor( onestepahead, robotmindctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, robotresourcectx, "it.unibo.robotactuator.Robotactuator").
  qactor( blinkinghandler, robotmindctx, "it.unibo.blinkinghandler.Blinkinghandler").
  qactor( sonarhandler, robotmindctx, "it.unibo.sonarhandler.Sonarhandler").
  qactor( resourcemodel, robotmindctx, "it.unibo.resourcemodel.Resourcemodel").
