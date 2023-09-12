package music;

public class MusicPurchaseDTO {
	private String idx;
	private String title;
	private String episode;
	private String id;
	private String postdate;
	
	String getIdx() {
		return idx;
	}
	void setIdx(String idx) {
		this.idx = idx;
	}
	String getTitle() {
		return title;
	}
	void setTitle(String title) {
		this.title = title;
	}
	String getEpisode() {
		return episode;
	}
	void setEpisode(String episode) {
		this.episode = episode;
	}
	String getId() {
		return id;
	}
	void setId(String id) {
		this.id = id;
	}
	String getPostdate() {
		return postdate;
	}
	void setPostdate(String postdate) {
		this.postdate = postdate;
	}
}
