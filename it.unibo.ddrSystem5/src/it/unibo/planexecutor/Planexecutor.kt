/* Generated by AN DISI Unibo */ 
package it.unibo.planexecutor

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Planexecutor ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				var Curmove     = ""  
				var x = 0
				var y = 0 
				var Map = ""
				var Tback = 0
				var StepTime   = 330
		
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
					}
					 transition(edgeName="t00",targetState="saveGoal",cond=whenDispatch("doPlan"))
				}	 
				state("saveGoal") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("doPlan(x,y)"), Term.createTerm("doPlan(x,y)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								x = payloadArg(0).toInt()
											  y = payloadArg(1).toInt()
						}
						itunibo.planner.moveUtils.doPlan(myself)
					}
					 transition( edgeName="goto",targetState="doPlan", cond=doswitch() )
				}	 
				state("doPlan") { //this:State
					action { //it:State
						stateTimer = TimerActor("timer_doPlan", 
							scope, context!!, "local_tout_planexecutor_doPlan", 50.toLong() )
					}
					 transition(edgeName="t11",targetState="doPlan1",cond=whenTimeout("local_tout_planexecutor_doPlan"))   
					transition(edgeName="t12",targetState="stopAppl",cond=whenDispatch("stopCmd"))
				}	 
				state("stopAppl") { //this:State
					action { //it:State
						forward("robotCmd", "robotCmd(h)" ,"robotactuator" ) 
						forward("modelUpdate", "modelUpdate(robot,h)" ,"resourcemodel" ) 
						solve("retractall(move(_))","") //set resVar	
					}
					 transition( edgeName="goto",targetState="s0", cond=doswitch() )
				}	 
				state("doPlan1") { //this:State
					action { //it:State
						Map =  itunibo.planner.plannerUtil.getMapOneLine()
						forward("modelUpdate", "modelUpdate(roomMap,$Map)" ,"resourcemodel" ) 
						itunibo.planner.plannerUtil.showMap(  )
						solve("retract(move(M))","") //set resVar	
						if(currentSolution.isSuccess()) { Curmove = getCurSol("M").toString()
						 }
						else
						{ Curmove="nomove" 
						 }
						println(Curmove)
					}
					 transition( edgeName="goto",targetState="handlemove", cond=doswitchGuarded({(Curmove != "nomove")}) )
					transition( edgeName="goto",targetState="choose", cond=doswitchGuarded({! (Curmove != "nomove")}) )
				}	 
				state("choose") { //this:State
					action { //it:State
						forward("robotCmd", "robotCmd(h)" ,"robotactuator" ) 
						forward("modelUpdate", "modelUpdate(robot,h)" ,"resourcemodel" ) 
						forward("planOk", "planOk" ,"robotmind" ) 
					}
					 transition( edgeName="goto",targetState="s0", cond=doswitch() )
				}	 
				state("handlemove") { //this:State
					action { //it:State
					}
					 transition( edgeName="goto",targetState="domove", cond=doswitchGuarded({(Curmove != "w")}) )
					transition( edgeName="goto",targetState="attempttogoahead", cond=doswitchGuarded({! (Curmove != "w")}) )
				}	 
				state("domove") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.doPlannedMove(myself ,Curmove )
						forward("robotCmd", "robotCmd($Curmove)" ,"robotactuator" ) 
						delay(700) 
						forward("robotCmd", "robotCmd(h)" ,"robotactuator" ) 
						forward("modelUpdate", "modelUpdate(robot,$Curmove)" ,"resourcemodel" ) 
					}
					 transition( edgeName="goto",targetState="doPlan", cond=doswitch() )
				}	 
				state("attempttogoahead") { //this:State
					action { //it:State
						forward("modelUpdate", "modelUpdate(robot,w)" ,"resourcemodel" ) 
						itunibo.planner.moveUtils.attemptTomoveAhead(myself ,StepTime )
					}
					 transition(edgeName="t23",targetState="stepDone",cond=whenDispatch("stepOk"))
					transition(edgeName="t24",targetState="stepFailed",cond=whenDispatch("stepFail"))
				}	 
				state("stepDone") { //this:State
					action { //it:State
						forward("modelUpdate", "modelUpdate(robot,h)" ,"resourcemodel" ) 
						itunibo.planner.moveUtils.doPlannedMove(myself ,"w" )
					}
					 transition( edgeName="goto",targetState="doPlan", cond=doswitch() )
				}	 
				state("stepFailed") { //this:State
					action { //it:State
						println("&&&  OBSTACLE FOUND")
						var TbackLong = 0L
						if( checkMsgContent( Term.createTerm("stepFail(R,T)"), Term.createTerm("stepFail(Obs,Time)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								Tback=payloadArg(1).toLong().toString().toInt() / 2
											TbackLong = Tback.toLong()
								println("stepFailed ${payloadArg(1).toString()}")
						}
						println(" backToCompensate stepTime=$Tback")
						forward("modelUpdate", "modelUpdate(robot,s)" ,"resourcemodel" ) 
						forward("robotCmd", "robotCmd(s)" ,"robotactuator" ) 
						delay(TbackLong)
						forward("robotCmd", "robotCmd(h)" ,"robotactuator" ) 
						forward("modelUpdate", "modelUpdate(robot,h)" ,"resourcemodel" ) 
						forward("planFail", "planFail" ,"robotmind" ) 
						delay(700) 
					}
					 transition( edgeName="goto",targetState="s0", cond=doswitch() )
				}	 
			}
		}
}
