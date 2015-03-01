package tp.pr4.testprofesor;
import tp.pr4.items.Item;
import tp.pr4.Player;
import tp.pr4.Room;


public class MockItem extends Item {

	public MockItem(String id, String description) {
		super(id, description);
	}

	@Override
	public boolean use(Player who, Room where) {
		return false;
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}

}
