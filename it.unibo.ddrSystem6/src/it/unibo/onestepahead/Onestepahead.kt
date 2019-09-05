/* Generated by AN DISI Unibo */ 
package it.unibo.onestepahead

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Onestepahead ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		 
		var foundObstacle = false
		var StepTime = 0L
		var Duration = 0 
		var fail = false
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						foundObstacle = false 
					}
					 transition(edgeName="t018",targetState="doMoveForward",cond=whenDispatch("onestep"))
				}	 
				state("doMoveForward") { //this:State
					action { //it:State
						println("======doMoveForward=========")
						if( checkMsgContent( Term.createTerm("onestep(DURATION)"), Term.createTerm("onestep(TIME)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								StepTime = payloadArg(0).toLong()
								forward("robotCmd", "robotCmd(w)" ,"robotactuator" ) 
								startTimer()
						}
						stateTimer = TimerActor("timer_doMoveForward", 
							scope, context!!, "local_tout_onestepahead_doMoveForward", StepTime )
					}
					 transition(edgeName="t119",targetState="endDoMoveForward",cond=whenTimeout("local_tout_onestepahead_doMoveForward"))   
					transition(edgeName="t120",targetState="stepFail",cond=whenEvent("sonar"))
				}	 
				state("endDoMoveForward") { //this:State
					action { //it:State
						forward("robotCmd", "robotCmd(h)" ,"robotactuator" ) 
						forward("stepOk", "stepOk" ,"planexecutor" ) 
					}
					 transition( edgeName="goto",targetState="s0", cond=doswitch() )
				}	 
				state("stepFail") { //this:State
					action { //it:State
						Duration=getDuration()
						println("$name in ${currentState.stateName} | $currentMsg")
						println("onestepahead stepFail Duration=$Duration ")
						forward("robotCmd", "robotCmd(h)" ,"robotactuator" ) 
						forward("stepFail", "stepFail(obstacle,$Duration)" ,"planexecutor" ) 
					}
					 transition( edgeName="goto",targetState="s0", cond=doswitch() )
				}	 
			}
		}
}
