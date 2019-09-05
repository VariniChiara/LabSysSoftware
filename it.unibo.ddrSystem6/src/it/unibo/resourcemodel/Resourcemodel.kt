/* Generated by AN DISI Unibo */ 
package it.unibo.resourcemodel

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Resourcemodel ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		var isLuggageDanger = false
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						solve("consult('sysRules.pl')","") //set resVar	
						solve("consult('resourceModel.pl')","") //set resVar	
						solve("showResourceModel","") //set resVar	
						itunibo.coap.modelResourceCoap.create(myself ,"resourcemodel" )
					}
					 transition( edgeName="goto",targetState="waitModelChange", cond=doswitch() )
				}	 
				state("waitModelChange") { //this:State
					action { //it:State
					}
					 transition(edgeName="t021",targetState="changeModel",cond=whenDispatch("modelChange"))
					transition(edgeName="t022",targetState="updateModel",cond=whenDispatch("modelUpdate"))
				}	 
				state("updateModel") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("modelUpdate(TARGET,VALUE)"), Term.createTerm("modelUpdate(robot,V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								itunibo.robot.resourceModelSupport.updateRobotModel(myself ,payloadArg(1) )
								solve("showResourceModel","") //set resVar	
						}
						if( checkMsgContent( Term.createTerm("modelUpdate(TARGET,VALUE)"), Term.createTerm("modelUpdate(sonarRobot,V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								itunibo.robot.resourceModelSupport.updateSonarRobotModel(myself ,payloadArg(1) )
						}
						if( checkMsgContent( Term.createTerm("modelUpdate(TARGET,VALUE)"), Term.createTerm("modelUpdate(roomMap,V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								itunibo.robot.resourceModelSupport.updateRoomMapModel(myself ,payloadArg(1) )
						}
						if( checkMsgContent( Term.createTerm("modelUpdate(TARGET,VALUE)"), Term.createTerm("modelUpdate(luggage,V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("C!!!!!!!")
								itunibo.robot.resourceModelSupport.updateLuggageModel(myself ,payloadArg(1) )
						}
					}
					 transition( edgeName="goto",targetState="waitModelChange", cond=doswitch() )
				}	 
				state("changeModel") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("modelChange(TARGET,VALUE)"), Term.createTerm("modelChange(robot,V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
						}
						if( checkMsgContent( Term.createTerm("modelChange(TARGET,VALUE)"), Term.createTerm("modelChange(luggage,V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								if(payloadArg(1) == "danger"){	
												isLuggageDanger = true
											}
						}
					}
					 transition( edgeName="goto",targetState="handleDangerLuggage", cond=doswitchGuarded({isLuggageDanger}) )
					transition( edgeName="goto",targetState="handleSafeLuggage", cond=doswitchGuarded({! isLuggageDanger}) )
				}	 
				state("handleDangerLuggage") { //this:State
					action { //it:State
						isLuggageDanger = false
						forward("luggageDanger", "luggageDanger" ,"robotmind" ) 
					}
					 transition( edgeName="goto",targetState="waitModelChange", cond=doswitch() )
				}	 
				state("handleSafeLuggage") { //this:State
					action { //it:State
						forward("luggageSafe", "luggageSafe" ,"robotmind" ) 
					}
					 transition( edgeName="goto",targetState="waitModelChange", cond=doswitch() )
				}	 
			}
		}
}
