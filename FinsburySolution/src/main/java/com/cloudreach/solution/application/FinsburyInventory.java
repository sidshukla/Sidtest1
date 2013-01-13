package com.cloudreach.solution.application;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.handler.BottomBrandBySoldHandler;
import com.cloudreach.solution.handler.BottomProductsBySoldHandler;
import com.cloudreach.solution.handler.SameTimeBuyProbabilityHandler;
import com.cloudreach.solution.handler.TopProductsByProfitHandler;
import com.cloudreach.solution.handler.TotalProfitHandler;
import com.cloudreach.solution.handler.RequestHandler;
import com.cloudreach.solution.handler.TopBrandBySoldHandler;
import com.cloudreach.solution.handler.TopProductsBySoldHandler;
import com.cloudreach.solution.handler.TotalSpendHandler;
import com.cloudreach.solution.metadata.StockItemMetadata;
import com.cloudreach.solution.metadata.TransactionMetadata;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.model.Transaction;
import com.cloudreach.solution.parser.FFTRFParser;
import com.cloudreach.solution.parser.WSSRFParser;
import com.cloudreach.solution.parser.impl.FFTRFParserImpl;
import com.cloudreach.solution.parser.impl.WSSRFParserImpl;

/*
 * Main class taking the files as input and prompting a command 
 * line selection for the user
 * 
 * @author siddharth
 * 
 */
public class FinsburyInventory {
	
	private WSSRFParser wssrfParser;
	private FFTRFParser fftrfParser;
	private Scanner scanner;
	
	private RequestHandler topProductBySoldHandler;
	private RequestHandler bottomProductBySoldHandler;
	private RequestHandler topBrandBySoldHandler;
	private RequestHandler bottomBrandsBySoldHandler;
	private RequestHandler topProductsByProfitHandler;
	private TotalProfitHandler profitHandler;
	private SameTimeBuyProbabilityHandler sameTimeBuyProbabilityHandler;
	private TotalSpendHandler totalSpendHandler;
	
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
		profitHandler = new TotalProfitHandler();
		sameTimeBuyProbabilityHandler = new SameTimeBuyProbabilityHandler();
		totalSpendHandler = new TotalSpendHandler();
		topProductsByProfitHandler = new TopProductsByProfitHandler();
	}
	
	
	/**
	 * Start method takes as input the paths to the WestBun XML file and the Finsbury Foods csv file
	 * It prompts a command line selection menu for the desired output
	 * 
	 *  @throws FinsburyApplicationException
	 */
	public void start() throws FinsburyApplicationException{
		
		boolean exit=false;
		
		System.out.println("========================");
		System.out.println("Please enter the path to the WSSRF file e.g.: /opt/temp/WSSRFFile.xml");
		String wssrfFile=scanner.nextLine();
		
		System.out.println("========================");
		System.out.println("Please enter the path to the FFTRFFile file e.g.: /opt/temp/FFTRFFile.csv");
		String fftrfFile=scanner.nextLine();
		/*
		 * Parse the input files and return a list of POJO objects
		 */
		List<StockItem> stockItems = wssrfParser.parseInputFile(wssrfFile);
		Map<String, Transaction> transactions =  fftrfParser.parseInputFile(fftrfFile);
		
		/*
		 * Perform data aggregation and selection based on the parsed information from the file
		 */
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
			System.out.println("6: Get probability that a customer will buy two products at the same time");
			System.out.println("7: Get total spend on WestBun produce");
			System.out.println("8: Get top n products by profit");
			System.out.println("9: Exit");
			
			int choice=scanner.nextInt();
			/*
			 * Delegate the control to appropriate handler class as per the selection to get the desired results
			 */
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
				Double totalProfit = profitHandler.processRequest(transactionMetadata, stockItemMetadata);
				System.out.println("-------------------------------");
				System.out.println("Total Profit from the sale is " + totalProfit );
				break;
			case 6:
				int probability = sameTimeBuyProbabilityHandler.processRequest(transactionMetadata);
				System.out.println("-------------------------------");
				System.out.println("The probability that a customer will buy two products at the same time is " + probability );
				break;
			case 7:
				Double totalSpend = totalSpendHandler.processRequest(transactionMetadata, stockItemMetadata);
				System.out.println("-------------------------------");
				System.out.println("Total spend on WestBun produce " + totalSpend );
				break;
			case 8:
				int requestSize5 = askInput(noOfTransaction);
				List<String> result5 = topProductsByProfitHandler.processRequest(stockItemMetadata , transactionMetadata , requestSize5);
				System.out.println("-------------------------------");
				System.out.println("Top " + requestSize5 +" product by profit are :");
				this.outputResult(result5);
				break;
			case 9:
				exit=true;
				System.out.println("***Good Bye***");
				continue;
			default:
				System.out.println("Invalid input. Please try again!!");
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
