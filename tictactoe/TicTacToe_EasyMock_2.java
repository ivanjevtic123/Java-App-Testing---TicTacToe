package tictactoe;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TicTacToe_EasyMock_2 {

	TicTacToeMain tic;
	TicTacToeAI mock;

    @Before
    public void setUp() throws Exception {
        mock = createNiceMock(TicTacToeAI.class);
        tic = new TicTacToeMain();
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNegativeIndexOutOfBounds() {
    	tic.playButton.doClick();
		tic.click(-1, -1);
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPositiveIndexOutOfBounds() {
    	tic.playButton.doClick();
		tic.click(20, 20);
    }
	
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPositiveNegativeIndexOutOfBounds() {
    	tic.playButton.doClick();
		tic.click(-1, 20);
    }
    
    
    
}







