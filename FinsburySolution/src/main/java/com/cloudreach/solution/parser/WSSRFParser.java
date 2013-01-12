package com.cloudreach.solution.parser;

import java.util.List;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.model.StockItem;

public interface WSSRFParser {
	
	public List<StockItem> parseInputFile(String wssrfFilePath) throws FinsburyApplicationException;
}
