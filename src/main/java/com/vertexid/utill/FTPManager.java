package com.vertexid.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


public class FTPManager{
    private static Session session = null;
    private static Channel channel = null;
    private static ChannelSftp channelSftp = null;
    private static JSch jsch  = null;

    
//    <entry key="ftp_host">40.74.125.230</entry>
//    <entry key="ftp_port">22</entry>
//    <entry key="ftp_user_name">azureuser</entry>
//    <entry key="ftp_password">ehdtjsp*2</entry>
//    static final String ftp_host = "40.74.125.230";
//    
//    static final String ftp_userName = "azureuser";
//    
//    static final String ftp_password = "ehdtjsp*2";
//    
//    static final int ftp_port = 22;
//    
    @Value("#{sys_info['ftp_host']}")
    String ftp_host;
    
    @Value("#{sys_info['ftp_user_name']}")
    String ftp_userName;
    
    @Value("#{sys_info['ftp_password']}")
    String ftp_password;
    
    @Value("#{sys_info['ftp_port']}")
    int ftp_port;
    
    
    public void connect() {
		if(jsch == null){
			System.out.println("널임");
			jsch = new JSch();
		}
        try {
            session = jsch.getSession(ftp_userName, ftp_host, ftp_port);
            session.setPassword(ftp_password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(60000);

            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("연결성공!!");
        } catch (JSchException e) {
        	System.out.println("연결실패!!");
            e.printStackTrace();
        }
        
        channelSftp = (ChannelSftp) channel;

    }
       
    
    //하나 파일을 업로드 >  dir 저장시킬 경로(서버) , file 저장할 파일111
    public void upload(String dir, File file, boolean retry) throws SftpException, IOException, FileNotFoundException {
    	
//    	connect();
		if(!isConnected()){
			connect();
//			upload(dir, file);
//			return;
		}
    	
		String[] folders = dir.split("/");
		channelSftp.cd("/");
		for (int i = 1; i < folders.length; i++) {
		  String folder = folders[i];
		    try {
		  	  channelSftp.cd(folder);
		    } catch (SftpException e) {
		  	  channelSftp.mkdir(folder);
		  	  channelSftp.cd(folder);
		    }
		}

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            //System.out.println("원격지 저장위치"+ dir);
            channelSftp.cd(dir);
            if(retry){
            	channelSftp.put(in, file.getName(), ChannelSftp.RESUME);
            }else{
            	channelSftp.put(in, file.getName(), ChannelSftp.OVERWRITE);
            }
//            disconnection();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
        	disconnection();
            try {
            	if(in != null){
            		in.close();
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {  
//    	System.out.println("연결 : "+(channel != null && channel.isConnected()));
        return (channel != null && channel.isConnected());  
    }  
  
    public void disconnect() {  
//        if (isConnected()) {  
            channel.disconnect();  
            session.disconnect();  
            channelSftp.exit();
//        }  
    }  
    // 서버와의 연결을 끊는다.
    public void disconnection() {
        channel.disconnect();
        session.disconnect();
        channelSftp.exit();
    }
    public static void main(String[] args) {
    	FTPManager FTP =  new FTPManager();
    	try {
			FTP.upload("/home/azureuser", new File("D://test2.doc"),true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

