package org.shz.katas.gameoflife.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


import org.netbeans.jemmy.*;
//import org.netbeans.jemmy.explorer.*;
import org.netbeans.jemmy.operators.*;

import org.junit.Test;

public class GuiTest {

	@Test
	public void shouldFindApplicationWindow() throws Exception {
		new ClassReference(new org.shz.katas.gameoflife.model.GameOfLifeGui()).startApplication();
		JFrameOperator mainFrame = new JFrameOperator("Game of Life");
		assertThat(mainFrame,notNullValue());
	}

}
