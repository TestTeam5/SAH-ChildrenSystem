package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileCopy {
	public static void copyFile(String filepath, String filename) {
		int index = filepath.lastIndexOf('\\');
		if (!filepath.substring(index + 1).equals(filename + ".xml")) {
			filename = filepath.substring(0, index) + "\\" + filename + ".xml";

			try {
				int bytesum = 0;
				int byteread = 0;
				InputStream inStream;
				inStream = new FileInputStream(filepath);
				FileOutputStream fs = new FileOutputStream(filename);
				byte[] buffer = new byte[1024];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 读入原文件
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public static void exportCopyFile(String folderPath){
		String[] paperPaths = {"resource\\file\\guangming.xml", "resource\\file\\nanfangdaily.xml", "resource\\file\\sichuan.xml"};
		String[] fileNames = {folderPath+"\\guangming.xml", folderPath+"\\nanfangdaily.xml", folderPath+"\\sichuan.xml"};
		
		for(int i = 0; i < 3; i++){
			try {
				int bytesum = 0;
				int byteread = 0;
				InputStream inStream;
				inStream = new FileInputStream(paperPaths[i]);
				FileOutputStream fs = new FileOutputStream(fileNames[i]);
				byte[] buffer = new byte[1024];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 读入原文件
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
