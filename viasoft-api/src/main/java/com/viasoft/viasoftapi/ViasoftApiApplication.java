package com.viasoft.viasoftapi;

import com.viasoft.viasoftapi.bd.Crawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class ViasoftApiApplication {

	public static void main(String[] args) throws IOException {
		Thread t = Thread.currentThread();

		Thread tmain = new Thread(() -> SpringApplication.run(ViasoftApiApplication.class, args));
		tmain.start();

		Thread tCrawler = new Thread(() -> rodaCrawler());
		tCrawler.start();

	}

	public static void rodaCrawler(){
		Crawler c = new Crawler();

		int delay = 1000;   // delay de 1 seg.
		int interval = 300000;/// intervalo de 5 min
		final int[] cont = {0};
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				c.atualizaDB();
				cont[0]++;
				System.out.println("Atualizacao " + cont[0]);
			}
		}, delay, interval);
	}

}
