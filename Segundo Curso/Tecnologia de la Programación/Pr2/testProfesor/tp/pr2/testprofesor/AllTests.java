package tp.pr2.testprofesor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class) 

@Suite.SuiteClasses( {  ItemTest.class, CommandTest.class , RoomTest.class, ParserTest.class, 
						PlayerTest.class, FoodTest.class, ValuableTest.class, DoorTest.class,
						KeyTest.class, MapTest.class, GameTest.class } ) 


public class AllTests {
    // Add new classes to the SuiteClasses array
}