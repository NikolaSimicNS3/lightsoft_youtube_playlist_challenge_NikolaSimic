package lightSoft.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lightSoft.controller.Controller;
import lightSoft.data.Video;
import lightSoft.data.YoutubeChannel;

public class Main {

	public static void main(String[] args) {
		Controller c = new Controller();

		List<YoutubeChannel> channels = c.readChannels();

		List<Video> my = new ArrayList<Video>();

		Video current = null;

		int o=1;

		Scanner s = new Scanner(System.in);

		while (o != 0) {
			o=9;
			String err= s.next();
			try {
				int br = Integer.parseInt(err);
				o=br;
			} catch (Exception e) {
				System.out.println("unesite broj");
			}
			

			switch (o) {
			case 0:
				System.out.println("Youtube shutingdown..");
				break;
			case 1:
				System.out.println("****************");
				System.out.println("0 quit");
				System.out.println("1 menu");
				System.out.println("2 videos in channel");
				System.out.println("3 list videos in playlist");
				System.out.println("4 add video in playlist");
				System.out.println("5 play next video");
				System.out.println("6 play privus video");
				System.out.println("7 replay current video");
				System.out.println("8 delete current video");
				System.out.println("****************");
				break;
			case 2:
				System.out.println("****************");
				System.out.println("unesite ime kanala ");
				String name = s.next();
				while (name == null) {

				}
				c.kanal(channels, name);
				System.out.println("****************");
				break;
			case 3:
				System.out.println("****************");
				System.out.println("My playlist");
				if (my.size() > 0) {
					for (Video v : my) {
						System.out.println(v.getTitle());
					}
				} else {
					System.out.println("playlist is empty");
				}
				System.out.println("****************");
				break;
			case 4:
				System.out.println("****************");
				System.out.println("Video:");
				String vid = null;
				
				while (vid == null ) {
					vid= s.next();
				}
				
				boolean ok = true;
				for (YoutubeChannel y : channels) {
					for (Video v : y.getVideos()) {
						if (v.getTitle().equalsIgnoreCase(vid)) {
							my.add(v);
							System.out.println("video has been added");
							ok = false;
						}
					}
				}
				if (ok) {
					System.out.println("video doesnt exist");
				}
				System.out.println("****************");
				break;
			case 5:
				System.out.println("****************");
				if (current == null) {
					if (my.size() > 0) {
						current = my.get(0);
						System.out.println("Now playing " + current.getTitle() + "  " + current.getLength());
					} else {
						System.out.println("no video in list");
					}
				} else {
					int i = my.indexOf(current);
					i += 1;
					if (i > my.size()) {
						// ako je na kraju liste, sledeci video je prvi video iz liste (krece listu iz
						// pocetka)
						i = 0;
					}
					
					System.out.println("Now playing " + current.getTitle() + "  " + current.getLength());
				}
				System.out.println("****************");
				break;
			case 6:
				System.out.println("****************");
				if (current == null) {
						System.out.println("There is no privus video");
					
				} else {
					int i = my.indexOf(current);
					i -= 1;
					if (i == -1) {
						// ako je na kraju liste, sledeci video je prvi video iz liste (krece listu iz
						// pocetka)
						System.out.println("There is no privus video");
					}
					else {
						System.out.println("Now playing " + current.getTitle() + "  " + current.getLength());
					}
					
				}
				System.out.println("****************");
				break;
			case 7:
				System.out.println("****************");
				if (current == null) {
					System.out.println("no video");
				} else {
					System.out.println("Now playing " + current.getTitle() + "  " + current.getLength());
				}
				System.out.println("****************");
				break;
			case 8:
				if (current==null) {
					System.out.println(" no selected video to delete");
				}else {
					my.remove(current);
					current= null;
					System.out.println("Video has been deleted");
				}
				break;
			case 9:
				// code block
				break;
			default:
				// code block
			}

		}

	}

}
