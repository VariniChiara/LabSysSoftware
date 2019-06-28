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
	var loadedMapStr: String? = null
	
	@Before
	@Throws(Exception::class)
	fun systemSetUp() {
		
		loadedMapStr = loadMapToString("testMap.txt")
		itunibo.planner.plannerUtil.loadMap(loadedMapStr)
		
		GlobalScope.launch{it.unibo.ddrSystem1.main()}
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
			MsgUtil.sendMsg("modelChange", "modelChange(robot, $move)", robot!!)
		}
		delay(100)
	}
	
	fun stoprobot() {
		println(" %%%%%%% stoprobot %%%%%%%")
		moveRobot( robot!!, "h")			
		solveCheckGoal( robot!!,  "model( actuator, robot, state(stopped), position(X,Y))" )
	}
	fun moveForward(  ) {
		println(" %%%%%%% moveForward %%%%%%%")
		moveRobot( robot!!, "w")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(movingForward), position(X,Y))" )
 	}
	
	fun moveBackward(  ) {
		println(" %%%%%%% moveBackward %%%%%%%")
		moveRobot( robot!!, "s")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(movingBackward), position(X,Y))" )
 	}

	 
	fun rotateLeft() {
		println(" %%%%%%% rotateLeft %%%%%%%")
		moveRobot( robot!!, "a")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(rotateLeft), position(X,Y))" )
	}
	
	 
	fun rotateRight() {
		println(" %%%%%%% rotateRight %%%%%%%")
		moveRobot( robot!!, "d")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(rotateRight), position(X,Y))" )
	}
	
	fun moveForwardWithWall() {
		println(" %%%%%%% moveForwardWithWall %%%%%%%")
	 		GlobalScope.launch{
				delay(1000)
 			    println(" %%%%%%% SIMULATES OBSTACLE")
				robot!!.emit("sonarRobot", "sonar(8)")
 			}
		moveRobot( robot!!, "w")			
		solveCheckGoal( robot!!, "model( actuator, robot, state(stopped), position(X,Y))" )
	}

	fun loadMapToString(filename: String): String{
		val mapStr: String = ""
		File(filename).useLines{mapStr.plus(it)}
		return mapStr
	}
	
	//some tests to check robot behaviour
	
	@Test
	fun initialStateTest(){
		println("%%%%%%%%%%%%%% initialStateTest %%%%%%%%%%%%%%")
		solveCheckGoal( robot!!, "model( actuator, robot, state(stopped), position(X,Y))")
		println( "FINAL ROBOT STATE = ${robot!!.resVar}")
		
	}
	
	@Test
	fun initialPositionTest(){
		println("%%%%%%%%%%%%%% initialStateTest %%%%%%%%%%%%%%")
		solveCheckGoal( robot!!, "model( actuator, robot, state(S), position(0,0))")
		println( "FINAL ROBOT STATE = ${robot!!.resVar}")
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
		
		robot!!.solve( "model( actuator, robot, state(STATE), position(0,0))", "STATE"  )
		println( "FINAL ROBOT STATE= ${robot!!.resVar}")
 	}
	
	@Test
	fun wallDetectingTest() {
		moveForward()	//no obstacle assumed
		moveForwardWithWall()
		robot!!.solve( "model( actuator, robot, state(STATE) )", "STATE"  )
		println( "FINAL ROBOT STATE= ${robot!!.resVar}")
	}
	
	fun exploringAllTest(){		
		val currentMapStr = itunibo.planner.plannerUtil.getMap()		
		assertTrue(loadedMapStr == currentMapStr)
		
	}
	
}