package com.cloudreach.solution.parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import junit.framework.Assert;

import org.junit.Test;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.impl.FFTRFParserImpl;

public class FFTRFParserTest {

	private FFTRFParser fftrfParser = new FFTRFParserImpl();

	private final String FFTRFFILEPATH = Thread.currentThread()
			.getContextClassLoader()
			.getResource("TransactionfileParsingTest.csv").getPath();

	@Test
	public void testParseInputFileWithValidInput() throws Exception {
		List<Transaction> expectedResponse = new ArrayList<Transaction>();

		Transaction t1 = new Transaction();
		Transaction t2 = new Transaction();

		t1.setEan("0746817152012");
		t1.setQuantity(2);
		Calendar cal = DatatypeConverter.parseDateTime("2011-10-25T13:41:26Z");
		t1.setTransactionDate(cal.getTime());
		t1.setTransactionId(3432);
		t2.setEan("8014215020045");
		t2.setQuantity(1);
		Calendar cal1 = DatatypeConverter.parseDateTime("2011-10-25T13:43:05Z");
		t2.setTransactionDate(cal1.getTime());
		t2.setTransactionId(3433);

		expectedResponse.add(t1);
		expectedResponse.add(t2);
		Map<String, Transaction> actualResonse = fftrfParser.parseInputFile(FFTRFFILEPATH);
		List<Transaction> responseArray = new ArrayList<Transaction>(actualResonse.values());

		Assert.assertEquals(t2, responseArray.get(0));
		Assert.assertEquals(t1, responseArray.get(1));
	}

	@Test(expected = FinsburyApplicationException.class)
	public void testParseInputFileWithInValidInput() throws Exception {
		fftrfParser.parseInputFile("InvalidFilePath");
	}
}
