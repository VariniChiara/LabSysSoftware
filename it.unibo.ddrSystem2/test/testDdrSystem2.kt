import it.unibo.kactor.*

import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.*
import itunibo.planner.model.RobotState.Direction

import java.io.File


class TestDdrSystem2 {
	
	companion object{
		var startUpDone = false 
	}
	
	var console : ActorBasic? = null
	var robot : ActorBasic? = null
	
	@Before
	@Throws(Exception::class)
	fun systemSetUp() {
		
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

//	fun loadMapToString(filename: String): String{
//		val mapStr: String = ""
//		File(filename).forEachLine { println(it) }
//		return mapStr
//	}
	
	fun getRobotState(): String {
		val dir = itunibo.planner.plannerUtil.getDirection()
		val pos = getRobotPos()
		return dir+ ", "+pos 
	}
	
	fun getRobotPos(): String {
		val x = itunibo.planner.plannerUtil.getPosX()
		val y = itunibo.planner.plannerUtil.getPosY()
		return "("+x+","+y+")"
	}
	
	fun printRobotState() {
		var state = getRobotState()
		println( "ROBOT STATE= [actuator, robot,"+state+"]")
	}
	
	//some tests to check robot behaviour	
	//@Test
	fun initialStateTest(){
		println("%%%%%%%%%%%%%% initialStateTest %%%%%%%%%%%%%%")
		val state = getRobotState()
		assertTrue(state == "downDir, (0,0)")
		printRobotState()
	}

	//@Test
	fun cheGoalTest() {
		
		GlobalScope.launch{
 			console!!.forward("startTest", "startTest(1,1)", "robotmind")
 		}
		delay(10000)
		val pos = getRobotPos()
		assertTrue(pos == "(1,1)")
		
		printRobotState()
	}

	
	/*
 	Turn on the Virtual robot
 		Simulator config
 	floor: {
        size: { x: 35, y: 35 }
    },
    player: {
        position: { x: 0.075, y: 0.075 },		//INIT
        speed: 0.2
    },
	 */
	@Test
	fun finalMapTest() {
		
		val testRoomMap = """|r, 1, 1, 1, 1, 1, 1, 1, X,  
|1, 1, 1, 1, 1, 1, 1, 1, X, 
|1, 1, 1, 1, 1, 1, 1, 1, X, 
|1, 1, 1, 1, 1, 1, 1, 1, X, 
|1, 1, 1, 1, 1, 1, 1, 1, X, 
|1, 1, 1, 1, 1, 1, 1, 1, X, 
|1, 1, 1, 1, 1, 1, 1, 1, X, 
|1, 1, 1, 1, 1, 1, 1, 1, X, 
|X, X, X, X, X, X, X, X, X, """

	
		GlobalScope.launch{
 			console!!.forward("startCmd", "startCmd", "robotmind")
 		}
		delay(99000)
		
		println(testRoomMap)
		println(itunibo.planner.plannerUtil.getMap())
		assert(itunibo.planner.plannerUtil.getMap() == testRoomMap)
		
		
		
	}
	
}