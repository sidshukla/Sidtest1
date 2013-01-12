package com.cloudreach.solution.parser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.impl.FFTRFParserImpl;

public class FFTRFParserTest {
	
	private FFTRFParser fftrfParser = new FFTRFParserImpl();
	
	private final String FFTRFFILEPATH = "C:\\Users\\binny\\git\\Cloudreach\\FinsburySolution\\src\\test\\resources\\TransactionfileParsingTest.csv";
	
	@Test
	public void testParseInputFileWithValidInput() throws Exception{
		List<Transaction> expectedResponse = new ArrayList<Transaction>();
		
		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		expectedResponse.add(t1);
		t1.setEan("0746817152012");
		t1.setQuantity(2);
		t1.setTransactionDate(formatter.parse("2011-10-25 13:41:26"));
		t1.setTransactionId(3432);
		expectedResponse.add(t2);
		t2.setEan("8014215020045");
		t2.setQuantity(1);
		t2.setTransactionDate(formatter.parse("2011-10-25 13:43:05 "));
		t2.setTransactionId(3433);
		
		Map<String, Transaction> actualResonse = fftrfParser.parseInputFile(FFTRFFILEPATH);

		Assert.assertEquals(t1 , actualResonse.get("0746817152012"));
		Assert.assertEquals(t2 , actualResonse.get("8014215020045"));
	}

	@Test (expected = FinsburyApplicationException.class)
	public void testParseInputFileWithInValidInput() throws FinsburyApplicationException{
		fftrfParser.parseInputFile("InvalidFilePath");
	}
}
