package com.cloudreach.solution.application;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.handler.BottomBrandBySoldHandler;
import com.cloudreach.solution.handler.BottomProductsBySoldHandler;
import com.cloudreach.solution.handler.RequestHandler;
import com.cloudreach.solution.handler.TopBrandBySoldHandler;
import com.cloudreach.solution.handler.TopProductsBySoldHandler;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.FFTRFParser;
import com.cloudreach.solution.parser.WSSRFParser;
import com.cloudreach.solution.parser.impl.FFTRFParserImpl;
import com.cloudreach.solution.parser.impl.WSSRFParserImpl;

public class FinsburyInventory {
	
	private WSSRFParser wssrfParser;
	private FFTRFParser fftrfParser;
	private Scanner scanner;
	
	private RequestHandler topProductBySoldHandler;
	private RequestHandler bottomProductBySoldHandler;
	private RequestHandler topBrandBySoldHandler;
	private RequestHandler bottomBrandsBySoldHandler;
	
	private StockItemMetadata stockItemMetadata;
	private TransactionMetadata transactionMetadata;
	
	public FinsburyInventory() {
		wssrfParser = new WSSRFParserImpl();
		fftrfParser = new FFTRFParserImpl();
		
		stockItemMetadata = new StockItemMetadata();
		transactionMetadata = new TransactionMetadata();
		
		scanner=new Scanner(System.in);
		
		topProductBySoldHandler = new TopProductsBySoldHandler();
		bottomProductBySoldHandler = new BottomProductsBySoldHandler();
		topBrandBySoldHandler = new TopBrandBySoldHandler();
		bottomBrandsBySoldHandler = new BottomBrandBySoldHandler();
	}
	
	public void start(String wssrfFile , String fftrfFile) throws FinsburyApplicationException{
		
		boolean exit=false;
		
		List<StockItem> stockItems = wssrfParser.parseInputFile(wssrfFile);
		Map<String, Transaction> transactions =  fftrfParser.parseInputFile(fftrfFile);
		stockItemMetadata.calculateMetadata(stockItems);
		transactionMetadata.calculateMetadata(transactions , stockItemMetadata.getStockItemEAMMapping());
		
		int noOfTransaction = transactionMetadata.getTransactionMap().size();
		
		
		while(!exit){
			System.out.println("========================");
			System.out.println("Please input the request");
			System.out.println("1: Get top n products by numbers sold");
			System.out.println("2: Get bottom n products by numbers sold");
			System.out.println("3: Get top n brands by numbers sold");
			System.out.println("4: Get bottom n brands by numbers sold");
			System.out.println("5: Get total profit");
			System.out.println("6: Exit");
			
			int choice=scanner.nextInt();
			switch(choice){
			case 1:
				
				int requestSize = askInput(noOfTransaction);
				List<String> result1 = topProductBySoldHandler.processRequest(stockItemMetadata , transactionMetadata , requestSize);
				System.out.println("-------------------------------");
				System.out.println("Top " + requestSize +" product by number sold are :");
				this.outputResult(result1);
				break;
				
			case 2:
				int requestSize2 = askInput(noOfTransaction);
				List<String> result2 = bottomProductBySoldHandler.processRequest(stockItemMetadata , transactionMetadata , requestSize2);
				System.out.println("-------------------------------");
				System.out.println("Bottom " + requestSize2 +" product by number sold are :");
				this.outputResult(result2);
				break;
				
			case 3:
				int requestSize3 = askInput(transactionMetadata.getSortedBrandQuantitySoldList().size());
				List<String> result3 = topBrandBySoldHandler.processRequest(stockItemMetadata , transactionMetadata , requestSize3);
				System.out.println("-------------------------------");
				System.out.println("Top " + requestSize3 +" brands by number sold are :");
				this.outputResult(result3);
				break;
			case 4:
				int requestSize4 = askInput(transactionMetadata.getSortedBrandQuantitySoldList().size());
				List<String> result4 = bottomBrandsBySoldHandler.processRequest(stockItemMetadata , transactionMetadata , requestSize4);
				System.out.println("-------------------------------");
				System.out.println("Bottom " + requestSize4 +" brands by number sold are :");
				this.outputResult(result4);
				break;
			case 5:
				System.out.println("Not yet implemented");
			case 6:
				System.out.println("Not yet implemented");
			case 7:
				exit=true;
				continue;
			default:
				System.out.println("Invalid input");
			}		
		}
	}

	private void outputResult(List<String> result) {
		System.out.println("-------------------------------");
		for(String item : result){
			System.out.println(item);
		}
	}
	
	private int askInput(int maxValue){
		System.out.println("Input value of n between 1 and " + maxValue + " :");
		int requestSize=scanner.nextInt();
		while(!((requestSize>0)&&(requestSize<=maxValue))){
			System.out.println("Invalid input , please try again !!");
			requestSize=scanner.nextInt();
		}
		return requestSize;
	}
}
