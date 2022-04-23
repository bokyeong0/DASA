package com.dasa.communication.vo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @파일명: SendNotification.java
 * @작성일: 2014. 3. 17.
 * @작성자: 전진기
 * @프로그램 설명: 알림 메시지 전송
 */
public class SendNotification {
	
	@Value("#{sys_info['apns_host']}")
	private String apns_host;
	
	@Value("#{sys_info['apns_port']}")
	private int apns_port;
	
	@Value("#{sys_info['apns_path']}")
	private String apns_path;

	@Value("#{sys_info['apns_pw']}")
	private String apns_pw;

//	private Sender sender;
//	private Message msg;

	private static final Logger logger = LoggerFactory.getLogger(Notification.class);

	@Autowired(required = true)
	private HttpServletRequest request;

	/**
	 * @메서드명: sendNotificationToFcm
	 * @작성일: 2019. 4. 2.
	 * @작성자:  김보경
	 * @설명: Android 알림 메시지
	 * @param notification
	 * @return "알림 여부"
	 */
	public boolean sendNotificationToFcm(List<Notification> vo, String subject) throws Exception {
		String FCMurl = vo.get(0).getFCMurl();
		String authKey = vo.get(0).getFcmAndroidApiKey();
		
		try {
        	URL url = new URL(FCMurl);
        	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        	
        	conn.setUseCaches(false);
        	conn.setDoInput(true);
        	conn.setDoOutput(true);
        	conn.setRequestMethod("POST");
        	conn.setRequestProperty("Authorization", "key=" + authKey);
        	conn.setRequestProperty("Content-Type", "application/json");
        	
        	JSONObject json = new JSONObject();
        	JSONObject info = new JSONObject();
        	
        	List<String> tokens = new ArrayList<String>();

			for (Notification notification : vo) {
				if (notification.getStringPropRegId() != null && !notification.getStringPropRegId().equals("")) {
					tokens.add(notification.getStringPropRegId());
				}
			}
//			info.put("body", "내용");
//			info.put("sound", "default"); 
			info.put("title", subject);
			json.put("notification", info);
			json.put("registration_ids", tokens);
			logger.debug(json.toString());
			
			try(
	    			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){
		            wr.write(json.toString());
		            wr.flush();
		        }catch(Exception e){
		        	e.printStackTrace();
		        }
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("푸시 connection실패 : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				logger.debug(output);
				System.out.println(conn.getResponseCode());
			}
			
			conn.disconnect();
			
		}catch(Exception e){
    		e.printStackTrace();
    	}
	        
		return true;
	}

	public boolean sendNotificationToApns(List<Notification> list, String subject) throws Exception {
		try {
			Notification notification = new Notification();
			PushNotificationManager pushManager = PushNotificationManager.getInstance();

			PayLoad payLoad = new PayLoad();
			payLoad.addAlert(subject);
			payLoad.addBadge(1);
			payLoad.addSound("default");

			for (Notification vo : list) {
				pushManager.addDevice("iPhone", vo.getStringPropRegId());
				pushManager.initializeConnection(apns_host, apns_port, apns_path, apns_pw, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
				Device client = pushManager.getDevice("iPhone");
				pushManager.sendNotification(client, payLoad);
				pushManager.stopConnection();
				pushManager.removeDevice("iPhone");
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean sendPushMessage(List<Notification> notificationList_android, List<Notification> notificationList_ios, String subject) {
		try {
			if (notificationList_android != null && notificationList_android.size() > 0)
//				sendNotificationToGcm(notificationList_android, subject);
				sendNotificationToFcm(notificationList_android, subject);

			if (notificationList_ios != null && notificationList_ios.size() > 0)
				sendNotificationToApns(notificationList_ios, subject);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	};

}
