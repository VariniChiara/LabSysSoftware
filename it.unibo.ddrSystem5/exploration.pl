%====================================================================================
% exploration description   
%====================================================================================
mqttBroker("192.168.1.215", "1883").
context(robotmindctx, "localhost",  "MQTT", "0" ).
context(robotresourcectx, "192.168.1.164",  "MQTT", "0" ).
 qactor( robotmind, robotmindctx, "it.unibo.robotmind.Robotmind").
  qactor( planexecutor, robotmindctx, "it.unibo.planexecutor.Planexecutor").
  qactor( onestepahead, robotmindctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, robotresourcectx, "it.unibo.robotactuator.Robotactuator").
  qactor( resourcemodel, robotmindctx, "it.unibo.resourcemodel.Resourcemodel").
  qactor( sonarhandler, robotmindctx, "it.unibo.sonarhandler.Sonarhandler").
