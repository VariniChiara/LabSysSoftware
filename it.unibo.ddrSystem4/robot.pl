%====================================================================================
% robot description   
%====================================================================================
mqttBroker("localhost", "1883").
context(robotctx, "localhost",  "MQTT", "0" ).
 qactor( robotmind, robotctx, "it.unibo.robotmind.Robotmind").
  qactor( robotactuator, robotctx, "it.unibo.robotactuator.Robotactuator").
  qactor( robotmodel, robotctx, "it.unibo.robotmodel.Robotmodel").
