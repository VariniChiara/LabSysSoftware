%====================================================================================
% Context ctx  SYSTEM-configuration: file it.unibo.ctx.ddrSys.pl 
%====================================================================================
pubsubserveraddr("").
pubsubsystopic("unibo/qasys").
%%% -------------------------------------------
context(ctx, "localhost",  "TCP", "8078" ).  		 
%%% -------------------------------------------
qactor( console , ctx, "it.unibo.console.MsgHandle_Console"   ). %%store msgs 
qactor( console_ctrl , ctx, "it.unibo.console.Console"   ). %%control-driven 
qactor( robot , ctx, "it.unibo.robot.MsgHandle_Robot"   ). %%store msgs 
qactor( robot_ctrl , ctx, "it.unibo.robot.Robot"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

