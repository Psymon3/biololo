import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class WebService 
{
	/*
	 * Attributes
	 */
	
	// Url for eukaryotes
	private static String URL_EUKARYOTES = "http://www.ncbi.nlm.nih.gov/genomes/Genome2BE/genome2srv.cgi?action=GetGenomes4Grid&king=Eukaryota&mode=2&page=";
	
	// Url to get prokaryotes
	private static String URL_PROKARYOTES = "http://www.ncbi.nlm.nih.gov/genomes/Genome2BE/genome2srv.cgi?action=GetGenomes4Grid&king=Bacteria&mode=2&page=";
	
	// Url to get viruses
	private static String URL_VIRUSES = "http://www.ncbi.nlm.nih.gov/genomes/Genome2BE/genome2srv.cgi?action=GetGenomes4Grid&king=Viruses&mode=2&page=";
	
	// HttpClient
	private static CloseableHttpClient httpclient;
	
	// HttpGet object
	private static HttpGet httpget;
	
	// Response Handler
	private static ResponseHandler<String> responseHandler;
	
	private WebService()
	{
		httpclient = HttpClients.createDefault();
		httpget = null;
		
		responseHandler = new ResponseHandler<String>() {
			
            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
	}
	
	private static class WebServiceHolder
	{
		private final static WebService instance = new WebService();
	}
	
	public static WebService getInstance()
	{				
		return WebServiceHolder.instance;		
	}
	
	public void CestParti()
	{
		Downloader(URL_PROKARYOTES);
	}
	
	private void Downloader(String url)
	{
		String responseBody = "tutuzezejojiffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffjiozejfizejifzjiofzejiofzejifzejofozejizejzioefzeifzfzejozejiozefjiozejfiozeozefjiozfjiozfejiozfjizefjiozefjiojiozfjiozfjiozefjizfejio";
		int k = 1;
		
		while(responseBody.length() > 100)
		{
			try 
			{
				httpget = new HttpGet(url + k);
				
				System.out.println("Executing request " + httpget.getRequestLine());
		
				responseBody = httpclient.execute(httpget, responseHandler);
				
				Pattern p = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=\\\"\\/genome\\/(\\d*)\\?(?:.*?)?>([\\w\\s\\-]+)[^\\>]\\/a>(?:(?:.*?)<\\/td>){3}(?:<td>([\\w\\s]+)<\\/td>)(?:<td>([\\w\\s]+)<\\/td>)(?:(?:.*?)<\\/td>){5}\\n(?:(?:.*?)<\\/td>){5}(?:<td>([\\d\\W]+)<\\/td>)");
	            // création d'un moteur de recherche
	            Matcher m = p.matcher(responseBody);
	            // lancement de la recherche de toutes les occurrences
	            while(m.find()) 
	            {
	                // pour chaque groupe
	            	if(!m.group(1).startsWith("genomes"))
	            	{
                        Genome genome = new Genome();
                        genome.setId(Long.parseLong(m.group(1)));
                        genome.setOrganism(m.group(2));
                        genome.setGroup(m.group(3));
                        genome.setSubgroup(m.group(4));
                        genome.setUpdateDate(m.group(5));
	                        
                        
	            	}
	            }
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			k++;
		}		
	}
}
