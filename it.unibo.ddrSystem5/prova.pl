%====================================================================================
% prova description   
%====================================================================================
mqttBroker("localhost", "1883").
context(provarobotmindctx, "localhost",  "MQTT", "0" ).
context(robotresourcectx, "localhost",  "MQTT", "0" ).
 qactor( planexecutor, provarobotmindctx, "it.unibo.planexecutor.Planexecutor").
  qactor( robotmind, provarobotmindctx, "it.unibo.robotmind.Robotmind").
  qactor( onestepahead, provarobotmindctx, "it.unibo.onestepahead.Onestepahead").
  qactor( robotactuator, provarobotmindctx, "it.unibo.robotactuator.Robotactuator").
  qactor( resourcemodel, provarobotmindctx, "it.unibo.resourcemodel.Resourcemodel").
