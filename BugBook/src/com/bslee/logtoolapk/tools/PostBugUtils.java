package com.bslee.logtoolapk.tools;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class PostBugUtils {
	
	  public static String PostNameValue(ArrayList<NameValuePair> nameValuePairs,String url){
	        HttpPost httpPost = new HttpPost(url);

	        try {

	            HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs,
	                    "UTF-8");
	            httpPost.setEntity(httpEntity);

	            HttpClient httpClient = new DefaultHttpClient();
	            httpClient.getParams().getIntParameter(
	                    HttpConnectionParams.CONNECTION_TIMEOUT, 10000);

	            HttpResponse response = httpClient.execute(httpPost);

	            if (response != null
	                    && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

	                HttpEntity entity = response.getEntity();
	                return EntityUtils.toString(entity);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
