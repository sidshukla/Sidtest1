package com.cloudreach.solution.hanlder;

import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cloudreach.solution.handler.TotalProfitHandler;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.impl.FFTRFParserImpl;
import com.cloudreach.solution.parser.impl.WSSRFParserImpl;

public class TotalProfitHandlerTest {
	
	TotalProfitHandler totalProfitHandler = new TotalProfitHandler();
	
	private static final String WSSRFFILEPATH = Thread.currentThread()
			.getContextClassLoader().getResource("WSSRFTestFile.xml")
			.getPath();
	private static final String FFTRFFILEPATH = Thread.currentThread()
			.getContextClassLoader()
			.getResource("TransactionTestFile.csv").getPath();

	
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
		Double totalProfit = totalProfitHandler.processRequest(transactionMetadata, stockItemMetadata);
		Assert.assertEquals(-133.64999999999998,totalProfit);
	}

}
