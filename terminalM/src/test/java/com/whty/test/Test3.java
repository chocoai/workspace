/**
 * 
 */
package com.whty.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import sun.awt.shell.ShellFolder;

/**
 * @author zhangzheng
 * @date 2018年4月19日
 */
public class Test3 {

	public static Icon toIcon(File file) throws FileNotFoundException {
		ShellFolder shellFolder = ShellFolder.getShellFolder(file);
		Icon icon = new ImageIcon(shellFolder.getIcon(true));
		return icon;
	}

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File( "D:\\互动课堂_2.5.0_setup_20180125.exe");   
	     OutputStream inStream = new FileOutputStream(new File("d:\\img.png")); 
	     try {   
	         BufferedImage www =   (BufferedImage)((ImageIcon) toIcon(file)).getImage();
	         ImageIO.write(www, "png", inStream);  
	         inStream.flush();  
	         inStream.close();  
	     } catch (IOException e) {  
	         // TODO Auto-generated catch block  
	         e.printStackTrace();  
	     }  
	}

}
