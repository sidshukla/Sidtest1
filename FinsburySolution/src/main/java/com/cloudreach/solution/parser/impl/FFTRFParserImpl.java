package com.cloudreach.solution.parser.impl;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import au.com.bytecode.opencsv.CSVReader;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.Brand;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.FFTRFParser;

public class FFTRFParserImpl implements FFTRFParser {

	private StockItemMetadata stockItemMetadata;

	public TransactionMetadata parseInputFile(String fftrfFile)
			throws FinsburyApplicationException {
		TransactionMetadata transactionMetadata = new TransactionMetadata();
		
		Map<String, Transaction> transactionMap = new HashMap<String, Transaction>();
		Map<String, StockItem> stockItemMap = stockItemMetadata.getStockItemEAMMapping();
		Map<String , Brand> brandQuantitySoldTempMap = new HashMap<String, Brand>();
		
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(fftrfFile));
			String[] row = null;
			while ((row = csvReader.readNext()) != null) {
				String brandName = stockItemMap.get(row[2]).getBrandName();

				if (transactionMap.containsKey(row[2])) {
					Transaction transaction = transactionMap.get(row[2]);
					transaction.setQuantity(transaction.getQuantity()
							+ Integer.parseInt(row[3]));
				} else {
					Transaction transaction = new Transaction();
					transaction.setEan(row[2]);
					transaction.setQuantity(Integer.parseInt(row[3]));
					transaction.setTransactionDate(DatatypeConverter.parseDateTime(row[0]).getTime());
					transaction.setTransactionId(Long.parseLong(row[1]));
					transaction.setBrandName(brandName);
					transaction.setProductName(stockItemMap.get(row[2]).getProductName());

					transactionMap.put(row[2], transaction);
				}
				
				
				if(brandQuantitySoldTempMap.containsKey(brandName)){
					Brand localBrandName = brandQuantitySoldTempMap.get(brandName);
					localBrandName.setQuantity(localBrandName.getQuantity() + Integer.parseInt(row[3]));
				}else{
					Brand brand = new Brand();
					brand.setBrand(brandName);
					brand.setQuantity(Integer.parseInt(row[3]));
					brandQuantitySoldTempMap.put(brandName, brand);
				}
			}
			csvReader.close();
		} catch (Exception e) {
			throw new FinsburyApplicationException(e.getMessage());
		}
		
		transactionMetadata.setTransactionMap(transactionMap);
		
		List<Transaction> transactions = new ArrayList<Transaction>(transactionMap.values());
		Collections.sort(transactions);
		transactionMetadata.setSortedTransactionOnSold(transactions);
		List<Brand> sortedBrands = new ArrayList<Brand>(brandQuantitySoldTempMap.values());
		Collections.sort(sortedBrands);
		transactionMetadata.setSortedBrandQuantitySoldList(sortedBrands);
		
		return transactionMetadata;
	}

	@Override
	public void setStockItemMetadata(StockItemMetadata stockItemMetadata) {
		this.stockItemMetadata = stockItemMetadata;
	}
}
