/**
 * 
 */
package com.whty.common.util;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import sun.awt.shell.ShellFolder;

/**
 * @author zhangzheng
 * @date 2018年4月19日
 */
public class IconUtils {
	
	public static Icon toIcon(File file) throws FileNotFoundException {
		ShellFolder shellFolder = ShellFolder.getShellFolder(file);
		Icon icon = new ImageIcon(shellFolder.getIcon(true));
		return icon;
	}
	
}
