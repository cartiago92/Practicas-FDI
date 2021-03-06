package tp.pr4.testprofesor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tp.pr4.testprofesor.maploader.*;
import tp.pr4.testprofesor.commands.*;
import tp.pr4.testprofesor.items.*;



@RunWith(Suite.class) 

@Suite.SuiteClasses( {  ItemTest.class,  
						FoodTest.class, 
						ValuableTest.class, 
						KeyTest.class,
						RoomTest.class,					
						DoorTest.class,
						MapTest.class,
						PlayerTest.class,
						GoCommandTest.class ,
						QuitCommandTest.class ,
						LookCommandTest.class ,
						ExamineCommandTest.class ,
						PickCommandTest.class ,
						DropCommandTest.class ,
						HelpCommandTest.class,
						UseCommandTest.class,
						ParserTest.class,	
						GameTest.class,
						MapLoaderTest.class
						} ) 


public class AllTests {
    // Add new classes to the SuiteClasses array
}
