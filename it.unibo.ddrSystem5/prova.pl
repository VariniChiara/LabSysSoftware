%====================================================================================
% prova description   
%====================================================================================
mqttBroker("localhost", "1883").
context(actuatorctx, "192.168.1.6",  "MQTT", "0" ).
context(mindctx, "localhost",  "MQTT", "0" ).
 qactor( robotmind, mindctx, "it.unibo.robotmind.Robotmind").
  qactor( robotactuator, actuatorctx, "it.unibo.robotactuator.Robotactuator").
