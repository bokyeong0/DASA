package com.dasa.communication.vo;

/**
 * @파일명: Notification.java
 * @작성일: 2014. 3. 17.
 * @작성자: 전진기
 * @프로그램 설명: GCM을 이용한 Push Notification
 */
public class Notification
{
  /**
   * 구글 API Key
   */
	private static String runMode;
	private static String certificatePath;
	private static String certificatePassword;
	
//다사마케팅
//  private static String stringAndroidApiKey = "AIzaSyADWjaz3nEK8qzOOstD1qfUELRhBLQkGJo";
  private static String FCMurl = "https://fcm.googleapis.com/fcm/send";
  private static String fcmAndroidApiKey = "AAAAcdSYXec:APA91bHDSgkD7nFBfiFkGHySp5eoMqsqyvfe3gMusHTHAQEAziVTZ8C7wFcwGhKaeqJcOOOZKBjG3CV2Z_Ew8F7wt-BN3EEBY9VBFc23o87DrjOfP3FY5h0QudG4PKP_YqqjyJuYT6M4";
//pebs
 // private static String stringAndroidApiKey = "AIzaSyBE_CD1-c-GpRuSXU19gy6EGlFiRbI-U7I";
  
  /**
   * APPLE
   */
  private static final String HOST = "gateway.sandbox.push.apple.com";
  private static final int PORT = 2195;

  // Badge
  private static final int BADGE = 66;

  /**
   * @메서드명: fcmAndroidApiKey
   * @작성일: 2019. 4. 2.
   * @작성자: 김보경
   * @설명: 구글 API Key 반환
   * @return "Copyright"
   */
  
  public String getFCMurl() {
	  return FCMurl;
  }
  public String getFcmAndroidApiKey()
  {
    return fcmAndroidApiKey;
  }
/**
   * @메서드명: getStringAndroidApiKey
   * @작성일: 2014. 5. 7.
   * @작성자: 전진기
   * @설명: 구글 API Key 반환
   * @return "Copyright"
   */
//  public static String getStringAndroidApiKey()
//  {
//    return stringAndroidApiKey;
//  }

  /**
   * 제목
   */
  private String stringPropTitle;

  /**
   * @메서드명: getStringPropTitle
   * @작성일: 2014. 5. 21.
   * @작성자: 전진기
   * @설명: 제목 반환
   * @return "제목"
   */
  public String getStringPropTitle()
  {
    return stringPropTitle;
  }

  /**
   * @메서드명: setStringPropTitle
   * @작성일: 2014. 5. 21.
   * @작성자: 전진기
   * @설명: 제목 설정
   * @param "제목"
   */
  public void setStringPropTitle(String stringPropTitle)
  {
    this.stringPropTitle = stringPropTitle;
  }

  /**
   * Registration ID or Device Token
   */
  private String stringPropRegId;

  /**
   * @메서드명: getStringPropRegId
   * @작성일: 2014. 5. 21.
   * @작성자: 전진기
   * @설명: Registration ID or Device Token 반환
   * @return "Registration ID or Device Token"
   */
  public String getStringPropRegId()
  {
    return stringPropRegId;
  }

  /**
   * @메서드명: setStringPropRegId
   * @작성일: 2014. 5. 21.
   * @작성자: 전진기
   * @설명: Registration ID or Device Token 설정
   * @return "Registration ID or Device Token"
   */
  public void setStringPropRegId(String stringPropRegId)
  {
    this.stringPropRegId = stringPropRegId;
  }
  
   //디바이스 타입 
   //안드로이드 "A", 아이폰 : "I"
  	private String deviceType;

	public String getDeviceType() {
		return deviceType;
	}
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
  
	


	public String getRunMode() {
		return runMode;
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getCertificatePassword() {
		return certificatePassword;
	}

	public void setCertificatePassword(String certificatePassword) {
		this.certificatePassword = certificatePassword;
	}
	
}