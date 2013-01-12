package com.cloudreach.solution.parser;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.parser.impl.WSSRFParserImpl;

public class WSSRFParserTest {

	private WSSRFParser wssrfParser = new WSSRFParserImpl();

	private final String WSSRFFILEPATH = Thread.currentThread().getContextClassLoader().getResource("WSSRFfileParsingTest.xml").getPath();

	@Test
	public void testParseInputFileWithValidInput()
			throws FinsburyApplicationException {
		List<StockItem> expectedResponse = new ArrayList<StockItem>();

		StockItem t1 = new StockItem();
		StockItem t2 = new StockItem();

		expectedResponse.add(t1);
		t1.setBrandName("V05");
		t1.setBatchSize(24);
		t1.setEan("0746817152012");
		t1.setProductName("hair gel - extra strong");
		t1.setQuantity("150ml");
		t1.setSellingPrice(6.99);
		t1.setWholesalePrice(4.30);

		expectedResponse.add(t2);
		t2.setBrandName("Alisea");
		t2.setBatchSize(20);
		t2.setEan("8014215020045");
		t2.setProductName("Naturale Italian mineral water");
		t2.setQuantity("50cl");
		t2.setSellingPrice(2.10);
		t2.setWholesalePrice(1.40);

		StockItemMetadata actualResonse = wssrfParser
				.parseInputFile(WSSRFFILEPATH);

		Assert.assertEquals(expectedResponse.get(0), actualResonse.getStockItems().get(0));
		Assert.assertEquals(expectedResponse.get(1), actualResonse.getStockItems().get(1));
	}

	@Test(expected = FinsburyApplicationException.class)
	public void testParseInputFileWithInValidInput()
			throws FinsburyApplicationException {
		wssrfParser.parseInputFile("InvalidFilePath");
	}

}
