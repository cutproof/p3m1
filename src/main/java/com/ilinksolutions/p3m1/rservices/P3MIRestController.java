package com.ilinksolutions.p3m1.rservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ilinksolutions.p3m1.domains.UKVisaMessage;
import com.ilinksolutions.p3m1.bservices.UKVisaService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class P3MIRestController
{
	Logger logger = LoggerFactory.getLogger(P3MIRestController.class);
	
	@RequestMapping(value = "/fileMessage", method = RequestMethod.POST, headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	public ResponseEntity<UKVisaMessage> fileMessage(@RequestHeader HttpHeaders headers,
	        @PathVariable String userId,
	        @RequestPart(value = "image", required = false) MultipartFile image,
	        @RequestPart(value = "", required = true) UKVisaMessage json)
	{
		logger.info("P3MIRestController: fileMessage: Begin!");
		logger.info("P3MIRestController: fileMessage: JSON: " + json.toString());
		logger.info("P3MIRestController: fileMessage: End!");
		UKVisaService service = new UKVisaService();
		UKVisaMessage returnValue = service.getEntry(new Integer(1).intValue());
        if (returnValue == null)
        {
        	logger.info("P3MIRestController: fileMessage: returnValue: NULL");
            return ResponseEntity.notFound().build();
        }
        else
        {
            logger.info("P3MIRestController: fileMessage: returnValue: " + returnValue.toString());
            return ResponseEntity.ok(returnValue);
        }
	}
	
    @GetMapping("/getmsg/{id}")
	public ResponseEntity<UKVisaMessage> readEntry(@PathVariable String id)
    {
    	logger.info("P3MIRestController: readEntry: Begin!");
    	logger.info("P3MIRestController: readEntry: Path Variable: " + id);
        UKVisaService service = new UKVisaService();
        UKVisaMessage returnValue = service.getEntry(new Integer(id).intValue());
        if (returnValue == null)
        {
        	logger.info("P3MIRestController: readEntry: returnValue: NULL");
            return ResponseEntity.notFound().build();
        }
        else
        {
            logger.info("P3MIRestController: readEntry: returnValue: " + returnValue.toString());
            return ResponseEntity.ok(returnValue);
        }
    }
    
    @PostMapping("/savemsg")
    public ResponseEntity<UKVisaMessage> registerMessage(@RequestBody UKVisaMessage message)
    {
    	logger.info("P3MIRestController: registerMessage: Begin.");
    	logger.info("P3MIRestController: registerMessage: Transform: " + message.toString());
    	UKVisaService service = new UKVisaService();
    	UKVisaMessage returnValue = service.addEntry(message);
    	if (returnValue == null)
    	{
    		logger.info("P3MIRestController: registerMessage: id: NULL.");
            return ResponseEntity.notFound().build();
        }
    	else
    	{
    		logger.info("P3MIRestController: registerMessage: id: End.");
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(returnValue.getId()).toUri();
            return ResponseEntity.created(uri).body(returnValue);
        }
    }
    
    @PutMapping("/updatemsg/{id}")
    public ResponseEntity<UKVisaMessage> update(@RequestBody UKVisaMessage message, @PathVariable int id)
    {
    	logger.info("P3MIRestController: update: Begin.");
        UKVisaService service = new UKVisaService();
        UKVisaMessage returnValue = service.updateEntry(id, message);
        if (returnValue == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(returnValue);
        }
    }
    
    @GetMapping("/getservice/{id}")
	public void getService(@PathVariable String id)
    {
    	logger.info("P3MIRestController: getService: Begin!");
    	logger.info("P3MIRestController: getService: Path Variable: " + id);
    	try
    	{
    		URL url = new URL("http://ilinkp3-ilinkp3.b9ad.pro-us-east-1.openshiftapps.com/p2/serviceCheck");
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("GET");
    		conn.setRequestProperty("Accept", "application/json");

    		if (conn.getResponseCode() != 200)
    		{
    			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
    		}

    		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

    		String output;
    		logger.info("Output from Server .... \n");
    		while ((output = br.readLine()) != null)
    		{
    			logger.info(output);
    		}
    		conn.disconnect();
    	}
    	catch (MalformedURLException e)
    	{
    		e.printStackTrace();
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    	//	ResponseEntity.ok(1);
    }
}