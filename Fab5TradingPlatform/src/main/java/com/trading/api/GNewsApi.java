package com.trading.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.trading.pojos.News;

public class GNewsApi {

	private static final String newsUrl = "https://gnewsapi.net/api/search?q=stocks&country=in&language=en&api_token=CHhUui3tTUVQ46PyCBdVovBUxsuMChRzwkXQODovGX8jK7WPqMHtncIC9IKP";
	
	public GNewsApi() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static List<News> loadLatestNews() {
		String jsonResponse = "";//JSON Response from the GNews REST Service
		List<News> list = new ArrayList<News>();
		try
		{
			//Create a HttpUrlConnection and set the parameters
			URL url = new URL(newsUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responsecode = conn.getResponseCode();
			System.out.println("Response code is: " +responsecode);
			
			//If Response is not OK(200 code) then ignore and the application will run with yesterday's news
			//Else get the JSON Response that needs to be parsed
			if(responsecode != 200)
				System.out.println("Issue with the news. Going with yesterday's news");
			else
			{
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					jsonResponse+=sc.nextLine();
				}
				sc.close();
			}
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject)parser.parse(jsonResponse);
			//Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
			JSONArray jsonArticles = (JSONArray) jsonObject.get("articles");
			//Get data for Results array
			for(int i=0;i<jsonArticles.size();i++)
			{
				//Store the JSON objects in an array
				//Get the index of the JSON object and print the values as per the index
				JSONObject jsonArticle = (JSONObject)jsonArticles.get(i);
				News news = new News((String)jsonArticle.get("title"),(String)jsonArticle.get("description"),(String)jsonArticle.get("article_url"),(String)jsonArticle.get("published_datetime"));
				list.add(news);
				
			}
			conn.disconnect();//Closing the HttpUrlConnection
		}
		catch(Exception e)
		{
			System.out.println("Issue with the news. Going with yesterday's news" + e.getMessage());
		}
		return list;
	}
	
	
	
}
