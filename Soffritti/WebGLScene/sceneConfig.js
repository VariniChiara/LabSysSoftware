const config = {
  floor: {
      size: { x: 30, y: 24                   }
  },
  player: {
      //position: { x: 0.5, y: 0.5 },		//CENTER
      position: { x: 0.1, y: 0.16 },		//INIT
      //position: { x: 0.8, y: 0.85 },		//END
      speed: 0.2
  },
    /*floor: {
        size: { x: 50, y: 35 }
    },
    player: {
        //position: { x: 0.5, y: 0.5 },		//CENTER
        position: { x: 0.15, y: 0.15 },		//INIT
    	// position: { x: 0.2, y: 0.2 },
        //position: { x: 0.8, y: 0.85 },		//END
        speed: 0.2
    },*/
    sonars: [
//        {
//            name: "sonar1",
//            position: { x: 0.064, y: 0.05 },
//            senseAxis: { x: false, y: true }
//        },
//        {
//            name: "sonar2",
//            position: { x: 0.94, y: 0.88},
//            senseAxis: { x: true, y: false }
//        }
     ],
    movingObstacles: [
       // {
       //     name: "moving-obstacle-1",
       //     position: { x: .5, y: .4 },
       //     directionAxis: { x: true, y: false },
       //     speed: 1,
       //     range: 8
       // }
       // {
       //     name: "moving-obstacle-2",
       //     position: { x: .5, y: .2 },
       //     directionAxis: { x: true, y: true },
       //     speed: 2,
       //     range: 2
       // }
    ],
    staticObstacles: [
//        {
//            name: "wall",
//            centerPosition: { x: 0.5, y: 0.9},
//            size: { x: 0.01, y: 0.01}
//        },
        {
        name: "wallUp",
        centerPosition: { x: 0.5, y: 1},
        size: { x: 1, y: 0.01}
        },
         {
            name: "wallDown",
            centerPosition: { x: 0.5, y: 0},
            size: { x: 1, y: 0.01}
        },
       {
            name: "wallLeft",
            centerPosition: { x: 0, y: 0.5},
            size: { x: 0.01, y: 1}
        },
        {
            name: "wallRight",
            centerPosition: { x: 1, y: 0.5},
            size: { x: 0.01, y: 1}
        },
       {
           name: "obstacle",
           centerPosition: {x: 0.06, y: 0.6},
           size: { x: 0.10, y: 0.10}
       },
        {
            name: "obstacle2",
            centerPosition: {x: 0.4, y: 0.4},
            size: { x: 0.10, y: 0.10}
        }
    ]
}

export default config;
