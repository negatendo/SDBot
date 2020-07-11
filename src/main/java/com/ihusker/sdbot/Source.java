package com.ihusker.sdbot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Source extends JavaPlugin implements Listener {

	private URL url;
	private final ExecutorService executorService = Executors.newSingleThreadExecutor();

	public void onEnable() {
		saveDefaultConfig();

		String url = getConfig().getString("webhook");
		if (url != null) {
			try {
				this.url = new URL(url);
			} catch (MalformedURLException e) {
				getLogger().warning("You must provide a valid url for the Webhook.");
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}

		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		executorService.execute(() -> {
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();

				try {
					connection.setRequestMethod("POST");
				} catch (ProtocolException e) {
					e.printStackTrace();
				}

				connection.setRequestProperty("Content-Type", "application/json; utf-8");
				connection.setRequestProperty("Accept", "application/json");
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
				connection.setDoOutput(true);

				Charset charset = StandardCharsets.UTF_8;
				try (OutputStream outputStream = connection.getOutputStream()) {
					// trim off text formatting garbage
					String eventString = event.getQuitMessage().substring(2);
					byte[] input = (
							"{\"username\":\"" + player.getName() +
									"\",\"content\":\"" + "_" + eventString + "_" +
									"\",\"avatar_url\":\"https://crafatar.com/renders/head/" + player.getUniqueId() + "?overlay\"}"
					).getBytes(charset);
					outputStream.write(input, 0, input.length);
				}

				if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) return;
				try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))) {
					String inputLine;
					StringBuilder response = new StringBuilder();
					while ((inputLine = bufferedReader.readLine()) != null) {
						response.append(inputLine);
					}
					getLogger().info(response.toString());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		executorService.execute(() -> {
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();

				try {
					connection.setRequestMethod("POST");
				} catch (ProtocolException e) {
					e.printStackTrace();
				}

				connection.setRequestProperty("Content-Type", "application/json; utf-8");
				connection.setRequestProperty("Accept", "application/json");
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
				connection.setDoOutput(true);

				Charset charset = StandardCharsets.UTF_8;
				try (OutputStream outputStream = connection.getOutputStream()) {
					// trim off text formatting garbage
					String eventString = event.getJoinMessage().substring(2);
					byte[] input = (
							"{\"username\":\"" + player.getName() +
									"\",\"content\":\"" + "_" + eventString + "_" +
									"\",\"avatar_url\":\"https://crafatar.com/renders/head/" + player.getUniqueId() + "?overlay\"}"
					).getBytes(charset);
					outputStream.write(input, 0, input.length);
				}

				if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) return;
				try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))) {
					String inputLine;
					StringBuilder response = new StringBuilder();
					while ((inputLine = bufferedReader.readLine()) != null) {
						response.append(inputLine);
					}
					getLogger().info(response.toString());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();

		executorService.execute(() -> {
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();

				try {
					connection.setRequestMethod("POST");
				} catch (ProtocolException e) {
					e.printStackTrace();
				}

				connection.setRequestProperty("Content-Type", "application/json; utf-8");
				connection.setRequestProperty("Accept", "application/json");
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
				connection.setDoOutput(true);

				Charset charset = StandardCharsets.UTF_8;
				try (OutputStream outputStream = connection.getOutputStream()) {
					byte[] input = (
							"{\"username\":\"" + player.getName() +
									"\",\"content\":\"" + "_" + event.getDeathMessage() + "_" +
									"\",\"avatar_url\":\"https://crafatar.com/renders/head/" + player.getUniqueId() + "?overlay\"}"
					).getBytes(charset);
					outputStream.write(input, 0, input.length);
				}

				if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) return;
				try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))) {
					String inputLine;
					StringBuilder response = new StringBuilder();
					while ((inputLine = bufferedReader.readLine()) != null) {
						response.append(inputLine);
					}
					getLogger().info(response.toString());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		executorService.execute(() -> {
			HttpURLConnection connection;
			try {
				connection = (HttpURLConnection) url.openConnection();

				try {
					connection.setRequestMethod("POST");
				} catch (ProtocolException e) {
					e.printStackTrace();
				}

				connection.setRequestProperty("Content-Type", "application/json; utf-8");
				connection.setRequestProperty("Accept", "application/json");
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
				connection.setDoOutput(true);

				Charset charset = StandardCharsets.UTF_8;
				try (OutputStream outputStream = connection.getOutputStream()) {
					byte[] input = (
							"{\"username\":\"" + player.getName() +
									"\",\"content\":\"" + event.getMessage() +
									"\",\"avatar_url\":\"https://crafatar.com/renders/head/" + player.getUniqueId() + "?overlay\"}"
					).getBytes(charset);
					outputStream.write(input, 0, input.length);
				}

				if (connection.getResponseCode() != HttpURLConnection.HTTP_CREATED) return;
				try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))) {
					String inputLine;
					StringBuilder response = new StringBuilder();
					while ((inputLine = bufferedReader.readLine()) != null) {
						response.append(inputLine);
					}
					getLogger().info(response.toString());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}
