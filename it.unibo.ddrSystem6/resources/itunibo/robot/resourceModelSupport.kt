package itunibo.robot

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import itunibo.coap.modelResourceCoap

object resourceModelSupport{
lateinit var resourcecoap : modelResourceCoap

	fun setCoapResource( rescoap : modelResourceCoap ){
		resourcecoap = rescoap
	}

	fun updateRobotModel( actor: ActorBasic, content: String ){
 			//actor.solve(  "action(robot, move($content) )" ) //change the robot state model
			//actor.solve(  "model( A, robot, STATE, DIR, POS)" )
			val RobotState = "state(" + itunibo.planner.plannerUtil.getState(content) + ")"
			val RobotDir = "direction(" + itunibo.planner.plannerUtil.getDirection() + ")"
			val RobotPos = "position(" + itunibo.planner.plannerUtil.getPosition() + ")"
	
		
			//println("			resourceModelSupport updateModel RobotState=$RobotState")
			actor.scope.launch{
 				//actor.emit( "modelChanged" , "modelChanged(  robot,  $content)" )  //for the robotmind
				actor.emit( "modelContent" , "content( robot( $RobotState, $RobotDir, $RobotPos ) )" ) //for the web server
				resourcecoap.updateState( "robot( $RobotState, $RobotDir, $RobotPos )" )
  			}
	}
	fun updateSonarRobotModel( actor: ActorBasic, content: String ){
 			actor.solve( "action( sonarRobot,  $content )" ) //change the robot state model
			actor.solve( "model( A, sonarRobot, STATE )" )
			val SonarState = actor.getCurSol("STATE")
			//println("			resourceModelSupport updateSonarRobotModel SonarState=$SonarState")
			actor.scope.launch{
 				actor.emit( "modelContent" , "content( sonarRobot( $SonarState ) )" )
				resourcecoap.updateState( "sonarRobot( $SonarState )" )
 			}
	}

	fun updateRoomMapModel( actor: ActorBasic, content: String ){
	println("			resourceModelSupport updateRoomMapModel content=$content")
		actor.scope.launch{
			actor.emit( "modelContent" , "content( roomMap( state( '$content' ) ) )" )
			resourcecoap.updateState( "roomMap( '$content' )" )
		}
	}
	
	fun updateLuggageModel( actor: ActorBasic, content: String ){
	println("			resourceModelSupport updateLuggageMapModel")
		var photo = "photo " + content
		var pos = "position: "+ Pair(itunibo.planner.plannerUtil.getPosX(), itunibo.planner.plannerUtil.getPosY() )
		var date_time =  "date: " + java.time.LocalDateTime.now()
		actor.scope.launch{
			actor.emit( "modelContent" , "content( luggage( state( '$photo, $pos, $date_time' ) ) )" )
			resourcecoap.updateState( "luggage( state( '$photo, $pos, $date_time' ) )" )
		}
	}

}
