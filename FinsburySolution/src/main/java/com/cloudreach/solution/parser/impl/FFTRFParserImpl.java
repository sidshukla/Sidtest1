package com.cloudreach.solution.parser.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import au.com.bytecode.opencsv.CSVReader;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.FFTRFParser;

public class FFTRFParserImpl implements FFTRFParser {

	public Map<String, Transaction> parseInputFile(String fftrfFile)
			throws FinsburyApplicationException {
		CSVReader csvReader = null;
		Map<String, Transaction> transactionMap = new HashMap<String, Transaction>();
		try {
			csvReader = new CSVReader(new FileReader(fftrfFile));
			String[] row = null;
			while ((row = csvReader.readNext()) != null) {

				if (transactionMap.containsKey(row[2])) {
					Transaction transaction = transactionMap.get(row[2]);
					transaction.setQuantity(transaction.getQuantity()
							+ Integer.parseInt(row[3]));
					transactionMap.put(row[2], transaction);
				} else {
					Transaction transaction = new Transaction();
					transaction.setEan(row[2]);
					transaction.setQuantity(Integer.parseInt(row[3]));
					Calendar cal = DatatypeConverter.parseDateTime(row[0]);
					transaction.setTransactionDate(cal.getTime());
					transaction.setTransactionId(Long.parseLong(row[1]));

					transactionMap.put(row[2], transaction);
				}
			}

		} catch (Exception e) {
			throw new FinsburyApplicationException(e.getMessage());
		} finally {
			try {
				csvReader.close();
			} catch (IOException e) {
				throw new FinsburyApplicationException(e.getMessage());
			}
		}
		return transactionMap;
	}
}
