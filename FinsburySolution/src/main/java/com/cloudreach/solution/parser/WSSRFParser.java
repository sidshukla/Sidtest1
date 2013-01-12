package com.cloudreach.solution.parser;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.metadata.StockItemMetadata;

public interface WSSRFParser {
	
	public StockItemMetadata parseInputFile(String wssrfFile) throws FinsburyApplicationException;
}
