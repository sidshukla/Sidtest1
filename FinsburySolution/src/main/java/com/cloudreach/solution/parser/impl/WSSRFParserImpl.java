package com.cloudreach.solution.parser.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cloudreach.solution.exception.FinsburyApplicationException;
import com.cloudreach.solution.model.StockItem;
import com.cloudreach.solution.parser.WSSRFParser;

public class WSSRFParserImpl implements WSSRFParser {
	
	public List<StockItem> parseInputFile(String wssrfFile) throws FinsburyApplicationException{

			List<StockItem> stockItems = new ArrayList<StockItem>();
			try{
				
				File file=new File(wssrfFile);
				
				DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		        Document document = documentBuilder.parse(file);
	            NodeList nodes = document.getElementsByTagName("line");
	            
	            for(int i=0;i<nodes.getLength();i++){
	            	StockItem stockItem = new StockItem();
	            	
	            	Element element = (Element) nodes.item( i );
	            	String ean = element.getAttribute( "ean" ).toString();
	                stockItem.setEan(ean);
	                stockItem.setBrandName(element.getElementsByTagName("brand").item(0).getTextContent());
	                stockItem.setProductName(element.getElementsByTagName("name").item(0).getTextContent());
	                stockItem.setQuantity(element.getElementsByTagName("quantity").item(0).getTextContent());
	                stockItem.setSellingPrice(Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent()));
	                stockItem.setWholesalePrice(Double.parseDouble(element.getElementsByTagName("price").item(1).getTextContent()));
	                stockItem.setBatchSize(Integer.parseInt(element.getElementsByTagName("batchsize").item(0).getTextContent()));
	                
	                stockItems.add(stockItem);
	            }
			}
			catch(Exception e){
				throw new FinsburyApplicationException(e.getMessage());
			}
			return stockItems;
	}
}
