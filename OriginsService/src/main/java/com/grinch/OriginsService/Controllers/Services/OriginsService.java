package com.grinch.OriginsService.Controllers.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.grinch.OriginsService.BusinessLogic.Origin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

@Service
public class OriginsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public boolean validateOrigin(Origin origin) {
	    List <NameValuePair> Input = new ArrayList<NameValuePair>();
	    Input.add(new BasicNameValuePair("Country", origin.getCountry()));
	    Input.add(new BasicNameValuePair("City", origin.getCity()));
		return true;
	}
}
