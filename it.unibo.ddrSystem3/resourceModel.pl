%===============================================================
%resourceModel.pl
%===============================================================

 
model( actuator, robot, state(stopped), direction(south), position(0,0)). %% initial state
model( sensor,   sonarRobot, state(unknown) ).   %% initial state


action(robot, move(w)) :- changeModel( actuator, robot, movingForward  ).
action(robot, move(s)) :- changeModel( actuator, robot, movingBackward ).
action(robot, move(a)) :- changeModel( actuator, robot, rotateLeft     ).
action(robot, move(d)) :- changeModel( actuator, robot, rotateRight    ).
action(robot, move(h)) :- changeModel( actuator, robot, stopped        ).

action(sonarRobot, V)  :- changeModel( sensor, sonarRobot, V  ).

changeModel( CATEG, robot, VALUE) :-
   update_pos(VALUE, ND, NP),
   replaceRule( model(C, N, _, _, _),  model(C, N, state(VALUE), ND, NP) ),
   showResourceModel1.	%% at each change, show the model
  
changeModel( CATEG, sonarRobot, VALUE ) :-
	replaceRule( model(CATEG,sonarRobot,_),  model(CATEG,sonarRobot,state(VALUE)) ).
%% showResourceModel.	%% at each change, show the model

update_pos(stopped, D, P):-	model(_,_,_,D, P),!.

%movingForward and movingBackward
update_pos(M, direction(D), NP):-	model(_,_,_,direction(D),_), find_new_pos(M, D, NP),!.
%update_pos(movingForward, direction(D), pNP):-	model(_,_,_,direction(D),_), new_pos_dir(movingForward, D, NP).
%update_pos(movingBackward, direction(D), NP):-	model(_,_,_,dir(D),_), new_pos_dir(movingBackward, D, NP).

%rotateLeft and rotateRight
update_pos(R, direction(ND), NP):-	model(_,_,_,direction(D),NP),  new_dir(R,D, ND),!.
%update_pos(rotateLeft, direction(ND), NP):-	model(_,_,_,dir(D),NP),  new_dir(rotateLeft,D, ND).
%update_pos(rotateRight, direction(ND), NP):-	model(_,_,_,dir(D),NP), new_dir(rotateRight,D, ND).

new_dir(rotateLeft, east, north).
new_dir(rotateLeft, south, east).
new_dir(rotateLeft, west, south).
new_dir(rotateLeft, notrh, west).

new_dir(rotateRight, east, south).
new_dir(rotateRight, south, west).
new_dir(rotateRight, west, notrh).
new_dir(rotateRight, notrh, east).

find_new_pos(movingForward, east, NP):- calculate_pos(1, 0, NP).
find_new_pos(movingForward, south, NP):- calculate_pos(0, 1, NP).
find_new_pos(movingForward, west, NP):- calculate_pos(-1, 0, NP).
find_new_pos(movingForward, notrh, NP):- calculate_pos(0, -1, NP).

find_new_pos(movingBackward, east, NP):- calculate_pos(-1, 0, NP).
find_new_pos(movingBackward, south, NP):- calculate_pos(0, -1, NP).
find_new_pos(movingBackward, west, NP):- calculate_pos(1, 0, NP).
find_new_pos(movingBackward, notrh, NP):- calculate_pos(0, 1, NP).

calculate_pos(A, B, position(C, D)):- model(_, _, _, _, position(X,Y)), C is A+X, D is B+Y.


%%replaceRule
replaceRule( Rule, NewRule ):-
	removeRule( Rule ),addRule( NewRule ).
	
addRule( Rule ):-
	%%output( addRule( Rule ) ),
	assert( Rule ).
removeRule( Rule ):-
	retract( Rule ),
	%%output( removedFact(Rule) ),
	!.
removeRule( A  ):- 
	%%output( remove(A) ),
	retract( A :- B ),!.
removeRule( _  ).

%print state
showResourceModel :- 
	stdout <- print("RESOURCE MODEL: [ "),
	model( CATEG, NAME, STATE, D, P ),
 	stdout <- print( model( CATEG, NAME, STATE, D, P ) ),
	stdout <- println(" ]").

showResourceModel1 :-
	print("RESOURCE MODEL: [ "),
	model( CATEG, NAME, STATE, D, P ),
 	print( model( CATEG, NAME, STATE, D, P ) ),
	print(" ]").
 			
output( M ) :- stdout <- println( M ).

initResourceTheory :- output("resourceModel loaded").
:- initialization(initResourceTheory).
		