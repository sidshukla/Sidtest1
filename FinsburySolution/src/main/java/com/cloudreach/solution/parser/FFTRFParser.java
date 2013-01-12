package com.cloudreach.solution.parser;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;


public interface FFTRFParser {

	TransactionMetadata parseInputFile(String fftrfFile) throws FinsburyApplicationException;
	
	void setStockItemMetadata(StockItemMetadata stockItemMetadata);
}
