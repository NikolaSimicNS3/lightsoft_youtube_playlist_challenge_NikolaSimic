package lightSoft.data;

import java.util.List;

public class YoutubeChannel {

	private String title;
	private List<Video> videos;
	
	public YoutubeChannel(String title, List<Video> videos) {
		super();
		this.title = title;
		this.videos = videos;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	
	
}
