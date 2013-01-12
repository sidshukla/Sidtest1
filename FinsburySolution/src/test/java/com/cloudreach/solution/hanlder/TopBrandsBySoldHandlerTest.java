package com.cloudreach.solution.hanlder;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cloudreach.solution.handler.TopBrandBySoldHandler;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.parser.impl.FFTRFParserImpl;
import com.cloudreach.solution.parser.impl.WSSRFParserImpl;

public class TopBrandsBySoldHandlerTest {
	TopBrandBySoldHandler topBrandBySoldHandler = new TopBrandBySoldHandler();

	private static final String WSSRFFILEPATH = Thread.currentThread()
			.getContextClassLoader().getResource("WSSRFfileParsingTest.xml")
			.getPath();
	private static final String FFTRFFILEPATH = Thread.currentThread()
			.getContextClassLoader()
			.getResource("TransactionfileParsingTest.csv").getPath();

	private static StockItemMetadata stockItemMetadata;
	private static TransactionMetadata transactionMetadata;

	@BeforeClass
	public static void setup() throws Exception {
		WSSRFParserImpl parserImpl = new WSSRFParserImpl();
		FFTRFParserImpl fftrfParserImpl = new FFTRFParserImpl();
		stockItemMetadata = parserImpl.parseInputFile(WSSRFFILEPATH);
		fftrfParserImpl.setStockItemMetadata(stockItemMetadata);
		transactionMetadata = fftrfParserImpl.parseInputFile(FFTRFFILEPATH);
	}

	@Test
	public void testProcesRequest() {

		List<String> topProducts = topBrandBySoldHandler.processRequest(stockItemMetadata, transactionMetadata, 2);

		Assert.assertEquals("V05", topProducts.get(0));
		Assert.assertEquals("Alisea",
				topProducts.get(1));
	}
}
