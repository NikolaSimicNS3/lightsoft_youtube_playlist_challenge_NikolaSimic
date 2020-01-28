package lightSoft.controller;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.ResourceUtils;

import lightSoft.data.Video;
import lightSoft.data.YoutubeChannel;

public class Controller {

	public List<YoutubeChannel> readChannels() {
		List<YoutubeChannel> rez= new ArrayList<YoutubeChannel>();
		JSONParser parse = new JSONParser();
		
		
		try {
			
			File file = ResourceUtils.getFile("classpath:youtube_channels_data.json");
			
			
			String content = FileUtils.readFileToString(file);
			//System.out.println(content);
			
			JSONArray niz = (JSONArray) parse.parse(content);
			for (int i = 0; i < niz.size(); i++) {
				JSONObject o = (JSONObject)niz.get(i);
				JSONArray niz2 = (JSONArray) o.get("channel");
				for (int j= 0; j<niz2.size(); j++) {
					JSONObject o1 = (JSONObject)niz2.get(j);
					String title = (String) o1.get("title");
					JSONArray niz3 = (JSONArray) o1.get("video");
					List<Video> vs= new ArrayList<Video>();
					for (int z=0; z<niz3.size(); z++) {
						JSONObject o2 = (JSONObject) niz3.get(z);
						
						String vt = (String) o2.get("title");
						String vl = (String) o2.get("length");
						Video v = new Video(vt, vl);
						vs.add(v);
						
			
					}
					YoutubeChannel yc= new YoutubeChannel(title, vs);
					rez.add(yc);
				}
			}
			
			return rez;
		} catch (Exception e) {
			e. printStackTrace();
			
		}
		return null;
	}
	
	public void kanal (List<YoutubeChannel> ytc, String name) {
		for (YoutubeChannel y: ytc) {
			if (y.getTitle().equalsIgnoreCase(name)) {
				System.out.println("videos on channel");
				for (Video v:y.getVideos()) {
					System.out.println(v.getTitle() + "  "+ v.getLength());
				}
				return;
			}
		}
		System.out.println("channel doesnt exist");
	}
}
