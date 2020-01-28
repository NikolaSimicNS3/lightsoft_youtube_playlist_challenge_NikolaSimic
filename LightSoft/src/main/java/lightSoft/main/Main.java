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

		int current = -99;

		int o = 1;

		Scanner s = new Scanner(System.in);

		while (o != 0) {

			
			// treba osigurati da program ne pukne ako se ne unese broj;
			o = s.nextInt();
			
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
				o = 9;
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

				while (vid == null || vid.equals("")) {
					vid = s.nextLine();
				}

				boolean ok = true;
				for (YoutubeChannel y : channels) {
					for (Video v : y.getVideos()) {
						if (v.getTitle().equalsIgnoreCase(vid)) {
							boolean ex = true;
							for (Video m : my) {
								if (m.getTitle().equalsIgnoreCase(vid)) {
									System.out.println("Video is in the playlist");
									ex = false;
									ok=false;
								}
							}
							if (ex) {
								my.add(v);

								System.out.println("video has been added");
								ok = false;
							}
						}
					}
				}
				if (ok) {
					System.out.println("video doesnt exist");
				}
				System.out.println("****************");
				o = 9;
				break;
			case 5:
				System.out.println("****************");
				if (current == -99) {
					if (my.size() > 0) {
						current = 0;
						System.out.println("Now playing " + my.get(current).getTitle() + "  " + my.get(current).getLength());
					} else {
						System.out.println("no video in list");
					}
				} else {
					current++;

					if (current > my.size()-1) {
						// ako je na kraju liste, sledeci video je prvi video iz liste (krece listu iz
						// pocetka)
						current = 0;
					}

					System.out.println("Now playing " + my.get(current).getTitle() + "  " + my.get(current).getLength());
				}
				System.out.println("****************");
				break;
			case 6:
				System.out.println("****************");
				if (current < 1) {
					System.out.println("There is no privus video");

				} else {
					current--;

					System.out.println("Now playing " + my.get(current).getTitle() + "  " + my.get(current).getLength());

				}
				System.out.println("****************");
				break;
			case 7:
				System.out.println("****************");

				if (current == -99) {
					System.out.println("no video");
				} else {
					System.out.println("Now playing " + my.get(current).getTitle() + "  " + my.get(current).getLength());
				}
				System.out.println("****************");
				break;
			case 8:
				if (current == -99) {
					System.out.println(" no selected video to delete");
				} else {
					my.remove(current);
					current = -99;
					System.out.println("Video has been deleted");
				}
				break;
			case 9:
				System.out.println("Unesite broj");
				break;
			default:
				// code block
			}

		}

	}

}
