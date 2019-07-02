import it.unibo.kactor.*

import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.*

import java.io.File


class TestDdrSystem1 {
	
	companion object{
		var startUpDone = false 
	}
	
	var console : ActorBasic? = null
	var robot : ActorBasic? = null
	var loadedMapStr: String = ""
	
	@Before
	@Throws(Exception::class)
	fun systemSetUp() {
		
		loadedMapStr = loadMapToString("test/testMap.txt")
		itunibo.planner.plannerUtil.loadRoomMap(loadedMapStr)
		
		GlobalScope.launch{it.unibo.ctx.main()}
		delay(4000)
		startUpDone = true
		getActors()
	}
	
	@After
	fun terminate() {
		println("TestDdrSystem1 terminates")
	}
	
	fun getActors() {
		console = sysUtil.getActor("console")
		robot = sysUtil.getActor("robot")	
	}
	
	fun delay(time: Long) {
		Thread.sleep(time)
	}
	
	//fun generateEnvCond(actor: ActorBasic, move: String) {
	//	actor.scope.launch{
	//		actor.emit()
	//	}
	//}
	
	fun solveCheckGoal( actor : ActorBasic, goal : String ){
		actor.solve( goal  )
		var result =  robot!!.resVar
		//println(" %%%%%%%  goal= $goal  result = $result")
		assertTrue("", result == "success" )
	}
	
	//some methods to move the robot 
	
	fun moveRobot( actor : ActorBasic, move : String ){
		actor.scope.launch{
			MsgUtil.sendMsg("userCmd", "userCmd( $move )", robot!!)
			
			//MsgUtil.sendMsg("modelChange", "modelChange(robot, $move)", robot!!)
		}
		delay(100)
	}
	
	fun stoprobot() {
		println(" %%%%%%% stoprobot %%%%%%%")
		moveRobot( robot!!, "h")			
		solveCheckGoal( robot!!,  "model( actuator, robot, state(stopped), direction(D), position(X,Y))" )
	}
	fun moveForward(  ) {
		println(" %%%%%%% moveForward %%%%%%%")
		moveRobot( robot!!, "w")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(movingForward), direction(D), position(X,Y))" )
 	}
	
	fun moveBackward(  ) {
		println(" %%%%%%% moveBackward %%%%%%%")
		moveRobot( robot!!, "s")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(movingBackward), direction(D), position(X,Y))" )
 	}

	 
	fun rotateLeft() {
		println(" %%%%%%% rotateLeft %%%%%%%")
		moveRobot( robot!!, "a")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(rotateLeft), direction(D), position(X,Y))" )
	}
	
	 
	fun rotateRight() {
		println(" %%%%%%% rotateRight %%%%%%%")
		moveRobot( robot!!, "d")
		solveCheckGoal(robot!!, "model(actuator,robot,state(rotateRight),direction(D), position(X,Y))")
	}
	
	fun moveForwardWithWall() {
		println(" %%%%%%% moveForwardWithWall %%%%%%%")
	 	GlobalScope.launch{
			//delay(1000)
 			println(" %%%%%%% SIMULATES OBSTACLE")
			robot!!.emit("sonarRobot", "sonarRobot(8)")
 		}
		delay(1000)
		solveCheckGoal( robot!!, "model( actuator, robot, state(stopped), _, _)" )
	}

	fun loadMapToString(filename: String): String{
		val mapStr: String = ""
		File(filename).useLines{mapStr.plus(it)}
		return mapStr
	}
	
	fun printRobotState() {
		robot!!.solve( "model( actuator, robot, S, _, _ )", "S"  )
		var state = "${robot!!.resVar}"
		robot!!.solve( "model( actuator, robot, _, D, _ )", "D"  )
		var direction = "${robot!!.resVar}"
		robot!!.solve( "model( actuator, robot, _, _, P )", "P"  )
		var position = "${robot!!.resVar}"
		
		println( "FINAL ROBOT STATE= [actuator, robot, "+state+","+direction+","+position+"]")
	}
	
	//some tests to check robot behaviour	
	@Test
	fun initialStateTest(){
		println("%%%%%%%%%%%%%% initialStateTest %%%%%%%%%%%%%%")
		solveCheckGoal( robot!!, "model( actuator, robot, state(stopped), direction(east), position(0,0))")
		printRobotState()
		
	}

	
	@Test
	fun moveTest() {
		println("%%%%%%%%%%%%%% moveTest  %%%%%%%%%%%%%%")
		
		rotateRight()
		delay(700)
		
		
		rotateLeft()
		delay(500)
		
		
		moveForward()
		delay(700)
		
		moveBackward()
		delay(700)
		
		stoprobot()
		
		solveCheckGoal( robot!!, "model( actuator, robot, state(stopped), direction(east), position(0,0))")
		printRobotState()
 	}
	
	@Test
	fun wallDetectingTest() {
		moveForward()	//no obstacle assumed
		moveForwardWithWall()
		
		solveCheckGoal( robot!!, "model( actuator, robot, state(stopped), _, _)" )
		printRobotState()
	}
	
	fun exploringAllTest(){		
		val currentMapStr = itunibo.planner.plannerUtil.getMap()		
		assertTrue(loadedMapStr == currentMapStr)
		
	}
	
}