package com.house.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.model.HouseDealInfoDto;
import com.house.model.NewsDto;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin("*")
@RequestMapping("/news")
@Api("News Crawling Controller v1")
public class CrawlingController {

	@GetMapping("/crawling")
	public ResponseEntity<?> getNewsFeed(){
		List<NewsDto> newsList = getStockPriceList();
		return new ResponseEntity<List<NewsDto> >(newsList, HttpStatus.OK);
	}

	public static List<NewsDto> getStockPriceList() {

	    final String stockList = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
	    final String url = "http://www.hapt.co.kr/news/articleList.html?view_type=sm";
	    Connection conn = Jsoup.connect(url);

	    try {
	      Document document = conn.get();
	      List<NewsDto> tbody = getStockList(document);   // 데이터 리스트
	      System.out.println(tbody);
	      return tbody;

	    } catch (IOException ignored) {
	    	return null;
	    }
	}

	  public static List<NewsDto> getStockList(Document document) {
	    Elements stockTableBody = document.select("ul.type2 li");
	    StringBuilder sb = new StringBuilder();
	    List<NewsDto> news = new ArrayList<NewsDto>();
	    
	    for (Element element : stockTableBody) {
	    	NewsDto dto = new NewsDto();
	    	dto.setTitle(element.select("li h4").text());
	    	dto.setUrl("http://www.hapt.co.kr"+element.select("li a").attr("href"));
	    	news.add(dto);
	    }
	    return news;
	  }
	
}
