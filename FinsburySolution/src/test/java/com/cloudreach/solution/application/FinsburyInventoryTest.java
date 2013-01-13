package com.cloudreach.solution.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;

import com.cloudreach.solution.handler.TopProductsBySoldHandler;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.parser.FFTRFParser;
import com.cloudreach.solution.parser.WSSRFParser;

public class FinsburyInventoryTest {
	
	private Scanner scanner;
	
	@Mock
	private WSSRFParser wssrfParser;
	
	@Mock
	private FFTRFParser fftrfParser;
	
	@Mock
	private TopProductsBySoldHandler productsBySoldHandler;
	
	@InjectMocks
	private FinsburyInventory finsburyInventory = new FinsburyInventory();
	
	private final String WSSRFFILE = "mock";
	
	private final String FFTRFFILE = "mock";

	
	public void testStartTestTopBySoldHandlerCall() throws Exception{
		PowerMockito.mockStatic(Scanner.class);
		
		//when(wssrfParser.parseInputFile(eq(WSSRFFILE))).thenReturn(mock(StockItemMetadata.class));
		//when(fftrfParser.parseInputFile(eq(FFTRFFILE))).thenReturn(mock(TransactionMetadata.class));
		when(scanner.nextInt()).thenReturn(1);
		
		finsburyInventory.start();
		verify(productsBySoldHandler.processRequest(mock(StockItemMetadata.class), mock(TransactionMetadata.class), 1));
		
	}
}
