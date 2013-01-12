package com.cloudreach.solution.parser;

import java.util.Map;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.model.Transaction;


public interface FFTRFParser {

	Map<String, Transaction> parseInputFile(String fftrfFile) throws FinsburyApplicationException;
}
