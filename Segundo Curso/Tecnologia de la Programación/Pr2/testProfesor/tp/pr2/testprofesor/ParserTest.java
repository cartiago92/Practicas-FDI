package tp.pr2.testprofesor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tp.pr2.Command;
import tp.pr2.Directions;
import tp.pr2.Parser;
import tp.pr2.VerbCommands;

public class ParserTest {
	
	private String itemId = "testId";
	

	@Test
	public void testNextCommandWrongCommand() {
		Command c = Parser.nextCommand("MOVE");
		assertFalse("ERROR: A wrong command (MOVE) does not return an unknown command", c.isValid());
	}

	@Test
	public void testNextCommandHelp() {
		Command c = Parser.nextCommand("help");
		assertEquals("ERROR: String \"help\" does not return a HELP command", VerbCommands.HELP, c.getVerb());
		
		c = Parser.nextCommand("HELP");
		assertEquals("ERROR: String \"HELP\" does not return a HELP command", VerbCommands.HELP, c.getVerb());
		
		c = Parser.nextCommand("heLP");
		assertEquals("ERROR: String \"heLP\" does not return a HELP command", VerbCommands.HELP, c.getVerb());
	}
	
	@Test
	public void testNextCommandQuit() {
		Command c = Parser.nextCommand("quit");
		assertEquals("ERROR: String \"quit\" does not return a QUIT command", VerbCommands.QUIT, c.getVerb());
		
		c = Parser.nextCommand("QUIT");
		assertEquals("ERROR: String \"QUIT\" does not return a QUIT command", VerbCommands.QUIT, c.getVerb());
		
		c = Parser.nextCommand("QuIT");
		assertEquals("ERROR: String \"QuIT\" does not return a QUIT command", VerbCommands.QUIT, c.getVerb());
	}
	
	@Test
	public void testNextCommandGoWithoutDirection() {
		Command c = Parser.nextCommand("go");
		assertEquals("ERROR: String \"go\" does not return a GO command", VerbCommands.GO, c.getVerb());
		assertFalse("ERROR: String \"go\" returns a valid command but it has not any direction",c.isValid());
		
		c = Parser.nextCommand("GO");
		assertEquals("ERROR: String \"GO\" does not return a GO command", VerbCommands.GO, c.getVerb());
		assertFalse("ERROR: String \"GO\" returns a valid command but it has not any direction",c.isValid());
		
		c = Parser.nextCommand("Go");
		assertEquals("ERROR: String \"Go\" does not return a GO command", VerbCommands.GO, c.getVerb());
		assertFalse("ERROR: String \"Go\" returns a valid command but it has not any direction",c.isValid());
	}
	
	@Test
	public void testNextCommandGoWithWrongDirection() {
		Command c = Parser.nextCommand("go norte");
		assertFalse("ERROR: String \"go norte\" returns a valid command but the direction is not correct",c.isValid());
	}
	
	@Test
	public void testNextCommandGoWithCorrectDirection() {
		Command c = Parser.nextCommand("go north");
		assertTrue("ERROR: String \"go north\" is a valid command",c.isValid());
		assertEquals("ERROR: String \"go north\" has a direction different of NORTH", Directions.NORTH, c.getDirection());
	}
	
	@Test
	public void testNextCommandLookWithoutParam() {
		Command c = Parser.nextCommand("look");
		assertEquals("ERROR: String \"look\" does not return a LOOK command", VerbCommands.LOOK, c.getVerb());
		
		c = Parser.nextCommand("LOOK");
		assertEquals("ERROR: String \"LOOK\" does not return a LOOK command", VerbCommands.LOOK, c.getVerb());
		
		c = Parser.nextCommand("LooK");
		assertEquals("ERROR: String \"LooK\" does not return a LOOK command", VerbCommands.LOOK, c.getVerb());
	}
	
	@Test
	public void testNextCommandLookWithParam() {
		Command c = Parser.nextCommand("look "+itemId);
		assertEquals("ERROR: String \"look "+itemId+"\" does not return a LOOK command", VerbCommands.LOOK, c.getVerb());
		
		assertEquals("ERROR: String \"look "+itemId+"\" has an id different of "+itemId, itemId, c.getIdItem());
	}
	
	@Test
	public void testNextCommandExamineWithoutParam() {
		Command c = Parser.nextCommand("examine");
		assertEquals("ERROR: String \"examine\" does not return a EXAMINE command", VerbCommands.EXAMINE, c.getVerb());
		
		c = Parser.nextCommand("EXAMINE");
		assertEquals("ERROR: String \"EXAMINE\" does not return a EXAMINE command", VerbCommands.EXAMINE, c.getVerb());
		
		c = Parser.nextCommand("ExAmInE");
		assertEquals("ERROR: String \"ExAmInE\" does not return a EXAMINE command", VerbCommands.EXAMINE, c.getVerb());
	}
	
	@Test
	public void testNextCommandPickWithoutId() {
		Command c = Parser.nextCommand("pick");
		assertFalse("ERROR: String \"pick\" is not a valid command",c.isValid());
	}
	
	@Test
	public void testNextCommandPickCorrect() {
		Command c = Parser.nextCommand("pick "+itemId);
		assertTrue("ERROR: String \"pick "+itemId+"\" is a valid command",c.isValid());
		assertEquals("ERROR: String \"pick "+itemId+"\" has an id different of "+itemId, itemId, c.getIdItem());
	}	
	
	@Test
	public void testNextCommandUseWithoutId() {
		Command c = Parser.nextCommand("use");
		assertFalse("ERROR: String \"use\" is not a valid command",c.isValid());
	}
	
	@Test
	public void testNextCommandUseCorrect() {
		Command c = Parser.nextCommand("use "+itemId);
		assertTrue("ERROR: String \"use "+itemId+"\" is a valid command",c.isValid());
		assertEquals("ERROR: String \"use "+itemId+"\" has an id different of "+itemId, itemId, c.getIdItem());
	}	
	
	@Test
	public void testHelp() {
		String help = Parser.getHelp();
		help.toUpperCase();
		assertTrue("ERROR: getHelp returns a description which does not contain information about command GO", help.contains("GO"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command HELP", help.contains("HELP"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command QUIT", help.contains("QUIT"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command PICK", help.contains("PICK"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command LOOK", help.contains("LOOK"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command USE", help.contains("USE"));
		assertTrue("ERROR: getHelp returns a description which does not contain information about command EXAMINE", help.contains("EXAMINE"));
	}
}
