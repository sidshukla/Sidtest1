package com.cloudreach.solution.hanlder;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cloudreach.solution.handler.TopProductsBySoldHandler;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.impl.FFTRFParserImpl;
import com.cloudreach.solution.parser.impl.WSSRFParserImpl;

public class TopProductsBySoldHandlerTest {
	
	TopProductsBySoldHandler topProductsBySoldHandler = new TopProductsBySoldHandler();
	
	private static final String WSSRFFILEPATH = Thread.currentThread().getContextClassLoader().getResource("WSSRFfileParsingTest.xml").getPath();
	private static final String FFTRFFILEPATH = Thread.currentThread().getContextClassLoader().getResource("TransactionfileParsingTest.csv").getPath();
	
	private static StockItemMetadata stockItemMetadata = new StockItemMetadata();
	private static TransactionMetadata transactionMetadata = new TransactionMetadata();
	
	
	@BeforeClass
	public static void setup() throws Exception{
		WSSRFParserImpl parserImpl = new WSSRFParserImpl();
		FFTRFParserImpl fftrfParserImpl = new FFTRFParserImpl();
		List<StockItem> stockItems = parserImpl.parseInputFile(WSSRFFILEPATH);
		stockItemMetadata.calculateMetadata(stockItems);
		Map<String, Transaction> transactions = fftrfParserImpl.parseInputFile(FFTRFFILEPATH);
		stockItemMetadata.calculateMetadata(stockItems);
		transactionMetadata.calculateMetadata(transactions , stockItemMetadata.getStockItemEAMMapping());
	}
	
	@Test
	public void testProcesRequest(){
		
		List<String> topProducts = topProductsBySoldHandler.processRequest(stockItemMetadata, transactionMetadata, 2);
		
		Assert.assertEquals("hair gel - extra strong", topProducts.get(0));
		Assert.assertEquals("Naturale Italian mineral water", topProducts.get(1));
	}

}
