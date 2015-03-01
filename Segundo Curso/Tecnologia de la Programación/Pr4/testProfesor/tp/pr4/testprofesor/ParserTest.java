package tp.pr4.testprofesor;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tp.pr4.Game;
import tp.pr4.Parser;
import tp.pr4.commands.Command;
import tp.pr4.commands.DropCommand;
import tp.pr4.commands.ExamineCommand;
import tp.pr4.commands.GoCommand;
import tp.pr4.commands.HelpCommand;
import tp.pr4.commands.LookCommand;
import tp.pr4.commands.PickCommand;
import tp.pr4.commands.QuitCommand;
import tp.pr4.commands.UseCommand;
import tp.pr4.commands.exceptions.WrongCommandFormatException;

/*import tp.pr4.Command;
import tp.pr4.Directions;
import tp.pr4.Parser;
import tp.pr4.VerbCommands;
*/
public class ParserTest {
	
	private String itemId = "testId";
	private Game theGame;
	
	@Before
	public void setUp(){
		theGame = new MockGame(new MockMap (null));
	}
	

	@Test
	public void testparseCommandWrongCommand() {
		try {
			Parser.parseCommand("MOVE", theGame);
			fail("ERROR: parseCommand does not throw the exception with a wrong command");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}

	@Test
	public void testParseCommandHelp() {
		Command c;
		try {
			c = Parser.parseCommand("help", theGame);
			assertEquals("ERROR: String \"help\" does not return a HelpCommand", HelpCommand.class, c.getClass());
			c = Parser.parseCommand("ayuda", theGame);
			assertEquals("ERROR: String \"ayuda\" does not return a HelpCommand", HelpCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}

	@Test
	public void testParseCommandQuit() {
		Command c;
		try {
			c = Parser.parseCommand("quit", theGame);
			assertEquals("ERROR: String \"quit\" does not return a QuitCommand", QuitCommand.class, c.getClass());
			c = Parser.parseCommand("salir", theGame);
			assertEquals("ERROR: String \"salir\" does not return a QuitCommand", QuitCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}	
	@Test
	public void testParseCommandGoWithoutDirection() {
			Command c;
		try {
			c = Parser.parseCommand("go north", theGame);
			assertEquals("ERROR: String \"go north\" does not return a GoCommand", GoCommand.class, c.getClass());
			c = Parser.parseCommand("ir north", theGame);
			assertEquals("ERROR: String \"ir north\" does not return a GoCommand", GoCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}

	
	@Test
	public void testParseCommandLookWithoutParam() {
		Command c;
		try {
			c = Parser.parseCommand("look", theGame);
			assertEquals("ERROR: String \"look\" does not return a LookCommand", LookCommand.class, c.getClass());
			c = Parser.parseCommand("mira", theGame);
			assertEquals("ERROR: String \"mira\" does not return a LookCommand", LookCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}

	}

	@Test
	public void testParseCommandLookWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("look "+itemId, theGame);
			assertEquals("ERROR: String \"look "+itemId+"\" does not return a LookCommand", LookCommand.class, c.getClass());
			c = Parser.parseCommand("mira "+itemId, theGame);
			assertEquals("ERROR: String \"mira "+itemId+"\" does not return a LookCommand", LookCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}

	@Test
	public void testParseCommandExamine() {
		Command c;
		try {
			c = Parser.parseCommand("examine", theGame);
			assertEquals("ERROR: String \"examine\" does not return a ExamineCommand", ExamineCommand.class, c.getClass());
			c = Parser.parseCommand("examinar", theGame);
			assertEquals("ERROR: String \"examinar\" does not return a ExamineCommand", ExamineCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandPickWithoutId() {
		try {
			Parser.parseCommand("pick", theGame);
			fail("ERROR: parseCommand does not throw the exception with a wrong pick command (\"pick\" without id)");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandPickWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("pick "+itemId, theGame);
			assertEquals("ERROR: String \"pick "+itemId+"\" does not return a PickCommand", PickCommand.class, c.getClass());
			c = Parser.parseCommand("coger "+itemId, theGame);
			assertEquals("ERROR: String \"coger "+itemId+"\" does not return a PickCommand", PickCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandDropWithoutId() {
		try {
			Parser.parseCommand("drop", theGame);
			fail("ERROR: parseCommand does not throw the exception with a wrong drop command (\"drop\" without id)");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandDropWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("drop "+itemId, theGame);
			assertEquals("ERROR: String \"drop "+itemId+"\" does not return a DropCommand", DropCommand.class, c.getClass());
			c = Parser.parseCommand("soltar "+itemId, theGame);
			assertEquals("ERROR: String \"soltar "+itemId+"\" does not return a DropCommand", DropCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	@Test
	public void testParseCommandUseWithoutId() {
		try {
			Parser.parseCommand("use", theGame);
			fail("ERROR: parseCommand does not throw the exception with a wrong use command string(\"use\" without id)");
		} 
		catch (WrongCommandFormatException e) {
		}
		catch (Exception e) {
			fail("ERROR: parseCommand does not throw a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testParseCommandUseWithParam() {
		Command c;
		try {
			c = Parser.parseCommand("use "+itemId, theGame);
			assertEquals("ERROR: String \"use "+itemId+"\" does not return a UseCommand", UseCommand.class, c.getClass());
			c = Parser.parseCommand("usar "+itemId, theGame);
			assertEquals("ERROR: String \"usar "+itemId+"\" does not return a UseCommand", UseCommand.class, c.getClass());
		} catch (WrongCommandFormatException e) {
			fail("ERROR: a correct string throws a WrongCommandFormatException");
		}
	}
	
	@Test
	public void testHelpEnglish() {
		String help = Parser.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain information about command GO", help.contains("GO"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command HELP", help.contains("HELP"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command QUIT", help.contains("QUIT"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command PICK", help.contains("PICK"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command DROP", help.contains("DROP"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command LOOK", help.contains("LOOK"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command USE", help.contains("USE"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command EXAMINE", help.contains("EXAMINE"));
	}

	@Test
	public void testHelpSpanish() {
		String help = Parser.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain information about command IR", help.contains("IR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command AYUDA", help.contains("AYUDA"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command SALIR", help.contains("SALIR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command COGER", help.contains("COGER"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command SOLTAR", help.contains("SOLTAR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command MIRA", help.contains("MIRA"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command USAR", help.contains("USAR"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command EXAMINAR", help.contains("EXAMINAR"));
	}
}
