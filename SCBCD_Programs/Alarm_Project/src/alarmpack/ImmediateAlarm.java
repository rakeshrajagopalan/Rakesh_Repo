package alarmpack;

import java.io.IOException;

public class ImmediateAlarm {

	public String[] SONGLIST;

	public ImmediateAlarm() {
		SONGLIST = new String[13];
		SONGLIST[0] = "E:\\Devotional Songs\\01 Devotional songs - T.M.S\\SOLLATHANAAL.mp3";
		SONGLIST[1] = "E:\\Devotional Songs\\01 Devotional songs - T.M.S\\Alagendra Sollukku.mp3";
		SONGLIST[2] = "E:\\Devotional Songs\\Krishna Ghanam\\Aayar_Padi_Maligayil.mp3";
		SONGLIST[3] = "E:\\Devotional Songs\\Krishna Ghanam\\Pulankuzhal_Kodutha.mp3";
		SONGLIST[4] = "E:\\Devotional Songs\\05 Hanuman_Chalisa.mp3";
		SONGLIST[5] = "E:\\Devotional Songs\\GANESHA_PANCHARATNAM.wma";
		SONGLIST[6] = "E:\\Devotional Songs\\KANNAPURAM SELVAN.mp3";
		SONGLIST[7] = "E:\\Devotional Songs\\GURUVAYURAPPA.mp3";
		SONGLIST[8] = "E:\\Devotional Songs\\kurai_ondrum.wma";
		SONGLIST[9] = "E:\\Devotional Songs\\NAMA RAMAYANA.wma";
		SONGLIST[10] = "E:\\Devotional Songs\\NARAYANA ENUM.mp3";
		SONGLIST[11] = "E:\\Devotional Songs\\THIRUPATHI MAZHAI.mp3";
		SONGLIST[12] = "E:\\Devotional Songs\\Vinayagane_Vinay_Theerpavane.mp3";
		raiseAlarm();
	}

	public void raiseAlarm() {
		try {
			int number = ((int) (Math.random() * 12));
			String[] commands = {
					"C:\\Program Files\\Windows Media Player\\wmplayer.exe",
					SONGLIST[number] };
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(commands);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ImmediateAlarm();
	}
}
